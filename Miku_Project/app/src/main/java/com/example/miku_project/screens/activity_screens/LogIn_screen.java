package com.example.miku_project.screens.activity_screens;

import static com.example.miku_project.Network.BASE_URL;
import static com.example.miku_project.RootData.USER_FILE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miku_project.R;
import com.example.miku_project.RootData;
import com.example.miku_project.adapters.CustomProgressBarDialog;
import com.example.miku_project.models.ResponseModel;
import com.example.miku_project.models.User;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;
import com.example.miku_project.screens.main_screens.Bottom_nav;

import retrofit2.Call;
import retrofit2.Callback;

public class LogIn_screen extends AppCompatActivity {

    private TextView tv_forgot_pwd, tv_signUp;
    private EditText edt_email, edt_password;
    private ImageButton imgBtn_login;
    private CustomProgressBarDialog dialog;
    private CheckBox chkRememberMe;
    private IRetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
        dialog = new CustomProgressBarDialog(LogIn_screen.this);

        tv_forgot_pwd = findViewById(R.id.tv_forgot_password);
        edt_email = findViewById(R.id.edt_email_login);
        edt_password = findViewById(R.id.edt_password_login);
        imgBtn_login = findViewById(R.id.imgBtn_login);
        tv_signUp = findViewById(R.id.tv_signUp);
        chkRememberMe = (CheckBox) findViewById(R.id.chkRememberMe);

        SharedPreferences pref = getSharedPreferences(USER_FILE, MODE_PRIVATE);
        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        String email = pref.getString("EMAIL", "");
        String pass = pref.getString("PASSWORD", "");

        if (!email.isEmpty()) {
            edt_email.setText(email);
        }

        if (!pass.isEmpty()) {
            edt_password.setText(pass);
        }

        imgBtn_login.setOnClickListener(new View.OnClickListener() {
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

        tv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogIn_screen.this, SignUp_screen.class));
            }
        });

        chkRememberMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chkRememberMe.isActivated()) {
                    RootData.savePrefUserFile(LogIn_screen.this, edt_email.getText().toString(), edt_password.getText().toString());
                } else {
                    RootData.clearPrefUserFile(LogIn_screen.this);
                }
            }
        });
    }

    Callback<ResponseModel> loginCB = new Callback<ResponseModel>() {
        @Override
        public void onResponse(@NonNull Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
            if (response.isSuccessful()){
                ResponseModel responseModel = response.body();
                assert responseModel != null;
                Toast.makeText(LogIn_screen.this, "Success", Toast.LENGTH_SHORT).show();
                if (responseModel.getStatus()){
                    dialog.dismiss();
                    startActivity(new Intent(LogIn_screen.this, Bottom_nav.class));
                    finish();
                }else {
                    dialog.dismiss();
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