package com.newsuper.code.fitimg;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.newsuper.code.R;

public class FitImgActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_fitimg);

        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setAdapter(new SplashAdapter(FitImgActivity.this));
        mRecyclerView.setLayoutManager(new ScollLinearLayoutManager(FitImgActivity.this));

        //smoothScrollToPosition滚动到某个位置（有滚动效果）
        mRecyclerView.smoothScrollToPosition(Integer.MAX_VALUE / 2);
    }


}