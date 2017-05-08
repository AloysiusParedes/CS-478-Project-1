package com.example.com.cs478proj1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DialerActivity extends AppCompatActivity {

    public final int RESULT_NOT_OKAY = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);
    }

    //method for checking what's entered into the phone number text field using regular expresions
    public void verifyPhoneNumber(View view) {
        //create toast message
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast;

        //extract the string from the text view
        final TextView phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        CharSequence phoneText = phoneTextView.getText();

        //valid formats: yyy-zzzz, (xxx) yyy-zzzz, or (xxx)yyy-zzzz
        String regex = "([0-9]{3}-[0-9]{4})|([(][0-9]{3}[)][ ]?[0-9]{3}-[0-9]{4})";

        String phoneNumber = phoneText.toString();
        boolean foundNumber = false;
        int startIndex = 0, endIndex = 0;

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNumber);   //get a matcher object

        //stop once first match has been found, and hold onto indices of the substring
        while(m.find() && !foundNumber){
            if(foundNumber)
                break;

            //debugging
            //System.out.println("start(): " + m.start());
            //System.out.println("end(): " + m.end());

            startIndex = m.start();
            endIndex = m.end();
            foundNumber = true;
        }

        //once valid number is found/not found, display toast, open dialer app if valid, and set result for parent activity
        if(foundNumber) {
            CharSequence status = "Valid Phone Number Entered";
            toast = Toast.makeText(context, status, duration);
            toast.show();

            String number = phoneText.subSequence(startIndex, endIndex).toString();

            //open the dialer if a valid phone number was read
            Intent dial = new Intent(Intent.ACTION_DIAL);
            dial.setData(Uri.parse("tel:" + number));
            startActivity(dial);

            setResult(RESULT_OK, new Intent());
        }
        else{
            CharSequence status = "Invalid Phone Number Entered";
            toast = Toast.makeText(context, status, duration);
            toast.show();

            setResult(RESULT_NOT_OKAY, new Intent());
        }
    }//end verifyPhoneNumber(...)

}//end DialerActivity
