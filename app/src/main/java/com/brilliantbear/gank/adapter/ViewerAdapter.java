package com.brilliantbear.gank.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.brilliantbear.gank.custom.PinchImageView;
import com.brilliantbear.gank.utils.ImageUtils;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Bear on 2016-5-19.
 */
public class ViewerAdapter extends android.support.v4.view.PagerAdapter {

    private Context context;
    private ArrayList<String> urls;
    private final LinkedList<PinchImageView> viewCache;


    public ViewerAdapter(Context context, ArrayList<String> urls) {
        this.context = context;
        this.urls = urls;
        viewCache = new LinkedList<PinchImageView>();
    }

    @Override
    public int getCount() {
        return urls == null ? 0 : urls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PinchImageView piv;
        if (viewCache.size() > 0) {
            piv = viewCache.remove();
            piv.reset();
        } else {
            piv = new PinchImageView(context);
        }
        ImageUtils.display(context, piv, urls.get(position));
        container.addView(piv);
        return piv;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        PinchImageView piv = (PinchImageView) object;
        container.removeView(piv);
        viewCache.add(piv);
    }
}
