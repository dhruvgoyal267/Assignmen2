package com.example.assignment2.Presenters;

import android.content.Context;
import android.net.ConnectivityManager;

import com.example.assignment2.Network.Base_Network;
import com.example.assignment2.Network.Executor;
import com.example.assignment2.Network.Retrofit_Client;
import com.example.assignment2.db.mydb;
import com.example.assignment2.model.Article;
import com.example.assignment2.model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class HomeFragmentPresenter {
    View view;
    Context context;

    public HomeFragmentPresenter(View view, Context context) {
        this.view = view;
        this.context = context;
    }


    public void loadArticles() {
        ConnectivityManager cm = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected())
            loadOnline();
        else
            loadOffline();
    }

    private void loadOffline() {
        mydb db = mydb.getInstance(this.context);
        List<Article> articles = db.apiDao().getArticles();
        if (articles == null || articles.size() == 0)
            view.onFailure("Enable internet at least once to load articles");
        else {
            view.onSuccess(articles);
        }
    }

    private void storeOffline(List<Article> articles) {
        mydb db = mydb.getInstance(this.context);
        db.apiDao().insertArticles(articles);
    }

    private void loadOnline() {
        Retrofit_Client retrofitClient = Base_Network.getInstance().create(Retrofit_Client.class);
        retrofitClient.getArticles().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.body() != null) {
                    List<Article> articles = response.body().articles;
                    view.onSuccess(articles);
                    Executor.backgroundExecutor.execute(
                            () -> {
                                storeOffline(articles);
                            }
                    );
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                view.onFailure(t.getMessage());
            }
        });
    }

    public interface View {
        void onSuccess(List<Article> articles);

        void onFailure(String msg);
    }
}
