package com.example.assignment2.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.assignment2.Presenters.ProfilePresenter;
import com.example.assignment2.R;

public class ProfileFragment extends Fragment implements ProfilePresenter.View {

    TextView nameText, emailText, phoneText;
    ProfilePresenter presenter;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        nameText = root.findViewById(R.id.name);
        emailText = root.findViewById(R.id.email);
        phoneText = root.findViewById(R.id.phoneNumber);

        presenter = new ProfilePresenter(this, this.context);
        presenter.updateUser();
        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void updateUser(String name, String email, String phone) {
        nameText.setText(name);
        emailText.setText(email);
        phoneText.setText(phone);
    }
}