package com.videoappbackend.Config;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {


    @Bean
    public AmazonS3 amazonS3Client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials("AKIAZJZ27FJKE4X2CHJE", "FiYmDhpIWe629qC2Gypp0gDA6Ohu/F+wY3PFcYVa");

        return AmazonS3Client.builder()
                .withRegion("us-east-1") // Replace with your AWS region
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
