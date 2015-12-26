package idi.jmotger.comandescambrer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by jmotger on 26/12/15.
 */
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText textDate;

    public DateDialog (View view) {
        textDate = (EditText)view;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String m;
        if (month < 10) m = "0" + (month+1);
        else m = String.valueOf(month+1);
        String d;
        if (day < 10) d = "0" + day;
        else d = String.valueOf(day);
        textDate.setText(d + "/" + m + "/" + year);
        textDate.clearFocus();
    }

}
