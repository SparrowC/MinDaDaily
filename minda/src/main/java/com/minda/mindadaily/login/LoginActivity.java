package com.minda.mindadaily.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.minda.mindadaily.BuildConfig;
import com.minda.mindadaily.MainActivity;
import com.minda.mindadaily.R;
import com.minda.mindadaily.app.constant.Constants;
import com.minda.mindadaily.base.activty.MinDaBaseActivity;
import com.minda.mindadaily.login.presenter.ILoginView;
import com.minda.mindadaily.login.presenter.LoginPresenter;
import com.minda.mindadaily.utils.SharedPrefUtils;
import com.minda.mindadaily.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kun on 2017/1/19.
 */

public class LoginActivity extends MinDaBaseActivity implements ILoginView {

    @BindView(R.id.et_user_name)
    EditText mEtUserName;

    @BindView(R.id.et_password)
    EditText mEtPassword;

    private LoginPresenter mLoginPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        if (SharedPrefUtils.getBoolean(Constants.SP_LOGIN_SUCCESS)) {
            goMainActivity();
        }
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.bindView(this);
        mEtUserName.setText(SharedPrefUtils.getString(Constants.SP_ACCOUNT));
        mEtPassword.setText(SharedPrefUtils.getString(Constants.SP_PASSWORD));
    }

    @OnClick(R.id.tv_login)
    public void click(View view) {
        if (BuildConfig.DEBUG && TextUtils.isEmpty(mEtUserName.getText().toString())) {
            mLoginPresenter.sendRequest("201321092024", "268013");
        } else {
            mLoginPresenter.sendRequest(mEtUserName.getText().toString().trim(), mEtPassword.getText().toString().trim());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoginPresenter != null) {
            mLoginPresenter.unbindView();
        }
    }

    @Override
    public void onSuccess() {
        ToastUtils.toast(this, getString(R.string.login_success));
        SharedPrefUtils.putBoolean(Constants.SP_LOGIN_SUCCESS, true);
        goMainActivity();
    }

    private void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailed() {
        ToastUtils.toast(this, getString(R.string.login_failed));
    }
}
