package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jmotger on 26/12/15.
 */
public class Order {

    Calendar date;
    int table;
    double total;
    HashMap<Product, OrderLine> orderLines;

    public Order(Calendar date, int table) {
        this.date = date;
        this.table = table;
        this.total = 0.0;
        orderLines = new HashMap<>();
    }

    public void addProduct(Product p) {
        //TODO add new product to order
    }

}
