package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.myapplication.hr.userstore.model.UserStore;


public class CategoryActivity extends AppCompatActivity {

    private ImageButton back_button;
    private Button qrcheck_button;
    private Button chatting_button;
    private Button calendar_button;
    private Button schedule_button;
    private Button notice_button;
    private Button checklist_button;
    private Button stafflist_button;
    private UserStore userStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent = getIntent();
        userStore = (UserStore) intent.getSerializableExtra("userStore");
        back_button = findViewById(R.id.back_button);
        qrcheck_button = findViewById(R.id.qrcheck_button);
        chatting_button = findViewById(R.id.chatting_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userAutyType = userStore.getUser().getAuthType();
                Intent intent;
                if(userAutyType.equals("근로자")){
                    intent = new Intent(CategoryActivity.this, QRcheckActivity.class);
                    intent.putExtra("userStore",userStore);
                }else{
                    intent = new Intent(CategoryActivity.this, MarketlistActivity.class);
                    intent.putExtra("user",userStore.getUser());
                }
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
