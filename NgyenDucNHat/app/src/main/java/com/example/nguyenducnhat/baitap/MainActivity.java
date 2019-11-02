package com.example.nguyenducnhat.baitap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnTinhToan, btnPT1, btnTongN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTinhToan = (Button) findViewById(R.id.btn_TinhToan);
        btnPT1 = (Button) findViewById(R.id.btn_PTB1);
        btnTongN = (Button) findViewById(R.id.btn_TongN);
        btnTinhToan.setOnClickListener(this);
        btnPT1.setOnClickListener(this);
        btnTongN.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_TinhToan:
                Intent myintent = new Intent(MainActivity.this, Tinhtoan.class);
                startActivity(myintent);
                break;

            case R.id.btn_PTB1:
                Intent myintent2 = new Intent(MainActivity.this, PTB1.class);
                startActivity(myintent2);
                break;

            case R.id.btn_TongN:
                //whatever
                break;
        }
    }
}
