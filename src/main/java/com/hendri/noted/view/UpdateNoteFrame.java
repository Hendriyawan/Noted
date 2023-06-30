/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hendri.noted.view;

import javax.swing.*;

import com.hendri.noted.controller.NoteController;
import com.hendri.noted.model.Note;
import com.hendri.noted.util.Util;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author HENDRIYAWAN
 * 211011401536
 */
public class UpdateNoteFrame extends JFrame {
    // Mendapatkan ukuran layar desktop
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = screenSize.width;
    int height = screenSize.height;

    // Logo dengan Label
    JLabel logoLabel = new JLabel(
            "<html><span style='font-family: Arial; sans-serif; font-size: 14px; font-style: italic; color:white;'>Noted v1.0</span></html>");

    // All Buttons
    RoundButton buttonCancel = new RoundButton("Cancel");
    RoundButton buttonSave = new RoundButton("Save Update");
    RoundButton buttonUnlock = new RoundButton("Unlock");


    // Toolbar
    JToolBar toolbar = new JToolBar(JToolBar.HORIZONTAL);

    // Note Controller
    private NoteController noteController;

    // Form Components
    JLabel titleLabel = new JLabel("Title:");
    JTextField titleField = new JTextField();
    JLabel contentLabel = new JLabel("Content:");
    JTextArea contentArea = new JTextArea();
    JScrollPane contentScrollPane = new JScrollPane(contentArea);
    JCheckBox privateCheckBox = new JCheckBox("Private Note");
    private Note note;

    public UpdateNoteFrame(Note note) {
        this.note = note;
        //create instance NoteController
        noteController = new NoteController();

        // Mengatur padding, background button, serta warna teks button
        buttonCancel.setMargin(new Insets(5, 5, 5, 5));
        buttonCancel.setBackground(new Color(0xFFFFC107));
        buttonCancel.setForeground(Color.WHITE);

        buttonSave.setMargin(new Insets(5, 5, 5, 5));
        buttonSave.setBackground(new Color(0xFFFFC107));
        buttonSave.setForeground(Color.WHITE);

        buttonUnlock.setMargin(new Insets(5, 5, 5, 5));
        buttonUnlock.setBackground(new Color(0xFFFFC107));
        buttonUnlock.setForeground(Color.WHITE);
        buttonUnlock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUnlockDialog();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ListNoteFrame();
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (privateCheckBox.isSelected()) {
                    showPasswordDialog();
                } else {
                    note.setTitle(titleField.getText());
                    note.setDate(Util.getDate());
                    note.setUsingPassword(false);
                    note.setPassword("");
                    note.setContent(contentArea.getText());
                    noteController.updateNote(false, note);
                    setVisible(false);
                    new ListNoteFrame();
                }
            }
        });

        // Menambahkan logoLabel komponen ke Toolbar
        toolbar.add(logoLabel);
        toolbar.setPreferredSize(new Dimension(width, 50));
        toolbar.setBackground(new Color(0xFF03A9FA));
        toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.X_AXIS));

        toolbar.add(Box.createHorizontalGlue());
        toolbar.add(buttonCancel);
        toolbar.add(Box.createHorizontalStrut(5)); // Spasi antara tombol
        toolbar.add(buttonSave);
        toolbar.add(Box.createHorizontalStrut(5)); // Spasi antara tombol
        toolbar.add(buttonUnlock);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        formPanel.add(titleLabel, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;

        titleField.setText(note.getTitle());
        formPanel.add(titleField, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        contentArea.setText("*****************");
        formPanel.add(contentLabel, gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        formPanel.add(contentScrollPane, gridBagConstraints);

        gridBagConstraints.gridy = 4;
        formPanel.add(privateCheckBox, gridBagConstraints);

        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);

        setSize(width / 2, height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    ///menampilkan dialog enter password
    private void showPasswordDialog() {
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
                "Enter password:", passwordField
        };
        int option = JOptionPane.showOptionDialog(null, message, "Password", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (option == JOptionPane.OK_OPTION) {
            char[] password = passwordField.getPassword();
            note.setTitle(titleField.getText());
            note.setDate(Util.getDate());
            note.setUsingPassword(true);
            note.setPassword(Util.convertToMD5(password));
            note.setContent(contentArea.getText());
            noteController.updateNote(true, note);
            setVisible(false);
            new ListNoteFrame();
        }
    }

    ///menampilkan unlock content Note
    private void showUnlockDialog() {
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
                "Enter password:", passwordField
        };
        int option = JOptionPane.showOptionDialog(null, message, "Unlock Note", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (option == JOptionPane.OK_OPTION) {
            char[] password = passwordField.getPassword();
            // Panggil metode untuk memeriksa password
            System.out.println(checkPassword(password));

            if (checkPassword(password)) {
                // Jika password benar, tampilkan isi Note
                ///JOptionPane.showMessageDialog(null, "Password is correct. Note content: " + getContent());
                contentArea.setText(note.getContent());
            } else {
                // Jika password salah, tampilkan pesan kesalahan
                JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.");
            }
        }
    }

    private boolean checkPassword(char[] password) {
        // Periksa apakah password cocok dengan password yang tersimpan di Note
        String storedPassword = note.getPassword();
        // Misalkan Anda menggunakan metode convertToMD5() yang ada di kelas Util untuk mengonversi password menjadi MD5
        String enteredPasswordMD5 = new String(Util.convertToMD5(password));
        System.out.println(enteredPasswordMD5);
        return storedPassword.equals(enteredPasswordMD5);
   }
}
