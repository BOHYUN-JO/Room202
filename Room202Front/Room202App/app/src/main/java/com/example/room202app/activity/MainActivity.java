package com.example.room202app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.room202app.Fragment.BoardFragment;
import com.example.room202app.Fragment.MainFragment;
import com.example.room202app.R;
import com.example.room202app.adapter.BoardAdapter;
import com.example.room202app.databinding.ActivityMainBinding;
import com.example.room202app.dto.Board;
import com.example.room202app.viewmodel.MainViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;
    private boolean isDrawerOpen = false;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Get the ViewModel
        viewModel=new ViewModelProvider(this).get(MainViewModel.class);

        // 초기 Fragment 설정
        fragmentManager = getSupportFragmentManager();
        MainFragment mainFragment = new MainFragment();
        showFragment(mainFragment);

        //ROOM202 클릭시 main fragment로 이동
        binding.mainIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragment mainFragment = new MainFragment();
                showFragment(mainFragment);
            }
        });


        //boards의 변화에 대한 observer등록
        viewModel.getIsSuccessGetBoards().observe(this, isSuccess-> {

            if(!isSuccess) {
                Toast.makeText(this, "게시판 목록을 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                binding.boardList.setAdapter(null);
                return ;
            }

            ArrayList<Board> boards = viewModel.getBoards().getValue();
            BoardAdapter boardAdapter = new BoardAdapter(boards, new BoardAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Board board) {
                    BoardFragment boardFragment=new BoardFragment(board);
                    showFragment(boardFragment);
                }
            });
            binding.boardList.setAdapter(boardAdapter);
        });


        //drawer 초기화 및  버튼 리스너 등록
        initDrawer();
    }

    protected void initDrawer() {
        binding.drawerButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDrawerOpen = !isDrawerOpen;//drawer가 열려있으면 닫아주고, 닫혀있으면 열어줌
                if (isDrawerOpen) {
                    //게시판 목록을 viewModel의 boards로 불러옴
                    viewModel.httpGetBoards();
                    binding.drawerLayout.openDrawer(Gravity.LEFT);
                }
                else
                    binding.drawerLayout.closeDrawer(Gravity.LEFT);

            }
        });
    }

    protected void showFragment(Fragment fragment){
        //drawer가 열려있을 수도 있으므로, drawer를 닫아줌
        if(isDrawerOpen) {
            isDrawerOpen = false;
            binding.drawerLayout.closeDrawer(Gravity.LEFT);
        }

        fragmentManager.popBackStack();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_tab, fragment);
        transaction.commit();
    }

}