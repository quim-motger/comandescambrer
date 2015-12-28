package idi.jmotger.comandescambrer;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
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
import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.OrderLine;

public class OrderInfo extends AppCompatActivity {

    List<OrderLine> orderLines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Total = " + NewOrderActivity.currentOrder.getTotal() + "€");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderLines.size() == 0)
                    Toast.makeText(getApplicationContext(), "No hi ha cap línia a la comanda", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(getApplicationContext(), "Comanda guardada", Toast.LENGTH_SHORT).show();
                    saveOrder();
                    loadMain();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView llista = (ListView) findViewById(R.id.llista);
        orderLines = new ArrayList<>();
        for (OrderLine o : NewOrderActivity.currentOrder.getOrderLines().values()) {
            orderLines.add(o);
        }
        Collections.sort(orderLines, new Comparator<OrderLine>() {
            @Override
            public int compare(OrderLine lhs, OrderLine rhs) {
                return lhs.getProductName().compareTo(rhs.getProductName());
            }
        });
        OrderLineAdapter<OrderLine> adapter = new OrderLineAdapter<>(this, orderLines);
        llista.setAdapter(adapter);

        llista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prompt(orderLines.get(position));
            }
        });
    }

    OrderLine currentOrderLine;

    protected void prompt(OrderLine ol) {
        currentOrderLine = ol;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quantitat");
        final EditText input = new EditText(this);
        input.setText(String.valueOf(ol.getAmount()));
        input.setSelection(0, input.getText().length());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        builder.setPositiveButton("Modifica", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int n = Integer.parseInt(input.getText().toString());
                if (n == 0)
                    NewOrderActivity.currentOrder.getOrderLines().remove(currentOrderLine.getProductName());
                else {
                    NewOrderActivity.currentOrder.getOrderLines().get(currentOrderLine.getProductName()).setAmount(n);
                }
                reloadInfo();
            }
        });
        builder.setNegativeButton("Cancela", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    protected void loadMain() {
        Intent ret = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(ret);
    }

    protected void reloadInfo() {
        ListView llista = (ListView) findViewById(R.id.llista);
        setTitle("Total = " + NewOrderActivity.currentOrder.getTotal() + "€");
        orderLines = new ArrayList<>();
        for (OrderLine o : NewOrderActivity.currentOrder.getOrderLines().values()) {
            orderLines.add(o);
        }
        Collections.sort(orderLines, new Comparator<OrderLine>() {
            @Override
            public int compare(OrderLine lhs, OrderLine rhs) {
                return lhs.getProductName().compareTo(rhs.getProductName());
            }
        });
        OrderLineAdapter<OrderLine> adapter = new OrderLineAdapter<>(this, orderLines);
        llista.setAdapter(adapter);

        llista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prompt(orderLines.get(position));
            }
        });
    }

    protected void saveOrder() {
        Log.d("SAVE_ORDER", "Obtenim DB");
        DataBaseSQLite d = new DataBaseSQLite(getApplicationContext());
        SQLiteDatabase db = d.getWritableDatabase();

        Log.d("SAVE_ORDER", "Generem valors de ORDER");
        ContentValues values = new ContentValues();
        Random r = new Random();
        String id = NewOrderActivity.currentOrder.getTable() +
                "#" + NewOrderActivity.currentOrder.getDate() +
                "#" + NewOrderActivity.currentOrder.getTime() +
                "#" + r.nextInt(10);
        values.put("ORDER_ID", id);
        values.put("DATE", NewOrderActivity.currentOrder.getDate());
        values.put("TIME", NewOrderActivity.currentOrder.getTime());
        values.put("N_TABLE", NewOrderActivity.currentOrder.getTable());

        Log.d("SAVE_ORDER", "Guardem instància de ORDER");
        db.insert("ORDERS", null, values);
        Log.d("SAVE_ORDER", "Instància de ORDER guardada i creada satisfactòriament");

        Log.d("SAVE_ORDER", "Generem i guardem instàncies de LINE_ORDER");
        for (OrderLine ol : NewOrderActivity.currentOrder.getOrderLines().values()) {
            values = new ContentValues();
            values.put("ORDER_ID", id);
            values.put("PRODUCT_NAME", ol.getProductName());
            values.put("QTT", ol.getAmount());
            values.put("TOTAL", ol.getTotal());
            values.put("PREU_UNIT", ol.getPreuUnit());
            Log.d("SAVE_ORDER", "Guardem instància de " + ol.getProductName());
            db.insert("LINE_ORDER", null, values);
        }
        Log.d("SAVE_ORDER","Registrament de dades satisfactori");
    }

}
