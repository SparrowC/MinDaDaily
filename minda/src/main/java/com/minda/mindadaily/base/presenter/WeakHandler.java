package com.minda.mindadaily.base.presenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by kun on 2017/1/19.
 */

public class WeakHandler extends Handler {

    public interface IHandler {
        void handleMessage(Message msg);
    }

    private WeakReference<IHandler> mReference;

    public WeakHandler(IHandler handler) {
        mReference = new WeakReference<IHandler>(handler);
    }

    public WeakHandler(Looper looper, IHandler handler) {
        super(looper);
        mReference = new WeakReference<IHandler>(handler);
    }

    @Override
    public void handleMessage(Message msg) {
        IHandler iHandler = mReference.get();
        if (iHandler != null && msg != null) {
            iHandler.handleMessage(msg);
        }
    }
}
