package com.example.sendmessage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etNumbers;
    private EditText etMessage;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        etNumbers = findViewById(R.id.editText_numbers);
        etMessage = findViewById(R.id.editText_message);
        tvResult = findViewById(R.id.textView);

    }

    @SuppressLint("SetTextI18n")
    public void sendMessage(View view) {
        String message = etMessage.getText().toString();
        String result = " ";
        String userInput = etNumbers.getText().toString();
        String numbers[] = userInput.split("\\s+|\n+");
        int i = 1, n=1;
        SmsManager smsManager = SmsManager.getDefault();


        try {

            for (String number : numbers) {
                result = result +n+ ".Sending message to... " + number + "\n ";
                tvResult.setText(result);
                n++;
                smsManager.sendTextMessage(number, null, message, null, null);
            }

        } catch (Exception e) {
            i--;
            tvResult.setText("Result...");
            Toast.makeText(MainActivity.this, "RAQAM VA XABARNI KIRITING!", Toast.LENGTH_SHORT).show();
        }

        if(i==1) {
            Toast.makeText(MainActivity.this, "HABAR YETKAZILDI!", Toast.LENGTH_SHORT).show();
            etNumbers.getText().clear();
        }
    }


}