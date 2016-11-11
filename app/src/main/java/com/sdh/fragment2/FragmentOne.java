package com.sdh.fragment2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdh.designsupporttest.Find_tab_Adapter;
import com.sdh.designsupporttest.MainActivity;
import com.sdh.designsupporttest.R;
import com.sdh.fragment.Find_hotCollectionFragment;
import com.sdh.fragment.Find_hotMonthFragment;
import com.sdh.fragment.Find_hotRecommendFragment;
import com.sdh.fragment.Find_hotToday;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by sdh on 2016-11-11.
 */

public class FragmentOne extends Fragment {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TabLayout tab_FindFragment_title;                            //定义TabLayout
    private ViewPager vp_FindFragment_pager;                             //定义viewPager
    private FragmentPagerAdapter fAdapter;                               //定义adapter

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表

    private Find_hotRecommendFragment hotRecommendFragment;              //热门推荐fragment
    private Find_hotCollectionFragment hotCollectionFragment;            //热门收藏fragment
    private Find_hotMonthFragment hotMonthFragment;                      //本月热榜fragment
    private Find_hotToday hotToday;                                      //今日热榜fragment

    private ImageView toshow;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_main,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tab_FindFragment_title = (TabLayout)view.findViewById(R.id.tab_FindFragment_title);
        vp_FindFragment_pager = (ViewPager)view.findViewById(R.id.vp_FindFragment_pager);
        View mainactivityView=View.inflate(getActivity(),R.layout.main,null);
       navigationView= (NavigationView)mainactivityView.findViewById(R.id.nv_main_navigation);
       drawer= (DrawerLayout) mainactivityView.findViewById(R.id.drawer);
        toshow= (ImageView) view.findViewById(R.id.toshow);
        toshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity parentActivity = (MainActivity ) getActivity();
                parentActivity.closeDrawer();
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");

            }
        });


        //初始化各fragment
        hotRecommendFragment = new Find_hotRecommendFragment();
        hotCollectionFragment = new Find_hotCollectionFragment();
        hotMonthFragment = new Find_hotMonthFragment();
        hotToday = new Find_hotToday();

        Bundle bundle1=new Bundle();
        bundle1.putString("str","热门推荐");
        hotRecommendFragment.setArguments(bundle1);


        Bundle bundle2=new Bundle();
        bundle2.putString("str","热门收藏");
        hotCollectionFragment.setArguments(bundle2);


        Bundle bundle3=new Bundle();
        bundle3.putString("str","本月热榜");
        hotMonthFragment.setArguments(bundle3);

        Bundle bundle4=new Bundle();
        bundle4.putString("str","今日热榜");
        hotToday.setArguments(bundle4);

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(hotRecommendFragment);
        list_fragment.add(hotCollectionFragment);
        list_fragment.add(hotMonthFragment);
        list_fragment.add(hotToday);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("热门推荐");
        list_title.add("热门收藏");
        list_title.add("本月热榜");
        list_title.add("今日热榜");

        //设置TabLayout的模式
        tab_FindFragment_title.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(0)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(1)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(2)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(3)));

        fAdapter = new Find_tab_Adapter(getActivity().getSupportFragmentManager(),list_fragment,list_title);

        //viewpager加载adapter
        vp_FindFragment_pager.setAdapter(fAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        tab_FindFragment_title.setupWithViewPager(vp_FindFragment_pager);
        //tab_FindFragment_title.set
    }

}
