package com.erentarak.hw1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Spinner drinkSpinner;
    SeekBar SeekBarCount;
    Button addtoOrderList;

    TextView tvResult;
    TextView appTitle;

    private ValueAnimator colorAnim;

    ArrayList<Product> products;
    EditText drinkCount;
    int num=0;


    double TotalPrice=0;
    double TotalCalorie=0;

    String selectedDrink;
    Product selectedProduct;
    ArrayList<Product> orderlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Hiding title bar using code
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        drinkSpinner = findViewById(R.id.spinner);
        addtoOrderList = findViewById(R.id.addBtn);

        SeekBarCount = findViewById(R.id.seekBar);
        tvResult = findViewById(R.id.tvResult);
        appTitle = findViewById(R.id.appTitle);

        // color animation
        colorAnim = ObjectAnimator.ofInt(appTitle, "textColor", Color.RED, Color.BLUE);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();



        prepareDrinksData();
        //Fill the custom spinner
         CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, products);
        drinkSpinner.setAdapter(adapter);



        int max = 20;
        int min = 1;
        int step = 1;
        SeekBarCount.setMax( (max - min) / step );
        SeekBarCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //called when progress level (thumb position) has been changed.

                tvResult.setText(SeekBarCount.getProgress() + "");
                num= SeekBarCount.getProgress();
                //Calculate total price and calorie

                TotalPrice =  calculateTotalPrice(selectedProduct,num);
                TotalCalorie = calculateTotalCalorie(selectedProduct,num);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        addtoOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast Message
                String msg;
                if(num == 0) msg= "You didnt add count!";
                else{
                 msg =num +" "+selectedDrink +" added to basket";}
                Toast.makeText(MainActivity.this,msg, Toast.LENGTH_SHORT).show();

                // Intents
                Intent intent = null;
                intent = new Intent(MainActivity.this,Second_Activity.class);
                intent.putExtra("order",selectedProduct);
                intent.putExtra("TotalPrice",TotalPrice);
                intent.putExtra("TotalCalorie",TotalCalorie);
                startActivity(intent);
                


            }
        });


        //Catch the item selected event over spinner
        drinkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Product stemp = products.get(position);
                orderlist = new ArrayList<Product>();
                orderlist.add(stemp);

                  TextView selectText= findViewById(R.id.selectText);
                //   ImageView imItem = view.findViewById(R.id.imgItemSocial);
                 ConstraintLayout clayout = view.findViewById(R.id.constraint);

                 selectText.setText( stemp.getName() +" selected");
                 selectedProduct = stemp;

                selectedDrink =  stemp.getName()  + " ";
                //imItem.setImageResource(R.mipmap.ic_launcher);
                 clayout.setBackgroundColor(Color.CYAN);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private double calculateTotalCalorie(Product selectedProduct, int num) {
        if(selectedProduct == null) return 0;
        return selectedProduct.getCalorie() * num;
    }

    private double calculateTotalPrice(Product product,int number) {
        if(product == null) return 0;
        return product.getPrice() * number;
    }

    private void prepareDrinksData() {
        products = new ArrayList<>();
        Collections.addAll(products,
                new Product("tea",R.drawable.tea,20,5),
                new Product("coffe",R.drawable.coffe,50,12),
                new Product("juice",R.drawable.juice,30,14)
        );
    }
}