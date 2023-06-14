package com.bibavix.spacedeadsftp.UI.ComponentLayout;

import com.bibavix.spacedeadsftp.UI.MainSdsftpFrame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainSdsftpLayout extends JPanel {
    private OpenFileButton openFileChooserBtn;
    private JButton uploadFileBtn;
    private JButton downloadFileBtn;
    private JTextArea pathAreaText;
    private JTextField bucketNameTxt;
    private JTextField objectKeyTxt;

    private JLabel bucketNameLabel;
    private JLabel objectKeyLabel;
    private JLabel pathFileLabel;

    public MainSdsftpLayout(){
        pathAreaText = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(pathAreaText);
        scrollPane.setPreferredSize(new Dimension(60, 60));
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);
        bucketNameLabel = new JLabel("Bucket name:");
        bucketNameLabel.setSize(new Dimension(20, 12));
        bucketNameTxt = new JTextField();
        bucketNameTxt.setText("dev1-ach-t1-batch-inputfiledirectory-681989517074");
        bucketNameTxt.setPreferredSize(new Dimension(Integer.MAX_VALUE, bucketNameTxt.getPreferredSize().height));
        objectKeyLabel = new JLabel("File name:");
        objectKeyLabel.setSize(new Dimension(20, 12));
        objectKeyTxt = new JTextField();
        objectKeyTxt.setText("");
        objectKeyTxt.setSize(new Dimension(60, 5));
        openFileChooserBtn = new OpenFileButton(pathAreaText, objectKeyTxt);
        UploadFileButton uploadFileButton = new UploadFileButton(pathAreaText, bucketNameTxt, objectKeyTxt);
        this.add(bucketNameLabel);
        this.add(bucketNameTxt);
        this.add(objectKeyLabel);
        this.add(objectKeyTxt);
        this.add(openFileChooserBtn);
        pathFileLabel = new JLabel("Path file:");
        this.add(pathFileLabel);
        this.add(scrollPane, BorderLayout.CENTER);
        Dimension minSize = new Dimension(5, 260);
        Dimension prefSize = new Dimension(5, 260);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 260);
        this.add(new Box.Filler(minSize, prefSize, maxSize));
        ListDownloadButton listDownloadButton = new ListDownloadButton(bucketNameTxt.getText());
        this.add(listDownloadButton);
        this.add(uploadFileButton);
        this.setPreferredSize(new Dimension(350, 500));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }


}
