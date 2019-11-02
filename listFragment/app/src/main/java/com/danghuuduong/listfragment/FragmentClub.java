package com.danghuuduong.listfragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

public class FragmentClub extends ListFragment {

    SQLiteDatabase database;
    final String DATABASE_NAME = "QLTop10DoiBong.sqlite";
    ArrayList<Club> list;
    ClubAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // database = Database.initDatabase(getActivity(),DATABASE_NAME);
       // list = new ArrayList<>();
        getData();
        adapter = new ClubAdapter(getActivity(),R.layout.dulieu_listview,list);
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();


        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    public void getData(){
        list.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM Club",null);
        while (cursor.moveToNext()){
            list.add(new Club(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            ));
        }
    }
}
