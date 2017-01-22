package com.minda.mindadaily.base.activty;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kun on 2017/1/19.
 */

public abstract class MinDaBaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected void initData() {

    }

    protected void initView() {

    }

    abstract protected int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Unbinder unbinder = ButterKnife.bind(this);
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
