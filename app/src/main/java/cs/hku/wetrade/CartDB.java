package cs.hku.wetrade;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CartDB extends SQLiteOpenHelper {
    // Database name and version number
    private static final String DATABASE_NAME = "Cart.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    public static final String CART_TABLE_NAME = "cartTable";

    // Column name
    public static final String COLUMN_CID = "_cid";
    private static final String COLUMN_NUMBER = "number";

    public CartDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create database table
        String createTableQuery = "CREATE TABLE " + CART_TABLE_NAME + "(" +
                COLUMN_CID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UserDB.COLUMN_UID + " INTEGER, " +
                ItemDB.COLUMN_PID + " INTEGER, " +
                COLUMN_NUMBER + " INTEGER," +
                "FOREIGN KEY (" + UserDB.COLUMN_UID + ") REFERENCES " + UserDB.USER_TABLE_NAME + "(" + UserDB.COLUMN_UID + ")," +
                "FOREIGN KEY (" + ItemDB.COLUMN_PID + ") REFERENCES " + ItemDB.ITEM_TABLE_NAME + "(" + ItemDB.COLUMN_PID + ")" +
                ")";
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
    public void insertData(int uid, int pid, int number) {
        SQLiteDatabase db = getWritableDatabase();
        String insertQuery = "INSERT INTO " + CART_TABLE_NAME + " (" +
                UserDB.COLUMN_UID + ", " +
                ItemDB.COLUMN_PID + ", " +
                COLUMN_NUMBER + ") VALUES ('" +
                uid + "', '" +
                pid + "', '" +
                number + "')";
        db.execSQL(insertQuery);
        db.close();
    }

    // Query all data
    public Cursor getAllData() {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + CART_TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    // Update data
    public void updateData(int cid, float newNumber) {
        SQLiteDatabase db = getWritableDatabase();
        String updateQuery = "UPDATE " + CART_TABLE_NAME + " SET " +
                COLUMN_NUMBER + " = '" + newNumber + "' WHERE " +
                COLUMN_CID + " = " + cid;
        db.execSQL(updateQuery);
        db.close();
    }

    // Delete data
    public void deleteData(int cid) {
        SQLiteDatabase db = getWritableDatabase();
        String deleteQuery = "DELETE FROM " + CART_TABLE_NAME + " WHERE " +
                COLUMN_CID + " = " + cid;
        db.execSQL(deleteQuery);
        db.close();
    }
}