package cs.hku.wetrade;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemDB extends SQLiteOpenHelper {
    // Database name and version number
    private static final String DATABASE_NAME = "Item.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    public static final String ITEM_TABLE_NAME = "itemTable";

    // Column name
    public static final String COLUMN_PID = "_pid";
    private static final String COLUMN_ITEMNAME = "itemname";
    private static final String COLUMN_ITEMIMAGE = "itemimage";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_DISCRIPTION = "discription";
    private static final String COLUMN_STOCK = "stock";
    private static final String COLUMN_SELLER = "seller";

    public ItemDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create database table
        String createTableQuery = "CREATE TABLE " + ITEM_TABLE_NAME + "(" +
                COLUMN_PID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ITEMNAME + " TEXT, " +
                COLUMN_ITEMIMAGE + " BLOB, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_PRICE + " FLOAT, " +
                COLUMN_DISCRIPTION + " TEXT, " +
                COLUMN_STOCK + " INTEGER, " +
                COLUMN_SELLER + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade the database version
        if (oldVersion < newVersion) {
            // Perform upgrade operations, such as adding a new table or modifying the table structure
        }
    }

    // Insert data
    public void insertData(String itemname, long itemimage, String category, float price, String discription, int stock, String seller) {
        SQLiteDatabase db = getWritableDatabase();
        String insertQuery = "INSERT INTO " + ITEM_TABLE_NAME + " (" +
                COLUMN_ITEMNAME + ", " +
                COLUMN_ITEMIMAGE + ", " +
                COLUMN_CATEGORY + ", " +
                COLUMN_PRICE + ", " +
                COLUMN_DISCRIPTION + ", " +
                COLUMN_STOCK + ", " +
                COLUMN_SELLER + ") VALUES ('" +
                itemname + "', '" +
                itemimage + "', '" +
                category + "', '" +
                price + "', '" +
                discription + "', '" +
                stock + "', '" +
                seller + "')";
        db.execSQL(insertQuery);
        db.close();
    }

    // Query all data
    public Cursor getAllData() {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + ITEM_TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    // Update data
    public void updateData(int pid, float newPrice) {
        SQLiteDatabase db = getWritableDatabase();
        String updateQuery = "UPDATE " + ITEM_TABLE_NAME + " SET " +
                COLUMN_STOCK + " = '" + newPrice + "' WHERE " +
                COLUMN_PID + " = " + pid;
        db.execSQL(updateQuery);
        db.close();
    }

    // Delete data
    public void deleteData(int pid) {
        SQLiteDatabase db = getWritableDatabase();
        String deleteQuery = "DELETE FROM " + ITEM_TABLE_NAME + " WHERE " +
                COLUMN_PID + " = " + pid;
        db.execSQL(deleteQuery);
        db.close();
    }
}