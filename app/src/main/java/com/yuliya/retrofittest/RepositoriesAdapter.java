package com.yuliya.retrofittest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by Yuliya on 06.02.2017.
 */

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder> {

    private RealmList<Repository> repositories = null;

    RepositoriesAdapter(RealmList<Repository> repositories){
        this.repositories = repositories;
    }

    @Override
    public RepositoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_repository_item, parent, false);
        return new RepositoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoriesViewHolder holder, int position) {
        holder.mTextView.setText((int) repositories.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return repositories == null ? 0 : repositories.size();
    }

    public static class RepositoriesViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public RepositoriesViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.cv_text_view);
        }
    }


}



