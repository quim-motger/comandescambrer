package idi.jmotger.comandescambrer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class StockActivity extends AppCompatActivity {

    ButtonTypeAdapter adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Stock de productes");
        setContentView(R.layout.activity_stock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewProductActivity.class);
                startActivity(intent);
            }
        });

        adap = new ButtonTypeAdapter(getApplicationContext());
        GridView gridView = (GridView) findViewById(R.id.gridButtonsStock);
        gridView.setAdapter(adap);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Log.v("TAG", "Clicked");
                Intent intent = new Intent(getApplicationContext(), StockLoadProducts.class);
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
