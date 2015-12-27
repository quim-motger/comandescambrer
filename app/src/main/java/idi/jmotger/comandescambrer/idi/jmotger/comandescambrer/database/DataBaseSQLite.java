package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileInputStream;

/**
 * Created by jmotger on 27/12/15.
 */
public class DataBaseSQLite extends SQLiteOpenHelper {

    public DataBaseSQLite(Context context) {
        super(context, "COMANDESCAMBRER", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE ORDERS (ORDER_ID TEXT PRIMARY KEY, DATE TEXT, TIME TEXT, N_TABLE INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS PRODUCT (NAME TEXT, PRICE REAL, TYPE TEXT, IMAGE BLOB, PRIMARY KEY (NAME));");
        db.execSQL("CREATE TABLE IF NOT EXISTS LINE_ORDER (ORDER_ID INTEGER, PRODUCT_NAME TEXT, QTT INTEGER, PRIMARY KEY (ORDER_ID, PRODUCT_NAME) FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (ORDER_ID));");

        initDatabase();
    }

    private void initDatabase() {
        //Init products
        FileInputStream f = new FileInputStream("res/mipmap/");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
