package idi.jmotger.comandescambrer;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import idi.jmotger.comandescambrer.idi.jmotger.comandescambrer.database.DataBaseSQLite;

public class NewProductActivity extends AppCompatActivity {

    ButtonTypeAdapter adap;
    byte[] loadedImage;
    Uri image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Crear nou producte");
        setContentView(R.layout.activity_new_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewProduct();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.product_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (image != null) {
            Log.d("TAG", "Image not lost");
            ImageView v = (ImageView)findViewById(R.id.loadImage);
            v.setImageURI(image);
        }

    }

    public void uploadImage(View view) {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 0);//one can be replaced with any action code
    }

    public void makePhoto(View view) {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(resultCode == RESULT_OK){
            ImageView v = (ImageView)findViewById(R.id.loadImage);
            image = intent.getData();
            v.setImageURI(image);
            try {
                InputStream iStream =   getContentResolver().openInputStream(image);
                loadedImage = getBytes(iStream);
            } catch (Exception e) {
                Log.e("LOAD_IMAGE", "Error while converting");
            }

        }
    }

    protected void createNewProduct() {
        EditText name = (EditText)findViewById(R.id.textname);
        EditText preu = (EditText)findViewById(R.id.textpreu);
        EditText stock = (EditText)findViewById(R.id.textstock);
        Spinner spinner = (Spinner)findViewById(R.id.spinnerType);

        if (name.getText().toString().equals("") ||
                preu.getText().toString().equals("") ||
                stock.getText().toString().equals("")
                ) {
            Toast.makeText(getApplicationContext(), "Introdueixi tots els valors", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (loadedImage == null) {
            Toast.makeText(getApplicationContext(), "Seleccioni una imatge pel producte", Toast.LENGTH_SHORT).show();
        }
        else {
            String n = name.getText().toString();
            Double p = Double.parseDouble(preu.getText().toString());
            int s = Integer.parseInt(stock.getText().toString());
            String type = spinner.getSelectedItem().toString();
            String t = "";
            switch (type) {
                case "Primer":
                    t = "FIRST";
                    break;
                case "Segon":
                    t = "SECOND";
                    break;
                case "Postre":
                    t = "DESSERT";
                    break;
                case "Beguda":
                    t = "DRINK";
                    break;
                default:
                    break;
            }

            Log.d("NEW_PRODUCT", "Carreguem la DB");
            DataBaseSQLite d = new DataBaseSQLite(getApplicationContext());
            SQLiteDatabase db = d.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("NAME", n);
            values.put("PRICE", p);
            values.put("TYPE", t);
            values.put("IMAGE", loadedImage);
            Log.d("NEW_PROCUT", "Insertem el nou " + t);
            db.insert("PRODUCT", null, values);
            Log.d("NEW_PRODUCT", "EXITO");

            values = new ContentValues();
            values.put("PRODUCT_NAME", n);
            values.put("UNITS", s);
            db.insert("STOCK", null, values);
            Log.d("NEW_PRODUCT", "EXITO DOBLE");

            Toast.makeText(getApplicationContext(), "Producte " + n + " creat satisfactòriament", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

}
