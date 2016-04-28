package com.brilliantbear.gank.net;

import com.brilliantbear.gank.bean.ResultEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by cx.lian on 2016/4/28.
 */
public interface GankService {

    @GET("all/{count}/{paged}")
    Observable<ResultEntity> getNews(@Path("count") int count, @Path("paged") int paged);
}
