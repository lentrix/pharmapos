/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.pharmapos.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lentrix
 */
public class CustomerRepresentative {
    private int id;
    private String name;
    private boolean active;

    public CustomerRepresentative(int id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public static CustomerRepresentative load(int id) throws SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery("SELECT * FROM cust_rep WHERE id=" + id);
        if(rs.first()) {
            return new CustomerRepresentative(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBoolean("active")
            );
        }else {
            throw new SQLException("ID Number " + id + " not found.");
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return name;
    }
    
    public void save() throws SQLException {
        if(this.id ==-1) {
            create();
        }else {
            update();
        }
    }
    
    private void create() throws SQLException {
        PreparedStatement ps = DB.connect().prepareStatement(
                "INSERT INTO cust_rep (name, active) VALUES (?,?)", 
                PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, this.name);
        ps.setBoolean(2, this.active);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.first();
        this.setId(rs.getInt(1));
    }
    
    private void update() throws SQLException {
        PreparedStatement ps = DB.connect().prepareStatement(
                "UPDATE cust_rep SET name=?, active=? WHERE id=?");
        ps.setString(1, this.name);
        ps.setBoolean(2, this.active);
        ps.setInt(3, this.id);
        ps.executeUpdate();
    }
}
