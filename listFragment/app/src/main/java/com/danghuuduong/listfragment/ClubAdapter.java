package com.danghuuduong.listfragment;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ClubAdapter  extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Club> list;

    public ClubAdapter(Context context, int layout, List<Club> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHoler{
        TextView textViewSTT, textViewTen;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler holer;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);

            holer = new ViewHoler();
            holer.textViewSTT = (TextView)convertView.findViewById(R.id.id.textViewLVClub);
            holer.textViewTen = (TextView)convertView.findViewById(R.id.textViewLVClubName);

            convertView.setTag(holer);
        }else{
            holer = (ViewHoler) convertView.getTag();
        }
        Club club = list.get(position);
        holer.textViewTen.setText(club.getName());
        holer.textViewSTT.setText(club.getId()+"");
        return convertView;
    }
}
