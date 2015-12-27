package idi.jmotger.comandescambrer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database.DataBaseSQLite;

public class LoadProductsOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String type = getIntent().getExtras().getString("TYPE");

        DataBaseSQLite d = new DataBaseSQLite(this);
        SQLiteDatabase db = d.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM PRODUCT WHERE TYPE = ?", new String[]{type});

        while (c.moveToNext()) {
            Log.d("LOAD PRODUCTS", "Loading another product");
            byte[] image = c.getBlob(3);
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            ImageView v = (ImageView)findViewById(R.id.imageV);
            v.setImageBitmap(bmp);

        }

        switch(type) {
            case "FIRST":
                setTitle("Nova comanda - Primers plats");
                break;
            case "SECOND":
                setTitle("Nova comanda - Segons plats");
                break;
            case "DRINK":
                setTitle("Nova comanda - Begudes");
                break;
            case "DESSERT":
                setTitle("Nova comanda - Postres");
                break;
            default:
                break;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_firsts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
