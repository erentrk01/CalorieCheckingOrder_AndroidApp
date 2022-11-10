package com.erentarak.hw1;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int img_Id;
    private  int calorie;
    private double price;

    public Product(String name, int img_Id, int calorie,double price) {
        this.name = name;
        this.img_Id = img_Id;
        this.calorie = calorie;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getImg_Id() {
        return img_Id;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg_Id(int img_Id) {
        this.img_Id = img_Id;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
