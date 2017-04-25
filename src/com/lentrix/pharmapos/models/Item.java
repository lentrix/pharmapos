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
public class Item {
    private int id;
    private String name;
    private String generic;
    private String barCode;
    private String manufacturer;
    private float disc;

    public Item(int id, String name, String generic, String barCode, String manufacturer, float disc) {
        this.id = id;
        this.name = name;
        this.generic = generic;
        this.barCode = barCode;
        this.manufacturer = manufacturer;
        this.disc = disc;
    }
    
    public static Item load(int id) throws java.sql.SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery("SELECT * FROM item WHERE id=" + id);
        if(rs.first()) {
            return new Item(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("generic"),
                    rs.getString("bar_code"),
                    rs.getString("manufacturer"),
                    rs.getFloat("disc")
            );
        }else {
            throw new java.sql.SQLException("Item ID " + id + " not found.");
        }
    }
    
    public static Item load(String barCode) throws java.sql.SQLException {
        ResultSet rs = DB.connect().createStatement().executeQuery("SELECT * FROM item WHERE bar_code=" + barCode);
        if(rs.first()) {
            return new Item(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("generic"),
                    rs.getString("bar_code"),
                    rs.getString("manufacturer"),
                    rs.getFloat("disc")
            );
        }else {
            throw new java.sql.SQLException("Bar Code " + barCode + " not found.");
        }
    }
    
    public static LinkedList<Item> getItems(String nameFilter) throws SQLException {
        String whereClause = "";
        if(!nameFilter.isEmpty()) {
            whereClause = " WHERE name LIKE '%" + nameFilter + "%' OR generic LIKE '%" + nameFilter + "%'";
        }
        ResultSet rs = DB.connect().createStatement().executeQuery(
                "SELECT * FROM item " + whereClause + " ORDER BY name, generic");
        rs.beforeFirst();
        LinkedList<Item> list = new LinkedList<Item>();
        
        while(rs.next()) {
            list.add(new Item(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("generic"),
                    rs.getString("bar_code"),
                    rs.getString("manufacturer"),
                    rs.getFloat("disc")
            ));
        }
        return list;
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

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public float getDisc() {
        return disc;
    }

    public void setDisc(float disc) {
        this.disc = disc;
    }

    @Override
    public String toString() {
        return this.name;
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
                "INSERT INTO item (name, generic, bar_code, manufacturer, disc) "
                        + "VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, this.name);
        ps.setString(2, this.generic);
        ps.setString(3, this.barCode);
        ps.setString(4, this.manufacturer);
        ps.setFloat(5, this.disc);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.first();
        this.setId(rs.getInt(1));
    }
    
    private void update() throws SQLException {
        PreparedStatement ps = DB.connect().prepareStatement(
                "UPDATE item SET name=?, generic=?, bar_code=?, manufacturer=?, disc=? "
                        + "WHERE id=?");
        ps.setString(1, this.name);
        ps.setString(2, this.generic);
        ps.setString(3, this.barCode);
        ps.setString(4, this.manufacturer);
        ps.setFloat(5, this.disc);
        ps.setInt(6, this.id);
        ps.executeUpdate();
    }
}
