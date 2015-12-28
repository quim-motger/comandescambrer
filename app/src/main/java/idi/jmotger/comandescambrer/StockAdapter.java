package idi.jmotger.comandescambrer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.Stock;
import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.domain.StockRepository;

/**
 * Created by jmotger on 27/12/15.
 */
public class StockAdapter extends BaseAdapter {

    private Context context;
    private String type;
    private StockRepository r;

    public StockAdapter(Context context, String type) {
        this.context = context;
        this.type = type;
        this.r = new StockRepository(context, type);
    }

    @Override
    public int getCount() {
        return r.getCount();
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }


    @Override
    public Stock getItem(int position) {
        return r.getProduct(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView imageProduct = (ImageView) view.findViewById(R.id.image_product);
        TextView nameProduct = (TextView) view.findViewById(R.id.label_product);

        final Stock item = getItem(position);

        Bitmap bitmap = BitmapFactory.decodeByteArray(item.getImage(), 0, item.getImage().length);
        imageProduct.setImageBitmap(bitmap);
        nameProduct.setText(item.getProduct().getName() + " (" + item.getUnits() + "u)");

        return view;
    }

}
