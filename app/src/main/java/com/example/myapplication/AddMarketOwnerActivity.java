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


public class AddMarketOwnerActivity extends AppCompatActivity {

    Button market1_button;
    ImageButton back_button;
    ImageButton mypage_imagebutton;
    Button add_button;
    EditText market_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_market);


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddMarketOwnerActivity.this, MarketlistOwnerActivity.class);
                startActivity(intent);
            }
        });

        mypage_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddMarketOwnerActivity.this, MyinfoActivity.class);
                startActivity(intent);
            }
        });


    }
}

