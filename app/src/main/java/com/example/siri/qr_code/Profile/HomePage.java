package com.example.siri.qr_code.Profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.siri.qr_code.R;

public class HomePage extends Activity {

    Button Lib,Eve,Inter,Cmat,History,Tnp,Profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        Lib = (Button) findViewById(R.id.lib);
        Lib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                //b.putInt("status", status);
                Intent i = new Intent(v.getContext(), Library.class);
                i.putExtras(b);
                startActivity(i);
            }
        });

        Eve = (Button) findViewById(R.id.eve);
        Eve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                //b.putInt("status", status);
                Intent i = new Intent(v.getContext(), Events.class);
                i.putExtras(b);
                startActivity(i);
            }
        });

        Inter = (Button) findViewById(R.id.inter);
        Inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                //b.putInt("status", status);
                Intent i = new Intent(v.getContext(), Stu_fac_inter_main.class);
                i.putExtras(b);
                startActivity(i);
            }
        });
        Cmat = (Button) findViewById(R.id.mmbutt);
        Cmat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                //b.putInt("status", status);
                Intent i = new Intent(v.getContext(), Course_MatMan.class);
                i.putExtras(b);
                startActivity(i);
            }
        });
        History = (Button) findViewById(R.id.Hist_butt);
        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                //b.putInt("status", status);
                Intent i = new Intent(v.getContext(), History.class);
                i.putExtras(b);
                startActivity(i);
            }
        });

        Tnp = (Button) findViewById(R.id.tnpbutt);
        Tnp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                Intent i = new Intent(v.getContext(), TNP.class);
                i.putExtras(b);
                startActivity(i);
            }
        });
        Profile = (Button) findViewById(R.id.myprobutt);
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                Intent i = new Intent(v.getContext(), MyProfile.class);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
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
