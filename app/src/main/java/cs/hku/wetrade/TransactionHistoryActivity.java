package cs.hku.wetrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TransactionHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transactionhistory);

        ImageView home = findViewById(R.id.home);
        ImageView following = findViewById(R.id.star);
        ImageView upload = findViewById(R.id.add);
        ImageView me = findViewById(R.id.humanIcon);
        ImageView settings = findViewById(R.id.accountSettings);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TransactionHistoryActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TransactionHistoryActivity.this, FollowingActivity.class);
                startActivity(intent);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TransactionHistoryActivity.this, UploadActivity.class);
                startActivity(intent);
            }});

        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TransactionHistoryActivity.this, MeActivity.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TransactionHistoryActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });




    }
}