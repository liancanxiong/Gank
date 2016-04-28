package com.brilliantbear.gank.model;

import android.util.Log;

import com.brilliantbear.gank.bean.NewsEntity;
import com.brilliantbear.gank.bean.ResultEntity;
import com.brilliantbear.gank.net.Net;

import java.util.Iterator;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by cx.lian on 2016/4/28.
 */
public class NewsModel implements IListModel {

    private OnGetDataListener listener;
    private final Net mNet;

    public NewsModel(OnGetDataListener listener) {
        this.listener = listener;
        mNet = Net.getInstance();
    }

    @Override
    public void getData(int count, final int paged) {
        Log.d("Gank", "get data");
        mNet.getNews(count, paged)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<ResultEntity, List<NewsEntity>>() {
                    @Override
                    public List<NewsEntity> call(ResultEntity resultEntity) {
//                        if (resultEntity.isError()) {
//                            //TODO
//                        }
                        return resultEntity.getResults();
                    }
                })
                .map(new Func1<List<NewsEntity>, List<NewsEntity>>() {
                    @Override
                    public List<NewsEntity> call(List<NewsEntity> newsEntities) {

                        Iterator<NewsEntity> iterator = newsEntities.iterator();
                        while (iterator.hasNext()) {
                            if (iterator.next().getType().equals("福利"))
                                iterator.remove();
                        }
                        return newsEntities;
                    }
                })
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
                        Log.d("Gank", newsEntities.toString());
                        listener.onSuccess(newsEntities, paged == 1);
                    }
                });
    }
}
