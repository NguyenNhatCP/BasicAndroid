package com.example.nguyenducnhat.nhatstore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nguyenducnhat.nhatstore.Model.Category;
import com.example.nguyenducnhat.nhatstore.Model.Products;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SqlLiteDbhelper extends SQLiteOpenHelper {

    private Context mctx;
    private static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "shop.sqlite";
    public static final String DBLOCATION = "/data/data/com.example.nguyenducnhat.nhatstore/databases/";
    private static final String DB_PATH_SUFFIX = "/databases/";
    private SQLiteDatabase mdatabase;

    public SqlLiteDbhelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
        this.mctx = context;
    }

    public void openDatabase() {
        Log.d("Test",""+DBLOCATION);
        String dbPath = mctx.getDatabasePath(DATABASE_NAME).getPath();
        Log.d("test",""+dbPath);
        if (mdatabase != null && mdatabase.isOpen()) {
            return;
        }
        mdatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mdatabase != null) {
            mdatabase.close();
        }
    }


    //get Category from database
    public List<Category> getListCategory() {
        Category category = null;
        List<Category> categoryList = new ArrayList<>();
        openDatabase();
        Log.d("113","mo");
        Cursor cursor = mdatabase.rawQuery("SELECT * FROM categories", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            category = new Category(cursor.getInt(0),cursor.getString(1), cursor.getString(2) ,cursor.getString(3));
            categoryList.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        Log.d("113","dong");
        return categoryList;
    }
    //get newproduct from database
    public ArrayList<Products> getListNewProduct() {
        Products newproduct = null;
        ArrayList<Products> listproduct = new ArrayList<>();
        openDatabase();
        Log.d("113","mo new sp");
        Cursor cursor = mdatabase.rawQuery("select * from products order by productID desc limit 6", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            newproduct = new Products(cursor.getInt(0),cursor.getString(1) ,cursor.getInt(2),cursor.getString(3),cursor.getString(4));
            listproduct.add(newproduct);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        Log.d("113","dong");
        return listproduct;
    }
    //get product from database
    public Cursor getproduct(String code) {
        SQLiteDatabase db = this.getReadableDatabase();
        openDatabase();
        String selectQuery = "SELECT p.productID, p.productName,p.productPrice,p.productDescrption, p.productImage FROM products p\n" +
                "                JOIN categories c ON c.categoryID = p.categoryID\n" +
                "                WHERE c.categoryCode = " +code;
        Log.d("id",code+"");
        Cursor cursor = db.rawQuery(selectQuery, null);
        closeDatabase();
        return cursor;
    }
    //get productDetail from database
    public Cursor getproductDetail(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        openDatabase();
        String selectQuery = "SELECT productID,productName,productPrice,productDescrption,productImage from products where productID ="+id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        closeDatabase();
        return cursor;
    }
    //get orderID from database
    public Cursor getoderID() {
        SQLiteDatabase db = this.getReadableDatabase();
        openDatabase();
        String selectQuery = "select orderID  from 'order' order by orderID desc limit 1";
        Cursor cursor = db.rawQuery(selectQuery, null);
        closeDatabase();
        return cursor;
    }
    //get order from database
    public Cursor getoderDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        openDatabase();
        String selectQuery = "select a.orderDetailsID,a.orderID,a.productID,b.User,a.quantity,totalPrice,b.orderDate from orderDetails a inner join 'order' b on a.orderID = b.orderID ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        closeDatabase();
        return cursor;
    }
    // Method  insert orders
    public boolean insertOrder(String tenkh, int sdt, String email, SimpleDateFormat ngaydat) {
        SQLiteDatabase db = this.getWritableDatabase();
        openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("User", tenkh);
        contentValues.put("Phone", sdt);
        contentValues.put("Email", email);
        contentValues.put("orderDate", String.valueOf(ngaydat));
        Long result = db.insert("'order'", null, contentValues);
        closeDatabase();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    // Method  insert orderDetails
    public boolean insertOrderDetail(int productID, int orderID, int quantity, int totalPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("productID", productID);
        contentValues.put("orderID", orderID);
        contentValues.put("quantity", quantity);
        contentValues.put("totalPrice", totalPrice);
        Long result = db.insert("orderDetails", null, contentValues);
        closeDatabase();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public void deleteOrderDetails(int idDetail,int idOrders)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        openDatabase();
        String query = "delete from orderDetails where orderDetailsID = "+idDetail+ " and orderID = "+idOrders;
        db.execSQL(query);
        closeDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
