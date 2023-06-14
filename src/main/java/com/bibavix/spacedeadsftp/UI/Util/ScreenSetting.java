package com.bibavix.spacedeadsftp.UI.Util;

import lombok.Data;
import java.awt.*;

@Data
public class ScreenSetting {

    private int xSize;
    private int ySize;

    public Point screenPosition(){
        Point point = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int xCenterSize =  xSize / 2;
        int yCenterSize =  ySize / 2;
        int xScreen = point.x - xCenterSize;
        int yScreen = point.y - yCenterSize;
        point.setLocation(xScreen, yScreen);
        return point;
    }

}
