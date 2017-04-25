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
public class VolumePrice {
    private int id;
    private Stock stock;
    private int quantity;
    private float price;
    private String unit;

    public VolumePrice(int id, Stock stock, int quantity, float price, String unit) {
        this.id = id;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
        this.unit = unit;
    }
    
    public static VolumePrice load(int id) throws SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery("SELECT * FROM volume_price WHERE id=" + id);
        if(rs.first()) {
            return new VolumePrice(
                    rs.getInt("id"),
                    Stock.load(rs.getInt("stock_id")),
                    rs.getInt("qty"),
                    rs.getFloat("price"),
                    rs.getString("unit")
            );
        }else {
            throw new SQLException("Volume price ID# " + id + " not found.");
        }
    }
    
    public static LinkedList<VolumePrice> list(int stockId) throws SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery("SELECT * FROM volume_price WHERE stock_id=" + stockId);
        LinkedList<VolumePrice> prices = new LinkedList<VolumePrice>();
        rs.beforeFirst();
        while(rs.next()) {
            prices.add(
                    new VolumePrice(
                            rs.getInt("id"),
                            Stock.load(rs.getInt("stock_id")),
                            rs.getInt("qty"),
                            rs.getFloat("price"),
                            rs.getString("unit")
                    )
            );
            return prices;
        }
        return prices;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
                "INSERT INTO volume_price (stock_id, qty, price, unit) "
                        + "VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, this.stock.getId());
        ps.setInt(2, this.quantity);
        ps.setFloat(3, this.price);
        ps.setString(4, this.unit);
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        rs.first();
        this.id = rs.getInt(1);
    }
    
    private void update() throws SQLException {
        PreparedStatement ps = DB.connect().prepareStatement(
                "UPDATE volume_price SET stock_id=?, qty=?, price=?, unit=? "
                        + "WHERE id=?");
        ps.setInt(1, this.stock.getId());
        ps.setInt(2, this.quantity);
        ps.setFloat(3, this.price);
        ps.setString(4, this.unit);
        ps.setInt(5, this.id);
        ps.executeUpdate();
    }
}
