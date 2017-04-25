/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.pharmapos.models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lentrix
 */
public class Sales {
    private int id;
    private java.sql.Date date;
    private CustomerRepresentative custRep;

    public Sales(int id, Date date, CustomerRepresentative custRep) {
        this.id = id;
        this.date = date;
        this.custRep = custRep;
    }

    public static Sales load(int id) throws SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery("SELECT * FROM sales WHERE id=" + id);
        if(rs.first()) {
            return new Sales(rs.getInt("id"), rs.getDate("date"), 
                    CustomerRepresentative.load(rs.getInt("rep_id")));
        }else {
            throw new SQLException("Sales ID# " + id + " not found.");
        }
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public CustomerRepresentative getCustRep() {
        return custRep;
    }

    public void setCustRep(CustomerRepresentative custRep) {
        this.custRep = custRep;
    }

    @Override
    public String toString() {
        return "Invoice# " + id;
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
                "INSERT INTO sales (date, rep_id) VALUES (?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setDate(1, this.date);
        ps.setInt(2, this.custRep.getId());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.first();
        this.id = rs.getInt(1);
    }
    
    private void update() throws SQLException {
        PreparedStatement ps = DB.connect().prepareStatement(
                "UPDATE sales SET date=?, rep_id=? WHERE id=?");
        ps.setDate(1, this.date);
        ps.setInt(2, this.custRep.getId());
        ps.setInt(3, this.id);
        ps.executeUpdate();
    }
}
