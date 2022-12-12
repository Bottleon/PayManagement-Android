package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.common.exception.APIError;
import com.example.myapplication.common.message.AlertMessage;
import com.example.myapplication.common.retrofit.RetrofitClient;
import com.example.myapplication.common.token.TokenUtil;
import com.example.myapplication.hr.attendance.api.AttendanceApi;
import com.example.myapplication.hr.attendance.model.Attendance;
import com.example.myapplication.hr.attendance.model.UserStoreAttendance;
import com.example.myapplication.hr.user.model.User;
import com.example.myapplication.hr.userstore.model.UserStore;
import com.google.gson.Gson;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class QRcheckActivity extends AppCompatActivity {

    private UserStore userStore;
    private AppCompatButton startWorkButton;
    private AppCompatButton finishWorkButton;
    private Retrofit retrofit;
    private AttendanceApi attendanceApi;
    private UserStoreAttendance userStoreAttendance;
    private ImageButton backButton;
    private AppCompatButton goToStore;
    private Button createQr;
    private ImageButton mypage_imagebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcheck);

        Intent intent = getIntent();
        userStore = (UserStore) intent.getSerializableExtra("userStore");
        initializationComponent();
        initializationEvents();
    }
    private void initializationComponent(){
        startWorkButton = findViewById(R.id.start_work_button);
        finishWorkButton = findViewById(R.id.finish_work_button);
        retrofit = RetrofitClient.getInstance();
        attendanceApi = retrofit.create(AttendanceApi.class);
        backButton = findViewById(R.id.back_button);
        userStoreAttendance = new UserStoreAttendance();
        goToStore = findViewById(R.id.go_to_store);
        createQr = findViewById(R.id.create_qr_button);
        mypage_imagebutton = findViewById(R.id.mypage_imagebutton);
    }

    private void initializationEvents(){
        onClickStartButton();
        onClickFinishButton();
        onClickBackButton();
        onClickGoToStorebutton();
        onClickCreateQR();
        onClickMyPage();
    }
    private void onClickGoToStorebutton(){
        goToStore.setOnClickListener(view -> {
            Intent intent = new Intent(QRcheckActivity.this,CategoryActivity.class);
            intent.putExtra("userStore",userStore);
            startActivity(intent);
        });
    }

    private void onClickStartButton(){
        startWorkButton.setOnClickListener(view->{
            Attendance attendance = new Attendance();
            attendance.setType("출근");
            userStoreAttendance.setUserStore(userStore);
            userStoreAttendance.setAttendance(attendance);
            attendanceApi.saveUserAttendance(TokenUtil.getAccessToken("act"),userStoreAttendance).enqueue(new Callback<UserStoreAttendance>() {
                @Override
                public void onResponse(Call<UserStoreAttendance> call, Response<UserStoreAttendance> response) {
                    if(response.isSuccessful()){
                        Intent intent = new Intent(QRcheckActivity.this,CategoryActivity.class);
                        intent.putExtra("userStore", userStore);
                        startActivity(intent);
                    }else{
                        if (response.errorBody() != null) {
                            try {
                                Gson gson = new Gson();
                                APIError error = gson.fromJson(response.errorBody().string(),APIError.class);
                                showMessage(QRcheckActivity.this,error.message());

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserStoreAttendance> call, Throwable t) {
                    Toast.makeText(QRcheckActivity.this,"start work Failed",Toast.LENGTH_SHORT).show();
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,t.getMessage());
                }
            });
        });
    }

    private void onClickFinishButton(){
        finishWorkButton.setOnClickListener(view->{
            Attendance attendance = new Attendance();
            attendance.setType("퇴근");
            userStoreAttendance.setAttendance(attendance);
            userStoreAttendance.setUserStore(userStore);
            attendanceApi.updateUserAttendance(TokenUtil.getAccessToken("atk"),userStoreAttendance).enqueue(new Callback<UserStoreAttendance>() {
                @Override
                public void onResponse(Call<UserStoreAttendance> call, Response<UserStoreAttendance> response) {
                    if(response.isSuccessful()){
                        AlertMessage.showMessage(QRcheckActivity.this,response.body().getWorkFinishTime()+"에 퇴근이 기록되었습니다.");
                    }else{
                        if (response.errorBody() != null) {
                            try {
                                Gson gson = new Gson();
                                APIError error = gson.fromJson(response.errorBody().string(),APIError.class);
                                AlertMessage.showMessage(QRcheckActivity.this,error.message());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserStoreAttendance> call, Throwable t) {
                    Toast.makeText(QRcheckActivity.this,"finish work Failed",Toast.LENGTH_SHORT).show();
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,t.getMessage());
                }
            });
        });
    }
    private void onClickBackButton(){
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(QRcheckActivity.this, MarketlistActivity.class);
            intent.putExtra("user",userStore.getUser());
            startActivity(intent);
        });
    }
    private void onClickCreateQR(){
        createQr.setOnClickListener(view->{
            Intent intent = new Intent(QRcheckActivity.this, CreateQRActivity.class);
            startActivity(intent);
        });
    }
    private void onClickMyPage(){
        mypage_imagebutton.setOnClickListener(view->{
            Intent intent = new Intent(QRcheckActivity.this, MyinfoActivity.class);
            startActivity(intent);
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
            Intent intent = new Intent(QRcheckActivity.this,CategoryActivity.class);
            intent.putExtra("userStore", userStore);
            startActivity(intent);
        });
        dialog.show();
    }
}