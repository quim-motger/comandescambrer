package idi.jmotger.comandescambrer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.OrderLine;

public class OrderInfo extends AppCompatActivity {

    List<OrderLine> orderLines;

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
                    NewOrderActivity.currentOrder.getOrderLines().remove(currentOrderLine.getProduct());
                else {
                    NewOrderActivity.currentOrder.getOrderLines().get(currentOrderLine.getProduct()).setAmount(n);
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

}
