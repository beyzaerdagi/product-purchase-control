/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproje;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author beyza
 */
public class MakeButtonEllipse extends JButton {

    public MakeButtonEllipse() {
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.height, size.width);
        setPreferredSize(size);
        setContentAreaFilled(false);
    }

    @Override
    //changes the color of the button when the button is pressed
    public void paintComponent(Graphics g) {

        if (getModel().isArmed()) {
            g.setColor(Color.MAGENTA);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        super.paintComponent(g);
    }

    @Override
    //draws an oval and adjusts the color of the button
    public void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    Shape shape = null;

    @Override
    //draws a 2D ellipse
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getSize().width - 1, getSize().height - 1);
        }
        return shape.contains(x, y);
    }
}
