package com.sdh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sdh.designsupporttest.R;

/**
 * Created by sdh on 2016-11-11.
 */

public class Find_hotCollectionFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        String text=bundle.getString("str");
        View view=inflater.inflate(R.layout.layoutfrag,container,false);
        TextView tv= (TextView) view.findViewById(R.id.tv);
        System.out.println(text+"LLLLLLLLLLLLLLLLLLL");
        tv.setText(text);
        return view;
    }
}
