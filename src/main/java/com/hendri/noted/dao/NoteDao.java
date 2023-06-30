/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hendri.noted.dao;

import com.hendri.noted.model.Note;
import java.util.List;

/**
 *
 * @author HENDRIYAWAN
 * 211011401536
 */
public interface NoteDao {

    ///CREATE NOTE
    void createNote(Note note);
    void createNoteWithPassword(Note note);

    ///READ NOTE
    //SINGLE
    Note readNote(int id);
    ///ALL
    List<Note> readNotes();

    void deleteNote(int id);

    void updateNote(Note note);

    void updateNoteWithPassword(Note note);
}
