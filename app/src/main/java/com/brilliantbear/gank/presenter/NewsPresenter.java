package com.brilliantbear.gank.presenter;

import com.brilliantbear.gank.bean.NewsEntity;
import com.brilliantbear.gank.model.IListModel;
import com.brilliantbear.gank.model.NewsModel;
import com.brilliantbear.gank.model.OnGetDataListener;
import com.brilliantbear.gank.view.IListView;

import java.util.List;

/**
 * Created by cx.lian on 2016/4/28.
 */
public class NewsPresenter implements IListPresenter, OnGetDataListener {

    private IListView view;
    private IListModel model;

    public NewsPresenter(IListView view) {
        this.view = view;
        model = new NewsModel(this);
    }

    @Override
    public void getData(int count, int paged) {
        view.showProgress();
        model.getData(count, paged);
    }

    @Override
    public void onSuccess(List<NewsEntity> newsEntities, boolean isRefresh) {
        view.hideProgress();
        if(isRefresh){
            view.dataRefresh(newsEntities);
        }else{
            view.dataLoadMore(newsEntities);
        }
    }

    @Override
    public void onFailed() {
        view.hideProgress();
        view.showError("获取数据失败");
    }
}
