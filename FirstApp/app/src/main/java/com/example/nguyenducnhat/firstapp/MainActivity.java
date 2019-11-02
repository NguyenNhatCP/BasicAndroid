package com.example.nguyenducnhat.firstapp;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nguyenducnhat.firstapp.View.mathFragment;

public class MainActivity extends AppCompatActivity {


    //EditText medit;
    //Button mbtn;
    //TextView mtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        loadFragmentFirst();
       /* medit = (EditText) findViewById(R.id.editName);
        mbtn = (Button) findViewById(R.id.btn_Chao);
        mtxt = (TextView) findViewById(R.id.txt_Show);

        View.OnClickListener listener = new View.OnClickListener()
        {
         @Override
         public  void onClick(View v)
         {
          String name = medit.getText().toString();
          String text = "Helo" + name +" !";
          mtxt.setText(text);
         }
        };
        mbtn.setOnClickListener(listener);
    }
    */
    }
    private void loadFragmentFirst() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content,new mathFragment(),null);
        transaction.commit();
    }

}
