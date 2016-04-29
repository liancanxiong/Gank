package com.brilliantbear.gank.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by cx.lian on 2016/4/29.
 */
public class Image extends RealmObject {
    @PrimaryKey
    private String url;
    private Integer width;
    private Integer height;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
