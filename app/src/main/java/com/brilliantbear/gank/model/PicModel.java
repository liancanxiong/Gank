package com.brilliantbear.gank.model;

import android.util.Log;

import com.brilliantbear.gank.bean.NewsEntity;
import com.brilliantbear.gank.db.Image;
import com.brilliantbear.gank.net.Net;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Bear on 2016-5-9.
 */
public class PicModel implements IPicModel {

    private OnLoadPicListener listener;

    public PicModel(OnLoadPicListener listener) {
        this.listener = listener;
    }

    @Override
    public void loadPic(List<NewsEntity> newsEntities) {
        Observable.from(newsEntities)
                .subscribeOn(Schedulers.io())
                .map(new Func1<NewsEntity, Image>() {
                    @Override
                    public Image call(NewsEntity newsEntity) {
                        return Net.getImageSize(newsEntity.getUrl());
                    }
                })
                .filter(new Func1<Image, Boolean>() {
                    @Override
                    public Boolean call(Image image) {
                        return image != null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Image>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailed(e.toString());
                    }

                    @Override
                    public void onNext(Image image) {
                        Log.d("Gank", "net image:" + image.getUrl());
                        listener.onSuccess(image);
                    }
                });
    }
}
