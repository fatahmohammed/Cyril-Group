package com.fatah.simulationfeuenforet.frame;

import javax.swing.*;
import java.awt.*;

public class TransparentLabel extends JLabel {
    public TransparentLabel() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getForeground());
        g.drawString(getText(), 0, g.getFontMetrics().getAscent());

    }
}
