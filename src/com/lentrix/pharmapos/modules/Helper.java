/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.pharmapos.modules;

import java.awt.Component;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author lentrix
 */
public class Helper {
    public static void error(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error!", JOptionPane.ERROR_MESSAGE);
    }
    public static void info(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message,"Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static String formatSQLDate(java.sql.Date date) {
        return date.toLocalDate().format(DateTimeFormatter.ofPattern("MMM-d-u"));
    }
    
    public static String formatSQLDateFull(java.sql.Date date) {
        return date.toLocalDate().format(DateTimeFormatter.ofPattern("MMMM d, u"));
    }
}
