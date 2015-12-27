package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain;

import android.graphics.Bitmap;

/**
 * Created by jmotger on 26/12/15.
 */
public class Product {

    String name;
    String type;
    double price;
    byte[] model;

    public Product(String name, String type, double price, byte[] model) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.model = model;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int getId() {
        return this.name.hashCode();
    }

    public double getPrice() {
        return this.price;
    }

    public byte[] getImage() {
        return this.model;
    }

}
