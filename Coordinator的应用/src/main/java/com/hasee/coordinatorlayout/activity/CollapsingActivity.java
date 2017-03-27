package com.hasee.coordinatorlayout.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.hasee.coordinatorlayout.R;
import com.hasee.coordinatorlayout.adapter.RecyclerItemAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/24.
 */

public class CollapsingActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private RecyclerView mRv_data;
    private ArrayList<String> data = new ArrayList<>();

    private CollapsingToolbarLayout mCollapsing;
    private FloatingActionButton mFab_add;
    private boolean isInverse = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing);
        mToolBar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolBar);
        mRv_data = (RecyclerView) findViewById(R.id.rv_content);
        mFab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        mRv_data.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        for (int i = 0; i < 20; i++) {
            data.add("我是内容：" + i);
        }
        Log.i("123123", data.toString());
        mRv_data.setAdapter(new RecyclerItemAdapter(data, this));

        mCollapsing = (CollapsingToolbarLayout) findViewById(R.id.coll_tbl);
        //主体渐变颜色，和折叠后ToolBar的颜色。
        mCollapsing.setContentScrimColor(getResources().getColor(R.color.colorAccent));
        mCollapsing.setStatusBarScrimColor(Color.parseColor("#00ff00"));

        //设置伸展状态下文字颜色
        mCollapsing.setExpandedTitleColor(Color.parseColor("#ffffff"));
        //折叠状态下的文字颜色
        mCollapsing.setCollapsedTitleTextColor(Color.parseColor("#ffffff"));

        mFab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float degree = isInverse ? 180 : -180;
                mFab_add.animate().rotation(degree).start();
                isInverse = !isInverse;
//                startActivity(new Intent(CollapsingActivity.this, ToolBarActivity.class));
            }
        });
    }

}
