package com.doctor.yhkr.fragmentbestpractice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class NewsContentActivity extends Activity {
    public static void actionStart(Context context,String newsTitle,String newsContent){
        Intent intent=new Intent(context,NewsContentActivity.class);
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        String newsTitle=getIntent().getStringExtra("news_title");
        //获取传入的新闻标题
        String newsContent=getIntent().getStringExtra("news_content");
        //获取传入的新闻内容
        NewsContentFragment newsContentFragment=(NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle,newsContent);
        //刷新NewsContentFragment界面
    }
}
