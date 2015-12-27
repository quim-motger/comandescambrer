package idi.jmotger.comandescambrer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database.DataBaseSQLite;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when the user clicks the "New order" button
     * @param view
     */
    public void newOrder(View view) {

        Intent intent = new Intent(this, NewOrderActivity.class);
        startActivity(intent);
    }

    public void fillOrder(View view) {
        Intent intent = new Intent(this, TodayCashActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the user clicks the "New order" button
     * @param view
     */
    public void todayCash(View view) {
        Intent intent = new Intent(this, TodayCashActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the user clicks the "New order" button
     * @param view
     */
    public void listOrders(View view) {
        Intent intent = new Intent(this, ListOrdersActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the user clicks the "New order" button
     * @param view
     */
    public void stock(View view) {
        Intent intent = new Intent(this, StockActivity.class);
        startActivity(intent);
    }


}
