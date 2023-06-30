/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hendri.noted.controller;

import com.hendri.noted.dao.NoteDaoImpl;
import com.hendri.noted.model.Note;
import com.hendri.noted.view.ListItemPanel;
import com.hendri.noted.view.UpdateNoteFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author HENDRIYAWAN
 *         211011401536
 */

/// PADA KELAS INI BERISI SEMUA LOGIC
public class NoteController {

    private JFrame frame;
    private NoteDaoImpl noteDaoImpl;
    private List<Note> listNote;
    private JPanel listPanel;

    public NoteController() {
        noteDaoImpl = new NoteDaoImpl();
    }

    public void loadNotes(JFrame frame) {
        this.frame = frame;
        listNote = noteDaoImpl.readNotes();
        // Membuat model untuk JList
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        refreshList();
        JScrollPane listScrollPane = new JScrollPane(listPanel);
        frame.add(listScrollPane, BorderLayout.CENTER);
    }

    /// REFRESH LIST NOTE
    private void refreshList() {
        listPanel.removeAll(); // Menghapus semua komponen dari panel
        listPanel.revalidate(); // Mevalidasi ulang panel
        listPanel.repaint(); // Menggambar ulang panel
        // Memperbarui listNote dengan catatan yang baru
        listNote = noteDaoImpl.readNotes();

        if (listNote.size() > 0) {
            for (Note note : listNote) {
                ListItemPanel itemPanel = new ListItemPanel(note.getTitle(), note.getDate());
                if (itemPanel.getDeleteButton() != null) {
                    itemPanel.getDeleteButton().addActionListener(e -> {
                        // Tindakan yang ingin dilakukan saat tombol aksi ditekan
                        // Misalnya, membuka catatan atau menjalankan fungsi tertentu
                        noteDaoImpl.deleteNote(note.getId());
                        refreshList();

                    });
                }
                if (itemPanel.getUpdateButton() != null) {
                    itemPanel.getUpdateButton().addActionListener(e -> {
                        frame.setVisible(false);
                        new UpdateNoteFrame(note);
                    });
                }
                listPanel.add(itemPanel);
            }

        } else {
            JLabel emptyNoteLabel = new JLabel("Not available Note !");
            listPanel.add(emptyNoteLabel, BorderLayout.CENTER);
        }
    }

    /// SIMPAN NOTE
    public void saveNote(Boolean usingPassword, Note note) {
        if (usingPassword) {
            noteDaoImpl.createNoteWithPassword(note);
        } else {
            noteDaoImpl.createNote(note);
        }
    }

    /// UPDATE NOTE
    public void updateNote(Boolean usingPassword, Note note) {
        if (usingPassword) {
            noteDaoImpl.updateNoteWithPassword(note);
        } else {
            noteDaoImpl.updateNote(note);
        }
    }
}
