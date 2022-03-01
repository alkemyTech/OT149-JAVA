package com.alkemy.ong.service.impl;

import com.alkemy.ong.service.AmazonS3Service;
import com.amazonaws.services.s3.AmazonS3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class AmazonS3ServiceImpl implements AmazonS3Service {

    @Autowired
    private AmazonS3 s3client;

    @Value("${amazonProperties.bucketName}")
    private String BUCKET_NAME;
}

