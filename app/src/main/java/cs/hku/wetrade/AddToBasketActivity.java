package cs.hku.wetrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddToBasketActivity extends AppCompatActivity {
    ImageView menu = findViewById(R.id.imageView2);
    TextView itemname, category, price, description;
    Button confirm, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtobasket);

        confirm = (Button) findViewById(R.id.confirm);
        cancel = (Button) findViewById(R.id.cancel);

        ItemDB itemDBHelper = new ItemDB(AddToBasketActivity.this);
        SQLiteDatabase db1 = itemDBHelper.getReadableDatabase(); // Gets a readable database instance

        CartDB cartDBHelper = new CartDB(AddToBasketActivity.this);
        SQLiteDatabase db2 = cartDBHelper.getWritableDatabase(); // Gets a writable database instance

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                cartDBHelper.insertData();
                Intent intent=new Intent(AddToBasketActivity.this, BasketActivity.class);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: adjust the skip page
                Intent intent=new Intent(AddToBasketActivity.this, BasketActivity.class);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddToBasketActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}