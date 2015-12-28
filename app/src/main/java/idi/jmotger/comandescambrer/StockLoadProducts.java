package idi.jmotger.comandescambrer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database.DataBaseSQLite;

public class StockLoadProducts extends AppCompatActivity {

    StockAdapter adap;
    Toast t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String type = getIntent().getExtras().getString("TYPE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_load_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adap = new StockAdapter(this, type);
        GridView gridView = (GridView) findViewById(R.id.gridProducts);
        gridView.setAdapter(adap);
        t = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                editStock(adap.getItem(position).getProduct().getName());
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

    protected void editStock(String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quantitat del producte " + name);
        final EditText input = new EditText(this);

        DataBaseSQLite d = new DataBaseSQLite(getApplicationContext());
        SQLiteDatabase db = d.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM STOCK WHERE PRODUCT_NAME = ?", new String[]{name});
        if (c.moveToNext()) {
            input.setText(String.valueOf(c.getInt(1)));
        }

        input.setSelection(0, input.getText().length());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        builder.setPositiveButton("Modifica", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (input.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "No s'ha modificat la informació (introdueixi algun valor)", Toast.LENGTH_SHORT).show();
                } else {
                    //TODO edit stock value
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
        input.setSelection(0, input.getText().length());
    }

}
