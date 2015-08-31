package com.test.socialauth;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.brickred.socialauth.android.SocialAuthAdapter;


public class home_screen extends ActionBarActivity {
    public static SocialAuthAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Intent intent = getIntent();
        String fname = intent.getStringExtra("fname");
        String lname = intent.getStringExtra("lname");
        String email = intent.getStringExtra("email");
        TextView fname_value=(TextView)findViewById(R.id.fname_value);
        TextView lname_value=(TextView)findViewById(R.id.lname_value);
        TextView email_value=(TextView)findViewById(R.id.email_value);
        fname_value.setText(fname);
        lname_value.setText(lname);
        email_value.setText(email);

    }
    public void logout(View v)
    {
        MainActivity.adapter.signOut(this,"facebook");
        MainActivity.adapter.signOut(this,"googleplus");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
