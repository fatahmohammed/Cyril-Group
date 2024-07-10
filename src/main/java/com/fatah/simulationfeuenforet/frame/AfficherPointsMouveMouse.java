package com.fatah.simulationfeuenforet.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class AfficherPointsMouveMouse {
String paths;
    BufferedImage image=null;
    int i=0;
    int couleurPoint;
    public int affiche(String paths, JLabel infoPoint, TransparentLabel label, MouseEvent e) {
        this.paths=paths;
        if(i==0)
        {
                image = ImageBuffeur.loadImage(paths);
                i++;

        }
        if (image != null) {

            couleurPoint= PositionCoulur.pixelValue(image,e);
            if (couleurPoint == 0) {
                infoPoint.setText("Zone Combustible :" + e.getX() + " : " + e.getY() + " : " + couleurPoint);
                infoPoint.setBackground(Color.RED);
            } else {
                infoPoint.setText("Zone Non Combustible :" + e.getX() + " : " + e.getY() + " : " + couleurPoint);
                infoPoint.setBackground(Color.BLUE);
            }
            label.setForeground(Color.RED);
            label.setText(e.getX() + " : " + e.getY() );
            label.setBounds(new Rectangle(e.getX() + 10, e.getY() + 10, 100, 20));
        }
        return couleurPoint;
    }


}
