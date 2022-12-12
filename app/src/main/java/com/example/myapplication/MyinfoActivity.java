package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.hr.userstore.model.UserStore;


public class MyinfoActivity extends AppCompatActivity {

    private ImageButton back_button;
    private UserStore userStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);

        Intent intent = getIntent();
        userStore = (UserStore) intent.getSerializableExtra("userStore");
        back_button = findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyinfoActivity.this, CategoryActivity.class);
                intent.putExtra("userStore",userStore);
                startActivity(intent);
            }
        });

    }
}