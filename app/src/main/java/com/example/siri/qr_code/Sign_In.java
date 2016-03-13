package com.example.siri.qr_code;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A login screen that offers login via email/password.
 */
public class Sign_In extends Activity implements LoaderCallbacks<Cursor>,OnClickListener {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */

    EditText EMAIL, PWD;
    String user_email, user_pass;
    Button Signin_Button;
    InputStream input;
    String line, result;
    public SharedPreferences pref;
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    String LoginUrl = "http://chandu.890m.com/android_connect/SignIn.php";
    Context ctx = this;

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);

        EMAIL = (EditText) findViewById(R.id.email);
        PWD = (EditText) findViewById(R.id.password);
        Signin_Button = (Button) findViewById(R.id.email_sign_in_button); // left here on 4th dec 2014 video time --11:23


        Signin_Button.setOnClickListener(this);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);

        //Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        /*mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });*/

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        getLoaderManager().initLoader(0, null, this);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC"
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    @Override
    public void onClick(final View v) {

     //   LoginData();

        user_email = EMAIL.getText().toString();
        user_pass = PWD.getText().toString();

        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        String status = info.isConnected() + "";


        if (status.equals("false")) {
            Toast.makeText(getApplicationContext(), "No Internet Connection \n\nPlease Connect to Internet", Toast.LENGTH_SHORT).show();
        }
        else if(status.equals("true")) {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("Username", user_email));
        nameValuePairs.add(new BasicNameValuePair("Password", user_pass));

        StrictMode.setThreadPolicy(policy);

        String str, suc, id;
        try {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            str = new String("http://chandu.890m.com/android_connect/SignIn.php?");
            str += "email=" + user_email;
            str += "&pwd=" + user_pass;
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
                Intent intent = new Intent(getApplicationContext(), com.example.siri.qr_code.Profile.HomePage.class);
                startActivity(intent);
            }
            else if (suc.equals("0")) {
                Toast.makeText(getApplicationContext(), "Invalid Username and Password", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

            /*try {
                BufferedReader reader = new BufferedReader
                        (new InputStreamReader(input, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                input.close();
                result = sb.toString();

            } catch (Exception e) {
                Log.e("Fail 2", e.toString());
            }*/
//            Log.e("Result of Query : ", result);
/*            try {
                JSONObject jsonObject = new JSONObject(result);
                int result = jsonObject.getInt("suc");
                final int id = jsonObject.getInt("id");

                // The Result Page

                Log.e("THE MAIN ID ",id+"");

                pref = getSharedPreferences("Result", 0);
                SharedPreferences.Editor edit1 = pref.edit();
                edit1.putString("ID", id + "");
                edit1.commit();


                if (result == 0) {

                    //Msg.setText("Invalid Username or Password");
                    onRestart();
                } else {
                    pref = getSharedPreferences("Ele_ID", 0);
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putString("ID", id + "");

                    edit.commit();
                    OnAsyncResult onResult = new OnAsyncResult() {
                        @Override
                        public void onAsyncResult(String result) {

                            //Log.e("The Election Result", result);

                            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), com.example.siri.qr_code.Profile.HomePage.class);
                            intent.putExtra("result", result);
                            intent.putExtra("username", user_email);
                            intent.putExtra("ID",id);

                           pref = getSharedPreferences("Ele_ID", 0);
                            SharedPreferences.Editor edit = pref.edit();
                            edit.putString("ID", id + "");


                            //This is Voter Generate Page
                            edit.putString("Result", result);
                            edit.putString("user", user_email);

                            edit.commit();
                            startActivity(intent);
                        }
                    };


                    //AsyncElectionLoader loader = new AsyncElectionLoader(MainActivity.this, LoginEle, onResult);
                    //loader.execute();

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }

        //}

        /*Bundle b = getIntent().getExtras();
        int status = b.getInt("status");

        if (status == 1) {
            Toast.makeText(getBaseContext(), "Please wait..", Toast.LENGTH_LONG).show();
            user_email = EMAIL.getText().toString();
            user_pass = PWD.getText().toString();
            DatabaseOps DOP = new DatabaseOps(ctx);
            Cursor CR = DOP.validate(DOP);
            CR.moveToFirst();       //pointer to first row in result set

            boolean login_status = false;
            String DName = "";
            do {
                if (user_email.equals(CR.getString(7)) && user_pass.equals(CR.getString(8))) {
                    login_status = true;
                    DName = CR.getString(0);
                }
            } while (CR.moveToNext());

            if (login_status == true) {
                Toast.makeText(getBaseContext(), "Login Succcess! \n Welcome " + DName, Toast.LENGTH_LONG).show();
                finish();
                Intent i = new Intent(v.getContext(), Sign_In.class);
                i.putExtras(b);
                startActivity(i);
                Intent in = new Intent(v.getContext(), com.example.siri.qr_code.Profile.HomePage.class);
                in.putExtras(b);
                startActivity(in);
            } else {
                Toast.makeText(getBaseContext(), "Login Failed! \n ", Toast.LENGTH_LONG).show();
                finish();
            }
        }
                *//*else if (status ==2 )
                {
                    Intent i = new Intent("update_filter");
                }*//*
*/
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }


    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(Sign_In.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}



