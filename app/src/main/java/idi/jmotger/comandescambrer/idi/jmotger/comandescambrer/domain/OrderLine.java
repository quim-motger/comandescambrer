package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain;

/**
 * Created by jmotger on 26/12/15.
 */
public class OrderLine {

    Product p;
    int amount;

    public OrderLine(Product p) {
        this.p = p;
        amount = 1;
    }

    public void incrAmount() {
        this.amount += 1;
    }

    public void setAmount(int n) {
        this.amount = n;
    }

    public Product getProduct() { return this.p;}

    public String getProductName() {
        return p.getName();
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
        return round((double)amount * p.getPrice(), 2);
    }

    @Override
    public String toString() {
        double d = getTotal();
        return p.getName() + ";" + "x " + amount + " = " + d + "€";
    }

}
