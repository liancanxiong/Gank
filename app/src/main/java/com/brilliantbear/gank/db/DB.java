package com.brilliantbear.gank.db;

import android.content.Context;

import com.brilliantbear.gank.bean.NewsEntity;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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

//        mRealm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.where(NewsEntity.class).findAll().deleteAllFromRealm();
//                realm.copyToRealmOrUpdate(news);
//            }
//        });
        mRealm.beginTransaction();
        mRealm.where(NewsEntity.class).findAll().deleteAllFromRealm();
        mRealm.copyToRealmOrUpdate(news);
        mRealm.commitTransaction();
    }

    public List<NewsEntity> getNews() {
        return mRealm.where(NewsEntity.class).findAll();
    }
}
