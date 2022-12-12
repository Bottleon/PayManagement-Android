package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class MarketlistOwnerActivity extends AppCompatActivity {

    Button market1_button;
    ImageButton back_button;
    ImageButton mypage_imagebutton;
    Button add_market_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketlist_owner);


        market1_button = findViewById(R.id.market1_button);
        back_button = findViewById(R.id.back_button);
        mypage_imagebutton = findViewById(R.id.mypage_imagebutton);
        add_market_button = findViewById(R.id.add_market_button);


        market1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarketlistOwnerActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarketlistOwnerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mypage_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarketlistOwnerActivity.this, MyinfoActivity.class);
                startActivity(intent);
            }
        });

        add_market_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarketlistOwnerActivity.this, AddMarketOwnerActivity.class);
                startActivity(intent);
            }
        });




    }
}
