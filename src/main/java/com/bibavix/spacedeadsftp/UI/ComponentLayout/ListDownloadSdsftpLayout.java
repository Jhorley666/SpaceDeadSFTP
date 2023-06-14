package com.bibavix.spacedeadsftp.UI.ComponentLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ListDownloadSdsftpLayout extends JPanel {

    JList<String> list;
    ListButton listButton;

    DownloadButton downloadButton;

    JTextField prefixTxt;
    JTextField bucketNameTxt;
    JTextField downloadTxt;

    JTextField toPathTxt;
    JLabel bucketNameLabel;

    JLabel prefixLabel;
    JLabel downloadLabel;
    JLabel toPathLabel;

    public ListDownloadSdsftpLayout(String bucketName){
        list = new JList<>();
        prefixTxt = new JTextField();
        prefixTxt.setText("config/app/XML/TDIR_OUT_HIS/");
        JScrollPane scrollPane = new JScrollPane(list);

        bucketNameLabel = new JLabel("Bucket name:");
        this.add(bucketNameLabel);
        bucketNameTxt = new JTextField("dev1-ach-t1-batch-outputfiledirectory-681989517074");
        this.add(bucketNameTxt);

        listButton = new ListButton(list,bucketNameTxt.getText(), prefixTxt.getText());
        prefixLabel = new JLabel("S3 Path:");
        this.add(prefixLabel);
        this.add(prefixTxt);
        this.add(listButton);
        this.add(scrollPane, BorderLayout.CENTER);
        downloadLabel = new JLabel("Download file path:");
        downloadTxt = new JTextField();
        downloadTxt.setEditable(false);
        this.add(downloadLabel);
        this.add(downloadTxt);
        toPathTxt = new JTextField();
        toPathLabel = new JLabel("Path");
        this.add(toPathTxt);
        downloadButton = new DownloadButton(list, downloadTxt, toPathTxt, bucketNameTxt.getText());
        this.add(downloadButton);
        Dimension minSize = new Dimension(5, 260);
        Dimension prefSize = new Dimension(5, 260);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 260);
        this.add(new Box.Filler(minSize, prefSize, maxSize));
        this.setPreferredSize(new Dimension(350, 500));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
