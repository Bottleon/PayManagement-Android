package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CategoryActivity extends AppCompatActivity {
    private ImageButton backButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initializationComponents(){
        backButton = findViewById(R.id.back_button);
    }

    private void initializationFunctions(){

    }

    private void clickBackFunction(){
        backButton.setOnClickListener(view->{

        });
    }
}
