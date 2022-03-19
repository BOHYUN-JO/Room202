package com.example.room202app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.room202app.R;
import com.example.room202app.auth.CurrentUser;
import com.example.room202app.databinding.ActivityLoginBinding;
import com.example.room202app.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.hyojunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentUser.userId=0L;
                CurrentUser.userName="김효준";
                startMainActivity();
            }
        });
        binding.bohyunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentUser.userId=1L;
                CurrentUser.userName="조보현";
                startMainActivity();
            }
        });
        binding.seongbinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentUser.userId=2L;
                CurrentUser.userName="이성빈";
                startMainActivity();
            }
        });
        binding.seonghyunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentUser.userId=3L;
                CurrentUser.userName="배성현";
                startMainActivity();
            }
        });
    }

    public void startMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}