package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.common.exception.APIError;
import com.example.myapplication.common.retrofit.RetrofitClient;
import com.example.myapplication.hr.message.api.MessageApi;
import com.example.myapplication.hr.user.api.UserApi;
import com.example.myapplication.hr.user.model.User;
import com.example.myapplication.hr.message.model.UserMessage;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {

    ImageButton back_button;
    RadioGroup authRadioGroup;
    RadioGroup genderRadioGroup;
    EditText idText;
    EditText passwordText;
    EditText nameText;
    EditText certificationText;
    AppCompatButton signUpButton;
    AppCompatButton cancelButton;
    AppCompatButton certificationbutton;
    AppCompatButton varificationButton;
    boolean isVarificationId = false;
    UserApi userApi;
    MessageApi messageApi;
    Retrofit retrofit;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[A-Za-z])" +
                    "(?=.*\\d)"+
                    "(?=.*[~@$!%*#?&])"+
                    "[A-Za-z\\d~@$!%*#?&]{8,16}" +
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        retrofit = RetrofitClient.getInstance();
        initializationComponents();
        initializationOnclick();

    }
    private void initializationComponents(){
        back_button = findViewById(R.id.back_button);
        authRadioGroup = findViewById(R.id.auth_radioGroup);
        genderRadioGroup = findViewById(R.id.gender_radioGroup);
        idText = findViewById(R.id.user_id);
        passwordText = findViewById(R.id.user_password);
        nameText = findViewById(R.id.user_name);
        signUpButton = findViewById(R.id.signup_button);
        cancelButton = findViewById(R.id.cancel_button);
        certificationbutton = findViewById(R.id.id_certification_send_button);
        varificationButton = findViewById(R.id.id_varification_button);
        userApi = retrofit.create(UserApi.class);
        messageApi = retrofit.create(MessageApi.class);
        certificationText = findViewById(R.id.id_certification_number);
    }
    private void initializationOnclick(){
        //뒤로가기 버튼
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //휴대폰 인증번호 전송
        certificationbutton.setOnClickListener(view -> {
            String id = idText.getText().toString();
            if(id.isEmpty()){
                showMessage("휴대폰 번호를 입력해주세요.");
                return;
            }
            messageApi.getCertificationNumber(id).enqueue(new Callback<UserMessage>() {
                @Override
                public void onResponse(Call<UserMessage> call, Response<UserMessage> response) {
                    if(response.isSuccessful()){
                        showMessage("메세지가 전송되었습니다.");
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
                public void onFailure(Call<UserMessage> call, Throwable t) {
                    showMessage("메세지 전송에 실패했습니다.");
                }
            });

        });
        //인증번호 확인
        varificationButton.setOnClickListener(view->{
            String id = idText.getText().toString();
            String certificationNumber = certificationText.getText().toString();
            if(id.isEmpty()){
                showMessage("아이디를 입력해주세요");
                return;
            }else if(certificationNumber.isEmpty()){
                showMessage("인증번호를 입력해주세요");
                return;
            }
            UserMessage userMessage = new UserMessage();
            userMessage.setMessageNumber(certificationNumber);
            userMessage.setId(id);

            messageApi.varifyNumber(userMessage).enqueue(new Callback<UserMessage>() {
                @Override
                public void onResponse(Call<UserMessage> call, Response<UserMessage> response) {
                    if(response.isSuccessful()){
                        showMessage("인증되었습니다.");
                        isVarificationId=true;
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
                public void onFailure(Call<UserMessage> call, Throwable t) {
                    showMessage("인증에 실패했습니다.");
                    Logger.getLogger(SignupActivity.class.getName()).log(Level.SEVERE,t.getMessage());
                }
            });
        });
        //회원가입하기 버튼
        signUpButton.setOnClickListener(view->{
            if(!isVarificationId){
                showMessage("휴대폰 인증을 해주세요.");
                return;
            }
            String id = idText.getText().toString();
            String password = passwordText.getText().toString();
            String name = nameText.getText().toString();
            RadioButton checkedAuthRadio = findViewById(authRadioGroup.getCheckedRadioButtonId());
            RadioButton checkedGenderRadio = findViewById(genderRadioGroup.getCheckedRadioButtonId());
            String authType = checkedAuthRadio.getText().toString();
            String gender = checkedGenderRadio.getText().toString();
            if(id.isEmpty()){
                showMessage("아이디를 입력해주세요");
                return;
            }else if(authType.isEmpty()){
                showMessage("근로자 혹은 고용주를 선택해주세요");
                return;
            }else if(password.isEmpty()){
                showMessage("비밀번호를 입력해주세요");
                return;
            }else if(!PASSWORD_PATTERN.matcher(password).matches()){
                showMessage("비밀번호는 영문,숫자,특수문자1개 이상 8~16자 사이로 입력해주세요\n사용가능한 특수문자 : ~@$!%*#?&");
                return;
            }
            User user = new User();
            user.setPassword(password);
            user.setId(id);
            user.setAuthType(authType);
            user.setGender(gender);
            user.setName(name);
            userApi.saveUser(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                        Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        if (response.errorBody() != null) {
                            try {
                                Gson gson = new Gson();
                                APIError error = gson.fromJson(response.errorBody().string(),APIError.class);
                                if(error.defaultMessage()!=null&&!error.defaultMessage().isEmpty()){
                                    showMessage(error.defaultMessage());
                                }else{
                                    showMessage(error.message());
                                }
                                return;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(SignupActivity.this,"SignUp Failed",Toast.LENGTH_SHORT).show();
                    Logger.getLogger(SignupActivity.class.getName()).log(Level.SEVERE,t.getMessage());
                }
            });
        });
        //취소버튼
        cancelButton.setOnClickListener(view->{
            Intent i = new Intent(SignupActivity.this, SignupActivity.class);
            finish();
            overridePendingTransition(0, 0);
            startActivity(i);
            overridePendingTransition(0, 0);
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