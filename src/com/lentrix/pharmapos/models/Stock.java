/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.pharmapos.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 *
 * @author lentrix
 */
public class Stock {
    private int id;
    private Item item;
    private int quantity;
    private float purchaseAmount;
    private int remaining;
    private float price;
    private User updatedBy;
    private java.sql.Date date;
    private User stockedBy;
    private java.sql.Date updatedDate;

    public Stock(int id, Item item, int quantity, float purchaseAmount, int remaining, float price, java.sql.Date date) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.purchaseAmount = purchaseAmount;
        this.remaining = remaining;
        this.price = price;
        this.date = date;
    }
    
    public static Stock load(int id) throws SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery("SELECT * FROM stock WHERE id=" + id);
        if(rs.first()) {
            Stock s = new Stock(rs.getInt("id"), Item.load(rs.getInt("item_id")), rs.getInt("qty"), 
                    rs.getFloat("pch_amt"), rs.getInt("remaining"), rs.getFloat("price"),
                    rs.getDate("date")
            );
            s.setStockedBy(User.load(rs.getInt("stocked_by")));
            s.setUpdatedBy(User.load(rs.getInt("updated_by")));
            s.setUpdatedDate(rs.getDate("updated_date"));
            return s;
        }else {
            throw new SQLException("Stock # " + id + " not found.");
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(float purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return item.getName() + "[" + getRemaining() + "]";
    }
    
    public void save(User user) throws SQLException {
        if(this.id==-1) {
            create(user);
        }else {
            update(user);
        }
    }
    
    private void create(User user) throws SQLException {
        PreparedStatement ps = DB.connect().prepareStatement(
                "INSERT INTO stock (item_id, qty, pch_amt, remaining, price, "
                        + "stocked_by, date, updated_by, updated_date) "
                        + "VALUES (?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, this.item.getId());
        ps.setInt(2, this.quantity);
        ps.setFloat(3, this.purchaseAmount);
        ps.setInt(4, this.remaining);
        ps.setFloat(5, this.price);
        ps.setInt(6, user.getId());
        ps.setDate(7, this.getDate());
        ps.setInt(8, user.getId());
        ps.setDate(9, this.getDate());
        
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        rs.first();
        this.id = rs.getInt(1);
    }
    
    private void update(User user) throws SQLException {
        PreparedStatement ps = DB.connect().prepareStatement(
                "UPDATE stock SET item_id=?, qty=?, pch_amt=?, remaining=?, price=?, updated_by=?, updated_date=? "
                        + "WHERE id=?");
        ps.setInt(1, this.item.getId());
        ps.setInt(2, this.quantity);
        ps.setFloat(3, this.purchaseAmount);
        ps.setInt(4, this.remaining);
        ps.setFloat(5, this.price);
        ps.setInt(6, user.getId());
        ps.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
        ps.setInt(8, this.id);
        ps.executeUpdate();
    }
    
    public int shellOut(int qty) throws SQLException {
        int q = this.remaining<qty ? this.remaining : qty;
        
        this.remaining = this.remaining-q;
        PreparedStatement ps = DB.connect().prepareStatement(
                "UPDATE stock SET remaining=? WHERE id=?");
        ps.setInt(1, this.remaining);
        ps.setInt(2, this.id);
        ps.executeUpdate();
        return qty-q;
    }
    
    public static Stock findNextAvailableStock(int itemId) throws SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery(
                "SELECT id FROM stock WHERE item_id=" + itemId 
                        + " AND remaining>0 ORDER BY id LIMIT 0, 1");
        if(rs.first()) {
            
            return Stock.load(rs.getInt(1));
            
        }else {
            throw new SQLException("According to system records,\nthis item is out of stock."
                    + "\nPlease verify actual stock and restock if necessary.");
        }
    }
    
    public static Stock findNextAvailableStock2(int itemId) throws SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery(
                "SELECT id FROM stock WHERE item_id=" + itemId 
                        + " AND remaining>0 ORDER BY id LIMIT 1, 2");
        if(rs.first()) {
            
            return Stock.load(rs.getInt(1));
            
        }else {
            throw new SQLException("According to system records,\nthis item is out of stock."
                    + "\nPlease verify actual stock and restock if necessary.");
        }
    }
    
    public static LinkedList<Stock> search(String filter) throws SQLException {
        String whereClause = "";
        if(!filter.isEmpty()) {
            whereClause = " WHERE i.name LIKE '%" + filter + "%' OR i.generic LIKE '%"
                    + filter + "%'";
        }
        
        PreparedStatement ps = DB.connect().prepareStatement(
                "SELECT s.id FROM stock s "
                        + "LEFT JOIN item i ON i.id=s.item_id "
                        + whereClause + " ORDER BY s.date DESC, i.name ASC");
        
        ResultSet rs = ps.executeQuery();
        rs.beforeFirst();
        LinkedList<Stock> stocks = new LinkedList<Stock>();
        while(rs.next()) {
            stocks.add(Stock.load(rs.getInt("id")));
        }
        return stocks;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public User getStockedBy() {
        return stockedBy;
    }

    public void setStockedBy(User stockedBy) {
        this.stockedBy = stockedBy;
    }

    public java.sql.Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(java.sql.Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    public static Stock barCodeSearch(String barCode) throws SQLException {
        PreparedStatement ps = DB.connect().prepareStatement(
                "SELECT i.bar_code, s.* FROM stock s "
                        + "LEFT JOIN item i ON i.id=s.item_id "
                        + "WHERE i.bar_code=? AND s.remaining>0 "
                        + "ORDER BY date ASC LIMIT 0, 1");
        ps.setString(1, barCode);
        ResultSet rs = ps.executeQuery();
        if(rs.first()) {
            return new Stock(
                    rs.getInt("id"), Item.load(rs.getInt("item_id")), rs.getInt("qty"), 
                    rs.getFloat("pch_amt"), rs.getInt("remaining"), rs.getFloat("price"),
                    rs.getDate("date")
            );
        }else {
            throw new SQLException("According to system records,\nthis item is out of stock."
                    + "\nPlease verify actual stock and restock if necessary.");
        }
    }
}
