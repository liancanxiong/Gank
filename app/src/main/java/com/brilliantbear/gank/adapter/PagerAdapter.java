package com.brilliantbear.gank.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by cx.lian on 2016/4/28.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> framgnets;
    private String[] tabTitles;

    public PagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] tabTitles) {
        super(fm);
        this.framgnets = fragments;
        this.tabTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return framgnets.get(position);
    }

    @Override
    public int getCount() {
        return framgnets == null ? 0 : framgnets.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
