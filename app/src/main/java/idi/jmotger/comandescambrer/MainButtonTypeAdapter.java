package idi.jmotger.comandescambrer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jmotger on 27/12/15.
 */
public class MainButtonTypeAdapter extends BaseAdapter {

    private Context context;
    List<String> buttons;

    public MainButtonTypeAdapter(Context context) {
        this.context = context;
        buttons = new ArrayList<>();
        buttons.add("NOVA COMANDA");
        buttons.add("CAIXA DEL DIA");
        buttons.add("COMANDES PER DATES");
        buttons.add("STOCK");

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
            view = inflater.inflate(R.layout.main_button_layout, viewGroup, false);
        }

       /* TextView b = (TextView) view.findViewById(R.id.buttonGrid);
        b.setText(getItem(position));*/

        ImageView v = (ImageView) view.findViewById(R.id.imageIcon);
        TextView t = (TextView) view.findViewById(R.id.textIcon);

        switch(getItem(position)) {
            case "NOVA COMANDA":
                v.setImageResource(R.drawable.waiter);
                t.setText("Nova comanda");
                break;
            case "CAIXA DEL DIA":
                v.setImageResource(R.drawable.cash);
                t.setText("Caixa del dia");
                break;
            case "COMANDES PER DATES":
                v.setImageResource(R.drawable.calendar);
                t.setText("Llistat comandes");
                break;
            case "STOCK":
                v.setImageResource(R.drawable.stock);
                t.setText("Stock");
                break;
            default:
                break;
        }

        return view;
    }

}
