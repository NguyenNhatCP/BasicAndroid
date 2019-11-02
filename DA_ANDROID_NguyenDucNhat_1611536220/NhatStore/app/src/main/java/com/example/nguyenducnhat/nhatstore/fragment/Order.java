package com.example.nguyenducnhat.nhatstore.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.nguyenducnhat.nhatstore.Model.OrderDetails;
import com.example.nguyenducnhat.nhatstore.Model.Orders;
import com.example.nguyenducnhat.nhatstore.Model.Products;
import com.example.nguyenducnhat.nhatstore.R;
import com.example.nguyenducnhat.nhatstore.data.OrderAdapter;
import com.example.nguyenducnhat.nhatstore.data.SqlLiteDbhelper;

import java.util.ArrayList;

public class  Order extends Fragment {
    static ListView lsvOrder;
    static SqlLiteDbhelper dbhelper;
    private static int id;
    static ArrayList<OrderDetails> orderDetails;
    static OrderAdapter orderAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_oders, container, false);
        dbhelper = new SqlLiteDbhelper(this.getContext());
        orderDetails = new ArrayList<>();
        lsvOrder = (ListView) v.findViewById(R.id.lstOrders);
        AddEvent(v);
        orderAdapter = new OrderAdapter(getContext(),R.layout.item_oders, orderDetails);
        loadOrderFromDatabase();
        return v;
    }
    private  void AddEvent(View v)
    {
        lsvOrder = (ListView)v.findViewById(R.id.lstOrders);
    }
    public static void loadOrderFromDatabase() {
        Cursor orderdetail = dbhelper.getoderDetails();
        dbhelper.openDatabase();
        if (orderdetail.moveToFirst()) {
            orderDetails.clear();
            do {
                orderDetails.add(new OrderDetails(
                        orderdetail.getInt(0),
                        orderdetail.getInt(1),
                        orderdetail.getInt(2),
                        orderdetail.getString(3),
                        orderdetail.getInt(4),
                        orderdetail.getInt(5),
                        orderdetail.getString(6)

                ));
            } while (orderdetail.moveToNext());
            orderdetail.close();
            dbhelper.close();
            orderAdapter.notifyDataSetChanged();
            lsvOrder.setAdapter(orderAdapter);
        }
    }
}
