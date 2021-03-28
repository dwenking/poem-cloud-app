package com.example.shiyun.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shiyun.Diary;
import com.example.shiyun.R;

import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder>{

    private List<Diary> diaryList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        ImageView ima;
        public ViewHolder(View view){
            super(view);
            text=(TextView) view.findViewById(R.id.diary_name);
            ima=(ImageView) view.findViewById(R.id.diary_image);
        }
    }

    public DiaryAdapter(List<Diary> tDiaryList){
        diaryList=tDiaryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Diary diary=diaryList.get(position);
        holder.text.setText(diary.getTitle());
        holder.ima.setImageResource(R.drawable.book);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        return diaryList.size();
    }

    //点击事件
    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
