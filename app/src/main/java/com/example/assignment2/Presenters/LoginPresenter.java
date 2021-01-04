package com.example.assignment2.Presenters;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.example.assignment2.Network.Executor;
import com.example.assignment2.db.mydb;
import com.example.assignment2.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginPresenter {
    private final mydb db;
    private final View view;
    private final Context context;

    public LoginPresenter(View view, Context context) {
        this.view = view;
        this.context = context;
        this.db = mydb.getInstance(context);
    }

    public void login(String email, String password) {
        view.showProgressBar();
        if (TextUtils.isEmpty(email)) {
            view.onFailure("Email must not be empty");
            view.hideProgressBar();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            view.onFailure("Password must not be empty");
            view.hideProgressBar();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.onFailure("Invalid email format");
            view.hideProgressBar();
            return;
        }


        Executor.backgroundExecutor.execute(() -> {
            User user = db.userDao().getUser(email, password);
            ((Activity)this.context).runOnUiThread(()->{
                if (user == null)
                    view.onFailure("Invalid email or password");
                else
                    view.onSuccess();
                view.hideProgressBar();
            });
        });
    }

    public interface View {
        void showProgressBar();

        void hideProgressBar();

        void onSuccess();

        void onFailure(String msg);
    }
}
