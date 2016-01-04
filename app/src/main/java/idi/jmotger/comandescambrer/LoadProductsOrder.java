package idi.jmotger.comandescambrer;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database.DataBaseSQLite;
import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.Order;
import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.OrderLine;

public class LoadProductsOrder extends AppCompatActivity {

    ProductAdapter adap;
    Toast t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String type = getIntent().getExtras().getString("TYPE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_firsts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adap = new ProductAdapter(this, type);
        GridView gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter(adap);
        t = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                int qtt = 0;
                for (OrderLine ol : NewOrderActivity.currentOrder.getOrderLines().values()) {
                    if (ol.getProductName().equals(adap.getItem(position).getName())) {
                        qtt = ol.getAmount();
                    }
                }

                DataBaseSQLite d = new DataBaseSQLite(getApplicationContext());
                SQLiteDatabase db = d.getReadableDatabase();

                Cursor c = db.rawQuery("SELECT * FROM STOCK WHERE PRODUCT_NAME = ?", new String[]{adap.getItem(position).getName()});
                if (c.moveToNext()) {
                    if (c.getInt(1) < qtt + 1) {
                        t.setText("No queden més unitats del producte " + adap.getItem(position).getName());
                        t.show();
                    }
                    else {
                        NewOrderActivity.currentOrder.addProduct(adap.getItem(position));
                        t.setText("Hi ha " + NewOrderActivity.currentOrder.getOrderLines().get(adap.getItem(position).getName()).getAmount() +
                                " unitat/s del producte " + adap.getItem(position).getName() +
                                " a la comanda");
                        t.show();
                    }
                }
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoadProductsOrder.this);
                final int pos = position;
                builder.setTitle(adap.getItem(position).getName());
                final EditText input = new EditText(LoadProductsOrder.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);
                builder.setPositiveButton("Afegeix", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (input.getText().toString().equals("")) {
                            Toast.makeText(getApplicationContext(), "No s'ha afegit cap producte (introdueixi algun valor)", Toast.LENGTH_SHORT).show();
                        } else {

                            int qtt = 0;
                            for (OrderLine ol : NewOrderActivity.currentOrder.getOrderLines().values()) {
                                if (ol.getProductName().equals(adap.getItem(pos).getName())) {
                                    qtt = ol.getAmount();
                                }
                            }

                            DataBaseSQLite d = new DataBaseSQLite(getApplicationContext());
                            SQLiteDatabase db = d.getReadableDatabase();

                            Cursor c = db.rawQuery("SELECT * FROM STOCK WHERE PRODUCT_NAME = ?", new String[]{adap.getItem(pos).getName()});
                            if (c.moveToNext()) {
                                int q = Integer.parseInt(input.getText().toString());
                                if (c.getInt(1) < qtt + q) {
                                    t.setText("No queden " + q + " unitats del producte " + adap.getItem(pos).getName() + " (només " + (c.getInt(1) - qtt) + " més disponibles)");
                                    t.show();
                                }
                                else {
                                    int qq = q;
                                    while (qq > 0) {
                                        NewOrderActivity.currentOrder.addProduct(adap.getItem(pos));
                                        --qq;
                                    }
                                    if (qq > 1) t.setText("S'ha afegit 1 unitat del producte " + adap.getItem(pos).getName());
                                    else t.setText("S'han afegit " + q + " unitats del producte " + adap.getItem(pos).getName());
                                    t.show();
                                }
                            }
                        }
                    }
                });
                builder.setNegativeButton("Cancela", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return false;
            }
        });

        switch(type) {
            case "FIRST":
                setTitle("Primers");
                break;
            case "SECOND":
                setTitle("Segons");
                break;
            case "DRINK":
                setTitle("Begudes");
                break;
            case "DESSERT":
                setTitle("Postres");
                break;
            default:
                break;
        }
    }

}
