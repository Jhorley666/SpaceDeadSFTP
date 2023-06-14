package com.bibavix.spacedeadsftp.FileProcessing.IAmazonS3;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;

public interface AmazonS3 {
    boolean existsBucket(String bucketName, S3Client s3Client);
    boolean isBucketEmpty(String bucketName, S3Client s3Client);
    boolean isUploadedFile(PutObjectResponse putObjectResponse);
    List<S3Object> objects();
    boolean download();
    AwsCredentialsProvider credentialsProvider();
}
