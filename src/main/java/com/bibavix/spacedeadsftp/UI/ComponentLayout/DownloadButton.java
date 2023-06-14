package com.bibavix.spacedeadsftp.UI.ComponentLayout;

import com.bibavix.spacedeadsftp.FileProcessing.DownloadFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DownloadButton extends JButton implements ActionListener {

    JList<String> list;
    JTextField downloadTxt;
    JTextField toPathTxt;
    DownloadFile downloadFile;

    String bucketName;



    public DownloadButton(JList<String> list, JTextField downloadTxt, JTextField toPathTxt, String bucketName){
        this.setSize(40, 16);
        this.setText("Download File");
        this.list = list;
        this.bucketName = bucketName;
        this.downloadTxt = downloadTxt;
        this.toPathTxt = toPathTxt;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedItem = list.getSelectedValue();
        if (selectedItem != null){
            String toPath = toPathTxt.getText();
            String[] paths = selectedItem.split("/");
            int length = paths.length - 1;
            String key = paths[length];
            String uri = "s3:://" + bucketName + selectedItem;
            downloadTxt.setText(key);
            if (!key.isEmpty()){
                downloadFile = new DownloadFile(bucketName, uri, selectedItem, toPath);
                Boolean downloaded = downloadFile.downloaded();
                if(Boolean.TRUE.equals(downloaded)){
                    JOptionPane.showMessageDialog(this, key +  "was downloaded!");
                } else {
                    JOptionPane.showMessageDialog(this, key +  "was not downloaded!");
                }
            }
        } else{
            downloadTxt.setText("No item selected.");
        }
    }
}
