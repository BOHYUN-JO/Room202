package com.example.room202app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.room202app.R;
import com.example.room202app.auth.CurrentUser;
import com.example.room202app.dto.Comment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditCommentDialog extends Dialog{
    private Context context;
    private Comment comment;
    private CommentDialogClickListener commentDialogClickListener;

    public EditCommentDialog(@NonNull Context context, Comment comment, CommentDialogClickListener commentDialogClickListener) {
        super(context);
        this.context= context;
        this.comment = comment;
        this.commentDialogClickListener = commentDialogClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_comment);
        EditText commentEditText = findViewById(R.id.comment);
        TextView commentSaveButton = findViewById(R.id.comment_save_button);
        TextView commentCancelButton = findViewById(R.id.comment_cancel_button);

        //원래 댓글 내용을 일단 표시 해줌
        commentEditText.setText(comment.getComment());

        Comment editedComment = new Comment();
        commentSaveButton.setOnClickListener(view->{
            editedComment.setCommentId(comment.getCommentId());
            editedComment.setArticleId(comment.getArticleId());
            editedComment.setUserId(CurrentUser.userId);
            editedComment.setComment(commentEditText.getText().toString());
            editedComment.setCommentDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            commentDialogClickListener.onSaveButtonClick(editedComment);
            dismiss();
        });

        commentCancelButton.setOnClickListener(view -> {
            dismiss();
        });
    }
}