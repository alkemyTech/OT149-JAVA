package com.alkemy.ong.service.impl;

import com.alkemy.ong.service.AmazonS3Service;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


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

}
