package com.yosefmoq.charlhie.repository.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.yosefmoq.charlhie.models.Category;
import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "product";
    private static final int DB_VERSION = 6;
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_DETAILS = "details";
    private static final String KEY_ID = "id";
    private static final String KEY_LONGDESCRIPTION = "longDescriptoin";
    private static final String KEY_NAME = "name";
    private static final String KEY_NATIVE_PRICE = "nativePrice";
    public static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PHOTO = "photo";
    private static final String KEY_PRICE = "price";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_SPRT = "sprt";
    public static final String  KEY_ISFAV = "isFave";
    private static final String KEY_SUBCATEGORY = "subCategory";
    private static final String TABLE_FAVORITE = "favCategory";
    private static final String TABLE_Users = "userdetails";

    public MyDatabase(Context context) {
        super(context, DB_NAME,  null, 8);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE userdetails(id INTEGER PRIMARY KEY AUTOINCREMENT,subCategory TEXT,details TEXT,category TEXT,price NUMBER,photo TEXT,quantity NUMBER,nativePrice NUMBER,longDescriptoin TEXT,sprt NUMBER,"+ KEY_ISFAV+" NUMBER, "+ KEY_DESCRIPTION+" TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS userdetails");
        onCreate(db);
    }

    public void addProduct(String subCategory, String details, String category, double price, String photo, int quantity, double nativePrice, String longDescription, int cprt,boolean isFav,String description) {
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_SUBCATEGORY, subCategory);
        cValues.put(KEY_DETAILS, details);
        cValues.put(KEY_CATEGORY, category);
        cValues.put(KEY_PRICE, price);
        cValues.put(KEY_PHOTO, photo);
        cValues.put(KEY_QUANTITY, quantity);
        cValues.put(KEY_NATIVE_PRICE, nativePrice);
        cValues.put(KEY_LONGDESCRIPTION, longDescription);
        cValues.put(KEY_SPRT, cprt);
        cValues.put(KEY_ISFAV,isFav);
        cValues.put(KEY_DESCRIPTION,description);
        getWritableDatabase().insert(TABLE_Users, null, cValues);
        getWritableDatabase().close();
    }

    public ArrayList<Category> getProducts() {
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM userdetails WHERE "+ KEY_ISFAV +" = 0", null);
        ArrayList<Category> productResponses = new ArrayList<>();
        while (c.moveToNext()) {
            Category productResponse = new Category();
            productResponse.setSubCategory(c.getString(1));
            productResponse.setDescription(c.getString(2));
            productResponse.setCategory(c.getString(3));
            productResponse.setPrice(c.getDouble(4));
            productResponse.setImage(c.getString(5));
            productResponse.setQuantity(c.getInt(6));
            productResponse.setNativePrice(c.getDouble(7));
            productResponse.setLongDescription(c.getString(8));
            productResponse.setId(c.getInt(9));
            productResponse.setFav(c.getInt(10) != 0);
            productResponse.setDescription(c.getString(11));
            productResponses.add(productResponse);
        }
        c.close();
        return productResponses;
    }

    public int getAnnal() {
        Cursor c = getReadableDatabase().rawQuery("SELECT quantity FROM userdetails WHERE "+ KEY_ISFAV +" = 0", null);
        int i = 0;
        while (c.moveToNext()) {
            i += c.getInt(0);
        }
        return i;
    }

    public double getPrice() {
        Cursor c = getReadableDatabase().rawQuery("SELECT price FROM userdetails WHERE "+ KEY_ISFAV +" = 0", null);
        double price = 0.0d;
        while (c.moveToNext()) {
            price += c.getDouble(0);
        }
        return price;
    }

    public void deleteAll() {
        getWritableDatabase().execSQL("delete from userdetails");
    }

    public ArrayList<Category> getFavorite(){
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM userdetails WHERE "+KEY_ISFAV +" = 1", null);
        ArrayList<Category> productResponses = new ArrayList<>();
        while (c.moveToNext()) {
            Category productResponse = new Category();
            productResponse.setSubCategory(c.getString(1));
            productResponse.setDescription(c.getString(2));
            productResponse.setCategory(c.getString(3));
            productResponse.setPrice(c.getDouble(4));
            productResponse.setImage(c.getString(5));
            productResponse.setQuantity(c.getInt(6));
            productResponse.setNativePrice(c.getDouble(7));
            productResponse.setLongDescription(c.getString(8));
            productResponse.setId(c.getInt(9));
            productResponse.setFav(c.getInt(10) != 0);
            productResponse.setDescription(c.getString(11));
            productResponses.add(productResponse);
        }
        c.close();
        return productResponses;

    }
    public boolean isFav(int id){
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM "+TABLE_Users+ " WHERE sprt=" + id+" AND "+ KEY_ISFAV+" = 1", null);
        return c.getCount()>0;
    }
    public void deleteItemById(int id) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(TABLE_Users, "sprt=" + id+" AND "+ KEY_ISFAV +" = 0", null);
    }

    public void deleteFavById(int id) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(TABLE_Users, "sprt=" + id+" AND "+ KEY_ISFAV +" = 1", null);
    }

}
