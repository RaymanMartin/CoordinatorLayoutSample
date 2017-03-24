package com.hasee.pro.bottomsheet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rayman on 2016/12/19.
 */
public class BottomSheetAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> data;
    private ViewHolder viewHolder;
    private int n = 0;

    public BottomSheetAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder.mTv_test.setText(data.get(position));
        n++;
        Log.i("BottomSheetAdapter", "调用了多少次？：" + n);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIv_pic;
        private TextView mTv_test;

        public ViewHolder(View itemView) {
            super(itemView);
            mIv_pic = (ImageView) itemView.findViewById(R.id.iv_test);
            mTv_test = (TextView) itemView.findViewById(R.id.tv_test);
        }
    }

}
