package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database.DataBaseSQLite;

/**
 * Created by jmotger on 28/12/15.
 */
public class StockRepository {

    private List<Stock> products;

    public StockRepository(Context context, String type) {

        products = new ArrayList<>();

        DataBaseSQLite d = new DataBaseSQLite(context);
        SQLiteDatabase db = d.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM PRODUCT WHERE TYPE = ? ORDER BY NAME", new String[]{type});

        while (c.moveToNext()) {
            Cursor cc = db.rawQuery("SELECT * FROM STOCK WHERE PRODUCT_NAME = ?", new String[]{c.getString(0)});
            if (cc.moveToNext()) {
                Cursor ccc = db.rawQuery("SELECT * FROM PRODUCT WHERE NAME = ?", new String[]{cc.getString(0)});
                if (ccc.moveToNext()) {
                    Product p = new Product(ccc.getString(0), ccc.getString(2), ccc.getDouble(1), ccc.getBlob(3));
                    products.add(new Stock(p, cc.getInt(1)));
                }
            }

        }

    }

    public Stock getProduct(int i) {
        return products.get(i);
    }

    public int getCount() {
        return products.size();
    }
}
