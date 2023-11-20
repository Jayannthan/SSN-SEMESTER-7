// DatabaseHelper.java
package com.example.products;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "products";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "products";

    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_PRODUCT_BRAND = "product_brand";
    public static final String COLUMN_PRODUCT_DESCRIPTION = "product_description";
    public static final String COLUMN_PRODUCT_PRICE = "product_price";

    private static final String SQL_CREATE_PRODUCT_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_PRODUCT_NAME + " TEXT," +
                    COLUMN_PRODUCT_BRAND + " TEXT," +
                    COLUMN_PRODUCT_DESCRIPTION + " TEXT," +
                    COLUMN_PRODUCT_PRICE + " REAL)";

    private static final String SQL_DELETE_PRODUCT_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PRODUCT_TABLE);
        onCreate(db);
    }

    public Product searchProductById(int productId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_PRODUCT_NAME,
                COLUMN_PRODUCT_BRAND,
                COLUMN_PRODUCT_DESCRIPTION,
                COLUMN_PRODUCT_PRICE
        };

        String selection = COLUMN_PRODUCT_ID + " = ?";
        String[] selectionArgs = {String.valueOf(productId)};

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Product product = null;
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME));
            String brand = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_BRAND));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_DESCRIPTION));
            double price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE));

            product = new Product(productId, name, brand, description, price);
            cursor.close();
        }

        db.close();
        return product;
    }
    public boolean deleteProductById(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_PRODUCT_ID + " = ?";
        String[] whereArgs = {String.valueOf(productId)};

        int rowsDeleted = db.delete(TABLE_NAME, whereClause, whereArgs);

        // Close the database
        db.close();

        // Check if any rows were deleted (deletion successful)
        return rowsDeleted > 0;
    }
    public boolean updateProductPrice(int productId, double newPrice) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_PRICE, newPrice);

        String whereClause = COLUMN_PRODUCT_ID + " = ?";
        String[] whereArgs = {String.valueOf(productId)};

        int rowsUpdated = db.update(TABLE_NAME, values, whereClause, whereArgs);

        // Close the database
        db.close();

        // Check if any rows were updated (update successful)
        return rowsUpdated > 0;
    }

    public List<String> getBrandList() {
        List<String> brandList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // SQL query to select distinct brands
        String query = "SELECT DISTINCT " + COLUMN_PRODUCT_BRAND + " FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String brand = cursor.getString(0); // 0 is the column index for brand
                brandList.add(brand);
                Log.d("DatabaseHelper", "Brand: " + brand); // Add this line for debugging
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return brandList;
    }


    // Add a method to select all items in a brand
    public List<Product> getProductsByBrand(String brandName) {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_PRODUCT_ID,
                COLUMN_PRODUCT_NAME,
                COLUMN_PRODUCT_PRICE
        };

        String selection = COLUMN_PRODUCT_BRAND + " = ?";
        String[] selectionArgs = {brandName};

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int productId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE));

                Product product = new Product(productId, name, brandName, null, price);
                productList.add(product);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return productList;
    }

}
