package cs.hku.wetrade;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ImageView pic;
    private List<HomeItem> ItemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ImageView home = findViewById(R.id.home);
        ImageView following = findViewById(R.id.star);
        ImageView upload = findViewById(R.id.add);
        ImageView me = findViewById(R.id.humanIcon);
        ImageView settings = findViewById(R.id.accountSettings);
        ImageView menu = findViewById(R.id.imageView2);
        pic = findViewById(R.id.textView18);

        initItem();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        HomeAdapter homeAdapter = new HomeAdapter(ItemList);
        recyclerView.setAdapter(homeAdapter);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, FollowingActivity.class);
                startActivity(intent);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(HomeActivity.this, UploadActivity.class);
               startActivity(intent);
           }});

        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, MeActivity.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initItem(){
        ItemDB itemDBHelper = new ItemDB(HomeActivity.this);
        SQLiteDatabase db = itemDBHelper.getReadableDatabase(); // Gets a readable database instance

        // Scan the database and put the information into an itemlist
        String selectItem = "SELECT * FROM itemTable";
        Cursor cursor = db.rawQuery(selectItem, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("_pid"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("itemname"));
                @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex("itemimage"));
                @SuppressLint("Range") int stock = cursor.getInt(cursor.getColumnIndex("stock"));
                @SuppressLint("Range") float price = cursor.getFloat(cursor.getColumnIndex("price"));
                @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex("category"));
                @SuppressLint("Range") String seller = cursor.getString(cursor.getColumnIndex("seller"));
                Bitmap bimage = stringToBitmap(image);
                pic.setImageBitmap(bimage);
                if (!name.equals("")) {
                    HomeItem homeItem = new HomeItem(id, name, bimage, stock, price, category, seller);
                    ItemList.add(homeItem);
                } else {
                    id = 1;
                    name = "DVD";
                    bimage = null;
                    stock = 1;
                    price = 500;
                    category = "Electronics";
                    seller = "hku";
                    HomeItem homeItem = new HomeItem(id, name, bimage, stock, price, category, seller);
                    ItemList.add(homeItem);
                }

            } while (cursor.moveToNext());
        }
        cursor.close();

    }

    public static Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string.split(",")[1], Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
