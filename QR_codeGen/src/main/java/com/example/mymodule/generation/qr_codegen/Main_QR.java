package com.example.mymodule.generation.qr_codegen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mymodule.generation.qr_codegen.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class Main_QR extends ActionBarActivity implements View.OnClickListener {

    private EditText seedEditText;
    private Button submitSeedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__qr);
        seedEditText =  (EditText) findViewById(R.id.qr_input_et);
        submitSeedButton = (Button) findViewById(R.id.submit_seed_btn);
        submitSeedButton.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main__qr, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        //Find screen size
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        int width = point.x;
        int height = point.y;
        int smallerDimension = 1000;
        smallerDimension = smallerDimension;

        //Encode with a QR Code image
        com.example.mymodule.generation.qr_codegen.QRCodeEncoder qrCodeEncoder = new com.example.mymodule.generation.qr_codegen.QRCodeEncoder(seedEditText.getText().toString(),
                null,
                Contents.Type.TEXT,
                BarcodeFormat.QR_CODE.toString(),
                smallerDimension);
        try {
            Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
            ImageView myImage = (ImageView) findViewById(R.id.imageView1);
            myImage.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }


    }
}
