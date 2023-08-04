package cs.hku.wetrade;

import static cs.hku.wetrade.UploadActivity.getSmallBitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UploadSuccessfulActivity extends AppCompatActivity {
    String image, name, category, desc;
    int stock;
    float price;

    @SuppressLint("Range")
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
        ImageView simage = findViewById(R.id.uploadedPhoto);
        TextView sname = findViewById(R.id.itemName);
        TextView scatagory = findViewById(R.id.itemCategory);
        TextView sstock = findViewById(R.id.itemStockQuantity);
        TextView sdesc = findViewById(R.id.itemDescription);
        TextView sprice = findViewById(R.id.itemPrice);

        ItemDB itemDBHelper = new ItemDB(UploadSuccessfulActivity.this);
        SQLiteDatabase db = itemDBHelper.getReadableDatabase(); // Gets a readable database instance

        String selectItem = "SELECT * FROM itemTable ORDER BY _pid desc LIMIT 1";
        Cursor cursor = db.rawQuery(selectItem, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                image = cursor.getString(cursor.getColumnIndex("itemimage"));
                name = cursor.getString(cursor.getColumnIndex("itemname"));
                category = cursor.getString(cursor.getColumnIndex("category"));
                stock = cursor.getInt(cursor.getColumnIndex("stock"));
                desc = cursor.getString(cursor.getColumnIndex("description"));
                float price = cursor.getFloat(cursor.getColumnIndex("price"));
                Bitmap bimage = getSmallBitmap(image);
                simage.setImageBitmap(bimage);
                sname.setText(name);
                scatagory.setText(category);
                sstock.setText(String.valueOf(stock));
                sdesc.setText(desc);
                sprice.setText(String.valueOf(price));
            } while (cursor.moveToNext());
        }
        cursor.close();

        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UploadSuccessfulActivity.this, BasketActivity.class);
                startActivity(intent);
            }
        });
    }
}