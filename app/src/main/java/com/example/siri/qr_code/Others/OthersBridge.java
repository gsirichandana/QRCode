package com.example.siri.qr_code.Others;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.siri.qr_code.R;

public class OthersBridge extends Activity {

    Button Regis_butt,Sec_butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.others_bridge);

        /*Regis_butt = (Button) findViewById(R.id.regis_butt);
        Regis_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                //b.putInt("status", status);
                Intent i = new Intent(v.getContext(), Registrar_Login.class);
                i.putExtras(b);
                startActivity(i);


            }
        });*/

        Sec_butt = (Button) findViewById(R.id.Sec_butt);
        Sec_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                //b.putInt("status", status);
                Intent i = new Intent(v.getContext(), SecurityLogin.class);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.others_bridge, menu);
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
