package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.common.exception.APIError;
import com.example.myapplication.common.retrofit.RetrofitClient;
import com.example.myapplication.common.token.TokenUtil;
import com.example.myapplication.hr.notice.api.NoticeApi;
import com.example.myapplication.hr.notice.model.Notice;
import com.example.myapplication.hr.userstore.model.UserStore;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NoticeActivity extends AppCompatActivity {

    private ImageButton back_button;
    private ImageButton mypage_imagebutton;
    private Button write_button;
    private Button notice_post;
    private UserStore userStore;
    private NoticeApi noticeApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        Intent intent = getIntent();
        userStore = (UserStore) intent.getSerializableExtra("userStore");

        back_button = findViewById(R.id.back_button);
        mypage_imagebutton = findViewById(R.id.mypage_imagebutton);
        write_button = findViewById(R.id.write_button);
        notice_post = findViewById(R.id.notice_post);
        noticeApi = RetrofitClient.getInstance().create(NoticeApi.class);

        noticeApi.getNoticeAll(TokenUtil.getAccessToken("atk"),userStore.getId()).enqueue(new Callback<List<Notice>>() {
            @Override
            public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                if(response.isSuccessful()){
                }else{
                    if (response.errorBody() != null) {
                        try {
                            Gson gson = new Gson();
                            APIError error = gson.fromJson(response.errorBody().string(),APIError.class);
                            showMessage(NoticeActivity.this,error.message());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {
                Toast.makeText(NoticeActivity.this,"get notice Failed",Toast.LENGTH_SHORT).show();
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,t.getMessage());
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoticeActivity.this, CategoryActivity.class);
                intent.putExtra("userStore",userStore);
                startActivity(intent);
            }
        });


        mypage_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoticeActivity.this, MyinfoActivity.class);
                intent.putExtra("userStore",userStore);
                startActivity(intent);
            }
        });

        write_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoticeActivity.this, NoticeWriteActivity.class);
                intent.putExtra("userStore",userStore);
                startActivity(intent);
            }
        });

        notice_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoticeActivity.this, NoticePostActivity.class);
                intent.putExtra("userStore",userStore);
                startActivity(intent);
            }
        });

    }
    private void showMessage(Context context, String msg){
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        dialog.setIcon(R.mipmap.ic_launcher);//알림창 아이콘 설정
        dialog.setTitle("알림창");
        dialog.setMessage(msg); //알림창 메세지 설정

        //알림창의 확인를 눌렀을때 발생 이벤트 설정
        //확인 눌렀을때, 토스트 알림메세지 뜸.
        dialog.setNeutralButton("확인", (dialog1, which) -> {
            Intent intent = new Intent(NoticeActivity.this,CategoryActivity.class);
            intent.putExtra("userStore", userStore);
            startActivity(intent);
        });
        dialog.show();
    }
}
