package com.brilliantbear.gank.model;

import com.brilliantbear.gank.bean.NewsEntity;

import java.util.List;

/**
 * Created by cx.lian on 2016/4/28.
 */
public interface OnGetDataListener {
    void onSuccess(List<NewsEntity> newsEntities, boolean isRefresh);

    void onFailed();
}
