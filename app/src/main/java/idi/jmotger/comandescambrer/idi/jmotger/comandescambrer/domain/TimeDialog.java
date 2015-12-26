package idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by jmotger on 26/12/15.
 */
public class TimeDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    EditText textDate;

    public TimeDialog (View view) {
        textDate = (EditText)view;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }

    public void onTimeSet(TimePicker view, int hour, int minute) {
        String date = hour + ":" + minute;
        textDate.setText(date);
    }

}
