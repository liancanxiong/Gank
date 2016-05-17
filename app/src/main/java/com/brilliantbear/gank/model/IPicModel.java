package com.brilliantbear.gank.model;

import com.brilliantbear.gank.bean.NewsEntity;

import java.util.List;

/**
 * Created by Bear on 2016-5-9.
 */
public interface IPicModel {

    void loadPic(List<NewsEntity> newsEntities);
}
