package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.common.exception.APIError;
import com.example.myapplication.common.recycleview.StoreAdapter;
import com.example.myapplication.common.retrofit.RetrofitClient;
import com.example.myapplication.common.token.TokenUtil;
import com.example.myapplication.hr.store.model.Store;
import com.example.myapplication.hr.user.api.UserApi;
import com.example.myapplication.hr.user.model.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MarketlistActivity extends AppCompatActivity {

    private ImageButton back_button;
    private Retrofit retrofit;
    private UserApi userApi;
    private User user;
    private List<Store> stores;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private StoreAdapter storeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketlist);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        initailizationComponent();
        initailizationEvent();
    }
    private void initailizationComponent(){
        back_button = findViewById(R.id.back_button);
        retrofit = RetrofitClient.getInstance();
        userApi = retrofit.create(UserApi.class);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        stores = new ArrayList<>();
        storeAdapter = new StoreAdapter((ArrayList<Store>) stores);
        recyclerView.setAdapter(storeAdapter);
    }
    private void initailizationEvent(){
        userApi.getStoresByUser(TokenUtil.getAccessToken("Authorization"),user.getId()).enqueue(new Callback<List<Store>>() {
            @Override
            public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
                if(response.isSuccessful()){
                    for(Store s : response.body()){
                        stores.add(s);
                        storeAdapter.notifyDataSetChanged();
                    }

                }else{
                    if (response.errorBody() != null) {
                        try {
                            Gson gson = new Gson();
                            APIError error = gson.fromJson(response.errorBody().string(),APIError.class);
                            showMessage(error.message());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Store>> call, Throwable t) {
                Toast.makeText(MarketlistActivity.this,"Failed get Store",Toast.LENGTH_SHORT).show();
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,t.getMessage());
            }
        });
        /*market1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarketlistActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });*/

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarketlistActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void showMessage(String msg){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setIcon(R.mipmap.ic_launcher);//알림창 아이콘 설정
        dialog.setTitle("알림창");
        dialog.setMessage(msg); //알림창 메세지 설정

        //알림창의 확인를 눌렀을때 발생 이벤트 설정
        dialog.setNeutralButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"창을 닫습니다",Toast.LENGTH_SHORT).show();
            } //확인 눌렀을때, 토스트 알림메세지 뜸.
        });
        dialog.show();
    }
}