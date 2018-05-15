package com.example.andresoller.bairesdevdemo;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GithubApiClient {

    @GET("search/repositories")
    Call<GithubResponse> listRepos(@QueryMap(encoded = true) HashMap<String, String> query);
}
