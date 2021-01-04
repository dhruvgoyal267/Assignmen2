package com.example.assignment2.Presenters;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.example.assignment2.Network.Executor;
import com.example.assignment2.db.mydb;
import com.example.assignment2.model.User;

public class SignUpPresenter {
    private final mydb db;
    private final View view;
    private final Context context;

    public SignUpPresenter(View view, Context context) {
        this.view = view;
        this.context = context;
        this.db = mydb.getInstance(context);
    }

    public void signUp(String nameText, String emailText, String passText, String phoneText) {
        view.showProgressBar();
        if (TextUtils.isEmpty(nameText)) {
            view.onFailure("Name must not be empty");
            view.hideProgressBar();
            return;
        }
        if (TextUtils.isEmpty(emailText)) {
            view.onFailure("Email must not be empty");
            view.hideProgressBar();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            view.onFailure("Invalid email format");
            view.hideProgressBar();
            return;
        }
        if (TextUtils.isEmpty(passText)) {
            view.onFailure("Password must not be empty");
            view.hideProgressBar();
            return;
        }
        if (TextUtils.isEmpty(phoneText)) {
            view.onFailure("PhoneText must not be empty");
            view.hideProgressBar();
            return;
        }

        if (!Patterns.PHONE.matcher(phoneText).matches()) {
            view.onFailure("Invalid phone format");
            view.hideProgressBar();
            return;
        }

        Executor.backgroundExecutor.execute(() -> {
            User user = new User(emailText,nameText, passText, phoneText);
            db.userDao().insertUser(user);
            ((Activity) (this.context)).runOnUiThread(() -> {
                view.hideProgressBar();
                view.onSuccess();
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
