package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain;

/**
 * Created by jmotger on 28/12/15.
 */
public class Stock {

    private Product product;
    private int     units;

    public Stock(Product p, int units) {
        this.product = p;
        this.units = units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getUnits() {
        return units;
    }

    public Product getProduct() {
        return product;
    }

    public long getId() {
        return product.hashCode();
    }

    public byte[] getImage() {
        return product.getImage();
    }

}
