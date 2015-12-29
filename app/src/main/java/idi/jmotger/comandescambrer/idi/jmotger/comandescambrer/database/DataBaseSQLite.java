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
        db.execSQL("CREATE TABLE IF NOT EXISTS LINE_ORDER (ORDER_ID INTEGER, PRODUCT_NAME TEXT, QTT INTEGER, TOTAL REAL, PREU_UNIT REAL, PRIMARY KEY (ORDER_ID, PRODUCT_NAME));");
        db.execSQL("CREATE TABLE IF NOT EXISTS STOCK(PRODUCT_NAME TEXT, UNITS INTEGER, PRIMARY KEY (PRODUCT_NAME));");

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
        values.put("NAME", "Bacallà al pil pil");
        values.put("PRICE", "8.90");
        values.put("TYPE", "SECOND");
        values.put("IMAGE", bacalla);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Bacallà inserted in database");

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
        values.put("NAME", "Pastís de formatge");
        values.put("PRICE", "3.50");
        values.put("TYPE", "DESSERT");
        values.put("IMAGE", pastis);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Pastís inserted in database");

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
        values.put("NAME", "Sorbet de llimona");
        values.put("PRICE", "3.20");
        values.put("TYPE", "DESSERT");
        values.put("IMAGE", sorbete);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Sorbete inserted in database");

        //ENTREMESOS
        Log.d("DB", "Inserting entremesos");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.entremesos);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] entremesos = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Entremesos");
        values.put("PRICE", "6.10");
        values.put("TYPE", "FIRST");
        values.put("IMAGE", entremesos);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Entremesos inserted in database");

        //LASAGNA
        Log.d("DB", "Inserting lassanya");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.lasagna);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] lasagna = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Lasagna de carn");
        values.put("PRICE", "7.20");
        values.put("TYPE", "FIRST");
        values.put("IMAGE", lasagna);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Lasagna inserted in database");

        //MUSCLOS
        Log.d("DB", "Inserting musclos");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.musclos);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] musclos = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Musclos a la marinera");
        values.put("PRICE", "8.15");
        values.put("TYPE", "FIRST");
        values.put("IMAGE", musclos);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Musclos inserted in database");

        //RISOTTO
        Log.d("DB", "Inserting risotto");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.risotto);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] risotto = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Risotto");
        values.put("PRICE", "7.80");
        values.put("TYPE", "FIRST");
        values.put("IMAGE", risotto);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Risotto inserted in database");

        //ALBERGINIA
        Log.d("DB", "Inserting alberginia");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.alberginia);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] alberginia = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Albergínia farcida");
        values.put("PRICE", "6.20");
        values.put("TYPE", "SECOND");
        values.put("IMAGE", alberginia);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Alberginia inserted in database");

        //CAVA
        Log.d("DB", "Inserting cava");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.cava);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] cava = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Cava");
        values.put("PRICE", "5.25");
        values.put("TYPE", "DRINK");
        values.put("IMAGE", cava);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Cava inserted in database");

        //COCACOLA
        Log.d("DB", "Inserting cocacola");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.cocacola);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] cocacola = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Coca-cola");
        values.put("PRICE", "1.70");
        values.put("TYPE", "DRINK");
        values.put("IMAGE", cocacola);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Cocacola inserted in database");

        //CREPE
        Log.d("DB", "Inserting crepe");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.crepe);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] crepe = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Crepe de xocolata");
        values.put("PRICE", "4.10");
        values.put("TYPE", "DESSERT");
        values.put("IMAGE", crepe);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Crepe inserted in database");

        //MACEDONIA
        Log.d("DB", "Inserting macedonia");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.macedonia);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] macedonia = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Macedònia");
        values.put("PRICE", "3.00");
        values.put("TYPE", "DESSERT");
        values.put("IMAGE", macedonia);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Macedonia inserted in database");

        //SIPIA
        Log.d("DB", "Inserting sipia");
        res = this.context.getResources();
        drawable = res.getDrawable(R.drawable.sipia);
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] sipia = stream.toByteArray();
        Log.d("DB", "Image loaded");

        values = new ContentValues();
        values.put("NAME", "Sípia amb patates");
        values.put("PRICE", "8.80");
        values.put("TYPE", "SECOND");
        values.put("IMAGE", sipia);

        db.insert("PRODUCT", null, values);
        Log.d("DB", "Sipia inserted in database");


        //STOCK
        values = new ContentValues();
        values.put("PRODUCT_NAME", "Aigua");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Vi");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Amanida");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Bacallà al pil pil");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Entrecot");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Macarrons a la bolonyesa");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Pastís de formatge");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Sorbet de llimona");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Entremesos");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Lasagna de carn");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Musclos a la marinera");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Risotto");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Albergínia farcida");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Cava");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Coca-cola");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Crepe de xocolata");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Macedònia");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        values = new ContentValues();
        values.put("PRODUCT_NAME", "Sípia amb patates");
        values.put("UNITS", 10);
        db.insert("STOCK", null, values);

        Log.d("DB", "EXITO");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
