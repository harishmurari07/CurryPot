package com.example.harishmurari.curries.model;

/**
 * Created by harishmurari on 6/5/2017.
 */

public class DataModel {

    String name;
    String price;
    int image;

    public DataModel(String name, String price, int image) {
        this.name = name;
        this.price = price;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() { return price; }

    public int getImage() {
        return image;
    }

}