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

    public void decrAmount() {
        this.amount -= 1;
    }

    public String getProductName() {
        return p.getName();
    }

    public int getAmount() {
        return amount;
    }

    public double getTotal() {
        return (double)amount * p.getPrice();
    }

    @Override
    public String toString() {
        double d = getTotal();
        return p.getName() + ";" + "x " + amount + " = " + d + "€";
    }

}
