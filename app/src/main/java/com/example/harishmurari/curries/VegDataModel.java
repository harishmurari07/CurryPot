package com.example.harishmurari.curries;

/**
 * Created by harishmurari on 6/5/2017.
 */

public class VegDataModel {


    String name;
    String price;
    int id_;
    int image;

    public VegDataModel(String name, String price, int id_, int image) {
        this.name = name;
        this.price = price;
        this.id_ = id_;
        this.image=image;
    }


    public String getName() {
        return name;
    }


    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }
}