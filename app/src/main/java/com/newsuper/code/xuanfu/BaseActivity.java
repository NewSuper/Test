package com.newsuper.code.xuanfu;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutRes() <= 0) {
            throw new IllegalArgumentException("xml should not be null");
        }
        setContentView(getLayoutRes());
    }

    protected abstract int getLayoutRes();

    protected void initView() {

    }
}