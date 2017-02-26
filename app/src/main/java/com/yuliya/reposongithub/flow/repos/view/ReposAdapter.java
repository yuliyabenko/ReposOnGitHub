package com.yuliya.reposongithub.flow.repos.view;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuliya.retrofittest.R;
import com.yuliya.reposongithub.model.Repo;

import java.util.List;

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

        TextView mTextViewName;
        TextView mTextViewLink;
        private Repo mRepo;
        private String text;

        public ReposViewHolder(View itemView) {
            super(itemView);
            mTextViewName = (TextView) itemView.findViewById(R.id.cv_tv_name);
            mTextViewLink = (TextView) itemView.findViewById(R.id.cv_tv_link);
            mTextViewLink.setClickable(true);
            mTextViewLink.setMovementMethod(LinkMovementMethod.getInstance());
            /*CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(itemView.getContext(), Uri.parse(text));*/
        }

        void bindView(Repo repo){
            mRepo = repo;
            text = "<a href='" + repo.getHtmlUrl() + "'> " + repo.getName() + " on GitHub </a>";
            mTextViewName.setText(repo.getName());
            mTextViewLink.setText(Html.fromHtml(text));
        }

        public Repo getRepo(){
            return mRepo;
        }
    }


}

