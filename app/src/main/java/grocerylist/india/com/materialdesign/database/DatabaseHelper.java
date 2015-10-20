package grocerylist.india.com.materialdesign.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abhijeetsogani on 10/20/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "inventoryManager";

    //table name
    public static final String TABLE_PRODUCT = "products";
    public static final String TABLE_ITEM = "items";
    public static final String TABLE_PRODUCT_ITEM = "product_item_mappings";
    public static final String TABLE_ITEMCOLOR = "colors";
    public static final String TABLE_ITEMSIZE = "sizes";
    public static final String TABLE_CATEGORY = "categories";
    public static final String TABLE_ORDER = "order_items";

    //columns name
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_COMPANY = "product_company";
    public static final String PRODUCT_HAS_COLOR = "is_color";
    public static final String PRODUCT_HAS_SIZE = "is_size";
    public static final String CATEGORY_ID = "category_id";
    public static final String ITEM_ID = "item_id";
    public static final String ITEM_STOCK = "item_stock";
    public static final String ITEM_COST_PRICE = "item_cost_price";
    public static final String ITEM_SELLING_PRICE = "item_selling_price";
    public static final String COLOR_ID = "color_id";
    public static final String ORDER_ID = "order_id";
    public static final String SIZE_ID = "size_id";
    public static final String COLOR_NAME = "color_name";
    public static final String SIZE_NAME = "size_name";
    public static final String CATEGORY_NAME = "category_name";
    //private static final String ORDER_NAME = "category_name";
    public static final String ORDER_ITEM_SOLD = "order_sold";
    public static final String ORDER_ITEM_BOUGHT = "order_bought";
    public static final String ORDER_SELLING_PRICE = "order_price";
    public static final String ORDER_DATE = "order_date";


    //table create statements
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE "
            + TABLE_CATEGORY + "(" + CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CATEGORY_NAME
            + " TEXT NOT NULL" + ")";

    private static final String CREATE_TABLE_ITEMSIZE = "CREATE TABLE "
            + TABLE_ITEMSIZE + "(" + SIZE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + SIZE_NAME
            + " TEXT NOT NUL" + ")";

    private static final String CREATE_TABLE_ITEMCOLOR = "CREATE TABLE "
            + TABLE_ITEMCOLOR + "(" + COLOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLOR_NAME
            + " TEXT NOT NULL" + ")";

    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE "
            + TABLE_PRODUCT + "(" + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PRODUCT_NAME
            + " TEXT ," + PRODUCT_COMPANY + " TEXT ," + CATEGORY_ID + " INTEGER ," +
            PRODUCT_HAS_COLOR + " INTEGER ," + PRODUCT_HAS_SIZE + " INTEGER)";

    private static final String CREATE_TABLE_ITEM = "CREATE TABLE "
            + TABLE_ITEM + "(" + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + ITEM_COST_PRICE
            + " INTEGER ," + ITEM_SELLING_PRICE + " INTEGER ," + COLOR_ID + " INTEGER ," + ITEM_STOCK + " INTEGER ," +
            SIZE_ID + " INTEGER)";

    private static final String CREATE_TABLE_PRODUCT_ITEM = "CREATE TABLE "
            + TABLE_PRODUCT_ITEM + "(" + ITEM_ID + " INTEGER PRIMARY KEY ," + PRODUCT_ID
            + " INTEGER PRIMARY KEY)";

    private static final String CREATE_TABLE_ORDER_ITEMS = "CREATE TABLE "
            + TABLE_ORDER + "(" + ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ITEM_ID + " INTEGER ," + ORDER_SELLING_PRICE + " INTEGER ," + ORDER_ITEM_SOLD + " INTEGER DEFAULT 0,"
            + ORDER_ITEM_BOUGHT + " INTEGER DEFAULT 0," + ORDER_DATE + " INTEGER )";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_ITEMSIZE);
        db.execSQL(CREATE_TABLE_ITEMCOLOR);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_ITEM);
        db.execSQL(CREATE_TABLE_PRODUCT_ITEM);
        db.execSQL(CREATE_TABLE_ORDER_ITEMS);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMCOLOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMSIZE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        // create new tables
        onCreate(db);
    }
}
