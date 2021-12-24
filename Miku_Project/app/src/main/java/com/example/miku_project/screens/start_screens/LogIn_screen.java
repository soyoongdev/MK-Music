package com.example.miku_project.screens.start_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miku_project.R;
import com.example.miku_project.adapters.CustomProgressBarDialog;
import com.example.miku_project.models.ResponseModel;
import com.example.miku_project.models.User;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;
import com.example.miku_project.screens.main_screens.Bottom_nav;

import retrofit2.Call;
import retrofit2.Callback;

public class LogIn_screen extends AppCompatActivity {

    private TextView tv_forgot_pwd;
    private EditText edt_email, edt_password;
    private RelativeLayout rl_login;
    private CustomProgressBarDialog dialog;

    private IRetrofitService service;

//    private static String BASE_URL = "http://10.0.2.2:8081/";
    private static String BASE_URL = "https://cielmusic1604.000webhostapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
        dialog = new CustomProgressBarDialog(LogIn_screen.this);

        tv_forgot_pwd = (TextView) findViewById(R.id.tv_forgot_password);
        edt_email = (EditText) findViewById(R.id.edt_email_login);
        edt_password = (EditText) findViewById(R.id.edt_password_login);
        rl_login = (RelativeLayout) findViewById(R.id.rl_login_success);

        rl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();
                dialog.show();
                service.login(new User("","","", email, password)).enqueue(loginCB);
            }
        });

        tv_forgot_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn_screen.this, ForgotPassword_screen.class));
            }
        });
    }

    Callback<ResponseModel> loginCB = new Callback<ResponseModel>() {
        @Override
        public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
            if (response.isSuccessful()){
                ResponseModel responseModel = response.body();
                if (responseModel.getStatus()){
                    dialog.dismiss();
                    startActivity(new Intent(LogIn_screen.this, Bottom_nav.class));
                    finish();
                }else {
                    dialog.dismiss();
                    Toast.makeText(LogIn_screen.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }else {
                dialog.dismiss();
                Toast.makeText(LogIn_screen.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseModel> call, Throwable t) {

        }
    };

}