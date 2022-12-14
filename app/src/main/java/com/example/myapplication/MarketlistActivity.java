package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.myapplication.hr.userstore.api.UserStoreApi;
import com.example.myapplication.hr.userstore.model.UserStore;
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
    private User sendUser;
    private List<Store> stores;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private StoreAdapter storeAdapter;
    private Handler handler;
    private UserStoreApi userStoreApi;
    private List<UserStore> storesRelatedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketlist);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        handler = new Handler();
        initailizationComponent();
        initailizationEvent();
    }
    private synchronized void initailizationComponent(){
        back_button = findViewById(R.id.back_button);
        retrofit = RetrofitClient.getInstance();
        userApi = retrofit.create(UserApi.class);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        stores = new ArrayList<>();
        userStoreApi = RetrofitClient.getInstance().create(UserStoreApi.class);
        userApi.getUser(TokenUtil.getAccessToken("atk"),user.getId()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    sendUser = response.body();
                    storeAdapter = new StoreAdapter((ArrayList<Store>) stores,sendUser,MarketlistActivity.this);
                    recyclerView.setAdapter(storeAdapter);
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
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MarketlistActivity.this,"Failed get User",Toast.LENGTH_SHORT).show();
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,t.getMessage());
            }
        });

    }
    private synchronized void initailizationEvent(){
        userStoreApi.getStoresByUserId(TokenUtil.getAccessToken("act"),user.getId()).enqueue(new Callback<List<UserStore>>() {
            @Override
            public void onResponse(Call<List<UserStore>> call, Response<List<UserStore>> response) {
                if(response.isSuccessful()){
                    for(UserStore us : response.body()){
                        stores.add(us.getStore());
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
            public void onFailure(Call<List<UserStore>> call, Throwable t) {
                Toast.makeText(MarketlistActivity.this,"Failed get Store",Toast.LENGTH_SHORT).show();
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,t.getMessage());
            }
        });
        back_button.setOnClickListener(view -> {
            Intent intent = new Intent(MarketlistActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
    private void showMessage(String msg){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setIcon(R.mipmap.ic_launcher);//????????? ????????? ??????
        dialog.setTitle("?????????");
        dialog.setMessage(msg); //????????? ????????? ??????

        //???????????? ????????? ???????????? ?????? ????????? ??????
        dialog.setNeutralButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"?????? ????????????",Toast.LENGTH_SHORT).show();
            } //?????? ????????????, ????????? ??????????????? ???.
        });
        dialog.show();
    }
}