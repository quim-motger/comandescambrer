package idi.jmotger.comandescambrer;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
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

    String date1;
    String date2;

    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (id == 0) {
            MyDatePickerDialog d = new MyDatePickerDialog(this, firstDate, year, month, day);
            d.setPermanentTitle("Primera data");
            return d;
        }
        if (id == 1) {
            String[] info = date1.split("/");
            MyDatePickerDialog d = new MyDatePickerDialog(this,
                                                        secondDate,
                                                        Integer.parseInt(info[2]),
                                                        Integer.parseInt(info[1])-1,
                                                        Integer.parseInt(info[0]));
            Calendar cc = Calendar.getInstance();
            cc.set(Calendar.YEAR, Integer.parseInt(info[2]));
            cc.set(Calendar.MONTH, Integer.parseInt(info[1])-1);
            cc.set(Calendar.DAY_OF_MONTH, Integer.parseInt(info[0]));
            d.getDatePicker().setMinDate(cc.getTimeInMillis() -10);
            d.setPermanentTitle("Segona data");
            return d;
        }
        return null;
    }

    private MyDatePickerDialog.OnDateSetListener secondDate = new MyDatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            date2 = arg3 + "/" + (arg2+1) + "/" + arg1;
            Intent intent = new Intent(MainActivity.this, ListOrdersActivity.class);
            ListOrdersActivity.date1 = date1;
            ListOrdersActivity.date2 = date2;
            startActivity(intent);
        }
    };

    private MyDatePickerDialog.OnDateSetListener firstDate = new MyDatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            date1 = arg3 + "/" + (arg2+1) + "/" + arg1;
            showDialog(1);
        }
    };

    public class MyDatePickerDialog extends DatePickerDialog {

        private String title;

        public MyDatePickerDialog(Context context, OnDateSetListener c, int year, int month, int day) {
            super(context, c, year, month, day);
        }

        public void setPermanentTitle(String title) {
            this.title = title;
            setTitle(title);
        }

        @Override
        public void onDateChanged(DatePicker view, int year, int month, int day) {
            super.onDateChanged(view, year, month, day);
            setTitle(title);
        }
    }

}
