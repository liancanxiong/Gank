package com.brilliantbear.gank.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.brilliantbear.gank.R;
import com.brilliantbear.gank.adapter.NewsAdapter;
import com.brilliantbear.gank.bean.NewsEntity;
import com.brilliantbear.gank.db.DB;
import com.brilliantbear.gank.presenter.IListPresenter;
import com.brilliantbear.gank.presenter.NewsPresenter;
import com.brilliantbear.gank.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by cx.lian on 2016/4/28.
 */
public class NewsFragment extends BaseFragment implements IListView, SwipeRefreshLayout.OnRefreshListener {

    public static final int NEWS_COUNT = 10;
    public static final int START_PAGED = 1;

    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.rv_list)
    RecyclerView mList;
    private NewsAdapter mAdapter;
    private IListPresenter mListPresenter;
    private List<NewsEntity> mNews;
    private int hadLoadPaged = 1;
    private LinearLayoutManager mLayoutManager;
    private DB mDB;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View mRootView) {
        super.initView(mRootView);
        mLayoutManager = new LinearLayoutManager(mContext);
        mList.setLayoutManager(mLayoutManager);
        mList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLayoutManager.findLastVisibleItemPosition() == mNews.size() - 1) {
                    Log.d("Gank", "load more");
                    mListPresenter.getData(NEWS_COUNT, hadLoadPaged + 1);
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
        mNews = mDB.getNews();
        if (mNews == null)
            mNews = new ArrayList<>();

        mAdapter = new NewsAdapter(mNews);
        mList.setAdapter(mAdapter);

        mListPresenter = new NewsPresenter(this);
//        mListPresenter.getData(NEWS_COUNT, START_PAGED);
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
    public void showProgress() {
        showRefresh(true);
    }

    @Override
    public void hideProgress() {
        showRefresh(false);
    }

    @Override
    public void dataRefresh(List<NewsEntity> newsEntities) {
        mNews.clear();
        mNews.addAll(newsEntities);
        mAdapter.notifyDataSetChanged();

        Log.d("Gank", "data refresh");

        mDB.saveNews(newsEntities);
    }

    @Override
    public void dataLoadMore(List<NewsEntity> newsEntities) {
        if (newsEntities != null) {
            hadLoadPaged++;
            int startPosition = mNews.size();
            mNews.addAll(newsEntities);
            mAdapter.notifyItemRangeInserted(startPosition, newsEntities.size());
        }
    }

    @Override
    public void showSth(String error) {
        Snackbar.make(mList, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        mListPresenter.getData(NEWS_COUNT, START_PAGED);
    }
}
