/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hendri.noted.model;

/**
 *
 * @author HENDRIYAWAN
 * 211011401536
 */
public class Note {
    private Integer id;
    private String title;
    private String date;
    private boolean usingPassword;
    private String password;
    private String content;


    public void setId(Integer id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setUsingPassword(boolean usingPassword){
        this.usingPassword = usingPassword;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setContent(String content){
        this.content = content;
    }


    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }

    public String getDate(){
        return date;
    }

    public boolean getUsingPassword(){
        return usingPassword;
    }

    public String getPassword(){
        return password;
    }


    public String getContent(){
        return content;
    }
}
