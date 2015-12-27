package idi.jmotger.comandescambrer;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database.DataBaseSQLite;
import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.Order;
import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.OrderLine;

public class LoadProductsOrder extends AppCompatActivity {

    ProductAdapter adap;
    Order o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        o = NewOrderActivity.currentOrder;
        String type = getIntent().getExtras().getString("TYPE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_firsts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adap = new ProductAdapter(this, type);
        GridView gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter(adap);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                o.addProduct(adap.getItem(position));
                Toast.makeText(getApplicationContext(), "Afegit " + adap.getItem(position).getName() + " a la comanda", Toast.LENGTH_SHORT).show();
            }
        });

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
    }

}
