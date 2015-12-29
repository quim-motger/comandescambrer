package idi.jmotger.comandescambrer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ListOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_orders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String date1 = getIntent().getExtras().getString("DATE1");
        String date2 = getIntent().getExtras().getString("DATE2");

        setTitle(date1 + " - " + date2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
