package com.ecnu.poemcloud.entity;

public class rank_user {
    private String name;
    private String score;
    private String rank;

    public rank_user(String rank,String name,String score)
    {
        this.rank=rank;
        this.name=name;
        this.score=score;
    }

    public String getRank(){
        return rank;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }
}
