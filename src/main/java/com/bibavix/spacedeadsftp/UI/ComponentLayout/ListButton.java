package com.bibavix.spacedeadsftp.UI.ComponentLayout;

import com.bibavix.spacedeadsftp.FileProcessing.ListFile;
import software.amazon.awssdk.services.s3.model.S3Object;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListButton extends JButton implements ActionListener {

    JList<String> objectsView;
    ListFile listFile;

    public ListButton(JList<String> objectsView, String bucketName, String prefix){
        this.setSize(40, 16);
        this.setText("List Files");
        this.objectsView = objectsView;
        listFile = new ListFile(bucketName, prefix);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<S3Object> objects = listFile.listS3Objects();
        List<String> objectKey = new ArrayList<>();
        for (S3Object object : objects){
            objectKey.add(object.key());
        }
        objectsView.setListData(objectKey.toArray(new String[0]));
    }
}
