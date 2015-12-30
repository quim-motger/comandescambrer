package idi.jmotger.comandescambrer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jmotger on 27/12/15.
 */
public class OrderAdapter<Order> extends ArrayAdapter<Order> {

    List<String> dates;

    public OrderAdapter(Context context, List<Order> objects) {
        super(context, 0, objects);
    }

    public OrderAdapter(Context context, List<Order> objects, List<String> dates) {
        super(context, 0, objects);
        this.dates = dates;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItemView = convertView;

        if (null == convertView) {
            listItemView = inflater.inflate(android.R.layout.two_line_list_item, parent, false);
        }

        TextView productName = (TextView)listItemView.findViewById(android.R.id.text1);
        TextView qttXprice = (TextView)listItemView.findViewById(android.R.id.text2);

        Order item = (Order) getItem(position);

        String[] split = item.toString().split(";");

        if (dates == null)
            productName.setText(split[0]);
        else {
            String[] info = split[0].split("\\(");
            productName.setText(info[0] + "(" + dates.get(position) + ", " + info[1]);
        }
        qttXprice.setText(split[1]);

        return listItemView;

    }

}
