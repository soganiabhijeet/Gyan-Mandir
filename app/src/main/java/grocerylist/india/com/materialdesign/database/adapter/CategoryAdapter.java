package grocerylist.india.com.materialdesign.database.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import grocerylist.india.com.materialdesign.database.DatabaseHelper;
import grocerylist.india.com.materialdesign.model.Category;

/**
 * Created by abhijeetsogani on 10/20/15.
 */
public class CategoryAdapter {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public CategoryAdapter(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addCategory(String categoryName) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.CATEGORY_NAME, categoryName);
        database.insert(DatabaseHelper.TABLE_CATEGORY, null, values);
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

}
