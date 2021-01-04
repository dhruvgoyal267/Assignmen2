package com.example.assignment2.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.Adapters.ArticlesAdapter;
import com.example.assignment2.Network.Executor;
import com.example.assignment2.Presenters.HomeFragmentPresenter;
import com.example.assignment2.R;
import com.example.assignment2.db.mydb;
import com.example.assignment2.model.Article;

import java.util.List;


public class HomeFragment extends Fragment implements HomeFragmentPresenter.View {


    private Activity myActivity;
    private RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        HomeFragmentPresenter homeFragmentPresenter = new HomeFragmentPresenter(this,getContext());
        Executor.backgroundExecutor.execute(homeFragmentPresenter::loadArticles);
        return root;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = (Activity)context;
    }

    @Override
    public void onSuccess(List<Article> articles) {
        ArticlesAdapter adapter = new ArticlesAdapter(articles,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
     }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(myActivity, msg, Toast.LENGTH_SHORT).show();
    }
}