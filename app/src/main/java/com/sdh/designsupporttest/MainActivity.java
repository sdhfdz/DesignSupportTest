package com.sdh.designsupporttest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.sdh.fragment.Find_hotCollectionFragment;
import com.sdh.fragment.Find_hotMonthFragment;
import com.sdh.fragment.Find_hotRecommendFragment;
import com.sdh.fragment.Find_hotToday;
import com.sdh.fragment2.FragmentFive;
import com.sdh.fragment2.FragmentFour;
import com.sdh.fragment2.FragmentOne;
import com.sdh.fragment2.FragmentThree;
import com.sdh.fragment2.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

import static com.sdh.designsupporttest.R.id.drawer;
import static com.sdh.designsupporttest.R.id.ll;
import static com.sdh.designsupporttest.R.id.tab_FindFragment_title;
import static com.sdh.designsupporttest.R.id.toshow;
import static com.sdh.designsupporttest.R.id.vp_FindFragment_pager;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private DrawerLayout drawer;
    private NavigationView navigationView;

    private BottomNavigationBar mBottomBar;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFore;
    private FragmentFive fragmentFive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


//        initView();
        initMyview();
        assignViews();
    }

    private void initMyview() {
        navigationView= (NavigationView) findViewById(R.id.nv_main_navigation);
        drawer= (DrawerLayout) findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                drawer.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.spring:
                        showtoast("春");
                        break;
                    case R.id.summer:
                        showtoast("夏");
                        break;
                    case R.id.Autumn:
                        showtoast("秋");
                        break;
                    case R.id.winter:
                        showtoast("冬");
                        break;
                    case R.id.Android:
                        showtoast("Android");
                        break;
                    case R.id.IOS:
                        showtoast("IOS");
                        break;
                }
                return true;

            }
        });
    }

    private void assignViews() {
        mBottomBar = (BottomNavigationBar) findViewById(R.id.bottom_bar);
        mBottomBar.setMode(BottomNavigationBar.MODE_CLASSIC);
        mBottomBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "首页"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "商品"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "分类"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "购物车"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "我的"))
                .initialise();
        mBottomBar.setTabSelectedListener(this);//设置监听
        setDefaultFragment();//设置默认选项
    }

    private void setDefaultFragment() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        fragmentOne=new FragmentOne();
        fragmentTransaction.add(R.id.ll_root,fragmentOne);
        fragmentTransaction.commit();
    }

    /**
     * 隐藏fragment
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (fragmentOne != null) {
            transaction.hide(fragmentOne);
        }
        if (fragmentTwo != null) {
            transaction.hide(fragmentTwo);
        }
        if (fragmentThree != null) {
            transaction.hide(fragmentThree);
        }
        if (fragmentFore != null) {
            transaction.hide(fragmentFore);
        }
        if (fragmentFive != null) {
            transaction.hide(fragmentFive);
        }
    }



    @Override
    public void onTabSelected(int position) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        switch (position){
            case 0:
                if (fragmentOne==null){
                    fragmentOne=new FragmentOne();
                    fragmentTransaction.add(R.id.ll_root,fragmentOne);

                }else {
                    fragmentTransaction.show(fragmentOne);
                }
                break;
            case 1:
                if (fragmentTwo==null){
                    fragmentTwo=new FragmentTwo();
                    fragmentTransaction.add(R.id.ll_root,fragmentTwo);

                }else {
                    fragmentTransaction.show(fragmentTwo);
                }
                break;
            case 2:
                if (fragmentThree==null){
                    fragmentThree=new FragmentThree();
                    fragmentTransaction.add(R.id.ll_root,fragmentThree);

                }else {
                    fragmentTransaction.show(fragmentThree);
                }
                break;
            case 3:
                if (fragmentFore==null){
                    fragmentFore=new FragmentFour();
                    fragmentTransaction.add(R.id.ll_root,fragmentFore);

                }else {
                    fragmentTransaction.show(fragmentFore);
                }
                break;
            case 4:
                if (fragmentFive==null){
                    fragmentFive=new FragmentFive();
                    fragmentTransaction.add(R.id.ll_root,fragmentFive);

                }else {
                    fragmentTransaction.show(fragmentFive);
                }
                break;
        }
        fragmentTransaction.commit();
        
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


    public void openDrawer(){
        drawer.openDrawer(navigationView);
    }

    private void showtoast(String msg){
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

}
