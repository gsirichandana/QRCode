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

public class StuFac_Inter extends Activity {

    private EditText recipient;
    private EditText subject;
    private EditText body;

    String content1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_fac__inter);

        recipient = (EditText) findViewById(R.id.recipient);

        subject = (EditText) findViewById(R.id.subject);

        body = (EditText) findViewById(R.id.body);

        Button sendBtn = (Button) findViewById(R.id.sendEmail);


        new AlertDialog.Builder(this)
                .setTitle("Scan Recipient")
                .setMessage("Do you want to scan the recepient or enter manually?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        Intent positiveActivity = new Intent(getApplicationContext(),Main_Scan.class);
                        positiveActivity.putExtra("val", "1");

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
                recipient.setText("");
                subject.setText("");
                body.setText("");
            }
        });
            Bundle bundle = getIntent().getExtras();
            content1 = bundle.getString("contents");
            recipient.setText(content1);
    }

    protected void sendEmail() {
        String[] recipients = {recipient.getText().toString()};
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));

        // prompts email clients only
         email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, recipients);

        email.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());

        email.putExtra(Intent.EXTRA_TEXT, body.getText().toString());
        try {
             // the user can choose the email client

            startActivity(Intent.createChooser(email, "Choose an email client from..."));



        } catch (android.content.ActivityNotFoundException ex) {

            Toast.makeText(StuFac_Inter.this, "No email client installed.",
                    Toast.LENGTH_LONG).show();
            finish();
         }
     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stu_fac__inter, menu);
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
