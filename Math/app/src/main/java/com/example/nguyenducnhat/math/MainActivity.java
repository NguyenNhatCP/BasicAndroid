package com.example.nguyenducnhat.math;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txt_NhapA,txt_NhapB;
    TextView tv_Result;
    Button btnPlus,btnMinus,btnMulti,btnDevide,btnUSCLN,btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddControls();
    }
    private void AddControls(){
        txt_NhapA = (EditText)findViewById(R.id.edit_NhapA);
        txt_NhapB = (EditText)findViewById(R.id.edit_NhapB);
        tv_Result = (TextView)findViewById(R.id.tv_result);
        btnPlus = (Button)findViewById(R.id.btn_Plus);
        btnMinus = (Button)findViewById(R.id.btn_minus);
        btnMulti = (Button)findViewById(R.id.btn_Multi);
        btnDevide = (Button)findViewById(R.id.btn_devide);
        btnUSCLN = (Button)findViewById(R.id.btn_USCLN);
        btnExit = (Button)findViewById(R.id.btn_Exit);

        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
        btnDevide.setOnClickListener(this);
        btnUSCLN.setOnClickListener(this);
        btnExit.setOnClickListener(this);

    }
    private boolean checkData()
    {
        if(txt_NhapA.getText().toString().equals(""))
        {
            tv_Result.setText("Request invalue number A");
            txt_NhapA.requestFocus();
            return false;

        }
        if(txt_NhapB.getText().toString().equals(""))
        {
            tv_Result.setText("Request invalue number B");
            txt_NhapB.requestFocus();
            return false;
        }
        return true;
    }
    private boolean checkDevide()
    {
        if(txt_NhapB.getText().toString().equals("0"))
        {
            tv_Result.setText("Cannot devided by 0");
            txt_NhapB.setText("");
            txt_NhapB.requestFocus();
            return  false;
        }
        return  true;
    }
    private boolean checkUSC(int n, int m)
    {
        if (n <= 0 || m <= 0)
            return false;
        return true;
    }
    private int USCLN(int a,int b) {
    while (a != b) {
        if (a > b)
            a = a - b;
        else
            b = b - a;
    }
    return a;
}
    @Override
    public void onClick(View v) {
    switch (v.getId()) {
        case R.id.btn_Plus:
            if (checkData()) {
                double kq;
                kq = Double.parseDouble(txt_NhapA.getText().toString()) + Double.parseDouble(txt_NhapB.getText().toString());
                tv_Result.setText(String.valueOf(kq));
            }
            break;
        case R.id.btn_minus: {
            if (checkData()) {
                double kq;
                kq = Double.parseDouble(txt_NhapA.getText().toString()) - Double.parseDouble(txt_NhapB.getText().toString());
                tv_Result.setText(String.valueOf(kq));
            }
            break;
        }
        case R.id.btn_Multi: {
            if (checkData()) {
                double kq;
                kq = Double.parseDouble(txt_NhapA.getText().toString()) * Double.parseDouble(txt_NhapB.getText().toString());
                tv_Result.setText(String.valueOf(kq));
            }
            break;
        }
        case R.id.btn_devide: {
            if (checkData()) {
                if(checkDevide()) {
                    double kq;
                    kq = Double.parseDouble(txt_NhapA.getText().toString()) / Double.parseDouble(txt_NhapB.getText().toString());
                    tv_Result.setText(String.valueOf(kq));
                }
            }
            break;
        }
        case R.id.btn_USCLN:
            int a, b;
            a = Integer.parseInt(txt_NhapA.getText().toString());
            b = Integer.parseInt(txt_NhapB.getText().toString());
            if(checkData())
            {
                if(checkUSC(a,b)) {
                    int Result = USCLN(a, b);
                    tv_Result.setText(String.valueOf(Result));
                }
                else
                tv_Result.setText("The number is not suitable! Please try again!");
            }
            break;
        case R.id.btn_Exit:
            finish();
        }
    }
}
