package com.brilliantbear.gank.view;

import com.brilliantbear.gank.bean.NewsEntity;

import java.util.List;

/**
 * Created by cx.lian on 2016/4/28.
 */
public interface IListView {

    void showProgress();

    void hideProgress();

    void dataRefresh(List<NewsEntity> newsEntities);

    void dataLoadMore(List<NewsEntity> newsEntities);

    void showSth(String error);
}
