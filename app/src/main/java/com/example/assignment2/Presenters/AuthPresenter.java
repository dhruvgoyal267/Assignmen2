package com.example.assignment2.Presenters;

import android.content.Context;

import com.example.assignment2.Fragments.LoginFragment;

public class AuthPresenter {
    View view;
    public AuthPresenter(View view){
        this.view = view;
    }

    public void isLoggedIn(){
        if(((Context)view).getSharedPreferences(LoginFragment.user, Context.MODE_PRIVATE).getBoolean(LoginFragment.isLogin,false))
            view.loggedIn();
    }

    public interface View{
        void loggedIn();
    }
}
