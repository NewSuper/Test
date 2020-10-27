package com.newsuper.code.swipeback;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newsuper.code.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends BaseSwipeActivity {
    private float density;
    private RecyclerView mRecyclerView;
    private List<String> dataList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        density = getResources().getDisplayMetrics().density;
        super.onCreate(savedInstanceState);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dataList.add("Test" + i);
        }
        mRecyclerView.setAdapter(adapter);
    }

    private RecyclerView.Adapter<ViewHolder> adapter = new RecyclerView.Adapter<ViewHolder>() {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(RecyclerViewActivity.this);
            textView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (40 * density)));
            textView.setGravity(Gravity.CENTER);
            return new ViewHolder(textView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv.setText(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    };

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }
    }
}
