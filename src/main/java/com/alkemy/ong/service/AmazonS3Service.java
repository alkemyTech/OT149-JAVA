package com.alkemy.ong.service;

import com.amazonaws.auth.AWSCredentialsProvider;

public interface AmazonS3Service {

    void initializeAmazon();

    AWSCredentialsProvider getAwsCredentialProvider();

}
