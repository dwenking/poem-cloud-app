package com.ecnu.poemcloud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.entity.rank_user;

import java.util.List;

public class rank_userAdapter extends ArrayAdapter<rank_user> {
    private int resourceID;

    public rank_userAdapter(Context context, int textViewResourcedID, List<rank_user> objects) {
        super(context,textViewResourcedID,objects);
        resourceID=textViewResourcedID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        rank_user rank_user = getItem(position);//获取当前项的user实例
        View view= LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        TextView userRank=(TextView) view.findViewById(R.id.user_rank);
        TextView userName=(TextView) view.findViewById(R.id.user_name);
        TextView userScore=(TextView) view.findViewById(R.id.user_score);
        userRank.setText(rank_user.getRank());
        userName.setText(rank_user.getName());
        userScore.setText(rank_user.getScore());
        return view;

    }
}
