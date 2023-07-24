package cs.hku.wetrade;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDB extends SQLiteOpenHelper {
    // Database name and version number
    private static final String DATABASE_NAME = "User.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    public static final String USER_TABLE_NAME = "userTable";

    // Column name
    public static final String COLUMN_UID = "_uid";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_USERIMAGE = "userimage";
    private static final String COLUMN_FOLLOWING = "following";
    private static final String COLUMN_FOLLOWERS = "followers";
    private static final String COLUMN_POSTS = "posts";
    private static final String COLUMN_REVENUE = "revenue";
    private static final String COLUMN_EXPENSE = "expense";
    private static final String COLUMN_ACCOUNT = "account";
    private static final String COLUMN_CREDIT = "credit";
    private static final String COLUMN_ADDRESS = "address";

    public UserDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create database table
        String createTableQuery = "CREATE TABLE " + USER_TABLE_NAME + "(" +
                COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_USERIMAGE + " BLOB, " +
                COLUMN_FOLLOWING + " INTEGER, " +
                COLUMN_FOLLOWERS + " INTEGER, " +
                COLUMN_POSTS + " INTEGER, " +
                COLUMN_REVENUE + " FLOAT, " +
                COLUMN_EXPENSE + " FLOAT, " +
                COLUMN_ACCOUNT + " TEXT, " +
                COLUMN_CREDIT + " TEXT, " +
                COLUMN_ADDRESS + " TEXT )";
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
    public void insertData(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        String insertQuery = "INSERT INTO " + USER_TABLE_NAME + " (" +
                COLUMN_USERNAME + ", " +
                COLUMN_PASSWORD + ") VALUES ('" +
                username + "', '" +
                password + "')";
        db.execSQL(insertQuery);
        db.close();
    }

    // Query all data
    public Cursor getAllData() {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + USER_TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    // Update data
    public void updateData(int uid, String newUsername) {
        SQLiteDatabase db = getWritableDatabase();
        String updateQuery = "UPDATE " + USER_TABLE_NAME + " SET " +
                COLUMN_USERNAME + " = '" + newUsername + "' WHERE " +
                COLUMN_UID + " = " + uid;
        db.execSQL(updateQuery);
        db.close();
    }

    // Delete data
    public void deleteData(int uid) {
        SQLiteDatabase db = getWritableDatabase();
        String deleteQuery = "DELETE FROM " + USER_TABLE_NAME + " WHERE " +
                COLUMN_UID + " = " + uid;
        db.execSQL(deleteQuery);
        db.close();
    }
}