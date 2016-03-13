package com.example.siri.qr_code.Profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.util.Calendar;

public class Library extends Activity {

    Button Con_butt,Rem_butt;
    EditText BookName;
    String bookname;
    String contents;
    TextView a;
    private ProgressDialog pDialog;
    private static final String TAG_SUCCESS = "success";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);

        BookName=(EditText)findViewById(R.id.NameVal);
        new AlertDialog.Builder(this)
                .setTitle("Scan QR Code of the book:")
                .setMessage("Would to like to scan the QR Code of the book..??")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        Intent positiveActivity = new Intent(getApplicationContext(),Main_Scan.class);
                        positiveActivity.putExtra("val", "4");

                        startActivity(positiveActivity);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

        Bundle bundle = getIntent().getExtras();
        contents = bundle.getString("contents");
        BookName.setText(contents);


        Con_butt = (Button) findViewById(R.id.con_butt);
        Con_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateBook().execute();
                Toast toast = Toast.makeText(getBaseContext(),"Book Issued:" + contents, Toast.LENGTH_LONG);
                toast.show();
                finish();
            }
        });

        Rem_butt=(Button)findViewById(R.id.Rbutt);
        Rem_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime", cal.getTimeInMillis());
                intent.putExtra("allDay", false);
                intent.putExtra("rrule", "FREQ=DAILY");
                intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
                intent.putExtra("title", "Return Book");
                startActivity(intent);
            }
        });

        }
    class CreateBook extends AsyncTask<String,String , String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Library.this);
            pDialog.setMessage("Issuing Book..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String  doInBackground(String... args) {
            bookname = BookName.getText().toString();

            // Building Parameters
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("bookname", bookname));

            String suc;
            String id;
            String str;
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
//http://chandu.890m.com/android_connect/create_user.php?name=hello&enno=27&branch=be&year=2012&cpi=7&ssc=12&hsc=10&email=shah&pwd=1212
                str = new String("http://chandu.890m.com/android_connect/create_book.php?");
                str += "bookname=" + bookname;

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
        getMenuInflater().inflate(R.menu.library, menu);
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
