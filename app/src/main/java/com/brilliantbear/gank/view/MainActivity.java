package com.brilliantbear.gank.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.brilliantbear.gank.R;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer)
    DrawerLayout mDrawer;
    @Bind(R.id.nav)
    NavigationView mNav;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initDrawer();
        initNav();
        toolbar.setTitleTextColor(Color.WHITE);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.frame_main, new MainFragment()).commit();
        }
    }

    private void initNav() {
        mNav.setNavigationItemSelectedListener(this);
    }

    private void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void closeDrawer() {
        if (mDrawer != null) {
            mDrawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        closeDrawer();
        return true;
    }
}
