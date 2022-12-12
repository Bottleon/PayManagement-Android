package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.common.retrofit.RetrofitClient;
import com.example.myapplication.common.token.TokenUtil;
import com.example.myapplication.hr.userstore.api.UserStoreApi;
import com.example.myapplication.hr.userstore.model.UserStore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    private UserStore relatedStoreInformation;
    private UserStoreApi userStoreApi;
    private Button market_info_button;
    private ImageButton mypage_imagebutton;
    private TextView market_phone_number_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent = getIntent();
        userStore = (UserStore) intent.getSerializableExtra("userStore");
        initializationComponent();
        initializationEvent();
    }
    private void initializationComponent(){
        back_button = findViewById(R.id.back_button);
        qrcheck_button = findViewById(R.id.qrcheck_button);
        chatting_button = findViewById(R.id.chatting_button);
        notice_button = findViewById(R.id.notice_button);
        stafflist_button = findViewById(R.id.stafflist_button);
        market_info_button = findViewById(R.id.market_info_button);
        mypage_imagebutton = findViewById(R.id.mypage_imagebutton);
        calendar_button = findViewById(R.id.calendar_button);
        market_phone_number_text = findViewById(R.id.market_phone_number);
        userStoreApi = RetrofitClient.getInstance().create(UserStoreApi.class);
        market_phone_number_text.setText(userStore.getStore().getName());
    }

    private void initializationEvent(){
        getRelatedStoreInformation();
        onClickChattingButton();
        onClickQRCheckButton();
        onClickBackButton();
        onClickCalanderButton();
        onClickMarketInfoButton();
        onClickNoticeButton();
        onClickMyPageButton();
        onClickStaffListButton();
    }
    private void getRelatedStoreInformation(){
        userStoreApi.getRelatedUserStore(TokenUtil.getAccessToken("act"),userStore.getStore().getId()).enqueue(new Callback<UserStore>() {
            @Override
            public void onResponse(Call<UserStore> call, Response<UserStore> response) {

            }

            @Override
            public void onFailure(Call<UserStore> call, Throwable t) {

            }
        });
    }
    private void onClickBackButton(){
        back_button.setOnClickListener(view -> {
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
        });
    }
    private void onClickChattingButton(){
        chatting_button.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this, ChattingActivity.class);
            intent.putExtra("userStore",userStore);
            startActivity(intent);
        });
    }
    private void onClickNoticeButton(){
        notice_button.setOnClickListener(view->{
            Intent intent = new Intent(CategoryActivity.this, NoticeActivity.class);
            intent.putExtra("userStore",userStore);
            startActivity(intent);
        });
    }
    private void onClickStaffListButton(){
        stafflist_button.setOnClickListener(view->{
            Intent intent = new Intent(CategoryActivity.this, StafflistActivity.class);
            intent.putExtra("userStore",userStore);
            startActivity(intent);
        });
    }
    private void onClickMarketInfoButton(){
        market_info_button.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this, MarketInfoActivity.class);
            intent.putExtra("userStore",userStore);
            startActivity(intent);
        });
    }
    private void onClickMyPageButton(){
        mypage_imagebutton.setOnClickListener(view->{
            Intent intent = new Intent(CategoryActivity.this, MyinfoActivity.class);
            intent.putExtra("userStore",userStore);
            startActivity(intent);
        });
    }
    private void onClickCalanderButton(){
        calendar_button.setOnClickListener(view->{
            Intent intent = new Intent(CategoryActivity.this, CalendarActivity.class);
            intent.putExtra("userStore",userStore);
            startActivity(intent);
        });
    }
    private void onClickQRCheckButton(){
        qrcheck_button.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this, QRcheckActivity.class);
            intent.putExtra("userStore",userStore);
            startActivity(intent);
        });
    }
}
