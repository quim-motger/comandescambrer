package idi.jmotger.comandescambrer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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

        TextView nameProduct = (TextView) view.findViewById(R.id.label_product);

        final Stock item = getItem(position);

        AsyncTask<Object, Void, Bitmap> async = new AsyncTask<Object, Void, Bitmap>() {
            View v;

            @Override
            protected Bitmap doInBackground(Object... params) {
                v = (View) params[0];
                Bitmap bitmap = BitmapFactory.decodeByteArray(((Stock) params[1]).getImage(), 0, ((Stock) params[1]).getImage().length);

                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int s = width/300;
                if (s == 0) s = 1;
                width = width/(s);
                height = height/(s);

                return Bitmap.createScaledBitmap(bitmap,width, height, false);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                ImageView imageProduct = (ImageView) v.findViewById(R.id.image_product);
                imageProduct.setImageBitmap(bitmap);
            }
        };
        async.execute(view, item);

        nameProduct.setText(item.getProduct().getName() + " (" + item.getUnits() + "u)");

        return view;
    }

}
