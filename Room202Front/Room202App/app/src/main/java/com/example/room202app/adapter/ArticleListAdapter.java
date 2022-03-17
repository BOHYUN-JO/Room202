package com.example.room202app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room202app.R;
import com.example.room202app.dto.Article;

import java.util.ArrayList;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {

    private ArrayList<Article> articleList;
    private ArticleListAdapter.OnItemClickListener listener;

    public ArticleListAdapter(ArrayList<Article> articleList, ArticleListAdapter.OnItemClickListener listener)
    {
        this.articleList =articleList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ArticleListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.board_article_item, parent, false);

        return new ArticleListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleListAdapter.ViewHolder holder, int position) {
        Article article = articleList.get(position);

        holder.articleTitle.setText(article.getArticleTitle());
        holder.articleUpdateDate.setText(article.getArticleUpdateDate());
        holder.userName.setText("by "+article.getUserName());
        holder.commentCount.setText("["+article.getCommentCount().toString()+"]");
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Article article);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView articleTitle;
        TextView articleUpdateDate;
        TextView userName;
        TextView commentCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            articleTitle = (TextView)itemView.findViewById(R.id.article_title);
            articleUpdateDate = (TextView)itemView.findViewById(R.id.article_update_date);
            userName = (TextView)itemView.findViewById(R.id.user_name);
            commentCount = (TextView)itemView.findViewById(R.id.comment_count);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(articleList.get(position));
                    }
                }
            });

        }
    }
}
