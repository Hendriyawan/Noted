package com.hendri.noted.db;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HENDRIYAWAN
 * 211011401536
 * 
 * ini adalah kelas untuk menampung semua operasi SQL
 */
public class SQL {
    private final static String TABLE_NAME = "notes";
    public final static String INSERT = "INSERT INTO "+TABLE_NAME +" (title, date, using_password, password, content) VALUES (?, ?, ?, ?, ?);";
    public final static String UPDATE = "UPDATE "+TABLE_NAME + " set title=?, date=?, using_password=?, password=?, content=? where id=? ;";
    public final static String DELETE = "DELETE FROM "+TABLE_NAME + " where id=? ;";
    public final static String SELECT = "SELECT * FROM "+TABLE_NAME+";";
    public final static String FIND_TITLE = "SELECT * FROM "+TABLE_NAME + " where title like ?;";
}
