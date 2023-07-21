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

public class MainActivity extends AppCompatActivity {
    EditText name;  //用户名
    EditText pass;  //密码
    Button Login;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        name = (EditText) findViewById(R.id.enterIDField);  // get username
        pass = (EditText) findViewById(R.id.enterPswdField);  // get password
        Register =(Button) findViewById(R.id.register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    // Login verification
    public void Check(View v) {
        String user = name.getText().toString().trim();
        String pwd = pass.getText().toString().trim();

        UserDB dbHelper = new UserDB(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Gets a writable database instance

        String selectUsername = "SELECT username FROM userTable";
        Cursor cursor1 = db.rawQuery(selectUsername, null);
        if (cursor1 != null && cursor1.moveToFirst()) {
            do {
                @SuppressLint("Range") String username = cursor1.getString(cursor1.getColumnIndex("username"));
                if (username.equals(user)) {
                    // If the username already exist
                    break;
                }
                // Process data
            } while (cursor1.moveToNext());
        }
        boolean exists = !cursor1.isAfterLast();
        cursor1.close();

        if (user.equals("") || pwd.equals("")) {
            Toast.makeText(this, "The username or password cannot be null!", Toast.LENGTH_SHORT).show();
        }
        if (user.equals("hku") && pwd.equals("666")) {
            Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        else if (exists) {
            String selectPassword = "SELECT password FROM userTable WHERE username = '"+user+"'";
            Cursor cursor2 = db.rawQuery(selectPassword, null);
            if (cursor2 != null && cursor2.moveToFirst()) {
                do {
                    @SuppressLint("Range") String password = cursor2.getString(cursor2.getColumnIndex("password"));
                    if (password.equals(pwd)) {
                        // If the password is correct
                        Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    } else {
                        Toast.makeText(this, "Sorry, wrong password!", Toast.LENGTH_SHORT).show();
                    }
                    // Process data
                } while (cursor2.moveToNext());
            }
            cursor2.close();
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