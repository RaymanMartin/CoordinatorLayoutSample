package com.hasee.coordinatorlayout.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hasee.coordinatorlayout.R;
import com.hasee.coordinatorlayout.adapter.RecyclerItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SocietyHotFragment extends BaseFragment {
    public static final int UPDATE = 1024;
    public static final int LOAD_MORE = 1025;
    private static final int TIME = 500;

    /*配置RecyclerView*/
    @Bind(R.id.rv_data)
    RecyclerView mRvData;
    private List<String> data = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, Boolean isAttach) {
        return inflater.inflate(R.layout.fragment_society_data, container, false);
    }

    @Override
    public void initViews(View view) {
        ButterKnife.bind(this, view);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRvData.setLayoutManager(layoutManager);
    }

    @Override
    public void initData() {
        for (int i = 0; i < 20; i++) {
            data.add("我是内容：" + i);
        }
        Log.i("123123", data.toString());
        mRvData.setAdapter(new RecyclerItemAdapter(data, getContext()));
    }


    @Override
    protected void initEvent() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void reSetView() {
        if (mRvData != null) {
            mRvData.scrollToPosition(0);
        }
    }
}