package com.example.nguyenducnhat.demointent_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Second_Activity extends AppCompatActivity {
    TextView txt_Name,txt_Birthday;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        txt_Name = (TextView)findViewById(R.id.txt_Name);
        txt_Birthday = (TextView)findViewById(R.id.txt_BirthDay);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(MainActivity.BUNDLE);
            if (bundle != null) {
                txt_Name.setText(bundle.getString(MainActivity.Name));
                txt_Birthday.setText(bundle.getString(MainActivity.BirthDay));
            } else {
                txt_Name.setText(intent.getStringExtra(MainActivity.Name));
                txt_Birthday.setText(intent.getStringExtra(MainActivity.BirthDay));
            }
        }
    }
}
