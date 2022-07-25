package com.example.qualification_js22_1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qualification_js22_1.DBOpenHelper;
import com.example.qualification_js22_1.R;
import com.example.qualification_js22_1.fragment.PromptFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PromptActivity extends AppCompatActivity {

    PromptFragment promptFragment = new PromptFragment();

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    EditText number;

    String phoneNum;
    int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        getSupportFragmentManager().beginTransaction().replace(R.id.OTPLayout,promptFragment).commit();
        sum = getIntent().getExtras().getInt("sum");

        if(!checkPermission(Manifest.permission.SEND_SMS))
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.SEND_SMS
                    },SEND_SMS_PERMISSION_REQUEST_CODE);
        }

    }

    public void sendSms(String otp)
    {
        if(checkPermission(Manifest.permission.SEND_SMS))
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNum,null,otp,null,null);
            Toast.makeText(this, "OTP Sent!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
        }
    }


    public boolean checkPermission(String permission)
    {
        int check = ContextCompat.checkSelfPermission(this,permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void setPhoneNum(String phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    public void insert()
    {
        DBOpenHelper.getInstance(this).
        getWritableDatabase().execSQL("DELETE FROM "+DBOpenHelper.CARTS);

        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        overridePendingTransition(0,0);

        SQLiteDatabase db = DBOpenHelper.getInstance(this).getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBOpenHelper.T_TOTAL, (sum + sum/10));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        cv.put(DBOpenHelper.T_TIME, formatter.format(new Date()));

        db.insert(DBOpenHelper.TRANSACTIONS, null,cv);
    }

}