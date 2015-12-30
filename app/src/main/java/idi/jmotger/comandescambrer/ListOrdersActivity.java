package idi.jmotger.comandescambrer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database.DataBaseSQLite;
import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.Order;

public class ListOrdersActivity extends AppCompatActivity {

    List<Order> orders;
    OrderAdapter<Order> adapter;
    static String date1 = "";
    static String date2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_orders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(date1 + " - " + date2);

        ListView llista = (ListView) findViewById(R.id.llistaOrders);

        fillOrders(date1, date2);

        adapter = new OrderAdapter<>(this, orders);
        llista.setAdapter(adapter);

        llista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), OrderInfoReadDate.class);
                Order o = adapter.getItem(position);
                intent.putExtra("ID", o.getId());
                intent.putExtra("TOTAL", o.getTotal());
                intent.putExtra("DATE1", date1);
                intent.putExtra("DATE2", date2);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void fillOrders(String date1, String date2) {
        orders = new ArrayList<>();
        DataBaseSQLite dd = new DataBaseSQLite(getApplicationContext());
        SQLiteDatabase db = dd.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM ORDERS", new String[]{});
        double totalGlobal = 0;
        while (c.moveToNext()) {
            String date = c.getString(1);
            Calendar d1 = toDate(date1);
            Calendar d2 = toDate(date2);
            Calendar d = toDate(date);

            d1.set(Calendar.HOUR_OF_DAY, 0);
            d2.set(Calendar.HOUR_OF_DAY, 23);
            d.set(Calendar.HOUR_OF_DAY, 12);
            if (d.after(d1) && d.before(d2)) {
                Order o = new Order(c.getString(1), c.getString(2), c.getInt(3));
                String s = c.getString(0);
                Cursor cc = db.rawQuery("SELECT * FROM LINE_ORDER WHERE ORDER_ID = ?", new String[]{s});
                double total = 0;
                while (cc.moveToNext()) {
                    total += cc.getDouble(3);
                }
                totalGlobal += total;
                o.setTotal(total);
                o.setId(s);
                orders.add(o);
            }
        }
        setTitle("Total = " + round(totalGlobal,2) + "€");
    }

    protected Calendar toDate(String date) {
        Calendar c = Calendar.getInstance();
        String[] info = date.split("/");
        c.set(Calendar.YEAR, Integer.parseInt(info[2]));
        c.set(Calendar.MONTH, Integer.parseInt(info[1]) - 1);
        c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(info[0]));

        return c;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
