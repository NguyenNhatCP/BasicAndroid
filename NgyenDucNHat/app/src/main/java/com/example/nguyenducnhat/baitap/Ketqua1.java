package com.example.nguyenducnhat.baitap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ketqua1 extends AppCompatActivity {
    TextView txtCong,txtTru,txtnhan,txtChia;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua1);
        txtCong = (TextView)findViewById(R.id.tv_KqCong) ;
        txtTru = (TextView)findViewById(R.id.tv_KqTru) ;
        txtnhan = (TextView)findViewById(R.id.tv_KqNhan) ;
        txtChia = (TextView)findViewById(R.id.tv_KqChia) ;
        btnBack = (Button) findViewById(R.id.btn_Back) ;

        Intent callerIntent=getIntent();
        Bundle packageFromCaller=
                callerIntent.getBundleExtra("MyTinhToan");
        int a=packageFromCaller.getInt("soa");
        int b=packageFromCaller.getInt("sob");
        giaipt(a,b);
    btnBack.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});

    }
    public void giaipt(int a,int b) {
        String Cong ="";
        Cong = String.valueOf(a + b);
        String Tru ="";
        Tru = String.valueOf(a - b);
        String Nhan ="";
        Nhan = String.valueOf(a * b);
        String Chia ="";
        Chia = String.valueOf(a / b);

        txtCong.setText(Cong);
        txtTru.setText(Tru);
        txtnhan.setText(Nhan);
        txtChia.setText(Chia);


    }
}
