/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hendri.noted.dao;

import com.hendri.noted.db.DbConnection;
import com.hendri.noted.model.Note;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import com.hendri.noted.db.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HENDRIYAWAN
 * 211011401536
 */
public class NoteDaoImpl implements NoteDao {

    private Connection dbConnection;

    public NoteDaoImpl(){
        dbConnection = DbConnection.createConnection();
    }


    @Override
    public void createNote(Note note) {
       PreparedStatement statement = null;
       try {
           statement = dbConnection.prepareStatement(SQL.INSERT);
           statement.setString(1, note.getTitle());
           statement.setString(2, note.getDate());
           statement.setBoolean(3, false);
           statement.setString(4, note.getPassword());
           statement.setString(5, note.getContent());
           statement.executeUpdate();
           ResultSet resultSet = statement.getGeneratedKeys();
           while(resultSet.next()){
             note.setId(resultSet.getInt(1))  ;
           }
       } catch(SQLException e){
           e.printStackTrace();
       } finally {
           try {
               statement.close();
           } catch(SQLException e){
               e.printStackTrace();
           }
       }
    }

    @Override
    public void createNoteWithPassword(Note note) {
        PreparedStatement statement = null;
       try {
           statement = dbConnection.prepareStatement(SQL.INSERT);
           statement.setString(1, note.getTitle());
           statement.setString(2, note.getDate());
           statement.setBoolean(3, true);
           statement.setString(4, note.getPassword());
           statement.setString(5, note.getContent());
           statement.executeUpdate();
           ResultSet resultSet = statement.getGeneratedKeys();
           while(resultSet.next()){
             note.setId(resultSet.getInt(1))  ;
           }
       } catch(SQLException e){
           e.printStackTrace();
       } finally {
           try {
               statement.close();
           } catch(SQLException e){
               e.printStackTrace();
           }
       }
    }

    @Override
    public Note readNote(int id) {
        return null;
    }

    @Override
    public List<Note> readNotes() {
        List<Note> listNote = null;
        try {
            listNote = new ArrayList<Note>();
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL.SELECT);
            while(resultSet.next()){
                Note note = new Note();

                note.setId(resultSet.getInt("id"));
                note.setTitle(resultSet.getString("title"));
                note.setDate(resultSet.getString("date"));
                note.setUsingPassword(resultSet.getBoolean("using_password"));
                note.setPassword(resultSet.getString("password"));
                note.setContent(resultSet.getString("content"));
                System.out.println("NOTE : " + note.getId());
                listNote.add(note);
            }

        } catch(SQLException e){
            Logger.getLogger(NoteDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return listNote;
    }

    @Override
    public void updateNote(Note note) {
        PreparedStatement statement = null;
        try {
            statement = dbConnection.prepareStatement(SQL.UPDATE);
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getDate());
            statement.setBoolean(3, false);
            statement.setString(4, "");
            statement.setString(5, note.getContent());
            statement.setInt(6, note.getId());
            statement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateNoteWithPassword(Note note) {
        PreparedStatement statement = null;
        try {
            statement = dbConnection.prepareStatement(SQL.UPDATE);
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getDate());
            statement.setBoolean(3, true);
            statement.setString(4, note.getPassword());
            statement.setString(5, note.getContent());
            statement.setInt(6, note.getId());
            statement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteNote(int id){
        PreparedStatement statement = null;
        try {
            statement = dbConnection.prepareCall(SQL.DELETE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            try {
              statement.close();
            }  catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}
