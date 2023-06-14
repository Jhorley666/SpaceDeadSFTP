package com.bibavix.spacedeadsftp.UI.ComponentLayout;

import com.bibavix.spacedeadsftp.UI.ListDownloadSdsftpFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListDownloadButton extends JButton implements ActionListener {

    private String bucketName;
    private ListDownloadSdsftpFrame listDownloadSdsftpFrame;

    public ListDownloadButton(String bucketName){
        this.setSize(40, 16);
        this.setText("List & Download Files");
        this.bucketName = bucketName;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listDownloadSdsftpFrame = new ListDownloadSdsftpFrame(bucketName);
        listDownloadSdsftpFrame.init();
    }
}
