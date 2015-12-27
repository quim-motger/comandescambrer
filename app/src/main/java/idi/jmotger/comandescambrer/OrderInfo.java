package idi.jmotger.comandescambrer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.OrderLine;

public class OrderInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Comanda guardada", Toast.LENGTH_SHORT).show();
                loadMain();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView llista = (ListView) findViewById(R.id.llista);
        List<OrderLine> orderLines = new ArrayList<>();
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
                //TODO show spinner to change amount
            }
        });
    }

    protected void loadMain() {
        Intent ret = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(ret);
    }

}
