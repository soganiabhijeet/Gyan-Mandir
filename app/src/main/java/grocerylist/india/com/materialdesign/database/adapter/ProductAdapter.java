package grocerylist.india.com.materialdesign.database.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import grocerylist.india.com.materialdesign.database.DatabaseHelper;
import grocerylist.india.com.materialdesign.model.Product;

/**
 * Created by abhijeetsogani on 10/20/15.
 */
public class ProductAdapter {
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
        database.insert(DatabaseHelper.TABLE_PRODUCT, null, values);
    }
}
