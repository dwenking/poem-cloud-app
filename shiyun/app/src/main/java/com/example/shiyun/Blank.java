package com.example.shiyun;


import com.example.shiyun.db.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(databaseName = AppDatabase.NAME, tableName = "blankTable")
public class Blank extends BaseModel {
    @Column(name = "TAG")
    private String tag;

    @Column(name = "CONTENT")
    @PrimaryKey
    private String content;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
