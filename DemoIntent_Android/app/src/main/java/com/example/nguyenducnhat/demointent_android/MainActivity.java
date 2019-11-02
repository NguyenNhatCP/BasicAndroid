package com.example.nguyenducnhat.demointent_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editName, editBirthDay;
    Button btnShow;
    public static final String Name = "Name";
    public static final String BirthDay = "birthday";
    public static final String BUNDLE = "bundel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = (EditText) findViewById(R.id.txt_Name);
        editBirthDay = (EditText) findViewById(R.id.txt_BirthDay);
        btnShow = (Button) findViewById(R.id.btn_Show);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byExtras();
                byBundle();
            }
        });
    }

    public void byExtras() {
        Intent intent = new Intent(MainActivity.this, Second_Activity.class);
        intent.putExtra(Name, editName.getText().toString());
        intent.putExtra(BirthDay, editBirthDay.getText().toString());
        startActivity(intent);
    }

    public void byBundle() {
        Intent intent = new Intent(MainActivity.this, Second_Activity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Name, editName.getText().toString());
        bundle.putString(BirthDay, editBirthDay.getText().toString());
        intent.putExtra(BUNDLE, bundle);
        startActivity(intent);
    }
}
