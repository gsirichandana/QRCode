package com.example.siri.qr_code.Others;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.siri.qr_code.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;

public class SecurityLogin extends Activity {
    EditText User,Pass;
    Button Sub_butt;
    String username,password;
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    String LoginUrl = "http://chandu.890m.com/android_connect/SignIn_security.php";
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_login);

        User = (EditText) findViewById(R.id.SUser_et);
        Pass = (EditText) findViewById(R.id.SPass_et);

        Sub_butt = (Button) findViewById(R.id.Slogin_btn);
        Sub_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = User.getText().toString();
                password = Pass.getText().toString();
                Log.i("Hi", "Out of IF");

                ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

//        String status = info.isConnected() + "";

/*
        if (status.equals("false")) {
            Toast.makeText(getApplicationContext(), status + "Please Connect Internet", Toast.LENGTH_SHORT).show();
        } else {
*/
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("Username", username));
                nameValuePairs.add(new BasicNameValuePair("Password", password));

                StrictMode.setThreadPolicy(policy);

                String str, suc, id;
                try {
                    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                    str = new String("http://chandu.890m.com/android_connect/SignIn_security.php?");
                    str += "user=" + username;
                    str += "&pwd=" + password;
                    HttpGet request = new HttpGet(str);
                    HttpResponse httpResponse = defaultHttpClient.execute(request);
                    String response = EntityUtils.toString(httpResponse.getEntity());
                    Log.e("result of service ", response);

                    JSONObject jsonObject = new JSONObject(response);
                    suc = jsonObject.getString("suc");
                    id = jsonObject.getString("id");

                    Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
                    if(suc.equals("1")) {

                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),SecurityHome.class);
                        startActivity(intent);
                    }
                    else if (suc.equals("0")) {
                        Toast.makeText(getApplicationContext(), "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.security_login, menu);
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
