package com.doctor.yhkr.fragmentbestpractice;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class NewsTitleFragment extends Fragment  implements AdapterView.OnItemClickListener{
    private ListView newsTitleListView;
    private List<News> newsList;
    private NewsAdapter adapter;
    private boolean isTwoPane;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList=getNews();//初始化i想你问数据
        adapter=new NewsAdapter(activity,R.layout.news_items,newsList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_title_frag,container,false);
        newsTitleListView=(ListView) view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane=true;//可以找到news_content_layout布局时，为双页模式
        }else{
            isTwoPane=false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        News news=newsList.get(position);
        if(isTwoPane){
            NewsContentFragment newsContentFragment=(NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refresh(news.getTitle(),news.getContent());
        }else{
            NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
        }
    }
    private List<News> getNews(){
        List <News> newsList=new ArrayList<News>();
        News news1=new News();
        news1.setTitle("一分钟速览习主席的到访地——芬兰");
        news1.setContent("席作为中国国家元首首次访问北欧。在习近平主席启程前夕，央视网微视频工作室推出动画视频《一分钟速览习主席的出访地——芬兰》，带你走近芬兰，共同期待习主席此次的访问之旅结出更多“互惠之花”。");
        newsList.add(news1);

        News news2=new News();
        news2.setTitle("新西兰总理公众号发视频文章：《回顾李克强总理访问》");
        news2.setContent("在李克强总理圆满结束对新西兰正式访问后，新西兰总理英格利希在他的微信公众号上发出第三篇文章：《回顾李克强总理访问》。\n" +
                "　　一周前，李克强到访新西兰当天，英格利希专门在中国的微信上注册了个人微信公众号，并先后发布两篇文章《欢迎李克强总理》和《会晤李克强总理：宣布多项措施》，对中国总理表示欢迎。在刚刚发布的第三篇文章中，英格利希还专门录制了一段视频，回顾李总理访问期间“即兴演讲”等令他印象深刻的瞬间，以及两国达成的合作成果。\n" +
                "　　英格利希说：“毋庸置疑，我们的关系将持续加强，两国会因此而受益。”");
        newsList.add(news2);

        return  newsList;
    }
}
