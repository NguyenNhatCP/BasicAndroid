package com.example.nguyenducnhat.baitap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class KetQua extends AppCompatActivity {
    TextView soA, soB, soC, Kq;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);
        AddEvent();
        //lấy intent gọi Activity này
        Intent callerIntent = getIntent();
        //có intent rồi thì lấy Bundle dựa vào MyPackage
        Bundle packageFromCaller =
                callerIntent.getBundleExtra("MyPT");
        //Có Bundle rồi thì lấy các thông số dựa vào a,b,c
        float a = packageFromCaller.getFloat("soa");
        float b = packageFromCaller.getFloat("sob");
        float c = packageFromCaller.getFloat("soc");
        //tiến hành xử lý
        giaiPtb2(a,b,c);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AddEvent() {
        btnBack = (Button) findViewById(R.id.btnBack);
        soA = (TextView) findViewById(R.id.giatriA);
        soB = (TextView) findViewById(R.id.giatriB);
        soC = (TextView) findViewById(R.id.giatriC);
        Kq = (TextView) findViewById(R.id.txt_Kq);
    }

    public void giaiPtb2(float a,float b,float c)
    {
        String kq = "";
        DecimalFormat dc = new DecimalFormat("#.00");
        if (a == 0) {
            if (b == 0) {
                if (c == 0)
                    kq = "PT vô số nghiệm";
                else
                    kq = "PT vô nghiệm";
            } else {
                kq = "Pt có 1 Nghiệm, x =" + dc.format(-c / b);
            }
        } else {
            double delta = b * b - 4 * a * c;
            if (delta < 0) {
                kq = "PT vô nghiệm";

            } else if (delta == 0) {
                kq = "Pt có nghiệm kép x1 = x2 =" + dc.format(-b / (2 * a));
            } else {
                kq = "Pt có 2 nghiệm: x1 = " + dc.format((-b + Math.sqrt(delta)) / (2 * a)) +
                        " và x2 = " + dc.format((-b - Math.sqrt(delta)) / (2 * a));
            }
        }
        Kq.setText(kq);
        soA.setText(String.valueOf(a));
        soB.setText(String.valueOf(b));
        soC.setText(String.valueOf(c));
    }
}
