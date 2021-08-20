package com.example.miku_project.screens.start_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

public class SignUp_screen extends AppCompatActivity {

    private EditText edt_username, edt_email, edt_password;
    private RelativeLayout btn_register;

    private CustomProgressBarDialog dialog;

    private IRetrofitService service;

//    private static String BASE_URL = "http://10.0.2.2:8081/";
    private static String BASE_URL = "https://cielmusic1604.000webhostapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
        dialog = new CustomProgressBarDialog(SignUp_screen.this);

        edt_username = (EditText) findViewById(R.id.edt_username_signup);
        edt_email = (EditText) findViewById(R.id.edt_email_signup);
        edt_password = (EditText) findViewById(R.id.edt_password_signup);
        btn_register = (RelativeLayout) findViewById(R.id.rl_signup_success);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_username.getText().toString();
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();

                dialog.show();

                service.register(new User("", username,"", email, password)).enqueue(registerCallback);

            }
        });
    }

    Callback<ResponseModel> registerCallback = new Callback<ResponseModel>() {
        @Override
        public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
            if (response.isSuccessful()){
                ResponseModel responseModel = response.body();
                if (responseModel.getStatus()){
                    startActivity(new Intent(SignUp_screen.this, LogIn_screen.class));
                    Toast.makeText(SignUp_screen.this, "Register Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(SignUp_screen.this, "Register Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<ResponseModel> call, Throwable t) {

        }
    };
}