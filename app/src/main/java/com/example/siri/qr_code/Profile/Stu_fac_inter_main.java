package com.example.siri.qr_code.Profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.siri.qr_code.R;

public class Stu_fac_inter_main extends Activity {

    Button t_butt,wr_butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_fac_inter_main);

        t_butt = (Button) findViewById(R.id.tbutt);
        t_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                //b.putInt("status", status);
                Intent i = new Intent(v.getContext(), StuFac_Inter.class);
                i.putExtras(b);
                startActivity(i);
            }
        });

        wr_butt = (Button) findViewById(R.id.wrbutt);

        wr_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                Intent i = new Intent(v.getContext(), StuFac_review.class);
                i.putExtras(b);
                startActivity(i);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stu_fac_inter_main, menu);
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
