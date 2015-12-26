package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain;

/**
 * Created by jmotger on 26/12/15.
 */
public class Product {

    String name;
    int stock;

    public Product(String name) {
        this.name = name;
    }

    public void addStock(int n) {
        this.stock += n;
    }



}
