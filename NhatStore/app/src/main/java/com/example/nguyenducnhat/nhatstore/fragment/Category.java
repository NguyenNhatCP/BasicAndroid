package com.example.nguyenducnhat.nhatstore.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nguyenducnhat.nhatstore.R;
import com.example.nguyenducnhat.nhatstore.activity.MainActivity;
import com.example.nguyenducnhat.nhatstore.data.CategoryAdapter;
import com.example.nguyenducnhat.nhatstore.data.SqlLiteDbhelper;

import java.util.ArrayList;
import java.util.List;

public class Category extends Fragment {
    //SQLiteDatabase database;
    ListView lsvCategory;
    private CategoryAdapter adapter;
    SqlLiteDbhelper dbhelper;
    List<com.example.nguyenducnhat.nhatstore.Model.Category> categorylist;
    SQLiteDatabase database;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category, container, false);
        dbhelper = new SqlLiteDbhelper(this.getContext());
        categorylist = new ArrayList<>();
        lsvCategory = (ListView) v.findViewById(R.id.lsv_Category);

        final Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setTitle("Doanh mục sản phẩm");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });


        loadCategoryFromDatabase();
        lsvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String categogyCode = categorylist.get(position).getCode();
                String categogyName = categorylist.get(position).getName();

                Toast.makeText(getActivity(), "" + categorylist.get(position).getCode(), Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putString("categogyCode", categogyCode);
                bundle.putString("categogyName", categogyName);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Product product = new Product();
                product.setArguments(bundle);

                fragmentTransaction.replace(R.id.fagmentContent, product);
                fragmentTransaction.commit();


            }
        });
        return v;

    }

    private void loadCategoryFromDatabase() {
            categorylist.clear();
            categorylist.addAll(categorylist);
             //Get product list in db when db exists
            categorylist = dbhelper.getListCategory();
            //passing the databasemanager instance this time to the adapter
            CategoryAdapter adapter = new CategoryAdapter(this.getContext(), R.layout.list_category, categorylist, dbhelper);
            //Set adapter for listview
            lsvCategory.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

