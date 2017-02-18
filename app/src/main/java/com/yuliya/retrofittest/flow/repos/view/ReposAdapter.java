package com.yuliya.retrofittest.flow.repos.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuliya.retrofittest.R;
import com.yuliya.retrofittest.model.Repo;

import java.util.List;

import rx.Single;

/**
 * Created by Yuliya on 13.02.2017.
 */

public class  ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposViewHolder> {

    private List<Repo> repositories = null;

    private View.OnClickListener mOnItemClickListener = null;

    public View.OnClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setDataSource(List<Repo> repositories) {
        this.repositories = repositories;
        notifyDataSetChanged();
    }

    @Override
    public ReposViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_repository_item, parent, false);
        return new ReposViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReposViewHolder holder, int position) {
        Repo repo = repositories.get(position);
        holder.bindView(repo);
        holder.itemView.setOnClickListener(mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return repositories == null ? 0 : repositories.size();
    }

    public static class ReposViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        private Repo mRepo;

        public ReposViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.cv_text_view);
        }

        void bindView(Repo repo){
            mRepo = repo;
            mTextView.setText(repo.getName());
        }

        public Repo getRepo(){
            return mRepo;
        }
    }


}



