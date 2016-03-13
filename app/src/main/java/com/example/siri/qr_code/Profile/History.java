package com.example.siri.qr_code.Profile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.siri.qr_code.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class History extends Activity {

    public Button b;
    private ProgressDialog pDialog;
    ListAdapter adapter;
    EditText Sample;
    String sample;
    ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        b = (Button) findViewById(R.id.getbutt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sample=(EditText)findViewById(R.id.SampleEt);
                sample=Sample.getText().toString();

                new MyHis().execute();

                /*adapter = new SimpleAdapter(getApplicationContext(),mylist, R.layout.history,new String[] { "QRText", "TimeStamp" },new int[] { R.id.item_title, R.id.item_subtitle });
                lv.setAdapter(adapter);*/

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.history, menu);
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


    class MyHis extends AsyncTask<String,String , String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(History.this);
            pDialog.setMessage("Getting History..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            Sample.setText("{\"QRText\":\"sbsjxbva\",\"TimeStamp\":\"25\\/04\\/2015 01:54:07pm\"}\n{\"QRText\":\"abcdefgh\",\"TimeStamp\":\"27\\/04\\/2015 11:49:18pm\"}\n{\"QRText\":\"\",\"TimeStamp\":\"27\\/04\\/2015 11:48:58pm\"}\n{\"QRText\":\"absjxhdbsj\",\"TimeStamp\":\"25\\/04\\/2015 01:53:40pm\"}");

        }

        protected String  doInBackground(String... args) {
            String suc;
            String id;
            String str;
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
//http://chandu.890m.com/android_connect/create_user.php?name=hello&enno=27&branch=be&year=2012&cpi=7&ssc=12&hsc=10&email=shah&pwd=1212
                str = new String("http://chandu.890m.com/android_connect/History.php");
                Log.e("My URL : ", str);

                HttpGet request = new HttpGet(str);
                //     HttpPost httpPost = new HttpPost(url_create_product);
                //     httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse httpResponse = httpClient.execute(request);
                String response = EntityUtils.toString(httpResponse.getEntity());
                Log.e("result of service ", response);

                JSONObject jsonObject = new JSONObject(response);
                suc = jsonObject.getString("count");
                id = jsonObject.getString("details");
                if (Integer.valueOf(suc)!=0) {
                    JSONArray ge = new JSONArray(jsonObject.getString("details"));
                    JSONObject object;

                   /* for (int i = 0; i < ge.length(); i++) {
                        object = ge.getJSONObject(i);*/

                        Sample.setText(sample+jsonObject.getString("details"));

                        /*HashMap<String, String> map = new HashMap<String, String>();
                        map.put("QRText", "Content :" + object.getString("QRText"));
                        map.put("TimeStamp", "on : " +  object.getString("TimeStamp"));
                        mylist.add(map);*/
                   // }
                }
                Log.e("Total : ",suc);
                Log.e("History ",id);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return "";

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.hide();

        }
    }
}
