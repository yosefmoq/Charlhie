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
    private static final String KEY_PHOTO = "photo";
    private static final String KEY_PRICE = "price";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_SPRT = "sprt";
    private static final String KEY_SUBCATEGORY = "subCategory";
    private static final String TABLE_FAVORITE = "favCategory";
    private static final String TABLE_Users = "userdetails";

    public MyDatabase(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 6);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE userdetails(id INTEGER PRIMARY KEY AUTOINCREMENT,subCategory TEXT,details TEXT,category TEXT,price NUMBER,photo TEXT,quantity NUMBER,nativePrice NUMBER,longDescriptoin TEXT,sprt NUMBER)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS userdetails");
        onCreate(db);
    }

    public void addProduct(String subCategory, String details, String category, double price, String photo, int quantity, double nativePrice, String longDescription, int cprt) {
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_SUBCATEGORY, subCategory);
        cValues.put(KEY_DETAILS, details);
        cValues.put(KEY_CATEGORY, category);
        cValues.put(KEY_PRICE, Double.valueOf(price));
        cValues.put(KEY_PHOTO, photo);
        cValues.put(KEY_QUANTITY, Integer.valueOf(quantity));
        cValues.put(KEY_NATIVE_PRICE, Double.valueOf(nativePrice));
        cValues.put(KEY_LONGDESCRIPTION, longDescription);
        cValues.put(KEY_SPRT, Integer.valueOf(cprt));
        getWritableDatabase().insert(TABLE_Users, null, cValues);
        getWritableDatabase().close();
    }

    public ArrayList<Category> getProducts() {
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM userdetails", null);
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
            productResponses.add(productResponse);
        }
        c.close();
        return productResponses;
    }

    public int getAnnal() {
        Cursor c = getReadableDatabase().rawQuery("SELECT quantity FROM userdetails", null);
        int i = 0;
        while (c.moveToNext()) {
            i += c.getInt(0);
        }
        return i;
    }

    public double getPrice() {
        Cursor c = getReadableDatabase().rawQuery("SELECT price FROM userdetails", null);
        double price = 0.0d;
        while (c.moveToNext()) {
            price += c.getDouble(0);
        }
        return price;
    }

    public void deleteAll() {
        getWritableDatabase().execSQL("delete from userdetails");
    }

    public void deleteItemById(int id) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(TABLE_Users, "sprt=" + id, null);
    }
}
