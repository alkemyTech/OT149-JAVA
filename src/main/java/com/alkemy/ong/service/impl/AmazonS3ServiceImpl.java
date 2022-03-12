package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.NullFileException;
import com.alkemy.ong.service.AmazonS3Service;
import com.amazonaws.services.s3.AmazonS3;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
@Log4j2
public class AmazonS3ServiceImpl implements AmazonS3Service {

    private final AmazonS3 s3client;

    @Value("${amazonProperties.bucketName}")
    private String BUCKET_NAME;

    @Autowired
    public AmazonS3ServiceImpl(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        if (filename == null) {
            throw new NullFileException("The file name must not be null");
        }
        File convFile = new File(filename);
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        String filename = multiPart.getOriginalFilename();
        if (filename == null) {
            throw new NullFileException("The file name must not be null");
        }
        return new Date().getTime() + "-" + filename.replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(this.BUCKET_NAME, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @SneakyThrows
    @Override
    public String uploadImage(MultipartFile multipartFile){
        File file = null;
        try {
            file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            String fileUrl = s3client.getUrl(this.BUCKET_NAME, fileName).toExternalForm();
            uploadFileTos3bucket(fileName, file);
            return fileUrl;

        } catch (IOException ex) {
            log.error("Error uploading file: ", ex);
            throw new FileUploadException("Error uploading file");
        } finally {
            if ((file != null) && !file.delete()) {
                log.error("Error deleting file");
            }
        }
    }
    
	public File b64ToFile(String b64) throws FileNotFoundException, IOException {
		
		byte[] bI = org.apache.commons.codec.binary.Base64.decodeBase64((b64.substring(b64.indexOf(",")+1)).getBytes());		
		
		File convFile = new File("slideName");
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(bI);
        }
		
		return convFile;
	}
	
	public String generateFileName(File file) {
        String filename = file.getName();
        if (filename == null) {
            throw new NullFileException("The file name must not be null");
        }
        return new Date().getTime() + "-" + filename.replace(" ", "_");
    }

	@Override
	public String uploadImage64(String b64) throws FileUploadException {

		File file = null;
        try {
            file = b64ToFile(b64);
            String fileName = generateFileName(file);
            String fileUrl = s3client.getUrl(this.BUCKET_NAME, fileName).toExternalForm();
            uploadFileTos3bucket(fileName, file);
            return fileUrl;

        } catch (IOException ex) {
            log.error("Error uploading file: ", ex);
            throw new FileUploadException("Error uploading file");
        } finally {
            if ((file != null) && !file.delete()) {
                log.error("Error deleting file");
            }
        }
	}
    
}

