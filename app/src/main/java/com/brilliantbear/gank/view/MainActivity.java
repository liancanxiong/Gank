package com.brilliantbear.gank.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

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
        switch (item.getItemId()) {
            case R.id.item_main:

                break;
            case R.id.item_android:

                break;
            case R.id.item_ios:

                break;
            case R.id.item_video:

                break;
            case R.id.item_girls:

                break;
            case R.id.item_resource:

                break;
            case R.id.item_web:

                break;
            case R.id.item_recommend:

                break;
            case R.id.item_app:

                break;
            case R.id.item_about:
                showAboutDialog();
                break;
        }
        closeDrawer();
        return true;
    }

    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        TextView textView = new TextView(this);
        textView.setText(Html.fromHtml(getString(R.string.about)));
        builder.setView(textView, 50, 50, 50, 0);
        builder.setTitle(getString(R.string.item_about));
        builder.setPositiveButton("Ok", null);
        builder.show();
    }
}
