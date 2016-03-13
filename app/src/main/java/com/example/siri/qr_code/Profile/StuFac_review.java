package com.example.siri.qr_code.Profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.siri.qr_code.Main_Scan;
import com.example.siri.qr_code.R;

public class StuFac_review extends Activity {

    private EditText Recipient;
    private EditText Faculty;
    private EditText Body;

    String content1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_fac_review);

        Recipient = (EditText) findViewById(R.id.recipient);

        Faculty = (EditText) findViewById(R.id.faculty);

        Body = (EditText) findViewById(R.id.body);

        Button sendBtn = (Button) findViewById(R.id.sendEmail);


        Recipient.setText("HOD's Email Address");
        new AlertDialog.Builder(this)
                .setTitle("Scan Faculty")
                .setMessage("Would you like to scan the faculty name or enter manually?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        Intent positiveActivity = new Intent(getApplicationContext(),Main_Scan.class);
                        positiveActivity.putExtra("val", "3");
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

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
                // after sending the email, clear the fields
                Faculty.setText("");
                Body.setText("");
            }
        });
        Bundle bundle = getIntent().getExtras();
        content1 = bundle.getString("contents");
        Faculty.setText(content1);
    }

    protected void sendEmail() {
        String[] recipients = {Recipient.getText().toString()};
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));

        // prompts email clients only
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, recipients);

        email.putExtra(Intent.EXTRA_SUBJECT, Faculty.getText().toString());

        email.putExtra(Intent.EXTRA_TEXT, Body.getText().toString());
        try {
            // the user can choose the email client

            startActivity(Intent.createChooser(email, "Choose an email client from..."));



        } catch (android.content.ActivityNotFoundException ex) {

            Toast.makeText(StuFac_review.this, "No email client installed.",
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stu_fac_review, menu);
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
