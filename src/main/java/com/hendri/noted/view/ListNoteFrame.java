package com.hendri.noted.view;

import com.hendri.noted.controller.NoteController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

/**
 *
 * @author HENDRIYAWAN 211011401536
 * Kelas ini untuk UI utama / List Notes
 */
public class ListNoteFrame extends JFrame {

    ///mendapatkan ukuran layar desktop
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = screenSize.width;
    int height = screenSize.height;

    //Logo Dengan Label
    JLabel logoLabel = new JLabel("<html><span style='font-family: Arial; sans-serif; font-size: 14px; font-style: italic; color:white;'>Noted v1.0</span></html>");

    ///ALL BUTTON
    RoundButton buttonCreateNote = new RoundButton("Create Note");
    RoundButton buttonAbout = new RoundButton("About Noted");
    RoundButton buttonExit = new RoundButton("Exit");

    ///Toolbar
    JToolBar toolbar = new JToolBar(JToolBar.HORIZONTAL);

    private NoteController noteController;


    public ListNoteFrame() {


        //mengatur padding, background button serta warna text button
        buttonCreateNote.setMargin(new Insets(5, 5, 5, 5));
        buttonCreateNote.setBackground(new Color(0xFFFFC107));
        buttonCreateNote.setForeground(Color.WHITE);

        buttonAbout.setMargin(new Insets(5, 5, 5, 5));
        buttonAbout.setBackground(new Color(0xFFFFC107));
        buttonAbout.setForeground(Color.WHITE);

        buttonExit.setMargin(new Insets(5, 5, 5, 5));
        buttonExit.setBackground(new Color(0xFFFFC107));
        buttonExit.setForeground(Color.WHITE);


        buttonCreateNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new CreateNoteFrame();
            }
        });

        buttonExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        ///menambahkan logoLabel komponen ke Toolbar
        toolbar.add(logoLabel);
        toolbar.setPreferredSize(new Dimension(width, 50));
        toolbar.setBackground(new Color(0xFF03A9FA));
        toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.X_AXIS));

        toolbar.add(Box.createHorizontalGlue());
        toolbar.add(buttonCreateNote);
        toolbar.add(Box.createHorizontalStrut(5)); // Spasi antara tombol
        toolbar.add(buttonAbout);
        toolbar.add(Box.createHorizontalStrut(5)); // Spasi antara tombol
        toolbar.add(buttonExit);

        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);

        setSize(width / 2, height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create object NoteController
        noteController = new NoteController();
        noteController.loadNotes(this);

        setVisible(true);
    }
}
