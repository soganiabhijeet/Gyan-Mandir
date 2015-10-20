package grocerylist.india.com.materialdesign.database.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import grocerylist.india.com.materialdesign.database.DatabaseHelper;

/**
 * Created by abhijeetsogani on 10/20/15.
 */
public class SizeAdapter {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public SizeAdapter(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addSize(String size)
    {
        ContentValues values=new ContentValues();
        values.put(DatabaseHelper.SIZE_NAME, size);
        long insertId=database.insert(DatabaseHelper.TABLE_ITEMSIZE,null,values);
    }

}
