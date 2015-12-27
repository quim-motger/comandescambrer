package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import idi.jmotger.comandescambrer.R;
import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database.DataBaseSQLite;

/**
 * Created by jmotger on 26/12/15.
 */
public class ProductRepository {

    private List<Product> products;

    public ProductRepository(Context context, String type) {

        products = new ArrayList<>();

        DataBaseSQLite d = new DataBaseSQLite(context);
        SQLiteDatabase db = d.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM PRODUCT WHERE TYPE = ? ORDER BY NAME", new String[]{type});

        while (c.moveToNext()) {
            Log.d("LOAD PRODUCTS", "Loading another product: " + c.getString(0) + "//" + c.getDouble(1) + "//" + c.getString(2));
            byte[] image = c.getBlob(3);
            if (image == null) Log.e("LOAD PRODUCTS", "Product image not loaded correctly" + image.length);
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);

            products.add(new Product(c.getString(0), c.getString(2), c.getDouble(1), image));

        }

    }

    public Product getProduct(int i) {
        return products.get(i);
    }

    public int getCount() {
        return products.size();
    }

}
