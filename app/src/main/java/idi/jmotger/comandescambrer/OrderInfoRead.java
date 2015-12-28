package idi.jmotger.comandescambrer;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database.DataBaseSQLite;
import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.Order;
import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.OrderLine;
import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.Product;

public class OrderInfoRead extends AppCompatActivity {

    List<OrderLine> orderLines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id = getIntent().getExtras().getString("ID");
        setContentView(R.layout.activity_order_info_read);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView llista = (ListView) findViewById(R.id.llistaRead);

        DataBaseSQLite dd = new DataBaseSQLite(getApplicationContext());
        SQLiteDatabase db = dd.getReadableDatabase();
        Cursor cc = db.rawQuery("SELECT * FROM LINE_ORDER WHERE ORDER_ID = ?", new String[]{id} );
        orderLines = new ArrayList<>();
        double totalGlobal = 0.;
        while (cc.moveToNext()) {
            OrderLine ol = new OrderLine(cc.getString(1), cc.getDouble(4));
            ol.setAmount(cc.getInt(2));
            ol.setTotal(cc.getDouble(3));
            totalGlobal += cc.getDouble(3);
            orderLines.add(ol);
        }
        setTitle("Total comanda = "+ totalGlobal + "€");


        Collections.sort(orderLines, new Comparator<OrderLine>() {
            @Override
            public int compare(OrderLine lhs, OrderLine rhs) {
                return lhs.getProductName().compareTo(rhs.getProductName());
            }
        });
        OrderLineAdapter<OrderLine> adapter = new OrderLineAdapter<>(this, orderLines);
        llista.setAdapter(adapter);

    }

}
