package com.example.siri.qr_code;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.siri.qr_code.Faculty.Attendance;
import com.example.siri.qr_code.Others.SecurityHome;
import com.example.siri.qr_code.Profile.Library;
import com.example.siri.qr_code.Profile.StuFac_Inter;
import com.example.siri.qr_code.Profile.StuFac_review;

public class Main_Scan extends Activity {

    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    public String contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__scan);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main__scan, menu);
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

    //product barcode mode
    public void scanBar(View v) {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
            showDialog(Main_Scan.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    //product qr code mode
    public void scanQR(View v) {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
            showDialog(Main_Scan.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    //on ActivityResult method
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                //get the extras that are returned from the intent
                contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format, Toast.LENGTH_LONG);
                toast.show();

                int flag;
                Intent i=getIntent();
                if(i!=null)
                {
                    Log.i("sample","Not Null");
                    String val = i.getStringExtra("val");

                    if((val != null) && val.equals("1"))
                    {
                        //Log.i("sample","Coming grom Required");
                        Intent in = new Intent(getApplicationContext(), StuFac_Inter.class);
                        in.putExtra("contents", contents);
                        startActivity(in);
                        finish();

                    }
                    else if((val != null) && val.equals("2"))
                    {
                        if(contents.contentEquals("http://chandu.890m.com/android_connect/register.php")) {
                            new AlertDialog.Builder(this)
                                    .setTitle("Open Page")
                                    .setMessage("Would you like to open the page in the browser..??")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Uri uri = Uri.parse("http://chandu.890m.com/android_connect/register.php");
                                            Intent positiveActivity = new Intent(Intent.ACTION_VIEW, uri);
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

                        }

                    }
                    else if((val != null) && val.equals("3"))
                    {
                        Intent in = new Intent(getApplicationContext(), StuFac_review.class);
                        in.putExtra("contents", contents);
                        startActivity(in);
                        finish();

                    }

                    else if((val != null) && val.equals("4"))
                    {
                        Intent in = new Intent(getApplicationContext(), Library.class);
                        in.putExtra("contents", contents);
                        startActivity(in);
                        finish();

                    }
                    else if((val != null) && val.equals("5"))
                    {
                        Intent in = new Intent(getApplicationContext(), Attendance.class);
                        in.putExtra("contents", contents);
                        startActivity(in);
                        finish();

                    }
                    else if((val != null) && val.equals("6"))
                    {
                        if(contents.contentEquals("http://chandu.890m.com/android_connect/register.php")) {
                            new AlertDialog.Builder(this)
                                    .setTitle("Open Page")
                                    .setMessage("Would you like to open the page in the browser..??")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Uri uri = Uri.parse("http://chandu.890m.com/android_connect/register.php");
                                            Intent positiveActivity = new Intent(Intent.ACTION_VIEW, uri);
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

                        }

                        finish();

                    }
                    else if((val != null) && val.equals("7"))
                    {
                        Intent in = new Intent(getApplicationContext(), SecurityHome.class);
                        in.putExtra("contents", contents);
                        startActivity(in);
                        finish();

                    }

                }
                else
                {
                    Log.i("sample","Direct Open : Not from any other");
                }

            }
        }
    }

    //alert dialog for downloadDialog
    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }

}
