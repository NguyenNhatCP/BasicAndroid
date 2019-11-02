package com.example.nguyenducnhat.ptb2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button btnTieptuc, btnGiai, btnThoat;
    EditText edita, editb, editc;
    TextView txtkq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddControl();
    }

    private void AddControl() {

        btnTieptuc = (Button) findViewById(R.id.btn_Tieptuc);

        btnGiai = (Button) findViewById(R.id.btn_Giai);

        btnThoat = (Button) findViewById(R.id.btn_Thoat);

        btnTieptuc.setOnClickListener(new MyEvent());

        btnGiai.setOnClickListener(new MyEvent());

        btnThoat.setOnClickListener(new MyEvent());

        edita = (EditText) findViewById(R.id.edit_NhapA);

        editb = (EditText) findViewById(R.id.edit_NhapB);

        editc = (EditText) findViewById(R.id.edit_NhapC);

        txtkq = (TextView) findViewById(R.id.tv_result);

    }

    public void giaiPtb2()

    {

        String sa = edita.getText() + "";

        String sb = editb.getText() + "";

        String sc = editc.getText() + "";

        int a = Integer.parseInt(sa);

        int b = Integer.parseInt(sb);

        int c = Integer.parseInt(sc);

        String kq = "";

        DecimalFormat dc = new DecimalFormat("#.00");

        if (a == 0)
        {
            if (b == 0)
            {
                if (c == 0)

                    kq = "PT vô số nghiệm";
                else
                    kq = "PT vô nghiệm";
            } else
            {
                kq = "Pt có 1 Nghiệm, x =" + dc.format(-c / b);
            }
        }
        else
        {
            double delta = b * b - 4 * a * c;
            if (delta < 0)
            {
                kq = "PT vô nghiệm";

            } else if (delta == 0)
            {
                kq = "Pt có nghiệm kép x1 = x2 =" + dc.format(-b / (2 * a));
            } else
            {
                kq = "Pt có 2 nghiệm: x1 = " + dc.format((-b - Math.sqrt(delta)) / (2 * a)) +
                        " và x2 = " + dc.format((-b - Math.sqrt(delta)) / (2 * a));
            }
        }
        txtkq.setText(kq);

    }

    private class MyEvent implements View.OnClickListener

    {

        @Override

        public void onClick(View v) {

            if (v == btnTieptuc)

            {

                edita.setText("");

                editb.setText("");

                editc.setText("");

                edita.requestFocus();

            }
            else if (v.getId() == R.id.btn_Giai)
                giaiPtb2();
            else if (v.getId() == R.id.btn_Thoat)
                finish();
        }

    }

}
