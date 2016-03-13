package com.example.siri.qr_code.Faculty;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;

public class Attendance extends Activity implements AdapterView.OnItemSelectedListener {

    Button SN_butt,Submit;
    Spinner period;
    public String selState,content1;
    private ProgressDialog pDialog;
    EditText Enno;
    String content;

    private static final String TAG_SUCCESS = "success";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance);

        period = (Spinner) findViewById(R.id.p_spinner);
        String[] items = new String[]{"Period1", "Period2", "Period3", "Period4", "Period5", "Period6", "Period7", "Period8"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        period.setAdapter(adapter);
        period.setOnItemSelectedListener(this);

        Enno=(EditText)findViewById(R.id.enet);
         //Toast.makeText(getApplicationContext(),content1+"present",Toast.LENGTH_SHORT).show();

        SN_butt=(Button)findViewById(R.id.SN_butt);
        SN_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                Intent i = new Intent(v.getContext(), Main_Scan.class);
                i.putExtras(b);
                i.putExtra("val",5);
                startActivity(i);
            }
        });
        Bundle bundle = getIntent().getExtras();
        content1 = bundle.getString("contents");
        Enno.setText(content1);

        Submit=(Button)findViewById(R.id.Sub_butt);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Presence().execute();
                finish();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        period.setSelection(position);
        selState = (String) period.getSelectedItem();
    }

    class Presence extends AsyncTask<String,String , String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Attendance.this);
            pDialog.setMessage("Marking presence..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String  doInBackground(String... args) {
            // Building Parameters

            //Log.e("Test",content1);

            content = Enno.getText().toString();
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("selState", selState));
            params.add(new BasicNameValuePair("content", content));

            String suc;
            String id;
            String str;
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
//http://chandu.890m.com/android_connect/create_user.php?name=hello&enno=27&branch=be&year=2012&cpi=7&ssc=12&hsc=10&email=shah&pwd=1212
                str = new String("http://chandu.890m.com/android_connect/attendance.php?");
                str += "selState=" + selState;
                str += "&content=" + content;

                HttpGet request = new HttpGet(str);
                //     HttpPost httpPost = new HttpPost(url_create_product);
                //     httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse httpResponse = httpClient.execute(request);

                String response = EntityUtils.toString(httpResponse.getEntity());
                Log.e("result of service ", response);

                JSONObject jsonObject = new JSONObject(response);
                suc = jsonObject.getString("success");
                id = jsonObject.getString("message");


                Toast.makeText(getApplicationContext(),id, Toast.LENGTH_LONG).show();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return "";

        }

        /**
         * After completing background task Dismiss the progress dialog
         * *
         */
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.hide();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.attendance, menu);
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
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
