package com.brilliantbear.gank.net;

import com.brilliantbear.gank.bean.ResultEntity;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by cx.lian on 2016/4/28.
 */
public class Net {
    public static final String BASE_URL = "http://gank.io/api/data/";

    private volatile static Net mNet;
    private final Retrofit mRetrofit;
    private final GankService mService;

    private Net() {
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mService = mRetrofit.create(GankService.class);
    }

    public static Net getInstance() {
        if (mNet == null) {
            synchronized (Net.class) {
                if (mNet == null) {
                    mNet = new Net();
                }
            }
        }
        return mNet;
    }

    public Observable<ResultEntity> getNews(int count, int paged) {
        return mService.getNews(count, paged);
    }
}
