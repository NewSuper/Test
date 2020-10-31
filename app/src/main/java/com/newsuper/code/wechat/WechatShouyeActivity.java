package com.newsuper.code.wechat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.newsuper.code.R;

import java.util.ArrayList;
import java.util.List;

public class WechatShouyeActivity extends AppCompatActivity implements View.OnClickListener {

    private WechatRecyclerView mRv;
    private RecyclerView rvinside;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> entities2 = new ArrayList<>();
    private MyAdapter mAdapter;
    private LinearLayout mLayoutTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_shouye);
        initView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }



    private void initView() {

        mRv = (WechatRecyclerView) findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(linearLayoutManager);
        for (int i = 0; i < 100; i++) {
            entities.add(new Entity());
        }
        mAdapter = new MyAdapter(entities);
        mRv.setAdapter(mAdapter);
        View view = LayoutInflater.from(this).inflate(R.layout.headview, null);
        mAdapter.addHeaderView(view);
        mRv.setHeadView(view);
        mRv.setMoveListener(new WechatRecyclerView.MoveCallback() {
            @Override
            public void onMove(boolean isMove) {
                if (isMove) {
                    mLayoutTitle.setVisibility(View.GONE);
                } else {
                    mLayoutTitle.setVisibility(View.VISIBLE);
                }
            }
        });
        mLayoutTitle =  findViewById(R.id.layout_title);
        rvinside = view.findViewById(R.id.rv_inside);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        rvinside.setLayoutManager(gridLayoutManager);
        rvinside.setItemViewCacheSize(50);
        for (int i = 0; i < 50; i++) {
            entities2.add(new Entity());
        }
        rvinside.setAdapter(new MyAdapter_inside(entities2));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.layout_title:
                break;
        }
    }


    class MyAdapter extends BaseQuickAdapter<Entity, BaseViewHolder> {

        public MyAdapter(@Nullable List<Entity> data) {
            super(R.layout.item_rv, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Entity item) {
        }
    }

    class MyAdapter_inside extends BaseQuickAdapter<Entity, BaseViewHolder> {

        public MyAdapter_inside(@Nullable List<Entity> data) {
            super(R.layout.item_rv2, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Entity item) {

        }
    }
}