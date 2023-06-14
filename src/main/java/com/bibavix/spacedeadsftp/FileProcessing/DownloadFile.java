package com.bibavix.spacedeadsftp.FileProcessing;

import com.bibavix.spacedeadsftp.FileProcessing.AmazonS3.AmazonS3Impl;

public class DownloadFile {
    AmazonS3Impl amazonS3;

    public DownloadFile(String bucketName, String uri,String objectKey, String toPath){
        amazonS3 = new AmazonS3Impl(bucketName, uri, objectKey,toPath);
    }

    public boolean downloaded(){
       return amazonS3.download();
    }

}
