package com.sdh.fragment;


import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdh.com.sdh.selfview.AutoScrollViewPager;
import com.sdh.designsupporttest.R;
import com.sdh.designsupporttest.sampleAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by sdh on 2016-11-11.
 */
public class Find_hotRecommendFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener  {
    private SwipeRefreshLayout swipeRef;
    private RecyclerView recyclerView;
    private Handler handler;
    private List<String> datas;
    private HomeAdapter myAdapter;
    private ArrayList<View> pageview;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_HEADER = 1;
    private int[] images={R.drawable.demo1,R.drawable.test,R.drawable.demo1,R.drawable.test};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        String text=bundle.getString("str");
        pageview=new ArrayList<>();
        for (int i=0;i<images.length;i++){
            View view=View.inflate(getActivity(),R.layout.viewpagerimageitem,null);
            ImageView imageView= (ImageView) view.findViewById(R.id.img);
            imageView.setImageResource(images[i]);
            pageview.add(view);
        }
        datas=new ArrayList<>();
        for (int i=0;i<100;i++){
            datas.add(i+"");
        }
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {

                if (swipeRef.isRefreshing()){
                    datas.add(1,"new");
                    myAdapter.notifyDataSetChanged();
                    swipeRef.setRefreshing(false);
                }
            }
        };
        View view=inflater.inflate(R.layout.recommendfragment,container,false);
        swipeRef= (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        recyclerView= (RecyclerView) view.findViewById(R.id.recycleview);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);

        recyclerView.setLayoutManager(layoutManager);
        swipeRef.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright), getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_green_light), getResources().getColor(android.R.color.holo_red_light));
        swipeRef.setOnRefreshListener(this);

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipeRef.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        myAdapter=new HomeAdapter();

        recyclerView.setAdapter(myAdapter);
        return view;
    }

    @Override
    public void onRefresh() {
        System.out.println("开始刷新了");
        Message message=new Message();

        handler.sendMessageDelayed(message,2000);

    }

    class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            if (viewType==TYPE_HEADER){
                View view=LayoutInflater.from(
                        getActivity()).inflate(R.layout.autoviewpager, parent,
                        false);
//                view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
//                        RecyclerView.LayoutParams.WRAP_CONTENT));
                HeaderViewHolder holder1 = new HeaderViewHolder(view);
                return holder1;
            }else if (viewType==TYPE_ITEM){
                View view=LayoutInflater.from(
                        getActivity()).inflate(R.layout.item_home, parent,
                        false);
//                view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
//                        RecyclerView.LayoutParams.WRAP_CONTENT));
                MyViewHolder holder2=new MyViewHolder(view);
                return  holder2;
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
        {


            System.out.println("holder is null"+holder.getItemViewType());

            if (holder!=null && holder instanceof MyViewHolder){
                ((MyViewHolder)holder).tv.setText(datas.get(position)+"");
                System.out.println(datas.size()+"----------------------------");
                ((MyViewHolder)holder).cardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "这是位置"+position, Toast.LENGTH_SHORT).show();
                    }
                });
            }else if (holder!=null && holder instanceof HeaderViewHolder){
                ((HeaderViewHolder)holder).vp.startAutoScroll();
                ((HeaderViewHolder)holder).vp.setCycle(true);
               // ((HeaderViewHolder)holder).vp.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_CYCLE);
                ((HeaderViewHolder)holder).vp.setInterval(2000);
                ((HeaderViewHolder)holder).vp.setBorderAnimation(false);
                ((HeaderViewHolder)holder).vp.setAdapter(new MyViewPager());
            }

        }

        @Override
        public int getItemCount()
        {
            return datas.size();
        }

        @Override
        public int getItemViewType(int position) {
           if (position==0){
               return TYPE_HEADER;
           }
            else{
               return TYPE_ITEM;
           }
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            if(manager instanceof GridLayoutManager) {
                final GridLayoutManager gridManager = ((GridLayoutManager) manager);
                gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return getItemViewType(position) == TYPE_HEADER
                                ? gridManager.getSpanCount() : 1;
                    }
                });
            }
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;
            CardView cardview;


            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
                cardview= (CardView) view.findViewById(R.id.cardview);
            }

        }


        class  HeaderViewHolder extends RecyclerView.ViewHolder{
            AutoScrollViewPager vp;
            public HeaderViewHolder(View itemView) {
                super(itemView);
                vp= (AutoScrollViewPager) itemView.findViewById(R.id.myviewpager);
            }
        }
    }


    class MyViewPager extends PagerAdapter{
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            pageview.get(position).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),"我是第"+(position+1)+"图片",Toast.LENGTH_SHORT).show();
                }
            });
            container.addView(pageview.get(position));
            return pageview.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {


            container.removeView(pageview.get(position));
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }
}
