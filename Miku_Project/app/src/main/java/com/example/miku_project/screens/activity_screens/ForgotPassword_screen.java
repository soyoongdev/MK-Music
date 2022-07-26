package com.example.miku_project.screens.activity_screens;

import static com.example.miku_project.Network.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miku_project.R;
import com.example.miku_project.models.ResponseModel;
import com.example.miku_project.models.User;
import com.example.miku_project.myRetrofit.IRetrofitService;
import com.example.miku_project.myRetrofit.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;

public class ForgotPassword_screen extends AppCompatActivity {

    private EditText edt_email_reset;
    private ImageButton btn_send_email;
    private TextView tv_countdown;
    private CountDownTimer countDownTimer;
    private ImageButton imgBtn_backLogin, imgBtn_back;

    private IRetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);

        edt_email_reset = (EditText) findViewById(R.id.edt_email_reset);
        btn_send_email = (ImageButton) findViewById(R.id.imgBtn_send);
        tv_countdown = (TextView) findViewById(R.id.tv_resetEmailMessage);
        imgBtn_backLogin = (ImageButton) findViewById(R.id.imgBtn_backLogin);
        imgBtn_back = (ImageButton) findViewById(R.id.imgBtn_backLogin);

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

        imgBtn_backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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