/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.pharmapos.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author lentrix
 */
public class User {
    public static final int ADMIN = 1;
    public static final int CASHIER = 2;
    private int id;
    private String user;
    private String _password;
    private String fullName;
    private int role;

    public User(int id, String user, String fullName, int role) {
        this.id = id;
        this.user = user;
        this.fullName = fullName;
        this.role = role;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public static User load(int id) throws SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery("SELECT * FROM user WHERE id=" + id);
        if(rs.first()) {
            return new User(
                    rs.getInt("id"),
                    rs.getString("user"),
                    rs.getString("full_name"),
                    rs.getInt("role")
            );
        }else {
            throw new SQLException("User # " + id + " not found.");
        }
    }
    
    public static User load(String user, String password) throws SQLException {
        PreparedStatement ps = DB.connect().prepareStatement(
                "SELECT * FROM user WHERE user=? AND password=md5(?)");
        ps.setString(1, user);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if(rs.first()) {
            return new User(
                    rs.getInt("id"),
                    rs.getString("user"),
                    rs.getString("full_name"),
                    rs.getInt("role")
            );
        }else {
            throw new SQLException("Invalid User name and/or password.");
        }
    }
    
    public static LinkedList<User> load() throws SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery(
                "SELECT * FROM user ORDER BY full_name");
        rs.beforeFirst();
        LinkedList<User> list = new LinkedList<User>();
        while(rs.next()) {
            list.add(
                    new User(
                    rs.getInt("id"),
                    rs.getString("user"),
                    rs.getString("full_name"),
                    rs.getInt("role"))
            );
        }
        return list;
    }
    
    public void save() throws SQLException {
        if(this.id==-1) {
            create();
        }else {
            update();
        }
    }
    
    private void create() throws SQLException {
        PreparedStatement ps = DB.connect().prepareStatement(
                "INSERT INTO user (user, password, fullname, role) "
                        + "VALUES (?,md5(?),?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, user);
        ps.setString(2, _password);
        ps.setString(3, fullName);
        ps.setInt(4, role);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.first();
        this.id = rs.getInt(1);
    }
    
    private void update() throws SQLException {
        PreparedStatement ps = DB.connect().prepareStatement(
                "UPDATE user SET user=?, full_name=?, role=? WHERE id=?");
        ps.setString(1,this.user);
        ps.setString(2,this.fullName);
        ps.setInt(3, this.role);
        ps.setInt(4, this.id);
        ps.executeUpdate();
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    public String getRoleText() {
        switch(this.role) {
            case 1 : return "Administrator";
            case 2 : return "Sales Representative";
            default: return "Error: unknown role code";
        }
    }
      
    public void changePassword(String newPassword) throws SQLException {
        PreparedStatement ps2 = DB.connect().prepareStatement(
                "UPDATE user SET password=md5(?) WHERE id=?");
        ps2.setString(1, newPassword);
        ps2.setInt(2, id);
        ps2.executeUpdate();
    }
    
    public boolean authenticatePassword(String password) throws SQLException {
        
        PreparedStatement ps = DB.connect().prepareStatement(
                "SELECT * FROM user WHERE id=? AND password=MD5(?)");
        ps.setInt(1,this.id);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        
        return rs.first();
        
    }
}
