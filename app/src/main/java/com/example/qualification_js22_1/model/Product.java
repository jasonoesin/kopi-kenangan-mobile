package com.example.qualification_js22_1.model;

import java.io.Serializable;

public class Product implements Serializable
{
    private int id;
    private String name;
    private int qty;

    public Product(int id, String name, int price, int image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Product(int id, String name, int price, int qty, int image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    private int price;
    private int image;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
