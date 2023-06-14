package com.bibavix.spacedeadsftp.FileProcessing.AmazonS3;
import com.bibavix.spacedeadsftp.FileProcessing.IAmazonS3.AmazonS3;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmazonS3Impl implements  AmazonS3{

    private String bucketName;
    private String regionName;
    private String objectKey;
    String toPath;
    private String prefix;
    private Region region = Region.US_EAST_1;

    private  String uri;
    public AmazonS3Impl(String bucketName, String uri, String objectKey, String toPath){
        this.bucketName = bucketName;
        this.toPath = toPath;
        this.uri = uri;
        this.objectKey = objectKey;
    }

    public AmazonS3Impl(String bucketName, String prefix){
        this.bucketName = bucketName;
        this.prefix = prefix;
    }

    public boolean uploadFile(String pathFile, String bucketName, String objectKey){


        S3Client s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(credentialsProvider())
                .build();
        PutObjectResponse putObjectResponse = null;
        if (existsBucket(bucketName, s3Client)){
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .build();
            putObjectResponse =
                    s3Client.putObject(putObjectRequest, RequestBody.fromFile(new File(pathFile)));
        }
        s3Client.close();
        return isUploadedFile(putObjectResponse);
    }

    @Override
    public boolean existsBucket(String bucketName, S3Client s3Client) {
        HeadBucketRequest headBucketRequest = HeadBucketRequest.builder()
                .bucket(bucketName)
                .build();
        try {
            HeadBucketResponse headBucketResponse = s3Client.headBucket(headBucketRequest);
            if (headBucketResponse.sdkHttpResponse().statusCode() == 200){
                return Boolean.TRUE;
            }
        }catch (AwsServiceException awsServiceException){
            awsServiceException.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isBucketEmpty(String bucketName, S3Client s3Client) {
        try {
            ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder()
                    .bucket(bucketName)
                    .build();
            ListObjectsResponse listObjectsResponse = s3Client.listObjects(listObjectsRequest);
            List<S3Object> objects = listObjectsResponse.contents();
            if (objects.isEmpty()){
                return Boolean.TRUE;
            }
        }catch (AwsServiceException awsServiceException){
            awsServiceException.printStackTrace();
        }
        return Boolean.FALSE;
    }

    @Override
    public boolean isUploadedFile(PutObjectResponse putObjectResponse) {
        int statusCode = putObjectResponse.sdkHttpResponse().statusCode();
        if (statusCode == 200){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<S3Object> objects() {
        S3Client s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(credentialsProvider())
                .build();

        try {
            ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                    .prefix(prefix)
                    .bucket(bucketName)
                    .maxKeys(10)
                    .build();
            ListObjectsV2Iterable listObjectsV2Iterable = s3Client.listObjectsV2Paginator(listObjectsRequest);
            List<S3Object> sortedS3Objects = new ArrayList<>();
            List<S3Object> listS3Objects = new ArrayList<>();
            Iterator<ListObjectsV2Response> iterator = listObjectsV2Iterable.iterator();
            while (iterator.hasNext()){
                ListObjectsV2Response response = iterator.next();
                List<S3Object> s3Objects = response.contents();
                sortedS3Objects.addAll(s3Objects);
            }

            sortedS3Objects.sort(Comparator.comparing(S3Object::lastModified).reversed());
            for (S3Object s3Object : sortedS3Objects){
                listS3Objects.add(s3Object);
                if (listS3Objects.size() >= 10){
                    break;
                }
            }
            if (!sortedS3Objects.isEmpty()){
                s3Client.close();
                return sortedS3Objects;
            }
        }catch (AwsServiceException awsServiceException){
            awsServiceException.printStackTrace();
        }
        s3Client.close();
        return Collections.emptyList();
    }

    @Override
    public boolean download() {
        S3Client s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(credentialsProvider())
                .build();
        String[] paths = objectKey.split("/");
        int length = paths.length - 1;
        String key = paths[length];
        Path downloadPath = Paths.get(toPath +"\\"+ key);

        try(FileOutputStream  fileOutputStream = new FileOutputStream(downloadPath.toFile())){
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(getObjectRequest);
            if (objectBytes.response().sdkHttpResponse().statusCode() == 200){
                fileOutputStream.write(objectBytes.asByteArray());
                return Boolean.TRUE;
            }

        }catch (IOException e){
            e.printStackTrace();
        } finally {
            s3Client.close();
        }
        return Boolean.FALSE;
    }

    @Override
    public AwsCredentialsProvider credentialsProvider() {
        return ProfileCredentialsProvider.builder()
                .profileName("681989517074_COBDeveloper")
                .build();
    }
}
