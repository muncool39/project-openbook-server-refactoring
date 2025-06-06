package com.openbook.openbook.configuration;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile(value = "local")
@Configuration
public class S3LocalConfig {

    private final String AWS_REGION = Regions.US_EAST_1.getName();
    private final String AWS_ENDPOINT = "http://127.0.0.1:4566";

    @Value("${aws.s3.bucket.name}")
    private String bucketName;
    @Value("aws.s3.accessKey")
    private String accessKey;
    @Value("aws.s3.secretKey")
    private String secretKey;

    @Bean
    public AmazonS3 amazonS3() {
        AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(AWS_ENDPOINT, AWS_REGION);
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(endpoint)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
        amazonS3.createBucket(bucketName);
        return amazonS3;
    }
}
