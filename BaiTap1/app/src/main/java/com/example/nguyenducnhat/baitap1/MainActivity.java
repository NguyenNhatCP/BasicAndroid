package com.example.nguyenducnhat.baitap1;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    Button btnBai1,btnBai2,btnBai3;
    TextView tv_Content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddEvent();
    btnBai1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tv_Content.setText("");
            readText("tho1.txt");
        }
    });
    btnBai2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tv_Content.setText("");
            readText("tho2.txt");
        }
    });
    btnBai3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tv_Content.setText("");
            readText("tho3.txt");
        }
    });
    }
    private void AddEvent()
    {
        tv_Content = (TextView)findViewById(R.id.tv_Content);
        btnBai1 = (Button)findViewById(R.id.Bai1);
        btnBai2 = (Button)findViewById(R.id.Bai2);
        btnBai3 = (Button)findViewById(R.id.Bai3);
    }
    public void readText(String text)
    {
        try{

            InputStream in = getAssets().open(text);
            int size = in.available();
            int len = 0;
            byte[] buffer= new  byte[size];
            while ((len = in.read(buffer))>0)
            {
                String mystring = new String(buffer);
                tv_Content.setText(mystring);
            }
            in.close();
        }
        catch (IOException el)
        {
            el.printStackTrace();
        }
    }
}
