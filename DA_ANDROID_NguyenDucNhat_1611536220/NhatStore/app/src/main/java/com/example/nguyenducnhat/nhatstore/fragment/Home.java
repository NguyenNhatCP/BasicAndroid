package com.example.nguyenducnhat.nhatstore.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.nguyenducnhat.nhatstore.Model.Products;
import com.example.nguyenducnhat.nhatstore.R;
import com.example.nguyenducnhat.nhatstore.data.NewProductAdapter;
import com.example.nguyenducnhat.nhatstore.data.SqlLiteDbhelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Home extends Fragment {
    ViewFlipper viewFlipper;
    RecyclerView recyclernewProduct;
    SqlLiteDbhelper dbhelper;
    ArrayList<Products> productlist;
    NewProductAdapter newProductAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        dbhelper = new SqlLiteDbhelper(this.getContext());
        productlist = new ArrayList<>();
        loadNewProductFromDatabase();
        AddEvent(v);
        ActionViewFliper();
        return v;
    }
    private void AddEvent(View v)
    {
        viewFlipper = (ViewFlipper) v.findViewById(R.id.ViewFliper);
        recyclernewProduct = (RecyclerView) v.findViewById(R.id.recycler_NewProduct);

        newProductAdapter = new NewProductAdapter(getContext(), productlist);
        recyclernewProduct.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclernewProduct.setLayoutManager(gridLayoutManager);
        recyclernewProduct.setAdapter(newProductAdapter);
        recyclernewProduct.setFocusable(false);
    }

    private void ActionViewFliper() {
        ArrayList<String> mangqc  = new ArrayList<>();
        mangqc.add("https://cdn.pixabay.com/photo/2015/10/12/15/18/clothing-store-984396__340.jpg");
        mangqc.add("https://cdn.pixabay.com/photo/2017/12/26/09/15/woman-3040029__340.jpg");
        mangqc.add("https://cdn.pixabay.com/photo/2015/01/21/17/22/shopping-606993__340.jpg");
        for(int i = 0 ;i < mangqc.size();i++) {

            ImageView imageView = new ImageView((Activity)getContext());
            Picasso.with(getContext()).load(mangqc.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation((Activity)getContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation((Activity)getContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void loadNewProductFromDatabase() {
            productlist.clear();
              productlist.addAll(productlist);
            //Get product list in db when db exists
            productlist = dbhelper.getListNewProduct();
            newProductAdapter = new NewProductAdapter(this.getContext(),productlist);
            newProductAdapter.notifyDataSetChanged();
        }
}
