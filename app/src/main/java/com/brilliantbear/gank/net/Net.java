package com.brilliantbear.gank.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.brilliantbear.gank.bean.ResultEntity;
import com.brilliantbear.gank.db.Image;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by cx.lian on 2016/4/28.
 */
public class Net {
    public static final String BASE_URL = "http://gank.io/api/data/";

    private volatile static Net mNet;
    private final Retrofit mRetrofit;
    private final GankService mService;

    private Net() {
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mService = mRetrofit.create(GankService.class);
    }

    public static Net getInstance() {
        if (mNet == null) {
            synchronized (Net.class) {
                if (mNet == null) {
                    mNet = new Net();
                }
            }
        }
        return mNet;
    }

    public Observable<ResultEntity> getNews(int count, int paged) {
        return mService.getAllNews(count, paged);
    }

    public Observable<ResultEntity> getGirls(int count, int paged) {
        return mService.getGirls(count, paged);
    }

    public static Image getImageSize(String url) {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        Response response = null;
        try {
            response = mClient.newCall(new Request.Builder().url(url).build()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response == null)
            return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream(), null, options);
        response.body().close();
        int width = options.outWidth;
        int height = options.outHeight;
        return new Image(url, width, height);
    }
}
