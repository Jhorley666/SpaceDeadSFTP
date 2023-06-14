package com.bibavix.spacedeadsftp.UI;

import com.bibavix.spacedeadsftp.UI.ComponentLayout.MainSdsftpLayout;
import com.bibavix.spacedeadsftp.UI.Util.ScreenSetting;
import lombok.Data;

import javax.swing.*;
import java.awt.*;


@Data
public class MainSdsftpFrame extends JFrame {


    private ScreenSetting screenSetting;
    private MainSdsftpLayout mainSdsftpLayout;

    public MainSdsftpFrame(){
        screenSetting = new ScreenSetting();
        mainSdsftpLayout = new MainSdsftpLayout();
    }

    public void init(){
        screenSetting.setXSize(400);
        screenSetting.setYSize(600);
        this.setTitle("Dead Space SFTP");
        this.setLocation(screenSetting.screenPosition());
        this.setSize(screenSetting.getXSize(), screenSetting.getYSize());
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(mainSdsftpLayout);
        this.pack();
    }
}
