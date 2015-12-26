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
    HashMap<String, OrderLine> orderLines;

    public Order(Calendar date, int table) {
        this.date = date;
        this.table = table;
    }

    public void addProduct(String product) {
        //TODO add new product to order
    }

}
