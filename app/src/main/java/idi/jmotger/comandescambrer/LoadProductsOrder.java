package idi.jmotger.comandescambrer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LoadProductsOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String type = getIntent().getExtras().getString("TYPE");
        switch(type) {
            case "FIRST":
                setTitle("Nova comanda - Primers plats");
                break;
            case "SECOND":
                setTitle("Nova comanda - Segons plats");
                break;
            case "DRINK":
                setTitle("Nova comanda - Begudes");
                break;
            case "DESSERT":
                setTitle("Nova comanda - Postres");
                break;
            default:
                break;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_firsts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
