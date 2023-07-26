package cs.hku.wetrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TransactionCompletedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transactioncompleted);

        Button orderStatus = findViewById(R.id.orderStatusButton);
        orderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TransactionCompletedActivity.this, TransactionHistoryActivity.class);
                startActivity(intent);
            }
        });




    }


}