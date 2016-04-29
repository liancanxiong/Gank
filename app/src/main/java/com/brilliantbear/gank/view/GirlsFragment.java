package com.brilliantbear.gank.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.brilliantbear.gank.R;

import butterknife.Bind;

/**
 * Created by cx.lian on 2016/4/29.
 */
public class GirlsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.rv_list)
    RecyclerView mList;
    private StaggeredGridLayoutManager mLayoutManager;

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

    }

    @Override
    public void onRefresh() {

    }

}
