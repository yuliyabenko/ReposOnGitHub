package com.yuliya.reposongithub.flow.repos.view;

import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuliya.reposongithub.ReposActivity;
import com.yuliya.retrofittest.R;
import com.yuliya.reposongithub.model.Repo;

import java.util.List;

/**
 * Created by Yuliya on 13.02.2017.
 */

public class  ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposViewHolder> {

    private static Context context = null;

    public ReposAdapter(Context context){
        this.context = context;
    }

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
        private String url;

        public ReposViewHolder(View itemView) {
            super(itemView);
            mTextViewName = (TextView) itemView.findViewById(R.id.cv_tv_name);
            mTextViewLink = (TextView) itemView.findViewById(R.id.cv_tv_link);
            mTextViewLink.setClickable(true);
            mTextViewLink.setOnClickListener(view -> {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(context, Uri.parse(url));
            });
            /*CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(itemView.getContext(), Uri.parse(text));*/
        }

        void bindView(Repo repo){
            mRepo = repo;
            url = repo.getHtmlUrl();
            text = "<a href='" + url + "'> " + repo.getName() + " on GitHub </a>";
            mTextViewName.setText(repo.getName());
            mTextViewLink.setText(Html.fromHtml(text));
        }

        public Repo getRepo(){
            return mRepo;
        }

//        @Override
//        public void onClick(View view) {
//            view.setOnClickListener(view1 -> {
//                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//                CustomTabsIntent customTabsIntent = builder.build();
//                customTabsIntent.launchUrl(view.getContext(), Uri.parse(text));
//            });
//        }
    }


}

