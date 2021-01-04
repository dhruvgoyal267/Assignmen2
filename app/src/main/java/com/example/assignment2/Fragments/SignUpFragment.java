package com.example.assignment2.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.assignment2.Activities.HomeActivity;
import com.example.assignment2.Presenters.SignUpPresenter;
import com.example.assignment2.R;

public class SignUpFragment extends Fragment implements SignUpPresenter.View {

    Activity myActivity;
    EditText userName, email, password, phone;
    AppCompatButton signUpButton, loginButton;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        userName = view.findViewById(R.id.userName);
        email = view.findViewById(R.id.userEmail);
        password = view.findViewById(R.id.userPassword);
        phone = view.findViewById(R.id.userPhoneNumber);
        signUpButton = view.findViewById(R.id.signUpButton);
        progressBar = view.findViewById(R.id.progressBar);
        loginButton = view.findViewById(R.id.loginButton);
        clickListener();
        return view;
    }

    private void clickListener() {
        signUpButton.setOnClickListener(v -> {
            SignUpPresenter presenter = new SignUpPresenter(this, myActivity);
            presenter.signUp(userName.getText().toString(), email.getText().toString(), password.getText().toString(), phone.getText().toString());
        });

        loginButton.setOnClickListener(v -> {
            login();
        });
    }

    void login(){
        getParentFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment, LoginFragment.class, null).commit();

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
        login();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(myActivity, msg, Toast.LENGTH_SHORT).show();
    }
}