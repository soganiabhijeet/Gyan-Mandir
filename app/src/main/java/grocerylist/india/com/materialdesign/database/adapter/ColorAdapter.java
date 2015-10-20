package grocerylist.india.com.materialdesign.database.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import grocerylist.india.com.materialdesign.database.DatabaseHelper;

/**
 * Created by abhijeetsogani on 10/20/15.
 */
public class ColorAdapter {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public ColorAdapter(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}
