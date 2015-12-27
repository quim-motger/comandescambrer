package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by jmotger on 27/12/15.
 */
public class DataBaseSQLite extends SQLiteOpenHelper {

    public DataBaseSQLite(Context context) {
        super(context, "COMANDESCAMBRER", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS ORDERS (ORDER_ID TEXT PRIMARY KEY, DATE TEXT, TIME TEXT, N_TABLE INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS PRODUCT (NAME TEXT, PRICE REAL, TYPE TEXT, IMAGE BLOB, PRIMARY KEY (NAME));");
        db.execSQL("CREATE TABLE IF NOT EXISTS LINE_ORDER (ORDER_ID INTEGER, PRODUCT_NAME TEXT, QTT INTEGER, PRIMARY KEY (ORDER_ID, PRODUCT_NAME) FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (ORDER_ID));");

        //Init products
        try {
            Log.d("DB", "Inserting first product");
            FileInputStream f = new FileInputStream("agua.jpg");
            byte[] aigua = new byte[f.available()];
            f.read(aigua);
            Log.d("DB", "Image loaded");

            ContentValues values = new ContentValues();
            values.put("NAME", "Aigua");
            values.put("PRICE", "1.0");
            values.put("TYPE", "DRINK");
            values.put("IMAGE", aigua);

            db.insert("PRODUCT", null, values);
            Log.d("DB", "Inserted in database");
            f.close();

        }
        catch (IOException e) {
            Log.e("DB", "Error initiating database");
        }

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
