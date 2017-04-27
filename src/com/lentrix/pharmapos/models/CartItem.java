/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.pharmapos.models;

/**
 *
 * @author lentrix
 */
public class CartItem {
    private Stock stock;
    private int qty;
    private boolean discounted;

    public CartItem(Stock stock, int qty, boolean discount) {
        this.stock = stock;
        this.qty = qty;
        this.discounted = discounted;
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
    
    public void increaseQty() {
        
    }
    
    public void decreaseQty() {
        
    }

    public boolean isDiscounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }
    
    public float getDiscountAmount() {
        if(discounted) {
            float amount = this.qty*stock.getPrice();
            return amount * stock.getItem().getDisc();
        }else {
            return 0.0f;
        }
    }
    
    public float getFinalAmount() {
        float amount = this.qty*stock.getPrice();
        return amount - getDiscountAmount();
    }
}
