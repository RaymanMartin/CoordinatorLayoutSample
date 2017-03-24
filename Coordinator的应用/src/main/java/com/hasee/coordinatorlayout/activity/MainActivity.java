package com.hasee.coordinatorlayout.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hasee.coordinatorlayout.R;
import com.hasee.coordinatorlayout.fragment.SocietyHotFragment;
import com.hasee.coordinatorlayout.util.DisplayUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Rayman on 2016/11/17.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    /*TopBar*/
    private Toolbar mTb_bar;
    private ImageView mIvSocietyBack;
    private ImageView mIvSocietyHome;
    private TextView mTv_topTitle;
    private RelativeLayout mRlSocietyTop;
    private AppBarLayout mAppBarLayout;
    private RelativeLayout mRlSocietyRect;

    /*效果设置*/
    private int UserHeadRectHeight = 0;
    private ViewTreeObserver viewTreeObserver;
    private ViewGroupGlobalListener mGlobalViewListener;

    /*滚动*/
    private CoordinatorLayout mCoordinator;
//    private SwipyRefreshLayout mSwipyRefreshLayout;

    /**/
    @Bind(R.id.tab_layout)
    TabLayout mTab_FixNavigation;
    @Bind(R.id.vp_society_my_data)
    ViewPager mVp_fragment;
    private ArrayList<Fragment> fragmentsList = new ArrayList<>();
    private ArrayList<String> tabList;
    private SocietyHotFragment latestFragment;
    private SocietyHotFragment hotFragment;

    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        context = MainActivity.this;
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        /*头部*/
        mTb_bar = (Toolbar) findViewById(R.id.tb_bar);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.abl_top);
        mIvSocietyBack = (ImageView) findViewById(R.id.iv_society_back);
        mIvSocietyHome = (ImageView) findViewById(R.id.iv_society_home);
        mTv_topTitle = (TextView) findViewById(R.id.tv_society_title);
        mRlSocietyTop = (RelativeLayout) findViewById(R.id.rl_top);
        mRlSocietyRect = (RelativeLayout) findViewById(R.id.rl_society_detail_rect);
        setSupportActionBar(mTb_bar);

        /*滚动设置*/
        viewTreeObserver = mRlSocietyRect.getViewTreeObserver();
        mGlobalViewListener = new ViewGroupGlobalListener();
        viewTreeObserver.addOnGlobalLayoutListener(mGlobalViewListener);

        /*刷新*/
        mCoordinator = (CoordinatorLayout) findViewById(R.id.cl_layout);
//        mSwipyRefreshLayout = (SwipyRefreshLayout) findViewById(R.id.srl_layout);

        latestFragment = new SocietyHotFragment();
        hotFragment = new SocietyHotFragment();
        /*初始化Fragment*/
        fragmentsList.add(latestFragment);
        fragmentsList.add(hotFragment);
        tabList = new ArrayList<>();
        tabList.add("最新");
        tabList.add("热门");
        initTab(tabList);
        initTabViewPager();
        mVp_fragment.setCurrentItem(0);
    }

    private void initData() {
        try {
            setIndicatorWidth();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void initEvent() {
        /*监听appBarLayout的滚动*/
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                boolean childMoving = mCoordinator.onNestedFling(mVp_fragment, 0.1f, 0.1f, false);
                Log.i(TAG, "输出offset：" + verticalOffset);
                Log.i(TAG, "输出Head：" + UserHeadRectHeight);
                Log.i(TAG, "onNestedFling：" + childMoving);
                if (verticalOffset == 0) {
//                    mSwipyRefreshLayout.setEnabled(true);
                } else {
//                    mSwipyRefreshLayout.setEnabled(false);
                }
//                if (childMoving) {
//                    mSwipyRefreshLayout.setEnabled(false);
//                } else {
//                    mSwipyRefreshLayout.setEnabled(true);
//                }
                if (Math.abs(verticalOffset) <= 0) {       //滑动
                    mRlSocietyTop.setBackgroundColor(Color.parseColor("#FBFBFB"));     //AGB由相关工具获得，或者美工提供
                    mTv_topTitle.setTextColor(Color.parseColor("#222222"));
                    mIvSocietyBack.setImageResource(R.drawable.icon_society_back);
                    mIvSocietyHome.setImageResource(R.drawable.icon_society_home);
                } else if (Math.abs(verticalOffset) > 0 && Math.abs(verticalOffset) < UserHeadRectHeight) {      //滑动，而且距离在头部背景范围内
                    float scale = (float) Math.abs(verticalOffset) / UserHeadRectHeight;
                    float alpha = (255 * scale);
                    // 只是layout背景透明(仿知乎滑动效果)
                    mRlSocietyTop.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
                    mTv_topTitle.setTextColor(Color.argb((int) alpha, 34, 34, 34));
                } else {                                            //
                    mRlSocietyTop.setBackgroundColor(Color.argb(255, 227, 29, 26));
                    mTv_topTitle.setTextColor(Color.parseColor("#ffffff"));
                    mIvSocietyBack.setImageResource(R.drawable.icon_society_back_white);
                    mIvSocietyHome.setImageResource(R.drawable.icon_society_home_white);
                }
            }
        });

        mTab_FixNavigation.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        latestFragment.reSetView();
                        mVp_fragment.setCurrentItem(0);
                        break;
                    case 1:
                        hotFragment.reSetView();
                        mVp_fragment.setCurrentItem(1);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

       /* mSwipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                Log.d(TAG, "Refresh triggered at " + (direction == SwipyRefreshLayoutDirection.TOP ? "top" : "bottom"));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipyRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });*/
    }

    /**
     * 初始化TabLayout
     *
     * @param tabList
     */
    public void initTab(List<String> tabList) {
        for (int i = 0; i < tabList.size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.tab_main_layout, null);
            TextView text = (TextView) view.findViewById(R.id.tv_tab_item);
            text.setText(tabList.get(i));
            TabLayout.Tab tab = mTab_FixNavigation.newTab();
            tab.setCustomView(view);
            //当前显示第一个tab内容
            mTab_FixNavigation.addTab(tab, i == 0 ? true : false);
        }
    }

    /**
     * TabLayout和ViewPager建立关联
     */
    public void initTabViewPager() {
        mVp_fragment.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentsList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentsList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabList.get(position);
            }
        });

        mVp_fragment.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTab_FixNavigation.setupWithViewPager(mVp_fragment);
    }

    /**
     * 通过反射设置Indicator的宽度
     *
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private void setIndicatorWidth() throws IllegalAccessException, NoSuchFieldException {
        Class<?> tabLayout = mTab_FixNavigation.getClass();
        Field tabStrip = tabLayout.getDeclaredField("mTabStrip");
        tabStrip.setAccessible(true);
        LinearLayout ll_tab = (LinearLayout) tabStrip.get(mTab_FixNavigation);
        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.setMarginStart(new DisplayUtil().dip2px(context, 30f));
            params.setMarginEnd(new DisplayUtil().dip2px(context, 30f));
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    class ViewGroupGlobalListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            UserHeadRectHeight = mRlSocietyRect.getMeasuredHeight();
        }
    }
}

