package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.hr.user.model.User;
import com.example.myapplication.hr.userstore.model.UserStore;


public class MarketInfoActivity extends AppCompatActivity {

    private ImageButton back_button;
    private ImageButton mypage_imagebutton;
    private UserStore userStore;
    private TextView market_phone_number_textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_info);

        Intent intent = getIntent();
        userStore = (UserStore) intent.getSerializableExtra("userStore");
        back_button = findViewById(R.id.back_button);
        mypage_imagebutton = findViewById(R.id.mypage_imagebutton);
        market_phone_number_textview = findViewById(R.id.market_phone_number);
        market_phone_number_textview.setText(userStore.getStore().getName());
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarketInfoActivity.this, CategoryActivity.class);
                intent.putExtra("userStore",userStore);
                startActivity(intent);
            }
        });

        mypage_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarketInfoActivity.this, MyinfoActivity.class);
                intent.putExtra("userStiore",userStore);
                startActivity(intent);
            }
        });

    }
}
