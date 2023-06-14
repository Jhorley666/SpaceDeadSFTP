package com.bibavix.spacedeadsftp.FileProcessing;

import com.bibavix.spacedeadsftp.FileProcessing.AmazonS3.AmazonS3Impl;

public class UploadFile {

    AmazonS3Impl amazonS3;

    public UploadFile(){
        amazonS3 = new AmazonS3Impl();
    }

    public boolean uploadFileFromPath(String pathFile, String bucketName, String objectKey){
        return amazonS3.uploadFile(pathFile, bucketName, objectKey);
    }
}
