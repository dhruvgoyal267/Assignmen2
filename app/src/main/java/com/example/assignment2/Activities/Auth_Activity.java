package com.example.assignment2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.assignment2.Fragments.LoginFragment;
import com.example.assignment2.Fragments.SignUpFragment;
import com.example.assignment2.Presenters.AuthPresenter;
import com.example.assignment2.R;

public class Auth_Activity extends AppCompatActivity implements AuthPresenter.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        AuthPresenter presenter = new AuthPresenter(this);
        presenter.isLoggedIn();
    }

    @Override
    public void loggedIn() {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}