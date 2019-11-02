package com.example.nguyenducnhat.bt2;

import android.graphics.drawable.LevelListDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.logging.Level;

public class MainActivity extends AppCompatActivity {
    private ImageView image;
    private Button buttonPrevious;
    private Button buttonNext;
    private TextView tv_name;
    private int currentIndex = 1;
   // private int i = 0;
    ArrayList<String> list = new ArrayList<String>();

    // Add objects to arraylist
    {
        list.add("bo");
        list.add("chuot");
        list.add("cop");
        list.add("rong");
        list.add("ran");
        list.add("heo");
        list.add("ga");
        list.add("ngua");
        list.add("khi");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonPrevious = (Button) findViewById(R.id.button_previous);
        buttonNext = (Button) findViewById(R.id.button_next);
        tv_name = (TextView)findViewById(R.id.tv_name);

        image = (ImageView) findViewById(R.id.imageSwitcher);
        image.setImageLevel(currentIndex);
        //tv_name.setText(list.get(i));
        this.buttonPrevious.setOnClickListener(new  Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                    previousImage();
            }
        });
        this.buttonNext.setOnClickListener(new  Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                nextImage();
            }
        });
    }

    private void previousImage()  {
        if(currentIndex > 1) {
            currentIndex--;
        }else  {
            currentIndex = 9;
        }
        image.setImageLevel(currentIndex);
    }

    private void nextImage()  {
        if(currentIndex < 9) {
            String name = "";
            int i = 0;
            do
            {
                //i++;
                name = list.get(i);
                tv_name.setText(name);
            }while (i< list.size());
            currentIndex++;
        }
        else  {
            currentIndex = 1;

        }
        image.setImageLevel(currentIndex);
    }
}
