package com.erentarak.hw1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    AlertDialog.Builder builder;
    TextView bmıValue;
    EditText editKg,editHeight;
    Button btn;
    Intent intent= null;
    TextView orderText;
    Double bmı;
    int kg;
    int height;
    int totalCal;
    Boolean isHealthy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        bmıValue =findViewById(R.id.bodymass);
        editKg = findViewById(R.id.editKg);
        editHeight = findViewById(R.id.editHeight);
        btn =  findViewById(R.id.btnCalc);
        builder = new AlertDialog.Builder(this);
        orderText=findViewById(R.id.orderText);

       // get data
        intent = getIntent();
        Bundle b = intent.getExtras();
        double totalPrice = b.getInt("totalPrice");
        totalCal = b.getInt("totalCal");



        // calculate
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    kg= Integer.parseInt(editKg.getText().toString());
                    height = Integer.parseInt((editHeight.getText().toString()));
                    bmı =calcBMI(kg,height);
                   isHealthy = calcIsHealthy(bmı,totalCal);


                } catch (NumberFormatException nfe) {
                    // Handle the condition when str is not a number.
                    kg =0;
                    height=0;
                }

                Log.d("bmı:",bmı +"");


                if(bmı ==0) bmıValue.setText("Invalid Input!");
                else
                bmıValue.setText(bmı+"");

                // msg for alert
                String msg;
                if(isHealthy)
                    msg="It is healthy Order it now";
                else
                    msg="It is harmful for your health,Do you still want to order?";
                builder.setTitle("Alert")
                        .setMessage(bmı+" "+msg)
                        .setCancelable(true)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               if(isHealthy)
                                orderText.setText("Your Order is prepared now!");
                               else
                                   orderText.setText("Please select a lower calorie order");
                                finish();
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                               orderText.setText("Your order is cancelled :(");
                                dialogInterface.cancel();
                            }
                        })
                        .show();


            }
        });




    }

    private Boolean calcIsHealthy(double bmı,int totalCal) {
        Boolean isHealthy =false;
       if(bmı<45 && bmı >35)
       {    if(totalCal <100)
           isHealthy = true;
       }

       else if(bmı<35 && bmı >25) {
           if(totalCal <180)
               isHealthy = true;
       }
       else if(bmı<25 && bmı >20) {
            if(totalCal < 250)
               isHealthy = true;

       }
       return isHealthy;

    }

    private double calcBMI(int kg,int height) {
        if(height== 0 || kg ==0) return 0;

        double BMI = kg / (height/100.0 * height/100.0);
        return BMI;
    }
}