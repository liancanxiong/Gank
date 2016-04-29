package com.brilliantbear.gank.model;

import com.brilliantbear.gank.bean.ImageSize;
import com.brilliantbear.gank.bean.NewsEntity;
import com.brilliantbear.gank.bean.ResultEntity;
import com.brilliantbear.gank.net.Net;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by cx.lian on 2016/4/29.
 */
public class GirlsModel implements IListModel {

    private Net mNet;

    public GirlsModel() {
        mNet = Net.getInstance();
    }

    @Override
    public void getData(int count, int paged) {
        mNet.getGirls(10, 1)
                .subscribeOn(Schedulers.io())
                .map(new Func1<ResultEntity, List<NewsEntity>>() {
                    @Override
                    public List<NewsEntity> call(ResultEntity resultEntity) {
                        return resultEntity.getResults();
                    }
                })
                .flatMap(new Func1<List<NewsEntity>, Observable<NewsEntity>>() {
                    @Override
                    public Observable<NewsEntity> call(List<NewsEntity> newsEntities) {
                        return Observable.from(newsEntities);
                    }
                })
                .map(new Func1<NewsEntity, ImageSize>() {
                    @Override
                    public ImageSize call(NewsEntity newsEntity) {
                        ImageSize imageSize = new ImageSize();
                        try {
                            imageSize = mNet.getImageSize(newsEntity.getUrl());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return imageSize;
                    }
                })
                .subscribe(new Subscriber<ImageSize>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ImageSize imageSize) {

                    }
                });
    }
}
