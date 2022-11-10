package com.erentarak.hw1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter<Product> {
    Context context;
    ArrayList<Product> spinnerItemValues;

    public CustomSpinnerAdapter(@NonNull Context context,  ArrayList<Product> spinnerItemValues) {
        super(context,R.layout.drinking_spinner,spinnerItemValues);
        this.context = context;
        this.spinnerItemValues = spinnerItemValues;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position,convertView,parent);
    }

    public View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.drinking_spinner, parent, false);

        ConstraintLayout constraintLayout = view.findViewById(R.id.constraint);
        ImageView imgItemProduct = view.findViewById(R.id.imgProduct);

        //values
        TextView productName = view.findViewById(R.id.productName);
        TextView productPrice = view.findViewById(R.id.priceValue);
        TextView productCalorie = view.findViewById(R.id.calValue);
        //titles
        TextView priceTitle = view.findViewById(R.id.priceTitle);
        TextView calorieTitle = view.findViewById(R.id.calorieTitle);


        Product selectedProduct = spinnerItemValues.get(position);
        productName.setText(selectedProduct.getName());
        imgItemProduct.setImageResource(selectedProduct.getImg_Id());
        productPrice.setText(""+ selectedProduct.getPrice());
        productCalorie.setText(""+selectedProduct.getCalorie());



        if(position % 2 == 0)
            constraintLayout.setBackgroundColor(Color.BLUE);
        else
            constraintLayout.setBackgroundColor(Color.YELLOW);


        return view;
    }
}
