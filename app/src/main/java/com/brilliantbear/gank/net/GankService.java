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
    Observable<ResultEntity> getAllNews(@Path("count") int count, @Path("paged") int paged);

    @GET("福利/{count}/{paged}")
    Observable<ResultEntity> getGirls(@Path("count") int count, @Path("paged") int paged);

    @GET("Android/{count}/{paged}")
    Observable<ResultEntity> getAndroidNews(@Path("count") int count, @Path("paged") int paged);

    @GET("iOS/{count}/{paged}")
    Observable<ResultEntity> getIOSNews(@Path("count") int count, @Path("paged") int paged);

    @GET("休息视频/{count}/{paged}")
    Observable<ResultEntity> getVideoNews(@Path("count") int count, @Path("paged") int paged);

    @GET("拓展资源/{count}/{paged}")
    Observable<ResultEntity> getResourceNews(@Path("count") int count, @Path("paged") int paged);

    @GET("前端/{count}/{paged}")
    Observable<ResultEntity> getWebNews(@Path("count") int count, @Path("paged") int paged);

    @GET("瞎推荐/{count}/{paged}")
    Observable<ResultEntity> getRecommendNews(@Path("count") int count, @Path("paged") int paged);
}
