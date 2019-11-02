package com.example.nguyenducnhat.nhatstore.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nguyenducnhat.nhatstore.Model.cart;
import com.example.nguyenducnhat.nhatstore.R;
import com.example.nguyenducnhat.nhatstore.data.SqlLiteDbhelper;
import com.example.nguyenducnhat.nhatstore.fragment.Category;
import com.example.nguyenducnhat.nhatstore.fragment.Home;
import com.example.nguyenducnhat.nhatstore.fragment.Location;
import com.example.nguyenducnhat.nhatstore.fragment.Order;
import com.nex3z.notificationbadge.NotificationBadge;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MainActivity main;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private NotificationBadge badge;
    public static int mCount = 0;
    SqlLiteDbhelper dbhelper;
    public static ArrayList<cart>  cartList;

    // Make sure to be using android.support.v7.app.ActionBarDrawerToggle version
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        loadFragmentFirst();
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Find drawer View
        nvDrawer = (NavigationView)findViewById(R.id.nvView);
        //set up drawer View
        setupDrawerContent(nvDrawer);
        drawerToggle =new ActionBarDrawerToggle(this,mDrawer,toolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        drawerToggle.setDrawerIndicatorEnabled(true);

        dbhelper = new SqlLiteDbhelper(this);
        //Check exists database
        File database = getApplicationContext().getDatabasePath(SqlLiteDbhelper.DATABASE_NAME);
        Log.d("dataName",""+database);
        if(false == database.exists()) {
            dbhelper.getReadableDatabase();
            Log.d("dataName", "ok");
            //Copy db
            if (copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
            }
        }
            else {
                Log.d("dataN","ok");
            }

            if(cartList != null)
            {

            }
            else
            {
                cartList = new ArrayList<>();
            }
            UpdateCart();
    }


    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(SqlLiteDbhelper.DATABASE_NAME);
            String outFileName = SqlLiteDbhelper.DBLOCATION + SqlLiteDbhelper.DATABASE_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private void loadFragmentFirst() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fagmentContent, new Home(), null);
        transaction.commit();
    }
    @Override
    public void onBackPressed() {
        if(mDrawer.isDrawerOpen(GravityCompat.START)){
            mDrawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        SelectDrawerItem(item);
                        return true;
                    }
                }
        );
    }


    public void SelectDrawerItem(MenuItem menuItem) {
            //Create a new Fragment
            Fragment fragment = null;
            Class fragmentClass;
            switch (menuItem.getItemId()) {
                case R.id.nav_first_fragment:
                    drawerToggle.setDrawerIndicatorEnabled(true);
                    Toast.makeText(this,"Home",Toast.LENGTH_LONG);
                    fragmentClass = Home.class;
                    break;
                case R.id.nav_second_fragment:
                    mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    drawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    drawerToggle.setDrawerIndicatorEnabled(false);
                    drawerToggle.syncState();
                    Toast.makeText(this,"Category",Toast.LENGTH_SHORT);
                    fragmentClass = Category.class;
                    break;
                case R.id.nav_third_fragment:
                    mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    drawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    drawerToggle.setDrawerIndicatorEnabled(false);
                    drawerToggle.syncState();
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    });
                    Toast.makeText(this,"Infor",Toast.LENGTH_LONG);
                    fragmentClass = Order.class;
                    break;
                case R.id.nav_Location:
                    mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    drawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    drawerToggle.setDrawerIndicatorEnabled(false);
                    drawerToggle.syncState();
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    });
                    Toast.makeText(this,"Location",Toast.LENGTH_LONG);
                    fragmentClass = Location.class;
                    break;

                case R.id.nav_Exit:
                    finish();
                    moveTaskToBack(true);

                default:
                        fragmentClass = Home.class;
            }
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
    // Insert the fragment by replacing any existing fragment
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.fagmentContent, fragment).commit();

    // Highlight the selected item has been done by NavigationView
    menuItem.setChecked(true);
    // Set action bar title
    setTitle(menuItem.getTitle());
    // Close the navigation drawer
    mDrawer.closeDrawers();

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        // Get the notifications MenuItem and
        final MenuItem item = menu.findItem(R.id.action_notifications);
        MenuItem items = menu.findItem(R.id.delete);

        if (MainActivity.cartList.size() > 0) {
            item.setVisible(true);
            items.setVisible(false);
        } else {
            item.setVisible(true);
            items.setVisible(false);
        }
        View Actionview = item.getActionView();
        if(Actionview != null) {
            ImageView imageView = (ImageView) Actionview.findViewById(R.id.cart_icon) ;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOptionsItemSelected(item);
                }
            });
            badge = (NotificationBadge) Actionview.findViewById(R.id.badge);
            badge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOptionsItemSelected(item);
                }
            });
        }
        LayerDrawable icon = (LayerDrawable) item.getIcon();
        UpdateCart();
        return true;
    }

    public  void UpdateCart()
    {
        mCount = cartList.size() ;
        Log.d("Count",mCount+"");
        if(badge == null) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
            if(mCount != 0) {
                badge.setVisibility(View.VISIBLE);
                Log.d("Checkoder","0 đơn hàng");
                badge.setText(String.valueOf(mCount));
            }
            else {
                badge.setVisibility(View.INVISIBLE);
                Log.d("Checkoder","có đơn hàng");
                badge.setText(String.valueOf(mCount));
            }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notifications:
                Intent intent = new Intent(MainActivity.this,Cart.class);
                startActivity(intent);
                Log.d("test","cart");
                return true;
            default:
                Toast.makeText(this,"Cart Empty",Toast.LENGTH_LONG);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        UpdateCart();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Toast.makeText(this,"Load",Toast.LENGTH_SHORT).show();
        super.onRestart();
    }
}
