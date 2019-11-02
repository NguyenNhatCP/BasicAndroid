package com.example.nguyenducnhat.bai2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText txt_doF, txt_doC;
    Button btn_Doi, btn_Xoa, btn_Thoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddControl();
    }

    private void AddControl() {

        txt_doC = (EditText) findViewById(R.id.edit_DoC);

        txt_doF = (EditText) findViewById(R.id.edit_DoF);

        btn_Doi = (Button) findViewById(R.id.btn_Doi);
        btn_Xoa = (Button) findViewById(R.id.btn_Xoa);
        btn_Thoat = (Button) findViewById(R.id.btn_Thoat);

        btn_Doi.setOnClickListener(new MyEvent());

        btn_Xoa.setOnClickListener(new MyEvent());

        btn_Thoat.setOnClickListener(new MyEvent());

    }

    private class MyEvent implements View.OnClickListener

    {

        @Override

        public void onClick(View v) {
            if (v == btn_Doi) {
                String doF;
                float doC;
                doF = txt_doF.getText().toString();
                Float F = Float.parseFloat(doF);
                doC = (F - 32)*5/9;
                txt_doC.setText(""+doC);

            }
            if(v == btn_Xoa)
            {
                txt_doC.setText("");
                txt_doF.setText("");
            }
            if(v == btn_Thoat)
                finish();

        }
    }
}
