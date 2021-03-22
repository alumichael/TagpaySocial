package com.aegletec.tagpaysocial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    Button callRegister, loginBtn;
    ImageView logo;
    TextView welcome, logPrompt;
    TextInputLayout email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Hooks
        callRegister = findViewById(R.id.register_screen);
        logo = findViewById(R.id.logo);
        welcome = findViewById(R.id.welcome);
        logPrompt = findViewById(R.id.log_prompt);
        email = findViewById(R.id.log_email);
        password = findViewById(R.id.log_password);
        loginBtn = findViewById(R.id.login_btn);

        callRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

            Pair[] pairs = new Pair[7];
            pairs[0] = new Pair<View, String>(logo, "logo_image");
            pairs[1] = new Pair<View, String>(welcome, "logo_text");
            pairs[2] = new Pair<View, String>(logPrompt, "slogan_text");
            pairs[3] = new Pair<View, String>(email, "email_tran");
            pairs[4] = new Pair<View, String>(password, "password_tran");
            pairs[5] = new Pair<View, String>(loginBtn, "button_tran");
            pairs[6] = new Pair<View, String>(callRegister, "login_register_tran");

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }
}