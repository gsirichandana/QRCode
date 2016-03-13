package com.example.siri.qr_code.Others;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.siri.qr_code.Main_Scan;
import com.example.siri.qr_code.R;

public class SecurityHome extends Activity implements AdapterView.OnItemSelectedListener {

    Button SN_butt,Submit;
    Spinner period;
    public String selState,content1;
    private ProgressDialog pDialog;
    EditText Enno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_home);

        period = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[]{"Period1", "Period2", "Period3", "Period4", "Period5", "Period6", "Period7", "Period8"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        period.setAdapter(adapter);
        period.setOnItemSelectedListener(this);

        Enno=(EditText)findViewById(R.id.enet);

        SN_butt=(Button)findViewById(R.id.SN_butt);
        SN_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                Intent i = new Intent(v.getContext(), Main_Scan.class);
                i.putExtras(b);
                i.putExtra("val",7);
                startActivity(i);
            }
        });
        Submit=(Button)findViewById(R.id.Sub_butt);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*Bundle bundle = getIntent().getExtras();
        content1 = bundle.getString("contents");*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.security_home, menu);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        period.setSelection(position);
        String selState = (String) period.getSelectedItem();
        Toast t=Toast.makeText(getBaseContext(),selState+"selected",Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
