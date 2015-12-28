package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jmotger on 26/12/15.
 */
public class Order {

    String id;
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
        if (newLine) orderLines.put(p.getName(), new OrderLine(p.getName(), p.getPrice()));
        total += p.getPrice();
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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public double getTotal() {
        double total = 0;
        for (OrderLine ol : orderLines.values()) {
            total += ol.getTotal();
        }
        return round(total, 2);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setId(String id) {this.id = id;}

    public String getId(){ return this.id;}

    @Override
    public String toString() {
        double t = round(total, 2);
        return "Taula " + table + " (" + time + ");" + t + "€";
    }

}
