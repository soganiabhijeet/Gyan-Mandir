package grocerylist.india.com.materialdesign.database.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import grocerylist.india.com.materialdesign.database.DatabaseHelper;
import grocerylist.india.com.materialdesign.model.Category;
import grocerylist.india.com.materialdesign.model.Product;

/**
 * Created by abhijeetsogani on 10/20/15.
 */
public class ProductAdapter {
    private String TAG = "ProductAdapter";
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public ProductAdapter(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.PRODUCT_NAME, product.getProductName());
        values.put(DatabaseHelper.PRODUCT_COMPANY, product.getCompanyName());
        values.put(DatabaseHelper.PRODUCT_SELLING_PRICE, product.getSellingPrice());
        values.put(DatabaseHelper.CATEGORY_ID, product.getCategory().getCategoryId());
        values.put(DatabaseHelper.PRODUCT_COST_PRICE, product.getCostPrice());
        if (product.getHasColor()) {
            values.put(DatabaseHelper.PRODUCT_HAS_COLOR, 1);
        } else {
            values.put(DatabaseHelper.PRODUCT_HAS_COLOR, 0);
        }
        if (product.getHasSize()) {
            values.put(DatabaseHelper.PRODUCT_HAS_SIZE, 1);
        } else {
            values.put(DatabaseHelper.PRODUCT_HAS_SIZE, 0);
        }
        Log.d(TAG, values.toString());
        database.insert(DatabaseHelper.TABLE_PRODUCT, null, values);
    }

    public List<Product> getProductListForCategory(Category category) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_PRODUCT + " WHERE " + DatabaseHelper.CATEGORY_ID + " = " + category.getCategoryId();
        Log.d(TAG, "getProductListForCategory query : " + query);
        Cursor c = database.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Product product = new Product();
            product.setCategory(category);
            product.setCompanyName(c.getString(c.getColumnIndex(DatabaseHelper.PRODUCT_COMPANY)));
            product.setCostPrice(c.getInt(c.getColumnIndex(DatabaseHelper.PRODUCT_COST_PRICE)));
            if (c.getInt(c.getColumnIndex(DatabaseHelper.PRODUCT_HAS_COLOR)) == 0) {
                product.setHasColor(false);
            } else {
                product.setHasColor(true);
            }
            if (c.getInt(c.getColumnIndex(DatabaseHelper.PRODUCT_HAS_SIZE)) == 0) {
                product.setHasSize(false);
            } else {
                product.setHasSize(true);
            }

            product.setProductName(c.getString(c.getColumnIndex(DatabaseHelper.PRODUCT_NAME)));
            product.setSellingPrice(c.getInt(c.getColumnIndex(DatabaseHelper.PRODUCT_SELLING_PRICE)));
            products.add(product);
            c.moveToNext();
        }

        return products;
    }

    public List<Product> getProductListForSearch(String searchQuery) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_PRODUCT + " WHERE " + DatabaseHelper.PRODUCT_NAME + " LIKE " + "'%"+searchQuery+"%'";
        Log.d(TAG, "getProductListForSearch query : " + query);
        Cursor c = database.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Product product = new Product();
            product.setCategory(null);
            product.setCompanyName(c.getString(c.getColumnIndex(DatabaseHelper.PRODUCT_COMPANY)));
            product.setCostPrice(c.getInt(c.getColumnIndex(DatabaseHelper.PRODUCT_COST_PRICE)));
            if (c.getInt(c.getColumnIndex(DatabaseHelper.PRODUCT_HAS_COLOR)) == 0) {
                product.setHasColor(false);
            } else {
                product.setHasColor(true);
            }
            if (c.getInt(c.getColumnIndex(DatabaseHelper.PRODUCT_HAS_SIZE)) == 0) {
                product.setHasSize(false);
            } else {
                product.setHasSize(true);
            }

            product.setProductName(c.getString(c.getColumnIndex(DatabaseHelper.PRODUCT_NAME)));
            product.setSellingPrice(c.getInt(c.getColumnIndex(DatabaseHelper.PRODUCT_SELLING_PRICE)));
            products.add(product);
            c.moveToNext();
        }

        return products;

    }
}
