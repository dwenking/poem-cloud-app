package com.example.shiyun;

import com.example.shiyun.db.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(databaseName = AppDatabase.NAME, tableName = "userTable")
public class User extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "CODE")
    private String code;

}