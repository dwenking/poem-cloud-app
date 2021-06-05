package com.ecnu.poemcloud.utils.poemGenerate;

import java.io.InputStream;
import java.util.List;

public class MyThread extends Thread{

    public Poem4Thread p;

    public InputStream is;

    public MyThread(InputStream is, Poem4Thread p) {
        this.is = is;
        this.p = p;
    }

    @Override
    public void run() {
        List<String> tmpList = Main.main(is);
        p.poemList = tmpList;
    }
}
