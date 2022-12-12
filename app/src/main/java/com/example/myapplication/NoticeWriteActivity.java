package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.common.exception.APIError;
import com.example.myapplication.common.message.AlertMessage;
import com.example.myapplication.common.retrofit.RetrofitClient;
import com.example.myapplication.common.token.TokenUtil;
import com.example.myapplication.hr.notice.api.NoticeApi;
import com.example.myapplication.hr.notice.model.Notice;
import com.example.myapplication.hr.userstore.model.UserStore;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NoticeWriteActivity extends AppCompatActivity {

    private ImageButton back_button;
    private ImageButton mypage_imagebutton;
    private UserStore userStore;
    private EditText title_notice_write_edittext;
    private EditText context_notice_write_edittext;
    private AppCompatButton notice_send_button;
    private NoticeApi noticeApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_write);

        Intent intent = getIntent();
        userStore = (UserStore) intent.getSerializableExtra("userStore");
        back_button = findViewById(R.id.back_button);
        mypage_imagebutton = findViewById(R.id.mypage_imagebutton);
        title_notice_write_edittext = findViewById(R.id.title_notice_write);
        notice_send_button = findViewById(R.id.notice_send);
        context_notice_write_edittext = findViewById(R.id.context_notice_write);
        noticeApi = RetrofitClient.getInstance().create(NoticeApi.class);
        notice_send_button.setOnClickListener(view->{
            Notice notice = new Notice();
            notice.setTitle(title_notice_write_edittext.getText().toString());
            notice.setContent(context_notice_write_edittext.getText().toString());

            noticeApi.saveNotice(TokenUtil.getAccessToken("atk"),notice).enqueue(new Callback<Notice>() {
                @Override
                public void onResponse(Call<Notice> call, Response<Notice> response) {
                    if(response.isSuccessful()){
                        Intent intent = new Intent(NoticeWriteActivity.this,NoticeActivity.class);
                        intent.putExtra("userStore",userStore);
                        startActivity(intent);
                    }else{
                        if (response.errorBody() != null) {
                            try {
                                Gson gson = new Gson();
                                APIError error = gson.fromJson(response.errorBody().string(),APIError.class);
                                AlertMessage.showMessage(NoticeWriteActivity.this,error.message());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Notice> call, Throwable t) {
                    Toast.makeText(NoticeWriteActivity.this,"failed",Toast.LENGTH_SHORT).show();
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,t.getMessage());
                }
            });
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoticeWriteActivity.this, NoticeActivity.class);
                intent.putExtra("userStore",userStore);
                startActivity(intent);
            }
        });

        mypage_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoticeWriteActivity.this, MyinfoActivity.class);
                intent.putExtra("userStore",userStore);
                startActivity(intent);
            }
        });

    }
}
