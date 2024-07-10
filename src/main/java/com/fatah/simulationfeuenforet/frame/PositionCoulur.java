package com.fatah.simulationfeuenforet.frame;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PositionCoulur {
    public static int pixelValue(BufferedImage image, MouseEvent e) {
        int pixel = image.getRGB(e.getX(),e.getY());
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;
        return (red << 16) | (green << 8) | blue;
    }
}
