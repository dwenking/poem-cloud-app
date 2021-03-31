package com.example.shiyun;


import com.example.shiyun.db.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(databaseName = AppDatabase.NAME, tableName = "soluTable")
public class UserBook extends BaseModel {
    @Column(name = "MAIL")
    private String mail;

    @Column(name="QUESTION")
    @PrimaryKey
    private String question;

    @Column(name="SOLUTION")
    private String solution;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
