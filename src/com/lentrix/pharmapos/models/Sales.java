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
import java.util.LinkedList;

/**
 *
 * @author lentrix
 */
public class Sales {
    private int id;
    private java.sql.Date date;
    private User rep;
    private float cashTender;

    public Sales(int id, Date date, User custRep, float cashTender) {
        this.id = id;
        this.date = date;
        this.rep = custRep;
        this.cashTender = cashTender;
    }

    public static Sales load(int id) throws SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery("SELECT * FROM sales WHERE id=" + id);
        if(rs.first()) {
            return new Sales(rs.getInt("id"), rs.getDate("date"), 
                    User.load(rs.getInt("rep_id")), rs.getFloat("cash_tender"));
        }else {
            throw new SQLException("Sales ID# " + id + " not found.");
        }
    }    

    public static LinkedList<Sales> loadHistory(User user, java.sql.Date from, java.sql.Date to) throws SQLException {
        PreparedStatement ps = DB.connect().prepareStatement(
                "SELECT s.id, s.rep_id, s.date, s.cash_tender "
                        + "FROM sales s LEFT JOIN sales_item si ON s.id=si.sales_id "
                        + "WHERE rep_id=? AND date BETWEEN ? AND ? GROUP BY s.id ");
        ps.setInt(1, user.getId());
        ps.setDate(2, from);
        ps.setDate(3, to);
        ResultSet rs = ps.executeQuery();
        LinkedList<Sales> list = new LinkedList<>();
        rs.beforeFirst();
        while(rs.next()) {
            list.add(new Sales(rs.getInt("id"), rs.getDate("date"), 
                    User.load(rs.getInt("rep_id")), rs.getFloat("cash_tender")));
        }
        
        return list;
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

    public User getCustRep() {
        return rep;
    }

    public void setCustRep(User custRep) {
        this.rep = custRep;
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
                "INSERT INTO sales (date, rep_id, cash_tender) VALUES (?,?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setDate(1, this.date);
        ps.setInt(2, this.rep.getId());
        ps.setFloat(3, this.cashTender);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.first();
        this.id = rs.getInt(1);
    }
    
    private void update() throws SQLException {
        PreparedStatement ps = DB.connect().prepareStatement(
                "UPDATE sales SET date=?, rep_id=?, cash_tender=? WHERE id=?");
        ps.setDate(1, this.date);
        ps.setInt(2, this.rep.getId());
        ps.setFloat(3, this.cashTender);
        ps.setInt(4, this.id);
        ps.executeUpdate();
    }
    
    public static int getNextId() throws SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery(
                "SELECT id FROM sales ORDER BY id DESC");
        if(rs.first()) {
            return rs.getInt("id")+1;
        }else {
            return 2016;
        }
    }
    
    public static int transactionCount(User user, java.sql.Date date) throws SQLException {
        PreparedStatement ps = DB.connect().prepareStatement(
                "SELECT COUNT(id) AS 'count' FROM sales WHERE rep_id=? AND date=?");
        ps.setInt(1, user.getId());
        ps.setDate(2, date);
        ResultSet rs = ps.executeQuery();
        if(rs.first()) {
            return rs.getInt("count");
        }else {
            return -1;
        }
    }

    public User getRep() {
        return rep;
    }

    public void setRep(User rep) {
        this.rep = rep;
    }

    public float getCashTender() {
        return cashTender;
    }

    public void setCashTender(float cashTender) {
        this.cashTender = cashTender;
    }
    
    public float getTotalAmount() throws SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery(
               "SELECT SUM( (ppq*qty)-disc ) AS 'amount' "
                       + "FROM sales_item "
                       + "RIGHT JOIN sales ON sales.id=sales_item.sales_id "
                       + "WHERE sales.id = " + this.id );
        if(rs.first()) {
            return rs.getFloat("amount");
        }else {
            return 0f;
        }
    }
}
