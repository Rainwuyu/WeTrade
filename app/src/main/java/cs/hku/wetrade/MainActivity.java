package cs.hku.wetrade;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText name;  //用户名
    EditText pass;  //密码
    Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        name=(EditText)findViewById(R.id.enterIDField);  // get username
        pass=(EditText)findViewById(R.id.enterPswdField);  // get password
    }

    // Login verification
    public void Check(View v) {
        String mname = "hku";
        String mpass = "2222";
        String user = name.getText().toString().trim();
        String pwd = pass.getText().toString().trim();
        if (user.equals(mname) && pwd.equals(mpass)) {
            Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Sorry, wrong password!", Toast.LENGTH_SHORT).show();
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