package com.example.shiyun.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shiyun.Diary;
import com.example.shiyun.R;
import com.example.shiyun.UserBook;
import com.huawei.nlp.restapi.model.User;

import java.util.List;

public class CuotiAdapter extends RecyclerView.Adapter<CuotiAdapter.ViewHolder> {

    private List<UserBook> diaryList;

    //大多数沿用diary的所以名字没有改
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        ImageView ima;
        public ViewHolder(View view){
            super(view);
            text=(TextView) view.findViewById(R.id.diary_name);
            ima=(ImageView) view.findViewById(R.id.diary_image);
        }
    }

    public CuotiAdapter(List<UserBook> tDiaryList){
        diaryList=tDiaryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_item,parent,false);
        CuotiAdapter.ViewHolder holder=new CuotiAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CuotiAdapter.ViewHolder holder, int position){
        UserBook diary=diaryList.get(position);
        holder.text.setText(diary.getQuestion());
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

    private CuotiAdapter.OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(CuotiAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
