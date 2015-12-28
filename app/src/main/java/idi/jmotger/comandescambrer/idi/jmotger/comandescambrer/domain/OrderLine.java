package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain;

import android.util.Log;

/**
 * Created by jmotger on 26/12/15.
 */
public class OrderLine {

    String p;
    int amount;
    double total;
    double preu_unit;

    public OrderLine(String p, double preu_unit) {
        this.p = p;
        this.amount = 1;
        this.total = preu_unit;
        this.preu_unit = preu_unit;
    }

    public void incrAmount() {
        Log.d("TAG", "INCR" );
        this.amount += 1;
        this.total += preu_unit;
    }

    public void setAmount(int n) {
        this.amount = n;
        this.total = preu_unit*(double)amount;
    }

    public String getProductName() {
        return p;
    }

    public int getAmount() {
        return amount;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public double getTotal() {
        return round(total, 2);
    }

    @Override
    public String toString() {
        double d = getTotal();
        return p + ";" + "x " + amount + " = " + d + "€";
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Double getPreuUnit() {
        return preu_unit;
    }

}
