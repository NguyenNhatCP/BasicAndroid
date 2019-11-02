package com.example.nguyenducnhat.bai1;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button login;
    TextView tv_Thongbao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.edit_user);
        password=findViewById(R.id.edit_pass);
        login=findViewById(R.id.btn_login);
        tv_Thongbao = findViewById(R.id.tv_Infor);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin")&& password.getText().toString().equals("admin"))
                {
                    tv_Thongbao.setText("Đăng nhập thành công.");
                    tv_Thongbao.setTextColor(Color.parseColor("#99FFFF"));
                }
                else {
                    tv_Thongbao.setText("Đăng nhập không thành công.");
                    tv_Thongbao.setTextColor(Color.parseColor("#FF0000"));
                }

            }
        });
    }
}
