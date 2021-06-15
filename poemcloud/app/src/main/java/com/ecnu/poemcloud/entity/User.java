package com.ecnu.poemcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id_user;

    private String email;

    private String password;

    private int score;

    public User(int id_user,String email,int score)
    {
        this.id_user=id_user;
        this.email=email;
        this.score=score;
    }

    public User(int id_user,String email,String password,int score)
    {
        this.id_user=id_user;
        this.email=email;
        this.password=password;
        this.score=score;
    }

    public int getScore() {
        return score;
    }

    public String getEmail() {
        return email;
    }
}


