package com.example.miku_project.screens.activity_screens;

import static com.example.miku_project.helper.Config.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miku_project.R;
import com.example.miku_project.helper.RootData;
import com.example.miku_project.adapters.CustomProgressBarDialog;
import com.example.miku_project.models.LoginResult;
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
        this.initView();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        service = new RetrofitBuilder().createSerVice(IRetrofitService.class, BASE_URL);
        dialog = new CustomProgressBarDialog(LogIn_screen.this);

        imgBtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = edt_email.getText().toString();
                String getPass = edt_password.getText().toString();
                if (isValidationForm(getEmail, getPass)) {
                    service.login(new User(getEmail, getPass)).enqueue(loginCB);
                    dialog.show();
                }
            }
        });

        tv_forgot_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn_screen.this, ResetPassword_screen.class));
            }
        });

        tv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogIn_screen.this, SignUp_screen.class));
            }
        });

        chkRememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chkRememberMe.isChecked()) {
                    RootData.savePrefUserFile(LogIn_screen.this, edt_email.getText().toString(), edt_password.getText().toString());
                    Toast.makeText(LogIn_screen.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    if (RootData.isUserFileExisted(LogIn_screen.this)) {
                        RootData.clearPrefUserFile(LogIn_screen.this);
                    }
                }
            }
        });
    }

    Callback<LoginResult> loginCB = new Callback<LoginResult>() {
        @Override
        public void onResponse(@NonNull Call<LoginResult> call, retrofit2.Response<LoginResult> response) {
            if (response.isSuccessful()){
                LoginResult responseModel = response.body();
                assert responseModel != null;
                if (responseModel.result){
                    dialog.dismiss();
                    startActivity(new Intent(LogIn_screen.this, Bottom_nav.class));
                    finish();
                } else {
                    dialog.dismiss();
                }
                Toast.makeText(LogIn_screen.this, responseModel.message, Toast.LENGTH_SHORT).show();
            }else {
                dialog.dismiss();
                Toast.makeText(LogIn_screen.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<LoginResult> call, Throwable t) {
            Toast.makeText(LogIn_screen.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void initView() {
        tv_forgot_pwd = findViewById(R.id.tv_forgot_password);
        edt_email = findViewById(R.id.edt_email_login);
        edt_password = findViewById(R.id.edt_password_login);
        imgBtn_login = findViewById(R.id.imgBtn_login);
        tv_signUp = findViewById(R.id.tv_signUp);
        chkRememberMe = (CheckBox) findViewById(R.id.chkRememberMe);

        String getEmail = RootData.getPrefUserData(LogIn_screen.this).getEmail();
        String getPassword = RootData.getPrefUserData(LogIn_screen.this).getPassword();

        if (!getEmail.isEmpty()) {
            edt_email.setText(getEmail);
        }
        if (!getPassword.isEmpty()) {
            edt_password.setText(getPassword);
        }
    }

    private boolean isValidationForm(String email, String password) {
        if (email.isEmpty() && password.isEmpty()) {
            toastMessage("Please to fill email and password");
            return false;
        }
        if (email.isEmpty()) {
            toastMessage("Please to fill email");
            return false;
        }
        if (password.isEmpty()) {
            toastMessage("Please to fill password");
            return false;
        }
        return true;
    }

    private void toastMessage(String message) {
        Toast.makeText(LogIn_screen.this, message, Toast.LENGTH_SHORT).show();
    }

}