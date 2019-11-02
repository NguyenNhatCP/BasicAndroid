package com.example.nguyenducnhat.nhatstore.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nguyenducnhat.nhatstore.Model.Products;
import com.example.nguyenducnhat.nhatstore.R;
import com.example.nguyenducnhat.nhatstore.activity.MainActivity;
import com.example.nguyenducnhat.nhatstore.activity.ProductsDetail;
import com.example.nguyenducnhat.nhatstore.data.ProductAdapter;
import com.example.nguyenducnhat.nhatstore.data.SqlLiteDbhelper;

import java.util.ArrayList;
import java.util.List;

public class Product extends Fragment {
    ListView lsvProduct;
    SqlLiteDbhelper dbhelper;
    private static int id;
    List<Products> productlist;
    private String name;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product, container, false);
        dbhelper = new SqlLiteDbhelper(this.getContext());
        productlist = new ArrayList<>();
        AddEvent(v);
        loadProductFromDatabase();
        getBundelName();
        AcTionBar();
        return v;
    }

    private void AddEvent(View v)
    {
        lsvProduct = (ListView) v.findViewById(R.id.lsv_Product);
        lsvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int productID = productlist.get(position).getId();
                Toast.makeText(getActivity(), "" + productlist.get(position).getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity().getBaseContext(), ProductsDetail.class);
                intent.putExtra("detail_ID", productID+"");
                getActivity().startActivity(intent);
            }
        });
    }

    private void AcTionBar()
    {
        final Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setTitle(name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Category category = new Category();

                fragmentTransaction.replace(R.id.fagmentContent, category);
                fragmentTransaction.commit();
            }
        });
    }
    private String getBundelName() {
        Bundle bundle = getArguments();
        name = bundle.getString("categogyName");
        Log.d("Search", "" + name);
        return name;
    }
    private String getBundelCode() {
        Bundle bundle = getArguments();
        String code = bundle.getString("categogyCode");
        Log.d("Search", "" + code);
        return code;
    }

    private void loadProductFromDatabase() {
        Cursor product = dbhelper.getproduct("'"+getBundelCode()+"'");
        dbhelper.openDatabase();
        if (product.moveToFirst()) {
            productlist.clear();
            do {
                productlist.add(new Products(
                        product.getInt(0),
                        product.getString(1),
                        product.getInt(2),
                        product.getString(3),
                        product.getString(4)
                ));
            } while (product.moveToNext());
            product.close();
            dbhelper.close();
            //passing the databasemanager instance this time to the adapter
            ProductAdapter adapter = new ProductAdapter(this.getContext(), R.layout.list_product, productlist, dbhelper);
            adapter.notifyDataSetChanged();
            lsvProduct.setAdapter(adapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Category category = new Category();

                fragmentTransaction.replace(R.id.fagmentContent, category);
                fragmentTransaction.commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
