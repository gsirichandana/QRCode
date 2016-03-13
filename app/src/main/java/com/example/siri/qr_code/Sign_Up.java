package com.example.siri.qr_code;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sign_Up extends Activity implements View.OnClickListener {
    EditText NAME, ENNo, BRANCH, YEAR, CPI, SSC, HSC, EMAIL, PWD, ConPWD;
    String name, enno, branch, year, cpi, ssc, hsc, email, pwd, cpwd;
    Button S_UP;
    Context ctx = this;
    int s=1;
//    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static String url_create_product = "http://127.0.0.1:80/android_connect/create_user.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        NAME = (EditText) findViewById(R.id.Name);
        ENNo = (EditText) findViewById(R.id.Enno);
        BRANCH = (EditText) findViewById(R.id.br);
        YEAR = (EditText) findViewById(R.id.yr);
        CPI = (EditText) findViewById(R.id.cpi);
        SSC = (EditText) findViewById(R.id.ssc);
        HSC = (EditText) findViewById(R.id.hsc);
        EMAIL = (EditText) findViewById(R.id.eml);
        PWD = (EditText) findViewById(R.id.pwd);
        ConPWD = (EditText) findViewById(R.id.cpwd);
        S_UP = (Button) findViewById(R.id.Sup);
        S_UP.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign__up, menu);
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
        /*name = NAME.getText().toString();
        enno = ENNo.getText().toString();
        branch = BRANCH.getText().toString();
        year = YEAR.getText().toString();
        cpi = CPI.getText().toString();
        ssc = SSC.getText().toString();
        hsc = HSC.getText().toString();
        email = EMAIL.getText().toString();
        pwd = PWD.getText().toString();
        cpwd = ConPWD.getText().toString();*/

       /* if (!(pwd.equals(cpwd))) {
            Toast.makeText(getBaseContext(), "Passwords are NOT matching", Toast.LENGTH_LONG).show();
            NAME.setText("");
            ENNo.setText("");
            BRANCH.setText("");
            YEAR.setText("");
            CPI.setText("");
            SSC.setText("");
            HSC.setText("");
            EMAIL.setText("");
            PWD.setText("");
            ConPWD.setText("");
        } else {
            DatabaseOps DB = new DatabaseOps(ctx);
            DB.putInfo(DB, name, enno, branch, year, cpi, ssc, hsc, email, pwd);
            Toast.makeText(getBaseContext(), "Registration Successful", Toast.LENGTH_LONG).show();
            finish();
        }*/
        name = NAME.getText().toString();
        enno = ENNo.getText().toString();
        branch = BRANCH.getText().toString();
        year = YEAR.getText().toString();
        cpi = CPI.getText().toString();
        ssc = SSC.getText().toString();
        hsc = HSC.getText().toString();
        email = EMAIL.getText().toString();
        pwd = PWD.getText().toString();
        cpwd = ConPWD.getText().toString();

            if (name.equalsIgnoreCase("")
                    || enno.equalsIgnoreCase("")
                    || branch.equalsIgnoreCase("")
                    || year.equalsIgnoreCase("")
                    || cpi.equalsIgnoreCase("")
                    || ssc.equalsIgnoreCase("")
                    || hsc.equalsIgnoreCase("")
                    || email.equalsIgnoreCase("")
                    || pwd.equalsIgnoreCase("")
                    || cpwd.equalsIgnoreCase("")) {
                Toast.makeText(Sign_Up.this, "All Fields Required.", Toast.LENGTH_SHORT).show();
                }

            if (pwd.equals(cpwd)) {
                Toast.makeText(getApplicationContext(), "Passwords match", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(getApplicationContext(), "Passwords dont match", Toast.LENGTH_SHORT);

            }

            if (!isValidEmail(email)) {
                EMAIL.setError("Invalid Email");

            }

            if (!isValidPassword(pwd)) {
                PWD.setError("Invalid Password");

            }

            if (!isValidEnno(enno)) {
                ENNo.setError("Invalid Enrollment Number");

            }
            if (!isValidYear(year)) {
                YEAR.setError("Invalid Year");

            }
            new CreateNewProduct().execute();

    }
    class CreateNewProduct extends AsyncTask<String,String , String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Sign_Up.this);
            pDialog.setMessage("Registering User..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Registering User
         */
        protected String  doInBackground(String... args) {

            // Building Parameters
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("enno", enno));
            params.add(new BasicNameValuePair("branch", branch));
            params.add(new BasicNameValuePair("year", year));
            params.add(new BasicNameValuePair("cpi", cpi));
            params.add(new BasicNameValuePair("ssc", ssc));
            params.add(new BasicNameValuePair("hsc", hsc));
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("pwd", pwd));



					String suc;
					String id;
					String str;
                    try {
                        DefaultHttpClient httpClient = new DefaultHttpClient();
//http://chandu.890m.com/android_connect/create_user.php?name=hello&enno=27&branch=be&year=2012&cpi=7&ssc=12&hsc=10&email=shah&pwd=1212
                        str = new String("http://chandu.890m.com/android_connect/create_user.php?");
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
    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

    private boolean isValidEnno(String enno) {
        if (enno != null && enno.length() == 12) {
            return true;
        }
        return false;
    }
    private boolean isValidYear(String year) {
        if (year != null && year.length() == 1 ) {
            return true;
        }
        return false;
    }
}
