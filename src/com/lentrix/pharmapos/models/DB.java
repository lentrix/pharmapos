/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.pharmapos.models;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author lentrix
 */
public class DB {
    private static Connection cxn;
    
    public static Connection connect() {
        if(cxn==null) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String host = "localhost";
                String dbName = "pharmacy";
                String user = "root";
                String password = "";
                cxn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + dbName, user, password);
            }catch(Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Error!",javax.swing.JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
        return cxn;
    }
}
