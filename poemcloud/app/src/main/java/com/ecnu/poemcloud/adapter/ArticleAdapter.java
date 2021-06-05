package com.ecnu.poemcloud.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.entity.Common_sense;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder>{

    private List<Common_sense> diaryList;
    int PIC_COUNT=12;
    int[] resource=new int[]{R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6
    ,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9,R.drawable.pic10,R.drawable.pic11,R.drawable.pic12};

    static class ViewHolder extends RecyclerView.ViewHolder{
        View articleView;
        TextView text;
        ImageView ima;
        public ViewHolder(View view){
            super(view);
            articleView=view;
            text= view.findViewById(R.id.item_text);
            ima= view.findViewById(R.id.item_image);
        }
    }

    public ArticleAdapter(List<Common_sense> tDiaryList){
        diaryList = tDiaryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Common_sense common_sense = diaryList.get(position);

        holder.text.setText(common_sense.title);
        holder.ima.setImageResource(resource[position%PIC_COUNT]);

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
