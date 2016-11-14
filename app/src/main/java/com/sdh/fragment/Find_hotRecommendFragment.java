package com.sdh.fragment;


import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdh.designsupporttest.R;
import com.sdh.designsupporttest.sampleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdh on 2016-11-11.
 */
public class Find_hotRecommendFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener  {
    private SwipeRefreshLayout swipeRef;
    private RecyclerView recyclerView;
    private Handler handler;
    private List<Integer> datas;
    private HomeAdapter myAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        String text=bundle.getString("str");
        datas=new ArrayList<>();
        for (int i=0;i<100;i++){
            datas.add(i);
        }
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {

                if (swipeRef.isRefreshing()){
                    datas.add(0,1234);
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

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.item_home, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position)
        {
            holder.tv.setText(datas.get(position)+"");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "这是位置"+position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return datas.size();
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
    }
}
