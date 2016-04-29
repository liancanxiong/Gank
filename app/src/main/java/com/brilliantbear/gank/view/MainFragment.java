package com.brilliantbear.gank.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.brilliantbear.gank.R;
import com.brilliantbear.gank.adapter.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by cx.lian on 2016/4/28.
 */
public class MainFragment extends BaseFragment {
    @Bind(R.id.tab)
    TabLayout mTab;
    @Bind(R.id.pager)
    ViewPager mPgaer;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View mRootView) {
        super.initView(mRootView);

        List<Fragment> fragments = new ArrayList<>();
        String[] tabTitles = getResources().getStringArray(R.array.tab_title);

        fragments.add(new NewsFragment());
        fragments.add(new GirlsFragment());

        PagerAdapter mAdapter = new PagerAdapter(getFragmentManager(), fragments, tabTitles);
        mPgaer.setAdapter(mAdapter);

        mTab.setupWithViewPager(mPgaer);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }
}
