package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.FileDeleteException;
import com.alkemy.ong.exception.FileUploadException;
import com.alkemy.ong.exception.InvalidValueException;
import com.alkemy.ong.service.AmazonS3Service;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Date;

@Log4j2
@Service
public class AmazonS3ServiceImpl implements AmazonS3Service {

    private AmazonS3 s3client;
    @Value("${amazonProperties.bucketName}")
    private String BUCKET_NAME;
    @Value("${amazonProperties.accessKey}")
    private String ACCESS_KEY;
    @Value("${amazonProperties.secretKey}")
    private String SECRET_KEY;
    @Value("${amazonProperties.regionEnum}")
    private String REGION_ENUM;

    /**
     * Initializes the Amazon Client with the region and credentials specifics into application.properties.
     */
    @Override
    public void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.ACCESS_KEY, this.SECRET_KEY);
        this.s3client =
                AmazonS3ClientBuilder
                        .standard()
                        .withRegion(Regions.valueOf(this.REGION_ENUM))
                        .withCredentials(getAwsCredentialProvider())
                        .build();
    }

    /**
     * Loads the ACCESS_KEY and SECRET_KEY to get the AWS credentials.
     *
     * @return AWS credentials
     */
    @Override
    @PostConstruct
    public AWSCredentialsProvider getAwsCredentialProvider() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(this.ACCESS_KEY,
                        this.SECRET_KEY);
        return new AWSStaticCredentialsProvider(awsCredentials);
    }

    /**
     * Converts the file to be uploaded of MultipartFile to File.
     *
     * @param file the file to be uploaded as MultipartFile
     * @return the file to be uploaded converted to File
     */
    @Override
    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        if (file.getOriginalFilename() == null) {
            throw new InvalidValueException("The file name must not be null");
        }
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    /**
     * Generates a unique name for the file to be uploaded using timestamp.
     *
     * @param multiPart the file to be uploaded as MultiPart
     * @return the unique name of the file as string
     */
    @Override
    public String generateFileName(MultipartFile multiPart) {
        if (multiPart.getOriginalFilename() == null) {
            throw new InvalidValueException("The file name must not be null");
        }
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    /**
     * Uploads the file to S3 bucket.
     *
     * @param fileName the unique name of the file to be uploaded
     * @param file     the file to be uploaded as File
     */
    @Override
    public void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(this.BUCKET_NAME, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    /**
     * This method must be called from the services or controller to upload the file to s3 bucket.
     *
     * @param multipartFile the file to be uploaded as MultiPart
     * @return the access url of uploaded file as String
     */

    @Override
    public String uploadFile(MultipartFile multipartFile) {
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
            if (file != null && !file.delete()) {
                log.error("Error deleting file");
            }
        }
    }

    /**
     * This method must be called from the services or controller to delete the file to s3 bucket.
     *
     * @param fileUrl the access url of the file to be deleted as String
     * @return a value true if the file is deleted otherwise false
     */
    @Override
    public Boolean deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        if (!s3client.doesObjectExist(this.BUCKET_NAME, fileName)) {
            throw new FileDeleteException("This file does not exist");
        }
        s3client.deleteObject(new DeleteObjectRequest(this.BUCKET_NAME, fileName));
        return !s3client.doesObjectExist(this.BUCKET_NAME, fileName);
    }

}
