package com.example.nguyenducnhat.nhatstore.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nguyenducnhat.nhatstore.Model.Orders;
import com.example.nguyenducnhat.nhatstore.R;
import com.example.nguyenducnhat.nhatstore.data.OrderAdapter;
import com.example.nguyenducnhat.nhatstore.data.SqlLiteDbhelper;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomerOder extends AppCompatActivity {
    SqlLiteDbhelper dbhelper;
    EditText txtName,txtPhone,txtEmail;
    Button btnConfirm,btnBack;
    DatePicker dateOder;
    ArrayList<Orders> orderList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor_oder);
        orderList = new ArrayList<>();
        dbhelper = new SqlLiteDbhelper(this.getApplicationContext());
        AddEvent();
    }
    private void AddEvent()
    {
        txtName = (EditText)findViewById(R.id.txt_CustomerName);
        txtPhone = (EditText)findViewById(R.id.txt_CustomerPhone);
        txtEmail = (EditText)findViewById(R.id.txt_CustomerEmail);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString().trim();
                String phone = txtPhone.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String currentDateandTime = sdf.format(new Date());
                if(name.equals("") || phone.equals("") || email.equals("")) {
                    AlertCheckData();
                    txtName.requestFocus();
                }
                else if (name.length() < 7)
                {
                    AlertCheckName();
                    txtPhone.requestFocus();
                }
                else if (phone.length() > 10)
                {
                    AlertCheckPhone();
                    txtPhone.requestFocus();
                }
                else
                {
                    Log.d("Cus", currentDateandTime + "");
                    boolean bl = dbhelper.insertOrder(name, Integer.parseInt(phone), email, sdf);
                    if (bl) {
                        FancyToast.makeText(getApplicationContext(), "Thêm thành công đơn hàng", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                        for (int i = 0; i < MainActivity.cartList.size(); i++) {
                            int orderID = 0;
                            Cursor order = dbhelper.getoderID();
                            dbhelper.openDatabase();
                            if (order.moveToFirst()) {
                                orderList.clear();
                                do {
                                    orderList.add(new Orders(
                                            order.getInt(0)
                                    ));
                                    orderID = order.getInt(0);
                                    Log.d("Cus", order.getInt(0) + "");
                                } while (order.moveToNext());
                                order.close();
                                dbhelper.close();
                            }
                            Log.d("orderID", orderID + "");
                            boolean bl1 = dbhelper.insertOrderDetail(MainActivity.cartList.get(i).getId(), orderID, MainActivity.cartList.get(i).getQuantity(), MainActivity.cartList.get(i).getPrice());
                            if (bl1) {
                                Toast.makeText(getApplicationContext(), "Thêm chi tiết đơn hàng thành công", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(getApplicationContext(), "Thêm chi tiết đơn hàng không thành công", Toast.LENGTH_SHORT).show();
                        }
                        MainActivity.cartList.clear();
                    } else
                        FancyToast.makeText(getApplicationContext(), "Thêm đơn hàng  không thành công", FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    Intent intent = new Intent(CustomerOder.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void AlertCheckData()
    {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Vui lòng điền đầy đủ thông tin");
        dlgAlert.setTitle("Thông báo");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
    private void AlertCheckName()
    {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Tên ít nhất 7 kí tự");
        dlgAlert.setTitle("Thông báo");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
    private void AlertCheckPhone()
    {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Số điện thoại không được lớn hơn 10 số");
        dlgAlert.setTitle("Thông báo");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}
