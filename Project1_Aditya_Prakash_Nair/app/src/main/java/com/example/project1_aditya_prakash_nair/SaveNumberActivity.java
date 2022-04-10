package com.example.project1_aditya_prakash_nair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class SaveNumberActivity extends AppCompatActivity {
    protected EditText numberInput;

    private static final Pattern validPhoneNumberPattern
            = Pattern.compile("^\\d{9}|\\(\\d{3}\\)\\s\\d{3}-\\d{3}|\\d{3}\\s\\d{3}\\s\\d{3}$");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_number);

        numberInput = (EditText) findViewById(R.id.number_input); // THe text field where user can enter
                                                                  // the phone number

        numberInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
//                Log.i("Action Id" + actionId, "test");
                // This if condition is executed when user presses the "Enter" Key of keyboard
                // or user presses the done key of soft keyboard
                if((keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)
                || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    onDoneClick();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * This method ends the activity and sends the user entered number
     * to main activity as intent
     */
    private void onDoneClick(){
        String enteredNumber = numberInput.getText().toString();
        Intent data = new Intent();
        data.putExtra("Number",enteredNumber);
        if(validateNumber(enteredNumber)){
            setResult(RESULT_OK, data);
        } else {
            setResult(RESULT_CANCELED, data);
        }
        finish();

    }

    /**
     *
     * @param number : The number entered by user
     * @return boolean variable indicating whether the entered is in valid format using regex.
     */
    private boolean validateNumber(String number){
        return validPhoneNumberPattern.matcher(number).matches();
    }

}
