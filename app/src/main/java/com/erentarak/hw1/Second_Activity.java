package com.erentarak.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Second_Activity extends AppCompatActivity {
    TextView titleView,priceValue,calorieValue;
    String titleValue;
    ImageView img;
    Button btn;
    Intent intent = null;
    int count;
    Double totalCalorie;
    Double totalPrice;
    TextView countVal ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        titleView = findViewById(R.id.secondTitle);
        img = findViewById(R.id.secondImg);
        priceValue =findViewById(R.id.totalPrice);
        calorieValue =findViewById(R.id.totalCalorie);
        countVal =findViewById(R.id.count);
        btn = findViewById(R.id.button);


       // get Intent
        Intent i = getIntent();
        Product orderedItem = (Product) i.getSerializableExtra("order");
         totalPrice = (Double)i.getSerializableExtra("TotalPrice");
        totalCalorie =(Double)i.getSerializableExtra("TotalCalorie") ;
        count = (int)i.getSerializableExtra("Count");

        String currency ="Tl";
        String cal="cal";
        String priceval = totalPrice + currency;
        String calval = totalCalorie + cal;
        priceValue.setText(priceval);
        calorieValue.setText(calval);

        titleValue = orderedItem.getName();
        titleView.setText(titleValue);
        countVal.setText(count+"");
        img.setImageResource(orderedItem.getImg_Id());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Second_Activity.this, ThirdActivity.class);
                Bundle b = new Bundle();
                b.putDouble("totalCal",totalCalorie);
                b.putDouble("totalPrice",totalPrice);

                intent.putExtras(b);
                startActivity(intent);
            }
        });



    }
}