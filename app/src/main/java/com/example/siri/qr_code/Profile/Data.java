package com.example.siri.qr_code.Profile;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.siri.qr_code.R;

public class Data extends Activity {

    EditText name,enno,branch,year,cpi,ssc,hsc,email;
    String Name,Enno,Branch,Year,Cpi,SSC,HSC,Email,Pwd;
    Button get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);

        name=(EditText)findViewById(R.id.tname);
        enno=(EditText)findViewById(R.id.tenno);
        branch=(EditText)findViewById(R.id.tbranch);
        year=(EditText)findViewById(R.id.tyear);
        cpi=(EditText)findViewById(R.id.tcpi);
        ssc=(EditText)findViewById(R.id.tssc);
        hsc=(EditText)findViewById(R.id.thsc);
        email=(EditText)findViewById(R.id.temail);



        name.setText("abc");
        enno.setText("110050131006");
        branch.setText("CSE");
        year.setText("4");
        cpi.setText("8.0");
        ssc.setText("80");
        hsc.setText("80");
        email.setText("abc@gmail.com");

        /*get=(Button)findViewById(R.id.getbutt);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name=name.getText().toString();
                Enno=enno.getText().toString();
                Branch=branch.getText().toString();
                Year=year.getText().toString();
                Cpi=cpi.getText().toString();
                SSC=ssc.getText().toString();
                HSC=hsc.getText().toString();
                Email=email.getText().toString();
                Pwd=pwd.getText().toString();

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", Name));
                nameValuePairs.add(new BasicNameValuePair("enno", Enno));
                nameValuePairs.add(new BasicNameValuePair("branch", Branch));
                nameValuePairs.add(new BasicNameValuePair("year", Year));
                nameValuePairs.add(new BasicNameValuePair("cpi", Cpi));
                nameValuePairs.add(new BasicNameValuePair("ssc", SSC));
                nameValuePairs.add(new BasicNameValuePair("hsc", HSC));
                nameValuePairs.add(new BasicNameValuePair("email", Email));
                nameValuePairs.add(new BasicNameValuePair("pwd", Pwd));

                String str, suc, id;
                try {
                    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                    str = new String("http://chandu.890m.com/android_connect/SignIn.php?");
                    str += "name=" + name;
                    str += "&enno=" + enno;
                    str += "&branch=" + branch;
                    str += "&year=" + year;
                    str += "&cpi=" + cpi;
                    str += "&ssc=" + ssc;
                    str += "&hsc=" + hsc;
                    str += "&email=" + email;
                    str += "&pwd=" + pwd;

                    HttpGet request = new HttpGet(str);
                    HttpResponse httpResponse = defaultHttpClient.execute(request);
                    String response = EntityUtils.toString(httpResponse.getEntity());
                    Log.e("result of service ", response);

                    JSONObject jsonObject = new JSONObject(response);
                    suc = jsonObject.getString("suc");
                    id = jsonObject.getString("id");

                    Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
                    if(suc.equals("1")) {
                        name.setText(Name);


                    }
                    else if (suc.equals("0")) {
                        Toast.makeText(getApplicationContext(), "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.data, menu);
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
