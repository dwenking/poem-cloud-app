package com.ecnu.poemcloud.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.entity.QuestionBasis;

import java.util.List;

public class CuotiAdapter extends RecyclerView.Adapter<CuotiAdapter.ViewHolder> {

    private List<QuestionBasis> diaryList;

    //大多数沿用diary的所以名字没有改
    static class ViewHolder extends RecyclerView.ViewHolder{
        View cuotiView;
        TextView text;
        public ViewHolder(View view){
            super(view);
            cuotiView=view;
            text=(TextView) view.findViewById(R.id.cuoti_title);
        }
    }

    public CuotiAdapter(List<QuestionBasis> tDiaryList){
        diaryList=tDiaryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cuoti_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CuotiAdapter.ViewHolder holder, int position){
        QuestionBasis questionBasis = diaryList.get(position);
        holder.text.setText("题目："+questionBasis.title);

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
