package com.example.nguyenducnhat.nhatstore.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyenducnhat.nhatstore.Model.Category;
import com.example.nguyenducnhat.nhatstore.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {

    Context mCtx;
    int listLayoutRes;
    List<Category> catelogyList;
    SqlLiteDbhelper dbhelper;

    public CategoryAdapter(Context mCtx, int listLayoutRes, List<Category> catelogyListList, SqlLiteDbhelper dbhelper) {
        super(mCtx, listLayoutRes, catelogyListList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.catelogyList = catelogyListList;
        this.dbhelper = dbhelper;
    }

    @Override
    public int getCount() {
        return catelogyList.size();
    }

    @Override
    public Category getItem(int position) {
        return catelogyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        //getting employee of the specified position
        Category category = catelogyList.get(position);
        Log.d("demo",position+"");


        //getting views
        ImageView imgView = view.findViewById(R.id.categoryImage);
        TextView textViewName = view.findViewById(R.id.categoryName);



        //adding data to views
         Picasso.with(getContext()).load(category.getImage()).placeholder(R.mipmap.ic_launcher)
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
         //imgView.setImageURI(Uri.parse(category.getImage()));
         textViewName.setText(category.getName());

        return view;
    }

}
