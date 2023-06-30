/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hendri.noted.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class DbConnection {
    static Connection connection;
    
    
    public static Connection createConnection() {
        if(connection == null){
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("db_noted");
            data.setUser("root");
            data.setPassword("");
            
            try {
                connection = data.getConnection();
                
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }
}
