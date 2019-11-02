package com.example.nguyenducnhat.firstapp.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.nguyenducnhat.firstapp.MainActivity;
import com.example.nguyenducnhat.firstapp.R;

import java.io.PrintWriter;
import java.util.logging.Logger;

public class mathFragment extends Fragment {

    private EditText editnumberA,editnumberB,editResult;
    private Button btnPlus,btnminus,btnmulti,btndivide;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_math,container, false);
        addControls(v);
        addEvents(v);
        return v;
    }
    private void addControls(View v) {
        editnumberA = (EditText) v.findViewById(R.id.editSoA);
        editnumberB = (EditText) v.findViewById(R.id.editSoB);
        editResult = (EditText) v.findViewById(R.id.editResult);

        btnPlus = (Button) v.findViewById(R.id.addition);
        btnminus = (Button) v.findViewById(R.id.subtraction);
        btnmulti = (Button) v.findViewById(R.id.multiplication);
        btndivide = (Button) v.findViewById(R.id.division);

    }

    private boolean checkData()
    {
        if(editnumberA.getText().toString().equals(""))
        {
            editResult.setText("Request invalue number A");
            editnumberA.requestFocus();
            return false;

        }
        if(editnumberB.getText().toString().equals(""))
        {
            editResult.setText("Request invalue number B");
            editnumberB.requestFocus();
            return false;
        }
        return true;
    }
    private boolean checkDevide()
    {
        if(editnumberB.getText().toString().equals("0"))
        {
            editResult.setText("Cannot devided by 0");
            editnumberB.setText("");
            editnumberB.requestFocus();
            return  false;
        }
        return  true;
    }
    private void addEvents(View v) {

        View.OnClickListener Plus = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
        double kq = Double.parseDouble(editnumberA.getText().toString()) + Double.parseDouble(editnumberB.getText().toString());
        editResult.setText(String.valueOf(kq));
                    }
            }
        };
        View.OnClickListener Minus = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    double kq = Double.parseDouble(editnumberA.getText().toString()) - Double.parseDouble(editnumberB.getText().toString());
                    editResult.setText(String.valueOf(kq));
                }
            }
        };
        View.OnClickListener Multi = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData())
                {
                        double kq = Double.parseDouble(editnumberA.getText().toString()) * Double.parseDouble(editnumberB.getText().toString());
                        editResult.setText(String.valueOf(kq));
                    }
            }
        };
        View.OnClickListener divide = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    if (checkDevide()) {
                        double kq = Double.parseDouble(editnumberA.getText().toString()) / Double.parseDouble(editnumberB.getText().toString());
                        editResult.setText(String.valueOf(kq));
                    }
                }
            }
        };
        btnPlus.setOnClickListener(Plus);
        btnminus.setOnClickListener(Minus);
        btnmulti.setOnClickListener(Multi);
        btndivide.setOnClickListener(divide);
    }
}
