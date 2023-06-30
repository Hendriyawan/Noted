/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hendri.noted.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * * @author HENDRIYAWAN
 * 211011401536
 * Custom JButton
 */
class RoundButton extends JButton {
    private boolean hover;
    private boolean click;

    public RoundButton(String text) {
        super(text);
        setFocusable(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        // Mengatur listener untuk mendeteksi mouse hover dan klik
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hover = true;
                repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                hover = false;
                click = false;
                repaint();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                click = true;
                repaint();
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                click = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();

        // Mengatur warna latar belakang berdasarkan status hover dan klik
        if (click) {
            g2.setColor(getBackground().darker());
        } else if (hover) {
            g2.setColor(getBackground().brighter());
        } else {
            g2.setColor(getBackground());
        }

        g2.fillRoundRect(0, 0, width, height, height, height);
        g2.setColor(getForeground());
        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getHeight();
        int x = (width - textWidth) / 2;
        int y = (height - textHeight) / 2 + fm.getAscent();
        g2.drawString(getText(), x, y);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Tidak melakukan apa-apa untuk menghindari tampilan border
    }

    @Override
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
        return shape.contains(x, y);
    }
}
