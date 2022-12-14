package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.common.exception.APIError;
import com.example.myapplication.common.exception.ErrorUtils;
import com.example.myapplication.common.retrofit.RetrofitClient;
import com.example.myapplication.common.token.TokenInfo;
import com.example.myapplication.common.token.TokenUtil;
import com.example.myapplication.hr.user.model.User;
import com.example.myapplication.hr.user.api.UserApi;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    AppCompatButton loginButton;
    EditText idText;
    EditText pwText;
    TextView signupButton;
    TextView findPwButton;
    UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializationComponents();
        initializationFunctions();
    }
    private void initializationComponents(){
        loginButton = findViewById(R.id.login_button);
        idText = findViewById(R.id.id_input);
        pwText = findViewById(R.id.pw_input);
        signupButton = findViewById(R.id.signin_text_button);
        findPwButton = findViewById(R.id.findpw_text_button);
        TokenUtil.init(getApplicationContext());
    }
    private void initializationFunctions(){
        userApi = RetrofitClient.getInstance().create(UserApi.class);
        clickLoginFunction();
        clickSignupFunction();
        clickFindPwFunction();
    }
    private void clickFindPwFunction(){
        findPwButton.setOnClickListener(view->{
            Intent intent = new Intent(MainActivity.this,FindpwActivity.class);
            startActivity(intent);
        });
    }
    private void clickSignupFunction(){
        signupButton.setOnClickListener(view->{
            Intent intent = new Intent(MainActivity.this,SignupActivity.class);
            startActivity(intent);
        });
    }
    private void clickLoginFunction(){
        loginButton.setOnClickListener(view -> {
            String id = idText.getText().toString();
            String pw = pwText.getText().toString();
            if(id.isEmpty()){
                showMessage("???????????? ??????????????????");
                return;
            }else if(pw.isEmpty()){
                showMessage("??????????????? ??????????????????");
                return;
            }
            if(pw.length()<8||pw.length()>16){
                showMessage("??????????????? 8~16??? ????????? ??????????????????");
                return;
            }
            User user = new User();
            user.setId(id);
            user.setPassword(pw);
            userApi.login(user).enqueue(new Callback<TokenInfo>() {
                @Override
                public void onResponse(Call<TokenInfo> call, Response<TokenInfo> response) {
                    if(response.isSuccessful()){
                        TokenUtil.setAccessToken(response.body().getAccessToken());
                        TokenUtil.setRefreshToken(response.body().getRefreshToken());
                        Intent intent = new Intent(MainActivity.this,MarketlistActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
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
                public void onFailure(Call<TokenInfo> call, Throwable t) {

                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,t.getMessage());
                }
            });
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