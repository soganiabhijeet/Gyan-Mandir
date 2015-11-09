package grocerylist.india.com.materialdesign.database.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import grocerylist.india.com.materialdesign.database.DatabaseHelper;
import grocerylist.india.com.materialdesign.model.Item;

/**
 * Created by abhijeetsogani on 10/20/15.
 */
public class ItemAdapter {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String TAG = "ItemAdapter";

    public ItemAdapter(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertItem(Item item) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.ITEM_STOCK, item.getItemStock());
        values.put(DatabaseHelper.PRODUCT_ID, item.getProductId());
        if (item.getColor() != null)
            values.put(DatabaseHelper.COLOR_ID, item.getColor().getColorId());
        Log.d(TAG, values.toString());
        return database.insert(DatabaseHelper.TABLE_ITEM, null, values);
    }
}
