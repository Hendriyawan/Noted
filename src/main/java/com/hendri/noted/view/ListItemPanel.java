/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hendri.noted.view;

/**
 *
 * @author HENDRIYAWAN
 * 211011401536
 */
import javax.swing.*;
import java.awt.*;


public class ListItemPanel extends JPanel {
    private JLabel titleLabel;
    private JLabel dateLabel;
    private RoundButton deleteButton;
    private RoundButton updateButton;

    public ListItemPanel(String title, String date) {
        setLayout(new BorderLayout());

        // Column
        JPanel columnPanel = new JPanel();
        columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.Y_AXIS));
        int itemPadding = 10; // Padding untuk setiap item

        // Judul (Title)
        titleLabel = new JLabel(title);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 14));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(itemPadding, itemPadding, itemPadding / 2, itemPadding));
        columnPanel.add(titleLabel);

        // Tanggal (Date)
        dateLabel = new JLabel(date);
        dateLabel.setBorder(BorderFactory.createEmptyBorder(0, itemPadding, itemPadding, itemPadding));
        columnPanel.add(dateLabel);

        add(columnPanel, BorderLayout.WEST);

        // Button
        deleteButton = new RoundButton("DELETE");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBorder(BorderFactory.createEmptyBorder(itemPadding, itemPadding, itemPadding, itemPadding));

        //Button Update
        updateButton = new RoundButton("UPDATE");
        updateButton.setBackground(new Color(0xFFFFC107));
        updateButton.setForeground(Color.WHITE);
        deleteButton.setBorder(BorderFactory.createEmptyBorder(itemPadding, itemPadding, itemPadding, itemPadding));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(deleteButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(itemPadding, itemPadding, itemPadding, itemPadding));
        buttonPanel.add(updateButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(itemPadding, itemPadding, itemPadding, itemPadding));
        add(buttonPanel, BorderLayout.EAST);
        setPreferredSize(new Dimension(getPreferredSize().width, 70));
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getUpdateButton(){
        return updateButton;
    }
}

