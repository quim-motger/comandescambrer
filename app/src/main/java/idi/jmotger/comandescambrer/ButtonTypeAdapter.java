package idi.jmotger.comandescambrer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jmotger on 27/12/15.
 */
public class ButtonTypeAdapter extends BaseAdapter {

    private Context context;
    List<String> buttons;

    public ButtonTypeAdapter(Context context) {
        this.context = context;
        buttons = new ArrayList<>();
        buttons.add("PRIMERS");
        buttons.add("SEGONS");
        buttons.add("POSTRES");
        buttons.add("BEGUDES");

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }


    @Override
    public String getItem(int position) {
        return buttons.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.button_item, viewGroup, false);
        }
        view.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 120));


        TextView b = (TextView) view.findViewById(R.id.buttonGrid);
        b.setText(getItem(position));

        return view;
    }

}
