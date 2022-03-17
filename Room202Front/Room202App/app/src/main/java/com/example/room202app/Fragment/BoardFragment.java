package com.example.room202app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.room202app.R;
import com.example.room202app.adapter.ArticleListAdapter;
import com.example.room202app.databinding.FragmentBoardBinding;
import com.example.room202app.dialog.ArticleDialogClickListener;
import com.example.room202app.dialog.EditArticleDialog;
import com.example.room202app.dto.Article;
import com.example.room202app.dto.Board;
import com.example.room202app.viewmodel.BoardViewModel;

import java.util.ArrayList;

public class BoardFragment extends Fragment {

    //현재 게시판 정보
    private Board board;
    private BoardViewModel viewModel;
    private FragmentBoardBinding binding;
    public BoardFragment(Board board) {
        this.board = board;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //게시판 이름 출력
        binding.boardName.setText(board.getBoardName());

        //viewModel 초기화
        viewModel = new ViewModelProvider(this).get(BoardViewModel.class);

        //boardId를 통해 articles를 불러오는데 성공했는지에 대한 옵저버
        viewModel.getIsSuccessGetArticles().observe(getViewLifecycleOwner(), isSuccess->{
            if(!isSuccess)
            {
                Toast.makeText(getContext(), "글 목록을 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                binding.articleList.setAdapter(null);
                return ;
            }
            Long totalPages = viewModel.getTotalPages().getValue();
            ArrayList<Article> articles = viewModel.getArticles().getValue();

            //articles를 list로 출력해줌
            showArticlesInList(articles);

            //currentPage와 totalPages에 따른 pageButton을 출력해줌
            showPageButtons(viewModel.getPage(), totalPages);
        });

        //article들을 불러옴
        viewModel.httpGetArticles(board.getBoardId(), 0L);

        //글쓰기버튼 클릭시 다이얼로그 표출
        binding.writeArticleButton.setOnClickListener(v -> {
            EditArticleDialog editArticleDialog = new EditArticleDialog(getContext(),
                    board, null,
                    new ArticleDialogClickListener() {
                @Override
                public void onSaveButtonClick(Article article) {
                        //저장 버튼 릭시 postArticle 수행
                        viewModel.httpPostArticle(article);

                    }
            });

            editArticleDialog.setCanceledOnTouchOutside(true);
            editArticleDialog.setCancelable(true);
            editArticleDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            editArticleDialog.show();
        });

        //글쓰기 성공 실패에 대한 옵저버
        viewModel.getIsSuccessPostArticle().observe(getViewLifecycleOwner(), isSuccess->{
            if(!isSuccess) {
                Toast.makeText(getContext(), "글 쓰기에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(getContext(), "글 쓰기에 성공하였습니다.", Toast.LENGTH_SHORT).show();
            viewModel.httpGetArticles(board.getBoardId(), viewModel.getPage());
        });

    }


    void showArticlesInList(ArrayList<Article> articles)
    {
        ArticleListAdapter articleListAdapter = new ArticleListAdapter(articles, new ArticleListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Article article) {
                //글을 누르면 글 fragment로 이동하도록 구현 이때 스택에 쌓이도록 함
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                ArticleViewFragment articleViewFragment=new ArticleViewFragment(article.getArticleId(), board);
                transaction.add(R.id.fragment_tab, articleViewFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        binding.articleList.setAdapter(articleListAdapter);
    }

    void showPageButtons(Long page, Long totalPages)
    {
        pageButtonSetting(binding.pageButton1, page-2, totalPages);
        pageButtonSetting(binding.pageButton2, page-1, totalPages);
        pageButtonSetting(binding.pageButton3, page, totalPages);
        pageButtonSetting(binding.pageButton4, page+1, totalPages);
        pageButtonSetting(binding.pageButton5, page+2, totalPages);
    }

    void pageButtonSetting(TextView button, Long page, Long totalPages)
    {
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                viewModel.httpGetArticles(board.getBoardId(), page);
            }
        });
        if(0<=page && page<totalPages)
        {
            button.setVisibility(View.VISIBLE);
            button.setText(String.valueOf(page+1));
        }
        else
            button.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}
