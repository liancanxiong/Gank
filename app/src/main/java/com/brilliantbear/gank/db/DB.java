package com.brilliantbear.gank.db;

import android.content.Context;

import com.brilliantbear.gank.bean.NewsEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by cx.lian on 2016/4/28.
 */
public class DB {

    private volatile static DB mDB;
    private static Realm mRealm;

    private DB() {
    }

    public static DB getInstance(Context context) {
        if (mDB == null) {
            synchronized (DB.class) {
                if (mDB == null) {
                    RealmConfiguration conf = new RealmConfiguration.Builder(context.getApplicationContext()).build();
                    Realm.setDefaultConfiguration(conf);
                    mRealm = Realm.getDefaultInstance();

                    mDB = new DB();
                }
            }
        }
        return mDB;
    }


    public void saveNews(final List<NewsEntity> news) {
        if (news == null)
            return;

        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(NewsEntity.class).findAll().deleteAllFromRealm();
                realm.copyToRealmOrUpdate(news);
            }
        });

    }

    public List<NewsEntity> getNews() {
        return new ArrayList<NewsEntity>(mRealm.where(NewsEntity.class).findAll());
    }


    public List<NewsEntity> checkImageSize(List<NewsEntity> newsEntities) {
        List<NewsEntity> list = new ArrayList<>();
        for (NewsEntity entity : newsEntities) {
            Image first = mRealm.where(Image.class)
                    .equalTo("url", entity.getUrl())
                    .notEqualTo("width", 0)
                    .notEqualTo("height", 0)
                    .findFirst();
            list.add(entity);
        }
        return list;
    }

    public void saveImage(Image image) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(image);
        mRealm.commitTransaction();
    }

    public Image getImage(String url){
        return mRealm.where(Image.class).equalTo("url", url).findFirst();
    }
}
