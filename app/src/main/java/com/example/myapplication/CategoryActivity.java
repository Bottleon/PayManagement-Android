package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class CategoryActivity extends AppCompatActivity {

    ImageButton back_button;
    Button qrcheck_button;
    Button chatting_button;
    Button calendar_button;
    Button schedule_button;
    Button notice_button;
    Button checklist_button;
    Button stafflist_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        back_button = findViewById(R.id.back_button);
        qrcheck_button = findViewById(R.id.qrcheck_button);
        chatting_button = findViewById(R.id.chatting_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, MarketlistActivity.class);
                startActivity(intent);
            }
        });

        qrcheck_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, QRcheckActivity.class);
                startActivity(intent);
            }
        });

        chatting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, ChattingActivity.class);
                startActivity(intent);
            }
        });

    }
}
