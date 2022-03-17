package com.example.room202app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.room202app.R;
import com.example.room202app.auth.CurrentUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: UserId를 임시로 초기화
        CurrentUser.userId = 0L;

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}