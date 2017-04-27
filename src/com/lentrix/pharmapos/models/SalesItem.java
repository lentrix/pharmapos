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
public class SalesItem {
    private int id;
    private Sales sales;
    private Stock stock;
    private int qty;
    private float pricePerQuantity;
    private float discount;

    public SalesItem(int id, Sales sales, Stock stock, int qty, float pricePerQuantity, float discount) {
        this.id = id;
        this.sales = sales;
        this.stock = stock;
        this.qty = qty;
        this.pricePerQuantity = pricePerQuantity;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sales getSales() {
        return sales;
    }

    public void setSales(Sales sales) {
        this.sales = sales;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getPricePerQuantity() {
        return pricePerQuantity;
    }

    public void setPricePerQuantity(float pricePerQuantity) {
        this.pricePerQuantity = pricePerQuantity;
    }

    @Override
    public String toString() {
        return "Sales Item: " + this.getStock().getItem().getName();
    }
    
    public static SalesItem load(int id) throws SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery(
                "SELECT * FROM sales_item WHERE id=" + id);
        if(rs.first()) {
            return new SalesItem(
                    rs.getInt("id"),
                    Sales.load(rs.getInt("sales_id")),
                    Stock.load(rs.getInt("stock_id")),
                    rs.getInt("qty"),
                    rs.getFloat("ppq"),
                    rs.getFloat("disc")
            );
        }else {
            throw new SQLException("Sales Item ID# " + id + " not found.");
        }
    }
    
    public static LinkedList<SalesItem> list(int salesId) throws SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery(
                "SELECT * FROM sales_item WHERE sales_id=" + salesId);
        rs.beforeFirst();
        LinkedList<SalesItem> salesItems = new LinkedList<SalesItem>();
        
        while(rs.next()) {
            salesItems.add(new SalesItem(
                    rs.getInt("id"),
                    Sales.load(rs.getInt("sales_id")),
                    Stock.load(rs.getInt("stock_id")),
                    rs.getInt("qty"),
                    rs.getFloat("ppq"),
                    rs.getFloat("disc")
            ));
        }
        return salesItems;
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
                "INSERT INTO sales_item (sales_id, stock_id, qty, ppq, disc) "
                        + "VALUES (?,?,?,?,?) ", PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, this.sales.getId());
        ps.setInt(2, this.stock.getId());
        ps.setInt(3, this.qty);
        ps.setFloat(4, this.pricePerQuantity);
        ps.setFloat(5, this.discount);
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        rs.first();
        this.id = rs.getInt(1);
    }
    
    private void update() throws SQLException {
        PreparedStatement ps = DB.connect().prepareStatement(
                "UPDATE sales_item SET sales_id=?, stock_id=?, qty=?, ppq=?, disc=? "
                        + "WHERE id=?");
        ps.setInt(1, this.sales.getId());
        ps.setInt(2, this.stock.getId());
        ps.setInt(3, this.qty);
        ps.setFloat(4, this.pricePerQuantity);
        ps.setFloat(5, this.discount);
        ps.setInt(6, this.id);
        ps.executeUpdate();
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
    
    public float getFinalAmount() {
        return (pricePerQuantity*qty)-discount;
    }
}
