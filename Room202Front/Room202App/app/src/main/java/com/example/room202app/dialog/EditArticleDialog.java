package com.example.room202app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.room202app.R;
import com.example.room202app.auth.CurrentUser;
import com.example.room202app.dto.Article;
import com.example.room202app.dto.Board;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditArticleDialog extends Dialog {

    private Context context;
    private Board board;
    private Article article;
    private ArticleDialogClickListener articleDialogClickListener;

    public EditArticleDialog(@NonNull Context context, Board board, Article article, ArticleDialogClickListener articleDialogClickListener) {
        super(context);
        this.context = context;
        this.board = board;
        this.article = article;
        this.articleDialogClickListener = articleDialogClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_article);
        TextView boardName = findViewById(R.id.board_name);
        EditText articleTitle = findViewById(R.id.article_title);
        EditText articleContent = findViewById(R.id.article_content);
        TextView saveButton = findViewById(R.id.save_button);
        TextView cancelButton = findViewById(R.id.cancel_button);


        boardName.setText(board.getBoardName());
        Article editedArticle=new Article();
        if(article!=null)//글 수정인 경우
        {
            //글 제목은 수정하지 못하도록 함
            articleTitle.setText(article.getArticleTitle());
            articleTitle.setEnabled(false);

            articleContent.setText(article.getArticleContent());
        }

        //저장 버튼 클릭 시
        saveButton.setOnClickListener(view -> {
            editedArticle.setBoardId(board.getBoardId());
            editedArticle.setArticleTitle(articleTitle.getText().toString());
            editedArticle.setUserId(CurrentUser.userId);
            editedArticle.setArticleContent(articleContent.getText().toString());
            editedArticle.setArticleUpdateDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            articleDialogClickListener.onSaveButtonClick(editedArticle);
            dismiss();
        });
        cancelButton.setOnClickListener(view -> {
            dismiss();
        });
    }

}
