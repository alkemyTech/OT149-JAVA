package com.alkemy.ong.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AmazonS3Config {
    @Value("${amazonProperties.accessKey}")
    private String ACCESS_KEY;
    @Value("${amazonProperties.secretKey}")
    private String SECRET_KEY;
    @Value("${amazonProperties.regionEnum}")
    private String REGION_ENUM;

    /**
     * Initializes the Amazon Client with the region and credentials specifics into application.properties.
     */

    @Bean
    AmazonS3 amazonS3() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(this.ACCESS_KEY,
                        this.SECRET_KEY);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.valueOf(this.REGION_ENUM))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

}
