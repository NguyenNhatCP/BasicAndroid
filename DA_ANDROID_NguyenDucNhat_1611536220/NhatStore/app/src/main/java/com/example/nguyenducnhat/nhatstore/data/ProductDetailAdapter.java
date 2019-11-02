package com.example.nguyenducnhat.nhatstore.data;

import android.content.Context;
import android.graphics.ColorSpace;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyenducnhat.nhatstore.Model.Products;
import com.example.nguyenducnhat.nhatstore.R;
import com.example.nguyenducnhat.nhatstore.fragment.Product;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.List;

public class ProductDetailAdapter extends ArrayAdapter<Products> {
    Context mCtx;
    int listLayoutRes;
    List<Products> productList;
    SqlLiteDbhelper dbhelper;

    public ProductDetailAdapter(Context mCtx, int listLayoutRes, List<Products> productList, SqlLiteDbhelper dbhelper) {
        super(mCtx, listLayoutRes,productList);
        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.productList = productList;
        this.dbhelper = dbhelper;
    }

    @NotNull
    @Override
    public View getView(int position, @Nullable View convertView, @NotNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        //getting employee of the specified position
        Products product = productList.get(position);
        Log.d("demo", position + "");


        //getting views
        ImageView imgView = view.findViewById(R.id.DetailProductImage);
        TextView textViewdescription = view.findViewById(R.id.description);
        TextView textViewPrice = view.findViewById(R.id.tv_price);


        //adding data to views
        Picasso.with(getContext()).load(product.getImage()).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imgView,new com.squareup.picasso.Callback()
                {

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
        imgView.setScaleType(ImageView.ScaleType.FIT_XY);
        textViewdescription.setText(product.getDescrption());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textViewPrice.setText(decimalFormat.format(product.getPrice())+" VNĐ");

        return view;
    }
}
