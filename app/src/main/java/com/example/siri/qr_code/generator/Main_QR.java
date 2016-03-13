package com.example.siri.qr_code.generator;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.siri.qr_code.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main_QR extends ActionBarActivity implements View.OnClickListener {

    private EditText seedEditText;
    private Button submitSeedButton;
    private ProgressDialog pDialog;
    String QRtext,strTime;

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
        QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(seedEditText.getText().toString(),
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


        new StoreQR().execute();


    }

    class StoreQR extends AsyncTask<String,String , String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Main_QR.this);
            pDialog.setMessage("Saving QR code..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Registering User
         */
        protected String  doInBackground(String... args) {
            QRtext = seedEditText.getText().toString();
            // Building Parameters
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("QRtext",QRtext));

            String suc;
            String id;
            String str;
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
//http://chandu.890m.com/android_connect/create_user.php?name=hello&enno=27&branch=be&year=2012&cpi=7&ssc=12&hsc=10&email=shah&pwd=1212
                str = new String("http://chandu.890m.com/android_connect/QR_Entry.php?");
                str += "QRtext=" + QRtext;
                Log.e("My URL : ",str);

                HttpGet request = new HttpGet(str);
                //     HttpPost httpPost = new HttpPost(url_create_product);
                //     httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse httpResponse = httpClient.execute(request);

                String response = EntityUtils.toString(httpResponse.getEntity());
                Log.e("result of service ", response);

                JSONObject jsonObject = new JSONObject(response);
                suc = jsonObject.getString("success");
                id = jsonObject.getString("message");


                Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();

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




}
