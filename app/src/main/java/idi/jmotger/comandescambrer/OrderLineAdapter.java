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
public class OrderLineAdapter<OrderLine> extends ArrayAdapter<OrderLine> {

    public OrderLineAdapter(Context context, List<OrderLine> objects) {
        super(context, 0, objects);
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

        OrderLine item = (OrderLine) getItem(position);

        String[] split = item.toString().split(";");

        productName.setText(split[0]);
        qttXprice.setText(split[1]);

        return listItemView;

    }

}
