package com.brilliantbear.gank.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.brilliantbear.gank.R;
import com.brilliantbear.gank.adapter.GirlsAdapter;
import com.brilliantbear.gank.bean.NewsEntity;
import com.brilliantbear.gank.db.DB;
import com.brilliantbear.gank.db.Image;
import com.brilliantbear.gank.presenter.GirlsPresenter;
import com.brilliantbear.gank.presenter.IListPresenter;
import com.brilliantbear.gank.presenter.ILoadPicPresenter;
import com.brilliantbear.gank.presenter.LoadPicPresenter;
import com.brilliantbear.gank.utils.DensityUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;

/**
 * Created by cx.lian on 2016/4/29.
 */
public class GirlsFragment extends BaseFragment implements IPicView, IListView, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.rv_list)
    RecyclerView mList;
    private StaggeredGridLayoutManager mLayoutManager;
    private DB mDB;
    private IListPresenter mGirlsPresenter;

    public static final int NEWS_COUNT = 10;
    public static final int START_PAGED = 1;
    private ILoadPicPresenter mLoadPicPresenter;
    private GirlsAdapter mAdapter;
    private List<Image> mImages;
    private int hadLoadPaged = 1;
    private boolean isLoading = false;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }


    private int findMax(int[] values) {
        int max = values[0];
        for (int i : values) {
            if (i > max)
                max = i;
        }
        return max;
    }

    @Override
    protected void initView(View mRootView) {
        super.initView(mRootView);

        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mList.setLayoutManager(mLayoutManager);
        mList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !isLoading) {
                    int[] position = new int[mLayoutManager.getSpanCount()];
                    mLayoutManager.findLastVisibleItemPositions(position);
                    int lastPosition = findMax(position);
                    if (lastPosition == mLayoutManager.getItemCount() - 1) {
                        Log.d("Gank", "load more");
                        isLoading = true;
                        mGirlsPresenter.getData(NEWS_COUNT, hadLoadPaged + 1);
                    }
                }
            }
        });

        mRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mRefresh.setOnRefreshListener(this);
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        mDB = DB.getInstance(mContext);

        mGirlsPresenter = new GirlsPresenter(this);
        mLoadPicPresenter = new LoadPicPresenter(this);

        mImages = new ArrayList<>();
        mAdapter = new GirlsAdapter(mContext, mImages);
        mList.setAdapter(mAdapter);
    }

    private void showRefresh(boolean isShow) {
        if (mRefresh == null)
            return;
        if (isShow) {
            mRefresh.setProgressViewOffset(false, 0, DensityUtils.dip2px(mContext, 24));
            mRefresh.setRefreshing(true);
        } else {
            mRefresh.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        isLoading = true;
        mGirlsPresenter.getData(NEWS_COUNT, START_PAGED);
    }

    @Override
    public void showProgress() {
        showRefresh(true);
    }

    @Override
    public void hideProgress() {
        showRefresh(false);
    }


    @Override
    public void dataRefresh(List<NewsEntity> newsEntities) {
        Log.d("Gank", newsEntities.toString());
        isLoading = false;
        mImages.clear();
        mAdapter.notifyDataSetChanged();
        hadLoadPaged = 1;

//        mLoadPicPresenter.loadPic(newsEntities);
        mLoadPicPresenter.loadPic(filter(newsEntities));
    }

    private List<NewsEntity> filter(List<NewsEntity> newsEntities) {
        Iterator<NewsEntity> iterator = newsEntities.iterator();
        while (iterator.hasNext()) {
            NewsEntity newsEntity = iterator.next();
            Image image = mDB.getImage(newsEntity.getUrl());
            if (image != null) {
                Log.d("Gank", "db:" + image.getUrl());
                mImages.add(image);
                mAdapter.notifyItemInserted(mAdapter.getItemCount());
                iterator.remove();
            }
        }
        return newsEntities;
    }

    @Override
    public void dataLoadMore(List<NewsEntity> newsEntities) {
        isLoading = false;
        if (newsEntities != null) {
            hadLoadPaged++;
            mLoadPicPresenter.loadPic(filter(newsEntities));
//            mLoadPicPresenter.loadPic(newsEntities);
        }
    }

    @Override
    public void showSth(String error) {
        Snackbar.make(mList, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showPic(Image image) {
        mImages.add(image);
        mAdapter.notifyItemInserted(mAdapter.getItemCount());
        mDB.saveImage(image);
    }
}
