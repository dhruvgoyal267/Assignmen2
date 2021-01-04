package com.example.assignment2.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.assignment2.Activities.HomeActivity;
import com.example.assignment2.Presenters.LoginPresenter;
import com.example.assignment2.R;

public class LoginFragment extends Fragment implements LoginPresenter.View {
    AppCompatButton login, signUp;
    Activity myActivity;
    ProgressBar progressBar;
    EditText email, password;

    public static final String user = "USER",isLogin = "LOGGED_IN", user_email = "EMAIL", user_password = "PASSWORD";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        login = view.findViewById(R.id.loginButton);
        signUp = view.findViewById(R.id.signUpButton);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        progressBar = view.findViewById(R.id.progressBar);
        clickListener();
        return view;
    }

    void clickListener() {
        login.setOnClickListener(v -> {
            LoginPresenter presenter = new LoginPresenter(this, myActivity);
            presenter.login(email.getText().toString(), password.getText().toString());
        });

        signUp.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment, SignUpFragment.class, null).commit();
        });
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = (Activity) context;
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess() {
        SharedPreferences sharedPreferences = myActivity.getSharedPreferences(user, Context.MODE_PRIVATE);
        sharedPreferences
                .edit()
                .putBoolean(isLogin, true)
                .putString(user_email, email.getText().toString())
                .putString(user_password, password.getText().toString())
                .apply();

        Intent intent = new Intent(myActivity, HomeActivity.class);
        startActivity(intent);
        myActivity.finish();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(myActivity, msg, Toast.LENGTH_SHORT).show();
    }
}