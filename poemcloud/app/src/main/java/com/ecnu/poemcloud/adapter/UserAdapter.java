package com.ecnu.poemcloud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ecnu.poemcloud.R;
import com.ecnu.poemcloud.entity.User;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    private int resourceID;

    public UserAdapter(@NonNull Context context, int textViewResourceId, List<User> objects) {
        super(context, textViewResourceId, objects);
        resourceID=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        User user = getItem(position);//获取当前项的user实例
        View view= LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        TextView userName=(TextView) view.findViewById(R.id.user_name);
        TextView userScore=(TextView) view.findViewById(R.id.user_score);
        userName.setText(user.getEmail());
        String str=String.valueOf(user.getScore());
        userScore.setText(str);
        return view;

    }
}
