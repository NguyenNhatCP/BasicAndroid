package com.example.nguyenducnhat.baitap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Tinhtoan extends AppCompatActivity implements View.OnClickListener {
    EditText txta,txtb;
    Button btnTinh,btnXoa,btnTroVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinhtoan);
        txta = (EditText)findViewById(R.id.txta);
        txtb = (EditText)findViewById(R.id.txtb);
        btnTinh = (Button)findViewById(R.id.btnketqua) ;
        btnTroVe = (Button)findViewById(R.id.btnTroVe);
        btnXoa = (Button)findViewById(R.id.btnXoa);

        btnTinh.setOnClickListener(this);
        btnTroVe.setOnClickListener(this);
        btnXoa.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnketqua:
                Intent myintent = new Intent(Tinhtoan.this, Ketqua1.class);
                Bundle bundle=new Bundle();
                int a=Integer.parseInt(txta.getText().toString());
                int b=Integer.parseInt(txtb.getText().toString());
                bundle.putInt("soa", a);
                bundle.putInt("sob", b);
                //Đưa Bundle vào Intent
                myintent.putExtra("MyTinhToan", bundle);
                startActivity(myintent);
                break;

            case R.id.btnXoa:
                txta.setText("");
                txtb.setText("");
                txta.requestFocus();
                break;

            case R.id.btnTroVe:
                finish();
                break;
        }
    }
}
