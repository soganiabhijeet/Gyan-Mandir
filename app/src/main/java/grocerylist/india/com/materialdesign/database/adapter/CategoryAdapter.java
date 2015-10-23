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

/**
 * Created by abhijeetsogani on 10/20/15.
 */
public class CategoryAdapter {
    private String TAG = "CategoryAdapter";

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public CategoryAdapter(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        Log.d(TAG, "database opened");
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        Log.d(TAG, "database closed");
        dbHelper.close();
    }

    public void addCategory(String categoryName) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.CATEGORY_NAME, categoryName);
        long insertId = database.insert(DatabaseHelper.TABLE_CATEGORY, null, values);
        Log.d(TAG, "insertId " + insertId);
    }

    public List<Category> getCategoryList() {
        List<Category> categories = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_CATEGORY;
        Cursor c = database.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setCategoryId(c.getInt(c.getColumnIndex(DatabaseHelper.CATEGORY_ID)));
                category.setCategoryName(c.getString(c.getColumnIndex(DatabaseHelper.CATEGORY_NAME)));
                categories.add(category);
            } while (c.moveToNext());
        }
        return categories;
    }

    public Category getCategoryFromName(String categoryName) {

        Category category = new Category();
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_CATEGORY + " WHERE " +
                DatabaseHelper.CATEGORY_NAME + " LIKE '" + categoryName + "'";
        Log.d(TAG,selectQuery);
        Cursor c = database.rawQuery(selectQuery, null);
        if (c != null ) {
            c.moveToFirst();
            category.setCategoryId(c.getInt(c.getColumnIndex(DatabaseHelper.CATEGORY_ID)));
            category.setCategoryName(c.getString(c.getColumnIndex(DatabaseHelper.CATEGORY_NAME)));
            return category;
        } else
            return null;

    }

}
