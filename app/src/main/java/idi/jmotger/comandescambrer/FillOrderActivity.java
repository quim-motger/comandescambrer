package idi.jmotger.comandescambrer;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
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

public class FillOrderActivity extends AppCompatActivity {

    List<OrderLine> orderLines;
    ButtonTypeAdapter adap;

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fill_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Total = " + NewOrderActivity.currentOrder.getTotal() + "€");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        adap = new ButtonTypeAdapter(getApplicationContext());
        GridView gridView = (GridView) findViewById(R.id.gridButtons);
        gridView.setAdapter(adap);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Log.v("TAG", "Clicked");
                Intent intent = new Intent(getApplicationContext(), LoadProductsOrder.class);
                String type = adap.getItem(position);
                switch (type) {
                    case "PRIMERS":
                        intent.putExtra("TYPE", "FIRST");
                        break;
                    case "SEGONS":
                        intent.putExtra("TYPE", "SECOND");
                        break;
                    case "BEGUDES":
                        intent.putExtra("TYPE", "DRINK");
                        break;
                    case "POSTRES":
                        intent.putExtra("TYPE", "DESSERT");
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        });

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

        llista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FillOrderActivity.this);
                builder.setTitle("Eliminar producte " + orderLines.get(position).getProductName() + " de la comanda?");
                builder.setPositiveButton("Confirma", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NewOrderActivity.currentOrder.getOrderLines().remove(orderLines.get(pos).getProductName());
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
                return true;
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
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        builder.setPositiveButton("Modifica", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (input.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "No s'ha modificat la informació (introdueixi algun valor)", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    DataBaseSQLite d = new DataBaseSQLite(getApplicationContext());
                    SQLiteDatabase db = d.getReadableDatabase();

                    Cursor c = db.rawQuery("SELECT * FROM STOCK WHERE PRODUCT_NAME = ?", new String[]{currentOrderLine.getProductName()});
                    int n = Integer.parseInt(input.getText().toString());
                    if (n == 0)
                        NewOrderActivity.currentOrder.getOrderLines().remove(currentOrderLine.getProductName());
                    else {
                        if (c.moveToNext()) {
                            if (c.getInt(1) < n)
                                Toast.makeText(getApplicationContext(), "No hi ha suficients unitats del producte " + currentOrderLine.getProductName() + " (disponibles " + c.getInt(1) + ")", Toast.LENGTH_SHORT).show();
                            else {
                                NewOrderActivity.currentOrder.getOrderLines().get(currentOrderLine.getProductName()).setAmount(n);
                            }
                        }
                    }
                    reloadInfo();
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

            Cursor c = db.rawQuery("SELECT * FROM STOCK WHERE PRODUCT_NAME = ?", new String[] {ol.getProductName()});
            if (c.moveToNext()) {
                int units = c.getInt(1) - ol.getAmount();
                db.execSQL("UPDATE STOCK SET UNITS = ? WHERE PRODUCT_NAME = ?", new String[]{String.valueOf(units), ol.getProductName()});
            }
        }
        Log.d("SAVE_ORDER", "Registrament de dades satisfactori");
    }

    public void loadFirsts(View view) {
        Intent intent = new Intent(this, LoadProductsOrder.class);
        intent.putExtra("TYPE", "FIRST");
        startActivity(intent);
    }

    public void loadSeconds(View view) {
        Intent intent = new Intent(this, LoadProductsOrder.class);
        intent.putExtra("TYPE", "SECOND");
        startActivity(intent);
    }

    public void loadDrinks(View view) {
        Intent intent = new Intent(this, LoadProductsOrder.class);
        intent.putExtra("TYPE", "DRINK");
        startActivity(intent);
    }

    public void loadDesserts(View view) {
        Intent intent = new Intent(this, LoadProductsOrder.class);
        intent.putExtra("TYPE", "DESSERT");
        startActivity(intent);
    }

}
