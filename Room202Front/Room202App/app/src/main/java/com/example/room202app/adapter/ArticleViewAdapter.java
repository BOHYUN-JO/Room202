package com.example.room202app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room202app.R;
import com.example.room202app.auth.CurrentUser;
import com.example.room202app.dto.Article;
import com.example.room202app.dto.Comment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ArticleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Article article;
    private ArrayList<Comment> commentList;
    private OnModifyDeleteClickListener listener;

    public ArticleViewAdapter(Article article, ArrayList<Comment> commentList, OnModifyDeleteClickListener listener)
    {
        this.article=article;
        this.commentList = commentList;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return ArticleViewHolder.VIEW_TYPE;
        else if(position == commentList.size()+1){
            return CommentAddViewHolder.VIEW_TYPE;
        }
        else
            return CommentViewHolder.VIEW_TYPE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(viewType, parent, false);

        RecyclerView.ViewHolder viewHolder;

        if(viewType == ArticleViewHolder.VIEW_TYPE)
            viewHolder = new ArticleViewHolder(itemView);
        else if(viewType == CommentViewHolder.VIEW_TYPE)
            viewHolder = new CommentViewHolder(itemView);
        else
            viewHolder = new CommentAddViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ArticleViewHolder)//article 표시
        {
            ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;
            articleViewHolder.articleTitle.setText(article.getArticleTitle());
            articleViewHolder.articleContent.setText(article.getArticleContent());
            articleViewHolder.articleViewCount.setText(article.getArticleViewCount().toString());
            articleViewHolder.userName.setText("by " + article.getUserName());
            if(!article.getUserId().equals(CurrentUser.userId))//현재 로그인한 유저와, 글을쓴 User의 아이디가 다르다면 수정/삭제를 보여주지 않음
                articleViewHolder.modifyDeleteLayout.setVisibility(View.GONE);
            else{
                //글 수정, 삭제버튼에 대한 listener 등
                articleViewHolder.articleModifyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onArticleModifyClickListener(article);
                    }
                });
                articleViewHolder.articleDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onArticleDeleteClickListener(article);
                    }
                });
            }

        }
        else if(holder instanceof CommentViewHolder)//댓글 표시
        {
            //글을 빼야하므로 position-1
            Comment comment = commentList.get(position-1);
            CommentViewHolder commentViewHolder = (CommentViewHolder) holder;
            commentViewHolder.userName.setText(comment.getUserName());
            commentViewHolder.comment.setText(comment.getComment());
            commentViewHolder.commentDate.setText(comment.getCommentDate());
            if(!comment.getUserId().equals(CurrentUser.userId))//현재 로그인한 유저와, 댓글을 쓴 user의 아이디가 다르다면 수정/삭제를 보여주지 않음
                commentViewHolder.modifyDeleteLayout.setVisibility(View.GONE);
            else
            {
                //댓글 수정삭제에 대한 리스너 등록
                commentViewHolder.commentModifyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onCommentModifyClickListener(comment);
                    }
                });
                commentViewHolder.commentDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onCommentDeleteClickListener(comment);
                    }
                });
            }
        }
        else//댓글 추가 표시
        {
            CommentAddViewHolder commentAddViewHolder = (CommentAddViewHolder) holder;
            //댓글  추가 버튼 클릭시
            commentAddViewHolder.commentSaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Comment comment= new Comment();
                    comment.setArticleId(article.getArticleId());
                    comment.setUserId(CurrentUser.userId);
                    comment.setComment(commentAddViewHolder.comment.getText().toString());
                    comment.setCommentDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
                    listener.onCommentAddClickListener(comment);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return commentList.size()+2;
    }


    public interface OnModifyDeleteClickListener{
        void onArticleModifyClickListener(Article article);
        void onArticleDeleteClickListener(Article article);
        void onCommentModifyClickListener(Comment comment);
        void onCommentDeleteClickListener(Comment comment);
        void onCommentAddClickListener(Comment comment);
    }


}
class ArticleViewHolder extends RecyclerView.ViewHolder{
    public static int VIEW_TYPE = R.layout.article_view_article_item;

    TextView articleTitle;
    TextView articleUpdateDate;
    TextView userName;
    TextView articleContent;
    TextView articleViewCount;
    LinearLayout modifyDeleteLayout;
    TextView articleModifyButton;
    TextView articleDeleteButton;

    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);
        articleTitle = (TextView)itemView.findViewById(R.id.article_title);
        articleUpdateDate = (TextView)itemView.findViewById(R.id.article_update_date);
        userName = (TextView)itemView.findViewById(R.id.user_name);
        articleContent = (TextView)itemView.findViewById(R.id.article_content);
        articleViewCount = (TextView)itemView.findViewById(R.id.article_view_count);
        modifyDeleteLayout = (LinearLayout) itemView.findViewById(R.id.modify_delete_layout);
        articleModifyButton =(TextView)itemView.findViewById(R.id.article_modify_button);
        articleDeleteButton = (TextView)itemView.findViewById(R.id.article_delete_button);
    }
}

class CommentViewHolder extends RecyclerView.ViewHolder{

    public static int VIEW_TYPE = R.layout.article_view_comment_item;

    TextView userName;
    TextView comment;
    TextView commentDate;
    LinearLayout modifyDeleteLayout;
    TextView commentModifyButton;
    TextView commentDeleteButton;
    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        userName = (TextView)itemView.findViewById(R.id.user_name);
        comment = (TextView)itemView.findViewById(R.id.comment);
        commentDate = (TextView)itemView.findViewById(R.id.comment_date);
        modifyDeleteLayout = (LinearLayout) itemView.findViewById(R.id.modify_delete_layout);
        commentModifyButton =(TextView)itemView.findViewById(R.id.comment_modify_button);
        commentDeleteButton = (TextView)itemView.findViewById(R.id.comment_delete_button);
    }
}
class CommentAddViewHolder extends RecyclerView.ViewHolder{

    public static int VIEW_TYPE = R.layout.article_view_comment_edit_item;
    EditText comment;
    TextView commentSaveButton;

    public CommentAddViewHolder(@NonNull View itemView) {
        super(itemView);

        comment = (EditText) itemView.findViewById(R.id.comment);
        commentSaveButton = (TextView) itemView.findViewById(R.id.comment_save_button);

    }
}