package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import idi.jmotger.comandescambrer.R;

/**
 * Created by jmotger on 26/12/15.
 */
public class ProductRepository {

    HashMap<String, Product> products;

    public ProductRepository() {
        products = new HashMap<>();
    }

    public void addNewProduct(String name) {
        products.put(name, new Product(name));
    }

    public HashMap listProducts() {
        return this.products;
    }

    public void addStock(String name, int n) {
        Product p = products.get(name);
        if (p != null)
            p.addStock(n);
        else throw new IllegalStateException("No existeix el producte");
    }

}
