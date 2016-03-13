package com.example.siri.qr_code;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity
{
Button Login_main, Reg_main;
  int status=1;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Login_main = (Button) findViewById(R.id.Login_main);
        Login_main.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putInt("status", status);
                Intent i = new Intent(v.getContext(), Sign_In.class);
                i.putExtras(b);
                startActivityForResult(i, 1);
            }

    });
        Reg_main = (Button) findViewById(R.id.Reg_main);
        Reg_main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putInt("status", status);
                Intent i = new Intent(v.getContext(), Sign_Up.class);
                i.putExtras(b);
                startActivityForResult(i, 2);
            }

        });
        }


/*
        private void addListenerOnButton() {
            if (status == 1) {
                final Context context = this;
                Login_main = (Button) findViewById(R.id.Login_main);


                });
            }
            else if(status==2)
            {
                final Context context=this;
                Reg_main = (Button) findViewById(R.id.Reg_main);
                Reg_main.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {

                        Bundle b = new Bundle();
                b.putInt("status",status);
                Intent i = new Intent(context, Sign_Up.class);
                i.putExtras(b);
                startActivity(i);
                     }

                     }

            });

        }


    private void addListenerOnButton()
    {
        final Context context=this;
        Reg_main = (Button) findViewById(R.id.Reg_main);
        Reg_main.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                status=1;
                Bundle b = new Bundle();
                b.putInt("status",status);
                Intent i = new Intent(context, Sign_Up.class);
                i.putExtras(b);
                startActivity(i);

            }
        });

    )


*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
