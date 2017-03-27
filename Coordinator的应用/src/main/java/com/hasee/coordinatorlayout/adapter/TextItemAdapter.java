package com.hasee.coordinatorlayout.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hasee.coordinatorlayout.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */

public class TextItemAdapter extends RecyclerView.Adapter<TextItemAdapter.ViewHolder> {

    private List<String> data = new ArrayList<>();

    public TextItemAdapter() {
        for (int i = 0; i < 50; i++) {
            data.add("数据：" + i);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, null);
        TextItemAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTv_content.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTv_content;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
