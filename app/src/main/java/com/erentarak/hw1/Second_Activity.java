package com.erentarak.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Second_Activity extends AppCompatActivity {
    TextView titleView,priceValue,calorieValue;
    String titleValue;
    ImageView img;
    Button btn;
    Intent intent = null;
    Double totalCalorie;
    Double totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        titleView = findViewById(R.id.secondTitle);
        img = findViewById(R.id.secondImg);
        priceValue =findViewById(R.id.totalPrice);
        calorieValue =findViewById(R.id.totalCalorie);
        btn = findViewById(R.id.button);


       // get Intent
        Intent i = getIntent();
        Product orderedItem = (Product) i.getSerializableExtra("order");
         totalPrice = (Double)i.getSerializableExtra("TotalPrice");
        totalCalorie =(Double)i.getSerializableExtra("TotalCalorie") ;
        String currency ="Tl";
        String cal="cal";
        String priceval = totalPrice + currency;
        String calval = totalCalorie + cal;
        priceValue.setText(priceval);
        calorieValue.setText(calval);

        titleValue = orderedItem.getName();
        titleView.setText(titleValue);
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