package com.minda.mindadaily.login.presenter;

import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.minda.mindadaily.app.constant.Api;
import com.minda.mindadaily.app.constant.Constants;
import com.minda.mindadaily.base.presenter.BasePresenter;
import com.minda.mindadaily.utils.ParseUtils;
import com.minda.mindadaily.utils.SharedPrefUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by kun on 2017/1/19.
 */

public class LoginPresenter extends BasePresenter<ILoginView, Object> {

    private static final Integer CODE_MOVED_TEMPORARILY = 302;//重定向代表认证成功
    boolean isRequesting = false;

    @Override
    protected void doRequest(final Object... params) {
        if (params.length < 2) return;
        isRequesting = true;
        Observable.just(params)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Object[], Boolean>() {
                    @Override
                    public Boolean call(Object[] objects) {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(Api.URL_LOGIN)
                                .addConverterFactory(SimpleXmlConverterFactory.create())
                                .build();
                        LoginServer loginServer = retrofit.create(LoginServer.class);
                        Call<ResponseBody> call = loginServer.getLoginResult(Api.URL_GOTO, (String) params[0], (String) params[1]);
                        int code = 0;
                        String result = null;
                        try {
                            Response<ResponseBody> response = call.execute();
                            code = response.code();
                            result = ParseUtils.InputStreamTOString(response.body().byteStream());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("vonnie", e.toString());
                            return false;
                        }
                        Log.e("vonnie", result);
                        boolean success = (!TextUtils.isEmpty(result)) && result.contains("window.top.document.location");
                        if (success) {
                            SharedPrefUtils.putString(Constants.SP_ACCOUNT, (String) params[0]);
                            SharedPrefUtils.putString(Constants.SP_PASSWORD, (String) params[1]);
                        }
                        return success;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("vonnie", e.toString());
                        if (mView != null) {
                            mView.onFailed();
                        }
                    }

                    @Override
                    public void onNext(Boolean success) {
                        if (mView == null) return;
                        if (success) {
                            mView.onSuccess();
                        } else {
                            mView.onFailed();
                        }
                        isRequesting = false;
                    }
                });
    }

    @Override
    public void handleMessage(Message msg) {

    }

    //?goto=http://my.scuec.edu.cn/index.portal
    public interface LoginServer {
        @Streaming
        @GET("/amserver/UI/Login")
        Call<ResponseBody> getLoginResult(@Query("goto") String url, @Query("IDToken1") String name, @Query("IDToken2") String password);
    }
}
