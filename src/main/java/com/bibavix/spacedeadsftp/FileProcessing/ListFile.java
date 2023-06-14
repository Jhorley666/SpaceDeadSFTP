package com.bibavix.spacedeadsftp.FileProcessing;

import com.bibavix.spacedeadsftp.FileProcessing.AmazonS3.AmazonS3Impl;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;

public class ListFile {

    AmazonS3Impl amazonS3;

    public ListFile(String bucketName, String prefix){
        amazonS3 = new AmazonS3Impl(bucketName, prefix);
    }

    public List<S3Object> listS3Objects(){
        return amazonS3.objects();
    }

}
