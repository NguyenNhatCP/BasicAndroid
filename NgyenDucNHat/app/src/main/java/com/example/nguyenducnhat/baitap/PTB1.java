package com.example.nguyenducnhat.baitap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PTB1 extends AppCompatActivity {
    EditText txta,txtb;
    Button btnketqua,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptb1);
        txta=(EditText) findViewById(R.id.txta);
        txtb=(EditText) findViewById(R.id.txtb);
        btnketqua=(Button) findViewById(R.id.btnketqua);
        btnBack =(Button)findViewById(R.id.btnBack) ;
        btnketqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent=new Intent(PTB1.this, Ketqua2.class);
                Bundle bundle=new Bundle();
                int a=Integer.parseInt(txta.getText().toString());
                int b=Integer.parseInt(txtb.getText().toString());
                bundle.putInt("soa", a);
                bundle.putInt("sob", b);
                //Đưa Bundle vào Intent
                myIntent.putExtra("MyPTB1", bundle);
                startActivity(myIntent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
