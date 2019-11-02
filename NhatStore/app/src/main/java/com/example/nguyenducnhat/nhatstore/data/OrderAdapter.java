package com.example.nguyenducnhat.nhatstore.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenducnhat.nhatstore.Model.OrderDetails;
import com.example.nguyenducnhat.nhatstore.Model.Orders;
import com.example.nguyenducnhat.nhatstore.R;
import com.example.nguyenducnhat.nhatstore.fragment.Order;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderAdapter extends BaseAdapter {
    Context mCtx;
    int layout;
    SqlLiteDbhelper dbhelper;
    ArrayList<OrderDetails> orderDetails;


    public OrderAdapter(Context mCtx, int layout, ArrayList<OrderDetails> orderDetails) {
        this.mCtx = mCtx;
        this.layout = layout;
        this.orderDetails = orderDetails;
    }

    @Override
    public int getCount() {
        return orderDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return orderDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView STT,MaHD,MaSP,TenKH,Soluong,Gia,Ngay;
        public Button btnRemove;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_oders,null);
            viewHolder.STT = (TextView) view.findViewById(R.id.txtSTT);
            viewHolder.MaHD = (TextView) view.findViewById(R.id.txtMaHD);
            viewHolder.MaSP = (TextView) view.findViewById(R.id.txtMaSP);
            viewHolder.TenKH = (TextView) view.findViewById(R.id.txtTenKH);
            viewHolder.Soluong = (TextView) view.findViewById(R.id.txtSoluong);
            viewHolder.Gia = (TextView) view.findViewById(R.id.txtGiaSp);
            viewHolder.Ngay = (TextView) view.findViewById(R.id.txtNgayDat);
            viewHolder.btnRemove = (Button)view.findViewById(R.id.btnRemove);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }
        final OrderDetails orderDetails = (OrderDetails) getItem(position);
        viewHolder.STT.setText("STT :"+String.valueOf(orderDetails.getSTT()));
        viewHolder.MaHD.setText("Mã ĐH: "+String.valueOf(orderDetails.getMaHD()));
        viewHolder.MaSP.setText("Mã SP :"+String.valueOf(orderDetails.getMaSP()));
        viewHolder.TenKH.setText("Tên KH: "+orderDetails.getTenKH());
        viewHolder.Soluong.setText("Số lượng: "+String.valueOf(orderDetails.getSoluong()));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.Gia.setText("Giá: "+decimalFormat.format(orderDetails.getGia())+" VNĐ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = sdf.parse(orderDetails.getNgay());
            viewHolder.Ngay.setText("Ngày đặt: "+ date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dbhelper = new SqlLiteDbhelper(mCtx);
        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idDetail = orderDetails.getSTT();
                int idOrder = orderDetails.getMaHD();
                dbhelper.deleteOrderDetails(idDetail,idOrder);
                Toast.makeText(mCtx,"Xóa đơn hàng thành công",Toast.LENGTH_SHORT).show();
                Order.loadOrderFromDatabase();
            }
        });
        return view;
    }
}
