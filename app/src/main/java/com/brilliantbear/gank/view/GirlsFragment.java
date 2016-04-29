package com.brilliantbear.gank.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.brilliantbear.gank.R;
import com.brilliantbear.gank.bean.NewsEntity;
import com.brilliantbear.gank.db.DB;
import com.brilliantbear.gank.utils.DensityUtils;

import java.util.List;

import butterknife.Bind;

/**
 * Created by cx.lian on 2016/4/29.
 */
public class GirlsFragment extends BaseFragment implements IListView, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.rv_list)
    RecyclerView mList;
    private StaggeredGridLayoutManager mLayoutManager;
    private DB mDB;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }


    @Override
    protected void initView(View mRootView) {
        super.initView(mRootView);

        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mList.setLayoutManager(mLayoutManager);

        mRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mRefresh.setOnRefreshListener(this);
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        mDB = DB.getInstance(mContext);
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

    }

    @Override
    public void dataLoadMore(List<NewsEntity> newsEntities) {

    }

    @Override
    public void showSth(String error) {
        Snackbar.make(mList, error, Snackbar.LENGTH_SHORT).show();
    }
}
