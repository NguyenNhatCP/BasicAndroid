package com.example.nguyenducnhat.nhatstore.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenducnhat.nhatstore.Model.cart;
import com.example.nguyenducnhat.nhatstore.R;
import com.example.nguyenducnhat.nhatstore.activity.Cart;
import com.example.nguyenducnhat.nhatstore.activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    Context mCtx;
    ArrayList<cart> cartArrayList;

    public CartAdapter(Context mCtx, ArrayList<cart> cartArrayList) {
        this.mCtx = mCtx;
        this.cartArrayList = cartArrayList;
    }


    @Override
    public int getCount() {
        return cartArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView cartName,cartPrice,cartQuantity;
        public ImageView imageViewCart;
        public Button btnminus,btnplush,btnRemove;
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cart_item,null);
            viewHolder.cartName = (TextView) view.findViewById(R.id.CartName);
            viewHolder.cartQuantity = (TextView) view.findViewById(R.id.quantity_Cart);
            viewHolder.cartPrice = (TextView) view.findViewById(R.id.CartPrice);
            viewHolder.btnminus =(Button) view.findViewById(R.id.decrementCart_button);
            viewHolder.btnplush = (Button) view.findViewById(R.id.incrementCart_button);
            viewHolder.imageViewCart = (ImageView) view.findViewById(R.id.cartImage);
            viewHolder.btnRemove = (Button)view.findViewById(R.id.btnRemove);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }
        cart cart = (com.example.nguyenducnhat.nhatstore.Model.cart) getItem(position);
        viewHolder.cartName.setText(cart.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.cartPrice.setText(decimalFormat.format(cart.getPrice()) + "VNĐ");
        Picasso.with(mCtx).load(cart.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(viewHolder.imageViewCart);
        viewHolder.cartQuantity.setText(cart.getQuantity() +"");
        int quantity = Integer.parseInt(viewHolder.cartQuantity.getText().toString());
        if(quantity >= 10)
        {
            viewHolder.btnplush.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }
        else if(quantity <=1)
        {
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
        }
        else if(quantity >= 1)
        {
            viewHolder.btnplush.setVisibility(View.VISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }
        final ViewHolder finalViewHolder = viewHolder;

        viewHolder.btnplush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = Integer.parseInt(finalViewHolder.cartQuantity.getText().toString()) + 1;
                int currentQuantity = MainActivity.cartList.get(position).getQuantity();
                int currentPrice = MainActivity.cartList.get(position).getPrice();
                MainActivity.cartList.get(position).setQuantity(newQuantity);
                int newPrice = (currentPrice * newQuantity)/currentQuantity;
                MainActivity.cartList.get(position).setPrice(newPrice);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.cartPrice.setText(decimalFormat.format(newPrice) + "VNĐ");
                Cart.EventTT();
                if(newQuantity > 9)
                {
                    finalViewHolder.btnplush.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.cartQuantity.setText(String.valueOf(newQuantity));
                }
                else
                {
                    finalViewHolder.btnplush.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.cartQuantity.setText(String.valueOf(newQuantity));
                }
            }
        });
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = Integer.parseInt(finalViewHolder.cartQuantity.getText().toString()) - 1;
                int currentQuantity = MainActivity.cartList.get(position).getQuantity();
                int currentPrice = MainActivity.cartList.get(position).getPrice();
                MainActivity.cartList.get(position).setQuantity(newQuantity);
                int newPrice = (currentPrice * newQuantity)/currentQuantity;
                MainActivity.cartList.get(position).setPrice(newPrice);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.cartPrice.setText(decimalFormat.format(newPrice) + "VNĐ");
                Cart.EventTT();
                if(newQuantity < 2)
                {
                    finalViewHolder.btnminus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnplush.setVisibility(View.VISIBLE);
                    finalViewHolder.cartQuantity.setText(String.valueOf(newQuantity));
                }
                else
                {
                    finalViewHolder.btnplush.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.cartQuantity.setText(String.valueOf(newQuantity));
                }
            }
        });
        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.cartList.remove(position);
                Toast.makeText(mCtx,"Xóa sản phẩm thành công",Toast.LENGTH_SHORT).show();
                Cart.CheckCart();
                Cart.EventTT();
            }
        });

        return view;
    }
}
