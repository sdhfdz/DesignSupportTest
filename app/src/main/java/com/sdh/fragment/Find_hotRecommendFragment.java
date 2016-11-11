package com.sdh.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sdh.designsupporttest.R;

/**
 * Created by sdh on 2016-11-11.
 */

public class Find_hotRecommendFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        String text=bundle.getString("str");
        View view=inflater.inflate(R.layout.layoutfrag,container,false);
        TextView tv= (TextView) view.findViewById(R.id.tv);
        tv.setText(text);
        ListView listView= (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 100;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view=null;
//                if (convertView==null){
//                    view=View.inflate(getActivity(),R.layout.list_item,null);
//                }else{
//                    view=convertView;
//                }
                view=View.inflate(getActivity(),R.layout.list_item,null);
                TextView tv= (TextView) view.findViewById(R.id.item_tv);
                System.out.println(tv+"LLLLLLLLLLLLLLLLLLl");
              tv.setText(position+"");
                return view;
            }
        });
        return view;
    }
}
