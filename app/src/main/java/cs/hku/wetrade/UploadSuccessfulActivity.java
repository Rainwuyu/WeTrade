package cs.hku.wetrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class UploadSuccessfulActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadsuccessful);

        Button OK = findViewById(R.id.OK);
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UploadSuccessfulActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        ImageView basket = findViewById(R.id.imageView);

        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UploadSuccessfulActivity.this, BasketActivity.class);
                startActivity(intent);
            }
        });
    }
}