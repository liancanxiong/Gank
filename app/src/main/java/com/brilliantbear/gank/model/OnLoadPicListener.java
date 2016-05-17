package com.brilliantbear.gank.model;

import com.brilliantbear.gank.db.Image;

/**
 * Created by Bear on 2016-5-16.
 */
public interface OnLoadPicListener {
    void onSuccess(Image image);

    void onFailed(String msg);
}
