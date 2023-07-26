package cs.hku.wetrade;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MeActivity extends AppCompatActivity {
    TextView loginID;
    public static String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me);
        ImageView home = findViewById(R.id.home);
        ImageView following = findViewById(R.id.star);
        ImageView upload = findViewById(R.id.add);
        ImageView me = findViewById(R.id.humanIcon);
        ImageView settings = findViewById(R.id.accountSettings);
        ImageView menu = findViewById(R.id.imageView);

        loginID = (TextView) findViewById(R.id.showLoginID);

        loginID.setText(uname);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MeActivity.this, FollowingActivity.class);
                startActivity(intent);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            Intent intent=new Intent(MeActivity.this, UploadActivity.class);
              startActivity(intent);
           }
        });

        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MeActivity.this, MeActivity.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MeActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MeActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }
}