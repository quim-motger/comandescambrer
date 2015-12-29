package idi.jmotger.comandescambrer;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database.DataBaseSQLite;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DataBaseSQLite d = new DataBaseSQLite(this);
        SQLiteDatabase db = d.getReadableDatabase();
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
        showDialog(0);
        /*Intent intent = new Intent(this, ListOrdersActivity.class);
        startActivity(intent);*/
    }

    /**
     * Called when the user clicks the "New order" button
     * @param view
     */
    public void stock(View view) {
        Intent intent = new Intent(this, StockActivity.class);
        startActivity(intent);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (id == 0) {
            DatePickerDialog d = new DatePickerDialog(this, firstDate, year, month, day);
            d.setTitle("Primera data");
            return d;
        }
        if (id == 1) {
            DatePickerDialog d = new DatePickerDialog(this, secondDate, year, month, day);
            d.setTitle("Segona data");
            return d;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener secondDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            date2 = arg3 + "/" + (arg2+1) + "/" + arg1;
            Intent intent = new Intent(MainActivity.this, ListOrdersActivity.class);
            intent.putExtra("DATE1", date1);
            intent.putExtra("DATE2", date2);
            startActivity(intent);
        }
    };

    String date1;
    String date2;

    private DatePickerDialog.OnDateSetListener firstDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            date1 = arg3 + "/" + (arg2+1) + "/" + arg1;
            showDialog(1);
        }
    };

}
