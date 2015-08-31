package com.test.socialauth;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends ActionBarActivity {
    public static SocialAuthAdapter adapter;
    public void signout()
    {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean s=isNetworkAvailable();
        if(!s)
            Toast.makeText(this,"Please Enable Internet Connection",Toast.LENGTH_LONG).show();
        adapter = new SocialAuthAdapter(new ResponseListener());

    }

    private class ResponseListener implements DialogListener {

        @Override
        public void onComplete(final Bundle values) {

            adapter.getUserProfileAsync(new ProfileDataListener());
        }

        @Override
        public void onError(SocialAuthError error) {
        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onBack() {
        }
    }

    private final class ProfileDataListener implements SocialAuthListener<Profile> {


        @Override
        public void onExecute(String s, Profile profile) {
            Log.d("Custom-UI", "Receiving Data");
            Profile profileMap = profile;
            Log.d("Custom-UI", "Validate ID         = " + profileMap.getValidatedId());
            Log.d("Custom-UI", "First Name          = " + profileMap.getFirstName());
            Log.d("Custom-UI", "Last Name           = " + profileMap.getLastName());
            Log.d("Custom-UI", "Email               = " + profileMap.getEmail());
            Log.d("Custom-UI", "Gender                   = " + profileMap.getGender());
            Log.d("Custom-UI", "Country                  = " + profileMap.getCountry());
            Log.d("Custom-UI", "Language                 = " + profileMap.getLanguage());
            Log.d("Custom-UI", "Location                 = " + profileMap.getLocation());
            Log.d("Custom-UI", "Profile Image URL  = " + profileMap.getProfileImageURL());

           nextpage(profileMap.getFirstName().toString(),profileMap.getLastName().toString(),profileMap.getEmail().toString());
        }

        @Override
        public void onError(SocialAuthError socialAuthError) {
            Log.w("error",socialAuthError);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void nextpage(String firstname,String lastname,String email){
        Intent intent = new Intent(this, home_screen.class);
        intent.putExtra("fname",firstname );
        intent.putExtra("lname",lastname );
        intent.putExtra("email",email );
        startActivity(intent);
    }
    public  void face(View v){
        adapter.authorize(this, SocialAuthAdapter.Provider.FACEBOOK);

    }

    public  void google(View v){
        adapter.authorize(this, SocialAuthAdapter.Provider.GOOGLEPLUS);
    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

}
