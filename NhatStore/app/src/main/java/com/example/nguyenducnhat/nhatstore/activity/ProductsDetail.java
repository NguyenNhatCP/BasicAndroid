package com.example.nguyenducnhat.nhatstore.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenducnhat.nhatstore.Model.Products;
import com.example.nguyenducnhat.nhatstore.Model.cart;
import com.example.nguyenducnhat.nhatstore.R;
import com.example.nguyenducnhat.nhatstore.data.SqlLiteDbhelper;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class ProductsDetail extends AppCompatActivity {
    SqlLiteDbhelper dbhelper;
    List<Products> productsList;
    ImageView imageView;
    TextView tvName,tvMota,tvPrice;
    Button btnAddCart;
    int id = 0;
    String Name,Image = "";
    int Price = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdetail);
        dbhelper = new SqlLiteDbhelper(this.getApplicationContext());
        productsList = new ArrayList<>();
        ActionBar();
        AddEvent();
        loadProductdetailFromDatabase();
    }

    private void ActionBar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarProductdetail);
        toolbar.setTitle(getString(R.string.title_activity_productsdetail));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setFocusable(true);
    }

    private void AddEvent()
    {
        imageView = (ImageView)findViewById(R.id.DetailProductImage);
        tvMota = (TextView)findViewById(R.id.description);
        tvName = (TextView)findViewById(R.id.tv_Name);
        tvPrice = (TextView)findViewById(R.id.tv_price);
        btnAddCart = (Button)findViewById(R.id.cart_button);
        imageView.setFocusable(true);

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.cartList.size() > 0)
                {
                    int quantity = 1;
                    boolean exists = false;
                    for (int i = 0;i<MainActivity.cartList.size();i++)
                    {
                        if(MainActivity.cartList.get(i).getId() == id) {
                            Log.d("ID", MainActivity.cartList.get(i).getId() + "=" + id);
                            MainActivity.cartList.get(i).setQuantity(MainActivity.cartList.get(i).getQuantity() + quantity);
                            if (MainActivity.cartList.get(i).getQuantity() >= 10) {
                                MainActivity.cartList.get(i).setQuantity(10);
                            }
                            MainActivity.cartList.get(i).setPrice(Price * MainActivity.cartList.get(i).getQuantity());
                            exists = true;
                        }
                    }
                    if (exists == false)
                    {
                        int Quantity = 1;
                        double total = Quantity * Price;
                        MainActivity.cartList.add(new cart(id,Name,Price,Image,quantity));
                    }
                }
                else
                {
                    int Quantity = 1;
                   double total = Quantity * Price;
                   MainActivity.cartList.add(new cart(id,Name,Price,Image,Quantity));
                }
                Intent intent = new Intent(getApplication(),Cart.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private int getBundelID() {
        Intent intent = getIntent();
        String ID = intent.getStringExtra("detail_ID");
        Log.d("Search", "" + ID);
        return Integer.parseInt(ID);
    }
    private void loadProductdetailFromDatabase() {

        Cursor product = dbhelper.getproductDetail(getBundelID());
        Log.d("detail_ID", "" + getBundelID());
        dbhelper.openDatabase();
        if (product.moveToFirst()) {
            productsList.clear();
            do {
                productsList.add(new Products(
                        product.getInt(0),
                        product.getString(1),
                        product.getInt(2),
                        product.getString(3),
                        product.getString(4)
                ));
            } while (product.moveToNext());
            product.close();
            dbhelper.close();
        }
        //SHOW DATA
        tvName.setText("Tên sản phẩm: " +productsList.get(0).getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvPrice.setText(decimalFormat.format(productsList.get(0).getPrice())+" VNĐ");
        tvMota.setText("Mô tả: \n \t\t\t\t\t\t"+productsList.get(0).getDescrption());
        //adding data to views
        Picasso.with(getApplicationContext()).load(productsList.get(0).getImage()).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView,new com.squareup.picasso.Callback()
                {

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        //enclose data
        id = productsList.get(0).getId();
        Name = productsList.get(0).getName();
        Image = productsList.get(0).getImage();
        Price = productsList.get(0).getPrice();

        Log.d("test",Name+"");
    }
}
