package com.example.andresoller.bairesdevdemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class GithubFragment extends Fragment implements RepoAdapter.RepoNavigationHandler {

    GithubApiClient githubApiClient;
    RepoAdapter adapter;

    public GithubFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        githubApiClient = retrofit.create(GithubApiClient.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_github, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new RepoAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        HashMap<String, String> query = new HashMap<>();
        query.put("q", "android");
        query.put("sort", "stars");
        query.put("order", "desc");

        githubApiClient.listRepos(query).enqueue(new Callback<GithubResponse>() {
            @Override
            public void onResponse(Call<GithubResponse> call, Response<GithubResponse> response) {
                GithubResponse githubResponse = response.body();
                if (response.isSuccessful() && githubResponse != null && githubResponse.getTotalCount() > 0) {
                    adapter.setItems(githubResponse.getItems());
                }
            }

            @Override
            public void onFailure(Call<GithubResponse> call, Throwable t) {
                Log.d("Error", "Here");
            }
        });
    }

    @Override
    public void navigateTo(String url) {

    }
}
