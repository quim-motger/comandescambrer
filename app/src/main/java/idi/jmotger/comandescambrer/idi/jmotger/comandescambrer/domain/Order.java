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
    HashMap<String, OrderLine> orderLines;

    public Order(String date, String time, int table) {
        this.date = date;
        this.time = time;
        this.table = table;
        this.total = 0.0;
        orderLines = new HashMap<>();
    }

    public void addProduct(Product p) {
        Boolean newLine = true;
        for (OrderLine ol : orderLines.values()) {
            if (ol.getProductName().equals(p.getName())) {
                newLine = false;
                ol.incrAmount();
            }
        }
        if (newLine) orderLines.put(p.getName(), new OrderLine(p));
    }

    public HashMap<String, OrderLine> getOrderLines() {
        return orderLines;
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

    public double getTotal() {
        double total = 0;
        for (OrderLine ol : orderLines.values()) {
            total += ol.getTotal();
        }
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Taula " + table + " (" + time + ");" + total + "€";
    }

}
