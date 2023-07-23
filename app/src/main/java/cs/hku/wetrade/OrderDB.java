package cs.hku.wetrade;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrderDB extends SQLiteOpenHelper {
    // Database name and version number
    private static final String DATABASE_NAME = "Order.db";

    private static final int DATABASE_VERSION = 1;

    // Table name
    public static final String ORDER_TABLE_NAME = "orderTable";

    // Column name
    public static final String COLUMN_OID = "_oid";
    private static final String COLUMN_TOTAL = "total";
    private static final String COLUMN_STATE = "state";

    public OrderDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create database table
        String createTableQuery = "CREATE TABLE " + ORDER_TABLE_NAME + "(" +
                COLUMN_OID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UserDB.COLUMN_UID + " INTEGER, " +
                ItemDB.COLUMN_PID + " INTEGER, " +
                COLUMN_TOTAL + " FLOAT," +
                COLUMN_STATE + " TEXT," +
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
    public void insertData(int uid, int pid) {
        SQLiteDatabase db = getWritableDatabase();
        String insertQuery = "INSERT INTO " + ORDER_TABLE_NAME + " (" +
                UserDB.COLUMN_UID + ", " +
                ItemDB.COLUMN_PID + ", " +
                COLUMN_TOTAL + ") VALUES ('" +
                uid + "', '" +
                pid + "')";
        db.execSQL(insertQuery);
        db.close();
    }

    // Query all data
    public Cursor getAllData() {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + ORDER_TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    // Update total
    public void updateTotal(int oid, float newTotal) {
        SQLiteDatabase db = getWritableDatabase();
        String updateQuery = "UPDATE " + ORDER_TABLE_NAME + " SET " +
                COLUMN_TOTAL + " = '" + newTotal + "' WHERE " +
                COLUMN_OID + " = " + oid;
        db.execSQL(updateQuery);
        db.close();
    }

    // Update state
    public void updateState(int oid, float newState) {
        SQLiteDatabase db = getWritableDatabase();
        String updateQuery = "UPDATE " + ORDER_TABLE_NAME + " SET " +
                COLUMN_STATE + " = '" + newState + "' WHERE " +
                COLUMN_OID + " = " + oid;
        db.execSQL(updateQuery);
        db.close();
    }

    // Delete data
    public void deleteData(int oid) {
        SQLiteDatabase db = getWritableDatabase();
        String deleteQuery = "DELETE FROM " + ORDER_TABLE_NAME + " WHERE " +
                COLUMN_OID + " = " + oid;
        db.execSQL(deleteQuery);
        db.close();
    }
}