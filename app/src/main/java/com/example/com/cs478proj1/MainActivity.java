package com.example.com.cs478proj1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "MainActivity";
    static final int DIALER_REQUEST_CODE = 1;  //the request code for child activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //text view of the status of the resulting activity
        final TextView statusTextView = (TextView) findViewById(R.id.statusTextView);
        statusTextView.setText("");

    }//end onCreate(...)

    //handles click event for dialer button
    public void openDialer(View view) {
        //display toast message
        Context context = getApplicationContext();
        CharSequence text = "Dialer Button Pressed";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //initialize and switch to the new activity
        Intent dialerIntent = new Intent(this, DialerActivity.class);
        //startActivity(dialerIntent);
        startActivityForResult(dialerIntent, DIALER_REQUEST_CODE);
    }//end openDialer(...)

    //handle click event for Android Docs button
    public void openAndroidDocs(View view) {
        //display toast message
        Context context = getApplicationContext();
        CharSequence text = "Android Docs Button Pressed";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //open browser and go to Android Docs page
        Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
        openURL.setData(Uri.parse("https://developer.android.com/index.html"));
        startActivity(openURL);
    }//end openAndroidDocs(...)

    //this method is called when the child activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final TextView statusTextView = (TextView) findViewById(R.id.statusTextView);

        //check status of child activity once returned to this activity
        if (requestCode == DIALER_REQUEST_CODE) {
            //set the text view accordingly
            if (resultCode == RESULT_OK)
                statusTextView.setText(R.string.valid_phone_number);
            else
                statusTextView.setText(R.string.invalid_phone_number);
        }
    }//end onActivityResult(...)

    //creates the menu for this activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }//end onCreateOptionsMenu(...)

    //handles click events for menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_about:
                openAbout();
        }

        return super.onOptionsItemSelected(item);
    }//end onOptionsItemSelected(...)

    //move to about activity
    public void openAbout(){
        //display toast message
        Context context = getApplicationContext();
        CharSequence text = "About Menu Item Pressed";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //initialize and switch to the new activity
        Intent aboutIntent = new Intent(this, AboutActivity.class);
        //startActivity(dialerIntent);
        startActivity(aboutIntent);
    }//end openAbout()

}//end MainActivity