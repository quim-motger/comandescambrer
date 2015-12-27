package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jmotger on 26/12/15.
 */
public class Order {

    String date;
    String time;
    int table;
    double total;
    HashMap<Product, OrderLine> orderLines;

    public Order(String date, String time, int table) {
        this.date = date;
        this.time = time;
        this.table = table;
        this.total = 0.0;
        orderLines = new HashMap<>();
    }

    public void addProduct(Product p) {
        if (!orderLines.containsKey(p))
            orderLines.put(p, new OrderLine(p));
        else orderLines.get(p).incrAmount();
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getTable() {
        return table;
    }

}
