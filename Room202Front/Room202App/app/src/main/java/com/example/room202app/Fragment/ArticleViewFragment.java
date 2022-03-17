package com.example.room202app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.room202app.adapter.ArticleViewAdapter;
import com.example.room202app.databinding.FragmentArticleViewBinding;
import com.example.room202app.dialog.ArticleDialogClickListener;
import com.example.room202app.dialog.CommentDialogClickListener;
import com.example.room202app.dialog.EditArticleDialog;
import com.example.room202app.dialog.EditCommentDialog;
import com.example.room202app.dto.Article;
import com.example.room202app.dto.Board;
import com.example.room202app.dto.Comment;
import com.example.room202app.viewmodel.ArticleViewModel;

import java.util.ArrayList;

public class ArticleViewFragment extends Fragment {
    //현재 게시판 정보
    private Long articleId;
    private Board board;
    private ArticleViewModel viewModel;
    private FragmentArticleViewBinding binding;
    private ArticleViewAdapter.OnModifyDeleteClickListener onModifyDeleteClickListener;

    public ArticleViewFragment(Long articleId, Board board) {
        this.articleId = articleId;
        this.board = board;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentArticleViewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //viewModel 초기화 및 onModifyDeleteClickListener 초기화
        viewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
        onModifyDeleteClickListener = getModifyDeleteClickListener(board, viewModel);

        //article과 comments에 대한 observer 등록
        viewModel.getIsSuccessGetArticleAndComments().observe(getViewLifecycleOwner(), isSuccess -> {
            //실패 시
            if (!isSuccess) {
                Toast.makeText(getContext(), "글과 댓글 목록을 읽어오는데에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                binding.article.setAdapter(null);
                return;
            }
            Article article = viewModel.getArticle().getValue();
            ArrayList<Comment> comments = viewModel.getComments().getValue();

            ArticleViewAdapter articleViewAdapter = new ArticleViewAdapter(article, comments, onModifyDeleteClickListener);
            binding.article.setAdapter(articleViewAdapter);
        });

        //글 수정에 대한 옵저버 등록
        viewModel.getIsSuccessPutArticle().observe(getViewLifecycleOwner(), isSuccess->{
            if(!isSuccess){
                Toast.makeText(getContext(), "글 수정에 실패햐였습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getContext(), "글 수정에 성공하였습니다.", Toast.LENGTH_SHORT).show();
            //수정되었으므로 article과 comments를 다시 가져옴
            viewModel.httpGetArticleAndComments(articleId);
        });

        //글 삭제에 대한 옵저버 등록
        viewModel.getIsSuccessDeleteArticle().observe(getViewLifecycleOwner(), isSuccess -> {
            if (!isSuccess) {
                Toast.makeText(getContext(), "글 삭제에 실패햐였습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getContext(), "글 삭제에 성공하였습니다.", Toast.LENGTH_SHORT).show();
            //해당 글에서 빠져 나감
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().remove(this).commit();
            fragmentManager.popBackStack();

        });

        //댓글 추가에 대한 옵저버 등록
        viewModel.getIsSuccessPostComment().observe(getViewLifecycleOwner(), isSuccess -> {
            if (!isSuccess) {
                Toast.makeText(getContext(), "댓글 추가에 실패햐였습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getContext(), "댓글 추가에 성공하였습니다.", Toast.LENGTH_SHORT).show();
            //댓글을 추가했으므로 article과 comments를 다시 가져옴
            viewModel.httpGetArticleAndComments(articleId);
        });

        //댓글 수정에 대한 옵저버 등록
        viewModel.getIsSuccessPutComment().observe(getViewLifecycleOwner(), isSuccess->{
            if (!isSuccess) {
                Toast.makeText(getContext(), "댓글 수정에 실패햐였습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getContext(), "댓글 수정에 성공하였습니다.", Toast.LENGTH_SHORT).show();
            //댓글을 수정했으므로 article과 comments를 다시 가져옴
            viewModel.httpGetArticleAndComments(articleId);
        });

        //댓글 삭제에 대한 옵저버 등록
        viewModel.getIsSuccessDeleteComment().observe(getViewLifecycleOwner(), isSuccess -> {
            if (!isSuccess) {
                Toast.makeText(getContext(), "댓글 삭제에 실패햐였습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getContext(), "댓글 삭제에 성공하였습니다.", Toast.LENGTH_SHORT).show();
            //댓글을 삭제했으므로 article과 comments를 다시 가져옴
            viewModel.httpGetArticleAndComments(articleId);
        });

        //article과 comment를 가져옴
        viewModel.httpGetArticleAndComments(articleId);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public ArticleViewAdapter.OnModifyDeleteClickListener getModifyDeleteClickListener(Board board, ArticleViewModel viewModel) {
        return new ArticleViewAdapter.OnModifyDeleteClickListener() {
            @Override
            public void onArticleModifyClickListener(Article article) {
                //수정 버튼 클릭시 다이얼로그 표출
                EditArticleDialog editArticleDialog = new EditArticleDialog(getContext(),
                        board, article,
                        new ArticleDialogClickListener() {
                            @Override
                            public void onSaveButtonClick(Article article) {
                                //저장 버튼 릭시 putArticle 수행
                                viewModel.httpPutArticle(article);

                            }
                        });

                editArticleDialog.setCanceledOnTouchOutside(true);
                editArticleDialog.setCancelable(true);
                editArticleDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                editArticleDialog.show();
            }

            @Override
            public void onArticleDeleteClickListener(Article article) {
                viewModel.httpDeleteArticle(article.getArticleId());
            }

            @Override
            public void onCommentModifyClickListener(Comment comment) {
                //수정 버튼 클릭시 다이얼로그 표출
                EditCommentDialog editCommentDialog = new EditCommentDialog(getContext(), comment, new CommentDialogClickListener() {
                    @Override
                    public void onSaveButtonClick(Comment comment) {
                        //저장 버튼 클릭시 putComment 수행
                        viewModel.httpPutComment(comment);
                    }
                });
                editCommentDialog.setCanceledOnTouchOutside(true);
                editCommentDialog.setCancelable(true);
                editCommentDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                editCommentDialog.show();
            }

            @Override
            public void onCommentDeleteClickListener(Comment comment) {
                viewModel.httpDeleteComment(comment.getArticleId(), comment.getCommentId());
            }

            @Override
            public void onCommentAddClickListener(Comment comment) {
                viewModel.httpPostComment(comment);
            }
        };
    }
}
