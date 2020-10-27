package com.newsuper.code.swipeback;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.newsuper.code.R;

import java.util.ArrayList;
import java.util.List;

public class SwipeRefreshLayoutActivity extends BaseSwipeActivity {
    private float density;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<String> dataList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_swipe_refresh_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        density = getResources().getDisplayMetrics().density;
        super.onCreate(savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(
                                SwipeRefreshLayoutActivity.this,
                                "The refresh is finished!",
                                Toast.LENGTH_SHORT).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);

            }
        });
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
            TextView textView = new TextView(SwipeRefreshLayoutActivity.this);
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