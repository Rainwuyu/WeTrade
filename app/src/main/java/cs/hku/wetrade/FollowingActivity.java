package cs.hku.wetrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FollowingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.following);

        ImageView home = findViewById(R.id.home);
        ImageView following = findViewById(R.id.star);
        ImageView upload = findViewById(R.id.add);
        ImageView me = findViewById(R.id.humanIcon);
        ImageView settings = findViewById(R.id.accountSettings);
        ImageView menu = findViewById(R.id.imageView);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FollowingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FollowingActivity.this, FollowingActivity.class);
                startActivity(intent);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FollowingActivity.this, UploadActivity.class);
                startActivity(intent);
           }
        });

        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FollowingActivity.this, MeActivity.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FollowingActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FollowingActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }
}