package com.example.siri.qr_code;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.siri.qr_code.generator.Main_QR;

public class SignIn_GenBridge extends Activity {
Button Gen_butt,Scan_butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in__gen_bridge);

        Gen_butt = (Button) findViewById(R.id.genQrButt);
        Gen_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                //b.putInt("status", status);
                Intent i = new Intent(v.getContext(), Main_QR.class);
                i.putExtras(b);
                startActivity(i);
            }
        });

        Scan_butt = (Button) findViewById(R.id.scanQrButt);
        Scan_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                //b.putInt("status", status);
                Intent i = new Intent(v.getContext(), Main_Scan.class);
                i.putExtras(b);
                startActivity(i);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_in__gen_bridge, menu);
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
}
