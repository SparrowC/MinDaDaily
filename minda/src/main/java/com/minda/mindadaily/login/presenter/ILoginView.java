package com.minda.mindadaily.login.presenter;

import com.minda.mindadaily.base.presenter.IBaseView;

/**
 * Created by kun on 2017/1/19.
 */

public interface ILoginView extends IBaseView {
    void onSuccess();

    void onFailed();
}
