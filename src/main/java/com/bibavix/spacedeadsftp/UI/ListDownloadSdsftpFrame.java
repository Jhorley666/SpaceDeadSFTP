package com.bibavix.spacedeadsftp.UI;

import com.bibavix.spacedeadsftp.UI.ComponentLayout.ListDownloadSdsftpLayout;
import com.bibavix.spacedeadsftp.UI.Util.ScreenSetting;

import javax.swing.*;
import java.awt.*;

public class ListDownloadSdsftpFrame extends JFrame {
    private ScreenSetting screenSetting;
    private String bucketName;

    private ListDownloadSdsftpLayout listDownloadSdsftpLayout;

    public ListDownloadSdsftpFrame(String bucketName){
        this.bucketName = bucketName;
        screenSetting = new ScreenSetting();
        listDownloadSdsftpLayout = new ListDownloadSdsftpLayout(bucketName);
    }

    public void init(){
        screenSetting.setXSize(600);
        screenSetting.setYSize(600);
        this.setTitle("List & Download Files");
        this.setLocation(screenSetting.screenPosition());
        this.setSize(screenSetting.getXSize(), screenSetting.getYSize());
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setContentPane(listDownloadSdsftpLayout);
        this.pack();
    }
}
