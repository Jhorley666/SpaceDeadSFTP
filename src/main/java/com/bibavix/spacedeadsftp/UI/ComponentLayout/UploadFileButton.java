package com.bibavix.spacedeadsftp.UI.ComponentLayout;

import com.bibavix.spacedeadsftp.FileProcessing.UploadFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadFileButton  extends JButton implements ActionListener {
    private JTextArea pathFile;
    private UploadFile uploadFile;
    private JTextField bucketNameTxt;
    private JTextField objectKeyTxt;
    public UploadFileButton(JTextArea pathFile, JTextField bucketNameTxt, JTextField objectKeyTxt){
        this.setSize(40, 16);
        this.setText("Upload File");
        this.pathFile = pathFile;
        this.bucketNameTxt = bucketNameTxt;
        this.objectKeyTxt = objectKeyTxt;
        this.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        uploadFile = new UploadFile();
        try{
            Boolean uploaded = uploadFile.uploadFileFromPath(pathFile.getText(), bucketNameTxt.getText(), objectKeyTxt.getText());
            if (uploaded){
                JOptionPane.showMessageDialog(this, "The file was successfully uploaded!");
            } else {
                JOptionPane.showMessageDialog(this, "The file was not successfully uploaded!");
            }
        }catch (Exception s3Exception){
            JOptionPane.showMessageDialog(this, s3Exception.getMessage());
        }
    }
}
