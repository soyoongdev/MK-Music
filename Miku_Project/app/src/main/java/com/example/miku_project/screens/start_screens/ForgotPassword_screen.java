package com.example.miku_project.screens.start_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miku_project.R;
import com.example.miku_project.models.ResponseModel;
import com.example.miku_project.models.User;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;
import com.example.miku_project.screens.main_screens.Bottom_nav;

import retrofit2.Call;
import retrofit2.Callback;

public class ForgotPassword_screen extends AppCompatActivity {

    private EditText edt_email_reset;
    private RelativeLayout btn_send_email;
    private TextView tv_countdown;
    private CountDownTimer countDownTimer;

    private IRetrofitService service;

//    private static String BASE_URL = "http://10.0.2.2:8081/";
    private String BASE_URL = "https://cielmusic1604.000webhostapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);

        edt_email_reset = (EditText) findViewById(R.id.edt_email_reset_password);
        btn_send_email = (RelativeLayout) findViewById(R.id.rl_send_emailreset);
        tv_countdown = (TextView) findViewById(R.id.tv_countdown_timer);

        btn_send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email_reset.getText().toString();
                if (email.equals("")){
                    Toast.makeText(ForgotPassword_screen.this, "Enter your mail address to reset password", Toast.LENGTH_SHORT).show();
                }else {
                    startCountdownTimer();
                    service.forgot_password(new User(email)).enqueue(forgotPasswordCallback);
                }
            }
        });
    }

    Callback<ResponseModel> forgotPasswordCallback = new Callback<ResponseModel>() {
        @Override
        public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
            if (response.isSuccessful()){
                ResponseModel responseModel = response.body();
                if (responseModel.getStatus()){
                    Toast.makeText(ForgotPassword_screen.this, "Check your email to reset password!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ForgotPassword_screen.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<ResponseModel> call, Throwable t) {

        }
    };

    private void startCountdownTimer(){
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_countdown.setText("Time remaining: " + millisUntilFinished / 1000);
                edt_email_reset.setEnabled(false);
                btn_send_email.setEnabled(false);
            }

            @Override
            public void onFinish() {
                Toast.makeText(ForgotPassword_screen.this, "Time out! Request again to reset password", Toast.LENGTH_SHORT).show();
                edt_email_reset.setEnabled(true);
                btn_send_email.setEnabled(true);
                tv_countdown.setText("");
            }
        }.start();
    }
}