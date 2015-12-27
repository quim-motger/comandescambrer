package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import idi.jmotger.comandescambrer.R;

/**
 * Created by jmotger on 27/12/15.
 */
public class DataBaseSQLite extends SQLiteOpenHelper {

    Context context;

    public DataBaseSQLite(Context context) {
        super(context, "COMANDESCAMBRER", null, 3);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS ORDERS (ORDER_ID TEXT PRIMARY KEY, DATE TEXT, TIME TEXT, N_TABLE INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS PRODUCT (NAME TEXT, PRICE REAL, TYPE TEXT, IMAGE BLOB, PRIMARY KEY (NAME));");
        db.execSQL("CREATE TABLE IF NOT EXISTS LINE_ORDER (ORDER_ID INTEGER, PRODUCT_NAME TEXT, QTT INTEGER, PRIMARY KEY (ORDER_ID, PRODUCT_NAME) FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (ORDER_ID));");

        //AIGUA
        Log.d("DB", "Inserting aigua");
        Resources res = this.context.getResources();
        Drawable drawable = res.getDrawable(R.drawable.agua);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] aigua = stream.toByteArray();
        Log.d("DB", "Image loaded");

        ContentValues values = new ContentValues();
        values.put("NAME", "Aigua");
        values.put("PRICE", "1.0");
        values.put("TYPE", "DRINK");
        values.put("IMAGE", aigua);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Aigua nserted in database");

        //VI
        Log.d("DB", "Inserting vi");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.vi);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] vi = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Vi");
        values.put("PRICE", "2.50");
        values.put("TYPE", "DRINK");
        values.put("IMAGE", vi);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Vi inserted in database");

        //AMANIDA
        Log.d("DB", "Inserting amanida");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.amanida);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] amanida = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Amanida");
        values.put("PRICE", "5.00");
        values.put("TYPE", "FIRST");
        values.put("IMAGE", amanida);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Amanida inserted in database");

        //BACALLA
        Log.d("DB", "Inserting bacalla");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.bacalla);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bacalla = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Bacalla al pil pil");
        values.put("PRICE", "8.90");
        values.put("TYPE", "SECOND");
        values.put("IMAGE", bacalla);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Bacalla inserted in database");

        //ENTRECOT
        Log.d("DB", "Inserting entrecot");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.entrecot);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] entrecot = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Entrecot");
        values.put("PRICE", "9.90");
        values.put("TYPE", "SECOND");
        values.put("IMAGE", entrecot);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Entrecot inserted in database");

        //MACARRONS
        Log.d("DB", "Inserting macarrons");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.macarrons);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] macarrons = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Macarrons a la bolonyesa");
        values.put("PRICE", "7.60");
        values.put("TYPE", "FIRST");
        values.put("IMAGE", macarrons);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Macarrons inserted in database");

        //PASTIS FORMATGE
        Log.d("DB", "Inserting pastis formatge");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.pastisformatge);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] pastis = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Pastis de formatge");
        values.put("PRICE", "3.50");
        values.put("TYPE", "DESSERT");
        values.put("IMAGE", pastis);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Pastis inserted in database");

        //SORBETE
        Log.d("DB", "Inserting sorbete");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.sorbete);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] sorbete = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Sorbete de llimona");
        values.put("PRICE", "3.20");
        values.put("TYPE", "DESSERT");
        values.put("IMAGE", sorbete);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Sorbete inserted in database");


    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
