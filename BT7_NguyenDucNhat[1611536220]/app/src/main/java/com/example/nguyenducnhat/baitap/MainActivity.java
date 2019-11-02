package com.example.nguyenducnhat.baitap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnExit;
    private List<DataItem> dataItems;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnExit = (Button)findViewById(R.id.btnExit);

        listView = (ListView)findViewById(R.id.idListView);
        dataItems = new ArrayList<>();
        dataItems.add(new DataItem(1,1,2,-3));
        dataItems.add(new DataItem(2,3,2,-5));
        dataItems.add(new DataItem(3,5,-8,10));
        dataItems.add(new DataItem(4,-10,8,2));

        CustomAdapter adapter = new CustomAdapter(this,R.layout.itemrow,dataItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Tạo Intent để mở ResultActivity
                Intent myIntent=new Intent(MainActivity.this, KetQua.class);
                //Khai báo Bundle
                Bundle bundle=new Bundle();
                float a= dataItems.get(position).getA();
                float b= dataItems.get(position).getB();
                float c= dataItems.get(position).getC();
                //đưa dữ liệu riêng lẻ vào Bundle
                bundle.putFloat("soa", a);
                bundle.putFloat("sob", b);
                bundle.putFloat("soc",c);
                //Đưa Bundle vào Intent
                myIntent.putExtra("MyPT", bundle);
                //Mở Activity ResultActivity
                startActivity(myIntent);

            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
