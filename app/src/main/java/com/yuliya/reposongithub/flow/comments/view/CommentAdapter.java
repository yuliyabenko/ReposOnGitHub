package com.yuliya.reposongithub.flow.comments.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuliya.reposongithub.model.Comment;
import com.yuliya.retrofittest.R;

import java.util.List;

/**
 * Created by Yuliya on 20.02.2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentsViewHolder> {

    List<Comment> mDataSource = null;

    public void setDataSource(List<Comment> dataSource) {
        this.mDataSource = dataSource;
        notifyDataSetChanged();
    }

    @Override
    public CommentAdapter.CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_comments_item, parent, false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.CommentsViewHolder holder, int position) {
        Comment comment = mDataSource.get(position);
        holder.bindView(comment);
    }

    @Override
    public int getItemCount() {
        return mDataSource == null ? 0 : mDataSource.size();
    }

    public static class CommentsViewHolder extends RecyclerView.ViewHolder {

        private Comment mComment;
        TextView mTextViewUser;
        TextView mTextViewComment;

        public CommentsViewHolder(View itemView) {
            super(itemView);
            mTextViewUser = (TextView) itemView.findViewById(R.id.cv_tv_user);
            mTextViewComment = (TextView) itemView.findViewById(R.id.cv_tv_comment);
        }

        void bindView(Comment comment) {
            mComment = comment;
            mTextViewUser.setText(comment.getUser().getLogin());
            mTextViewComment.setText(comment.getBody());
        }

        public Comment getmComment() {
            return mComment;
        }
    }
}
