package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain;

import android.graphics.Bitmap;

/**
 * Created by jmotger on 26/12/15.
 */
public class Product {

    String name;
    String type;
    double price;
    Bitmap mp;

    public Product(String name, String type, double price, Bitmap mp) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.mp = mp;
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

    public Bitmap getImage() {
        return this.mp;
    }

}
