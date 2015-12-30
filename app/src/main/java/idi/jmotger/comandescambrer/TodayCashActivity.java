package idi.jmotger.comandescambrer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database.DataBaseSQLite;
import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.Order;

public class TodayCashActivity extends AppCompatActivity {

    List<Order> orders;
    OrderAdapter<Order> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_cash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView llista = (ListView) findViewById(R.id.llistaTodayCash);

        DataBaseSQLite dd = new DataBaseSQLite(getApplicationContext());
        SQLiteDatabase db = dd.getReadableDatabase();
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        String d;
        if (day < 10) d = "0" + day;
        else d = String.valueOf(day);
        String m;
        if (month < 10) m = "0" + month;
        else m = String.valueOf(month);
        String date = d + "/" + m + "/" + year;

        Cursor cc = db.rawQuery("SELECT * FROM ORDERS WHERE DATE = ?", new String[]{date} );
        orders = new ArrayList<>();
        double totalGlobal = 0;
        while (cc.moveToNext()) {
            Order o = new Order(cc.getString(1), cc.getString(2), cc.getInt(3));
            String id = cc.getString(0);
            o.setId(id);
            Cursor ccc = db.rawQuery("SELECT * FROM LINE_ORDER WHERE ORDER_ID = ?", new String[]{id});
            double total = 0;
            while (ccc.moveToNext()) {
                total += ccc.getDouble(3);
            }
            totalGlobal += total;
            o.setTotal(total);
            orders.add(o);
        }

        setTitle("Total dia = " + round(totalGlobal, 2) + "€");
        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order lhs, Order rhs) {
                if (lhs.getTime().equals(rhs.getTime())) {
                    if (lhs.getTable() < rhs.getTable()) return -1;
                    else return 1;
                }
                else return lhs.getTime().compareTo(rhs.getTime());
            }
        });

        adapter = new OrderAdapter<>(this, orders);
        llista.setAdapter(adapter);
        llista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), OrderInfoRead.class);
                Order o = adapter.getItem(position);
                intent.putExtra("ID", o.getId());
                intent.putExtra("TOTAL", o.getTotal());
                startActivity(intent);
            }
        });
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
