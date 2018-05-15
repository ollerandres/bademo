package com.example.andresoller.bairesdevdemo;

import com.google.gson.annotations.SerializedName;

public class Item {

    private String name;
    @SerializedName("html_url")
    private String repoUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }
}
