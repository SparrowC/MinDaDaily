package com.minda.mindadaily.base.presenter;

import android.os.Message;

/**
 * Created by kun on 2017/1/19.
 */

public abstract class BasePresenter<T extends IBaseView, D> implements WeakHandler.IHandler {
    protected T mView;
    protected D mData;
    protected WeakHandler mHandler;

    public BasePresenter() {
        mHandler = new WeakHandler(this);
    }

    public void bindView(T view) {
        mView = view;
    }

    public void unbindView() {
        mView = null;
    }

    public void sendRequest(Object... params) {
        doRequest(params);
    }

    abstract protected void doRequest(Object... params);

    @Override
    public abstract void handleMessage(Message msg);
}
