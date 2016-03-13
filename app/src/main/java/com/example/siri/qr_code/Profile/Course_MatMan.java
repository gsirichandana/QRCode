package com.example.siri.qr_code.Profile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.siri.qr_code.R;

public class Course_MatMan extends Activity implements AdapterView.OnItemSelectedListener {

    Spinner dropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course__mat_man);

        dropdown = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[]{"Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6", "Semester 7", "Semester 8"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
    }

        public void onItemSelected(AdapterView<?> parent, View view, int position,long id)
    {

        dropdown.setSelection(position);
        String selState = (String) dropdown.getSelectedItem();
        Toast t=Toast.makeText(getBaseContext(),selState+"selected",Toast.LENGTH_SHORT);
        t.show();

        if(selState.equals("Semester 8"))
        {
            Uri uri = Uri.parse("https://www.dropbox.com/sh/y4y47klkaifog2f/AACvfvj00da80uY9q6vaZIMja?n=63694787");
            Intent positiveActivity = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(positiveActivity);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.course__mat_man, menu);
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
