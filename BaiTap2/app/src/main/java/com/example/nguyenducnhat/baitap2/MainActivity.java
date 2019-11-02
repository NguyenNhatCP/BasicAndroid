package com.example.nguyenducnhat.baitap2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtuserName, edtpw;
    CheckBox cbsave;
    Button btnLogin;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddEvent();

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        edtuserName.setText(sharedPreferences.getString("account", ""));
        edtpw.setText(sharedPreferences.getString("password", ""));
        cbsave.setChecked(sharedPreferences.getBoolean("checked", false));
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtuserName.getText().toString().trim();
                String pw = edtpw.getText().toString().trim();

                if(username.equalsIgnoreCase("admin") && pw.equals("123")) {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    if (cbsave.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("account", username);
                        editor.putString("password", pw);
                        editor.putBoolean("checked", true);
                        editor.commit();
                    } else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("account");
                        editor.remove("password");
                        editor.remove("checked");
                        editor.commit();
                    }
                }
                else Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void AddEvent()
    {
        edtuserName  = (EditText) findViewById(R.id.edt_username);
        edtpw= (EditText) findViewById(R.id.edt_pw);
        cbsave = (CheckBox) findViewById(R.id.cb_save);
        btnLogin = (Button) findViewById(R.id.btn_Login);
    }

}
