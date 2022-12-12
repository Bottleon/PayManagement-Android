package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.myapplication.common.recycleview.CustomAdapter;
import com.example.myapplication.hr.userstore.model.UserStore;

import java.util.ArrayList;


public class StafflistActivity extends AppCompatActivity {


    private ImageButton back_button;
    private ImageButton mypage_imagebutton;
    private UserStore userStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stafflist);

        Intent intent = getIntent();
        userStore = (UserStore) intent.getSerializableExtra("userStore");
        back_button = findViewById(R.id.back_button);
        mypage_imagebutton = findViewById(R.id.mypage_imagebutton);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StafflistActivity.this, CategoryActivity.class);
                intent.putExtra("userStore",userStore);
                startActivity(intent);
            }
        });

        mypage_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StafflistActivity.this, MyinfoActivity.class);
                intent.putExtra("userStore",userStore);
                startActivity(intent);
            }
        });

        ArrayList<String> testDataSet = new ArrayList<>();
        testDataSet.add("이름 01055555555");
        testDataSet.add("이름 01011111112");

        RecyclerView recyclerView = findViewById(R.id.stafflist_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter customAdapter = new CustomAdapter(testDataSet);
        recyclerView.setAdapter(customAdapter);
    }
}










