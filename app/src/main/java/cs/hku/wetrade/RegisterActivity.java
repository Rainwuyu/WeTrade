package cs.hku.wetrade;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {
    EditText name;  //用户名
    EditText pass;  //密码
    Button Register;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        name=(EditText)findViewById(R.id.enterIDField);  // get username
        pass=(EditText)findViewById(R.id.enterPswdField);  // get password
        Register =(Button) findViewById(R.id.register);
    }

    // Register verification
    public void Register(View v) {
        String user = name.getText().toString().trim();
        String pwd = pass.getText().toString().trim();

        UserDB dbHelper = new UserDB(RegisterActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Gets a writable database instance

        String selectQuery = "SELECT username FROM userTable";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
                if (username.equals(user)) {
                    // If the username already exist
                    break;
                }
                // Process data
            } while (cursor.moveToNext());
        }
        boolean exists = !cursor.isAfterLast();
        cursor.close();

        if (user.equals("") || pwd.equals("")) {
            Toast.makeText(this, "The username or password cannot be null!", Toast.LENGTH_SHORT).show();
        }
        else if (!exists) {
            // insert new user data into database
            String insertQuery = "INSERT INTO userTable (username, password) VALUES ('" + user + "', '" + pwd + "')";
            db.execSQL(insertQuery);
            Toast.makeText(this, "Successful registration!", Toast.LENGTH_SHORT).show();
            // wait for 1 second
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // turn back to the login page
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Sorry, This user name has been registered!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

}