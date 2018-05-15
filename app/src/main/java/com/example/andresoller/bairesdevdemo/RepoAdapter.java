package com.example.andresoller.bairesdevdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    public interface RepoNavigationHandler {
        void navigateTo(String url);
    }

    private ArrayList<Item> repos = new ArrayList<>();
    private RepoNavigationHandler repoNavigationHandler;

    public void setItems(ArrayList<Item> repos) {
        this.repos = repos;
        notifyDataSetChanged();
    }

    public RepoAdapter(RepoNavigationHandler repoNavigationHandler) {
        this.repoNavigationHandler = repoNavigationHandler;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RepoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.name.setText(repos.get(position).getName());
        holder.url.setText(repos.get(position).getRepoUrl());
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView url;

        public RepoViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.repo_name);
            url = itemView.findViewById(R.id.repo_url);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            repoNavigationHandler.navigateTo(url.getText().toString());
        }
    }
}
