package com.example.nguyenducnhat.nhatstore.data;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenducnhat.nhatstore.Model.Products;
import com.example.nguyenducnhat.nhatstore.R;
import com.example.nguyenducnhat.nhatstore.activity.ProductsDetail;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.ItemHolder> {
    Context context;
    ArrayList<Products> arrayListNewProduct;

    public NewProductAdapter(Context context, ArrayList<Products> arrayListNewProduct) {
        this.context = context;
        this.arrayListNewProduct = arrayListNewProduct;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_newproduct,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        final Products product = arrayListNewProduct.get(i);
        Log.d("KThu",""+i);
        itemHolder.tvNewProductName.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        itemHolder.tvNewProductPrice.setText("Giá :" +decimalFormat.format(product.getPrice())+" VNĐ");
        Picasso.with(context).load(product.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(itemHolder.imgNewProduct);
        itemHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductsDetail.class);
                intent.putExtra("detail_ID", product.id +"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListNewProduct.size();
    }

    public class  ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imgNewProduct;
        public  TextView tvNewProductName;
        public  TextView tvNewProductPrice;
        public LinearLayout linearLayout;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgNewProduct = (ImageView) itemView.findViewById(R.id.Image_NewProduct);
            tvNewProductName = (TextView) itemView.findViewById(R.id.tv_nameNewProduct);
            tvNewProductPrice = (TextView) itemView.findViewById(R.id.tv_priceNewProduct);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);


        }
    }
}
