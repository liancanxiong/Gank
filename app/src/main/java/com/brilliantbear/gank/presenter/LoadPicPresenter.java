package com.brilliantbear.gank.presenter;

import android.util.Log;

import com.brilliantbear.gank.bean.NewsEntity;
import com.brilliantbear.gank.db.Image;
import com.brilliantbear.gank.model.IPicModel;
import com.brilliantbear.gank.model.OnLoadPicListener;
import com.brilliantbear.gank.model.PicModel;
import com.brilliantbear.gank.view.IPicView;

import java.util.List;

/**
 * Created by Bear on 2016-5-9.
 */
public class LoadPicPresenter implements ILoadPicPresenter, OnLoadPicListener {

    private IPicView view;
    private final IPicModel model;

    public LoadPicPresenter(IPicView view) {
        this.view = view;
        model = new PicModel(this);
    }

    @Override
    public void loadPic(List<NewsEntity> newsEntities) {
        model.loadPic(newsEntities);
    }


    @Override
    public void onSuccess(Image image) {
        view.showPic(image);
    }

    @Override
    public void onFailed(String msg) {
        Log.e("Gank", msg);
    }


}
