package com.fatah.simulationfeuenforet.zonecombistible;

import com.fatah.simulationfeuenforet.frame.InterfaceParImageSat;

import javax.swing.*;

public class MainImageSat {
    public static void main(String[] args) {
        InterfaceParImageSat gh=new InterfaceParImageSat();
        gh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gh.setSize(500,500);
        gh.setLocationRelativeTo(null);
        gh.setVisible(true);

    }
}
