package com.example.assignment2.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment2.Activities.Auth_Activity;
import com.example.assignment2.Activities.HomeActivity;
import com.example.assignment2.R;

public class LogoutFragment extends Fragment {

    Activity myActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = (Activity)context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myActivity.getSharedPreferences(LoginFragment.user, Context.MODE_PRIVATE).edit().clear().apply();

        Intent intent = new Intent(myActivity, Auth_Activity.class);
        startActivity(intent);
        myActivity.finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }
}