package idi.jmotger.comandescambrer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class FillOrderActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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
