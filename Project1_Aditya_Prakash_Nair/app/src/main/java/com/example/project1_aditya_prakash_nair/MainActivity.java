package com.example.project1_aditya_prakash_nair;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected Button saveNumber;  // Button instance which navigates to Save Number Activity.
    protected Button callNumber;  // Button instance which navigates to Phone Dialer
    public String phoneNumber = "";  // Stores the number which is entered by user.
    private boolean isValidNumber = false;  // Boolean variable indicating whether the number entered
                                            // by user is valid


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveNumber = (Button) findViewById(R.id.numberSaveButton);
        callNumber = (Button) findViewById(R.id.phoneButton);

        saveNumber.setOnClickListener(saveNumberListener);
        callNumber.setOnClickListener(callNumberListener);

//        ActivityResultLauncher saveNumberActivity = registerForActivityResult(new SaveNumberActivity(),
//                new ActivityResultCallback<Uri>() {
//                    @Override
//                    public void onActivityResult(Uri result) {
//                        Log
//                    }
//                });
    }

    /**
     * Event Listener for Save Number Button
     */
    public View.OnClickListener saveNumberListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switchToSaveNumberActivity();
        }
    };

    /**
     * Event Listener for Phone Dialler Button
     */
    public View.OnClickListener callNumberListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switchToDialerActivity();
        }
    };

    /**
     * This method will be invoked when the user saves number i.e., when the SaveNumber activity
     *  sends the intent back to Main Activity.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Context context = getApplicationContext();
        if(resultCode == RESULT_OK) {
//            Log.i(data.getExtras().getString("Number"), "test");
            isValidNumber = true;
            Toast.makeText(context, "The Phone Number is in CORRECT format", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(context, "The Phone Number is of INVALID format", Toast.LENGTH_LONG).show();
            isValidNumber = false;
        }
        phoneNumber = data.getExtras().getString("Number");
    }

    /**
     * This method will start Save Number Activity for user to save phone number.
     */
    private void switchToSaveNumberActivity(){
        Intent intent = new Intent(this, SaveNumberActivity.class);
        startActivityForResult(intent, 1);
    }

    /**
     * This method will redirect user to Phone Dialer provided the user has
     *  save a phone number which is valid
     */
    private void switchToDialerActivity() {
        if(!isValidNumber){
            Context context = getApplicationContext();
            // If user has not saved the number, the app will show a toast message stating :
            // "Please Save a Phone Number".
            // If the user has saved invalid number, the app will show a toast message stating:
            // "The save number : ${number saved by user} is invalid
            String toastMessage = phoneNumber.length() == 0 ? "Please Save a Phone Number" :
                    "The saved number : " + phoneNumber + " is of INVALID format";
            Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show();
        } else {
            Intent dialerIntent = new Intent(Intent.ACTION_DIAL);
            dialerIntent.setData(Uri.parse("tel: " + phoneNumber));
            startActivity(dialerIntent);
        }
    }
}