package com.brilliantbear.gank.model;

import android.util.Log;

import com.brilliantbear.gank.bean.NewsEntity;
import com.brilliantbear.gank.bean.ResultEntity;
import com.brilliantbear.gank.net.Net;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by cx.lian on 2016/4/29.
 */
public class GirlsModel implements IListModel {

    private Net mNet;
    private OnGetDataListener listener;

    public GirlsModel(OnGetDataListener listener) {
        this.listener = listener;
        mNet = Net.getInstance();
    }

    @Override
    public void getData(int count, final int paged) {
        Log.d("Gank", "load girls data");
        mNet.getGirls(count, paged)
                .subscribeOn(Schedulers.io())
                .map(new Func1<ResultEntity, List<NewsEntity>>() {
                    @Override
                    public List<NewsEntity> call(ResultEntity resultEntity) {
                        return resultEntity.getResults();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<NewsEntity>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Gank", e.toString());
                        listener.onFailed();
                    }

                    @Override
                    public void onNext(List<NewsEntity> newsEntities) {
//                        Log.d("Gank", newsEntities.toString());
                        listener.onSuccess(newsEntities, paged == 1);
                    }
                });
    }
}
