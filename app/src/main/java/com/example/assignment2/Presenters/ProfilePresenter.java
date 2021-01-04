package com.example.assignment2.Presenters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.assignment2.Fragments.LoginFragment;
import com.example.assignment2.Network.Executor;
import com.example.assignment2.db.mydb;
import com.example.assignment2.model.User;

public class ProfilePresenter {
    Context context;
    mydb db;
    View view;

    public ProfilePresenter(View view, Context context) {
        this.view = view;
        this.db = mydb.getInstance(context);
        this.context = context;
    }

    public void updateUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LoginFragment.user, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(LoginFragment.user_email, "");
        String pass = sharedPreferences.getString(LoginFragment.user_password, "");

        Executor.backgroundExecutor.execute(() -> {
            User user = this.db.userDao().getUser(email, pass);
            ((Activity)context).runOnUiThread(()->{
                if (user != null)
                    view.updateUser(user.getFullName(), user.getEmail(), user.getPhoneNumber());
            });
        });
    }
    public interface View {
        void updateUser(String name, String email, String phone);
    }
}
