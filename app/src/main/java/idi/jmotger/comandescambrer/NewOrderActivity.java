package idi.jmotger.comandescambrer;

import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.TimeDialog;
import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database.DataBaseSQLite;
import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.Order;

public class NewOrderActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    public static Order currentOrder;

    public void onStart() {
        super.onStart();
        //Configure date
        EditText textDate = (EditText)findViewById(R.id.textdate);
        textDate.clearFocus();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        String m;
        if (month < 10) m = "0" + (month+1);
        else m = String.valueOf(month+1);
        String d;
        if (day < 10) d = "0" + day;
        else d = String.valueOf(day);
        textDate.setText(d + "/" + m + "/" + year);
        textDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = DateDialog.newInstance(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });
        //Configure time
        EditText textTime = (EditText)findViewById(R.id.texttime);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        String h;
        if (hour < 10) h = "0" + hour;
        else h = String.valueOf(hour);
        String mn;
        if (minute < 10) mn = "0" + minute;
        else mn = String.valueOf(minute);
        textTime.setText(h + ":" + mn);
        textTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    TimeDialog dialog = TimeDialog.newInstance(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "TimePicker");
                }
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.table_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.table_number, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void startOrder(View view) {
        EditText date = (EditText)findViewById(R.id.textdate);
        EditText time = (EditText)findViewById(R.id.texttime);
        Spinner spinner = (Spinner) findViewById(R.id.table_spinner);
        String d = date.getText().toString();
        String t = time.getText().toString();
        String table = spinner.getSelectedItem().toString();

        Toast toast = Toast.makeText(getApplicationContext(), "Comanda iniciada el dia " + d + " a les " + t + " a la taula " + table, Toast.LENGTH_SHORT);
        toast.show();

        currentOrder = new Order(d, t, Integer.parseInt(table));

        Intent intent = new Intent(this, FillOrderActivity.class);
        intent.putExtra("DATE", d);
        intent.putExtra("TIME", t);
        intent.putExtra("N_TABLE", Integer.parseInt(table));
        startActivity(intent);
    }

}
