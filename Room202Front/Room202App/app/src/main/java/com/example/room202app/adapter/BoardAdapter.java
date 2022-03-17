package com.example.room202app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room202app.R;
import com.example.room202app.dto.Board;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private ArrayList<Board> boardList;
    private OnItemClickListener listener;

    public BoardAdapter(ArrayList<Board> boardList, OnItemClickListener listener)
    {
        this.boardList=boardList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.board_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Board board = boardList.get(position);

        holder.boardName.setText(board.getBoardName());
    }

    @Override
    public int getItemCount() {
        return boardList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Board board);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView boardName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            boardName = itemView.findViewById(R.id.board_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(boardList.get(position));
                    }
                }
            });

        }
    }
}
