package com.brilliantbear.gank.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by cx.lian on 2016/4/29.
 */
public class GirlsAdapter extends RecyclerView.Adapter<GirlsAdapter.GirlsViewHolder> {

    private List<Image> images;

    public GirlsAdapter(List<Image> images) {
        this.images = images;
    }

    @Override
    public GirlsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(GirlsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    static class GirlsViewHolder extends RecyclerView.ViewHolder {
        public GirlsViewHolder(View itemView) {
            super(itemView);
        }
    }
}
