package com.hasee.pro.bottomsheet;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    /*BottomSheet控件*/
    private BottomSheetBehavior behavior;

    /*套用的RecyclerView控件*/
    private RecyclerView mRecyclerView;
    private BottomSheetAdapter mAdapter;
    private List<String> data;

    private Button button_normal;
    private Button button_dialog;
    private Button button_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        button_normal = (Button) findViewById(R.id.btn_normal);
        button_dialog = (Button) findViewById(R.id.btn_dialog);
        button_fragment = (Button) findViewById(R.id.btn_fragment);
        initRecyclerView();
        initBottomSheet();
    }

    private void initListener() {
        button_normal.setOnClickListener(this);
        button_dialog.setOnClickListener(this);
        button_fragment.setOnClickListener(this);
    }

    private void initData() {

    }

    /**
     * 创建BottomSheet
     */
    private void initBottomSheet() {
        //创建BottomSheetBehavior
        View bottomSheet = findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                //这里是bottomSheet状态的改变
                Log.i(TAG, "目前状态：" + newState);
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                //这里是拖拽中的回调，根据slideOffset可以做一些动画
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("测试内容：" + i);
        }
        mAdapter = new BottomSheetAdapter(this, data);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_normal:
                if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
            case R.id.btn_dialog:
                break;
            case R.id.btn_fragment:
                break;
        }
    }
}
