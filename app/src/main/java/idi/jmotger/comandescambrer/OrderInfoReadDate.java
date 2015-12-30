package idi.jmotger.comandescambrer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database.DataBaseSQLite;
import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.OrderLine;

public class OrderInfoReadDate extends AppCompatActivity {

    List<OrderLine> orderLines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info_read_date);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String id = getIntent().getExtras().getString("ID");
        Log.d("DDJI", id + "");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView llista = (ListView) findViewById(R.id.llistaReadDate);

        DataBaseSQLite dd = new DataBaseSQLite(OrderInfoReadDate.this);
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
        setTitle("Total comanda = "+ round(totalGlobal, 2) + "€");


        Collections.sort(orderLines, new Comparator<OrderLine>() {
            @Override
            public int compare(OrderLine lhs, OrderLine rhs) {
                return lhs.getProductName().compareTo(rhs.getProductName());
            }
        });
        OrderLineAdapter<OrderLine> adapter = new OrderLineAdapter<>(this, orderLines);
        llista.setAdapter(adapter);

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
