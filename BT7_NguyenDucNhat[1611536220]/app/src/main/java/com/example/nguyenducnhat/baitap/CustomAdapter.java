package com.example.nguyenducnhat.baitap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenducnhat.baitap.MainActivity;
import java.util.List;


public class CustomAdapter extends BaseAdapter {
    private Context context;
    private int idLayout;
    private List<DataItem> listDataItem;

    public CustomAdapter(Context context, int idLayout, List<DataItem> listDataItem) {
        this.context = context;
        this.idLayout = idLayout;
        this.listDataItem = listDataItem;
    }

    @Override
    public int getCount() {
        if(listDataItem.size() != 0 && !listDataItem.isEmpty())
        {
            return listDataItem.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if((convertView == null))
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(idLayout,parent,false);

        }
        final TextView tv_name = (TextView)convertView.findViewById(R.id.tvName);
        ImageView img = (ImageView)convertView.findViewById(R.id.logo);
        final LinearLayout linearLayout = (LinearLayout)convertView.findViewById(R.id.idLinearLayout);
        final DataItem dataItem = listDataItem.get(position);

        if(listDataItem != null && !listDataItem.isEmpty())
        {
            String name;
            name = dataItem.getA()+"x\u00B2"+(dataItem.getB()>0 ? "+":"")+dataItem.getB()+"x"+(dataItem.getC()>0? "+":"")+dataItem.getC();
            tv_name.setText(name);
            int resid = dataItem.getResid();
            switch (resid)
            {
                case 1:
                    img.setImageResource(R.drawable.pt1);
                    break;
                case 2:
                    img.setImageResource(R.drawable.pt3);
                    break;
                case 3:
                    img.setImageResource(R.drawable.pt3);
                    break;
                case 4:
                    img.setImageResource(R.drawable.pt4);
                    break;
                    default:
                        break;
            }
        }

        return convertView;
    }
}

