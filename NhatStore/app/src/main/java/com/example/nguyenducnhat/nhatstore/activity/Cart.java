package com.example.nguyenducnhat.nhatstore.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenducnhat.nhatstore.R;
import com.example.nguyenducnhat.nhatstore.data.CartAdapter;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.text.DecimalFormat;

public class Cart extends AppCompatActivity {
    private static ListView lstCart;
    private static TextView tv_Noteice;
    private Button btnOder,btnContinous;

    static TextView totalPrice;
    ScrollView scrollView;
    static CartAdapter cartAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        AddEvent();
        CheckCart();
        ActionBar();
        EventTT();
        RemoveCartItem();
    }

    private void RemoveCartItem() {
        lstCart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,final int postision, long id) {
                Log.i("co","asaf");
                AlertDialog.Builder builder = new AlertDialog.Builder(Cart.this);
                builder.setTitle("Xóa sản phẩm");
                builder.setMessage("Bạn có muốn xóa sản phẩm này");
                builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if(MainActivity.cartList.size() <= 0)
                        {
                            tv_Noteice.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            MainActivity.cartList.remove(postision);
                            cartAdapter.notifyDataSetChanged();
                            EventTT();
                            if(MainActivity.cartList.size() <= 0)
                            {
                                tv_Noteice.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                tv_Noteice.setVisibility(View.INVISIBLE);
                                cartAdapter.notifyDataSetChanged();
                                EventTT();
                            }

                        }
                    }
                });
                builder.setPositiveButton("không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cartAdapter.notifyDataSetChanged();
                        EventTT();
                    }
                });
                builder.show();
                return true;
            }
        });
    }
    public static void EventTT()
    {
        long totalPrices = 0;
        for(int i = 0;i < MainActivity.cartList.size(); i++)
        {
         totalPrices += MainActivity.cartList.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        totalPrice.setText(decimalFormat.format(totalPrices) +" VNĐ");
    }
    public static void CheckCart()
    {
        if(MainActivity.cartList.size() <= 0)
        {
            cartAdapter.notifyDataSetChanged();
            tv_Noteice.setVisibility(View.VISIBLE);
            lstCart.setVisibility(View.INVISIBLE);
        }
        else {
            cartAdapter.notifyDataSetChanged();
            tv_Noteice.setVisibility(View.INVISIBLE);
            lstCart.setVisibility(View.VISIBLE);
        }
    }
    private void AddEvent()
    {
        lstCart = (ListView)findViewById(R.id.cart_listview);
        totalPrice = (TextView)findViewById(R.id.totalPrice);
        tv_Noteice = (TextView)findViewById(R.id.cartEmpty);
        scrollView = (ScrollView)findViewById(R.id.Cartscollview);
        btnOder = (Button)findViewById(R.id.button_oder);
        btnContinous = (Button)findViewById(R.id.button_continuousShop);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        cartAdapter = new CartAdapter(Cart.this,MainActivity.cartList);
        lstCart.setAdapter(cartAdapter);

        btnOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.cartList.size() >0) {
                    Intent intent = new Intent(Cart.this, CustomerOder.class);
                    startActivity(intent);
                }
                else
                {
                    FancyToast.makeText(getApplicationContext(),"Giỏ hàng rỗng không thể thanh toán",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                }
            }
        });
        btnContinous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void ActionBar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCart);
        toolbar.setTitle(getString(R.string.title_activity_Cart));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setFocusable(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.action_notifications);
        MenuItem items = menu.findItem(R.id.delete);
        if (MainActivity.cartList.size() > 0) {
            item.setVisible(false);
            items.setVisible(true);
        } else {
            item.setVisible(false);
            items.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                MainActivity.cartList.clear();
                cartAdapter.notifyDataSetChanged();
                CheckCart();
                EventTT();
                this.recreate();

                return true;
            default:
                Toast.makeText(this,"Cart Empty",Toast.LENGTH_LONG);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
