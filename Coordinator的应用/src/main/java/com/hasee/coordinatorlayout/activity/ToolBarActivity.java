package com.hasee.coordinatorlayout.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hasee.coordinatorlayout.R;
import com.hasee.coordinatorlayout.adapter.TextItemAdapter;

/**
 * Created by Administrator on 2017/3/27.
 */

public class ToolBarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView mRv_data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRv_data = (RecyclerView) findViewById(R.id.rv_data);
        mRv_data.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRv_data.setAdapter(new TextItemAdapter());
    }
}
