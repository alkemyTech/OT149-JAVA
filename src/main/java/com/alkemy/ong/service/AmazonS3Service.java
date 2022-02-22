package com.alkemy.ong.service;

import com.alkemy.ong.exception.FileUploadException;
import com.amazonaws.auth.AWSCredentialsProvider;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

public interface AmazonS3Service {

    void initializeAmazon();

    AWSCredentialsProvider getAwsCredentialProvider();

    File convertMultiPartToFile(MultipartFile file) throws IOException;

    String generateFileName(MultipartFile multiPart);

    void uploadFileTos3bucket(String fileName, File file);

    String uploadFile(MultipartFile multipartFile) throws FileUploadException;

    Boolean deleteFileFromS3Bucket(String fileUrl);
}
