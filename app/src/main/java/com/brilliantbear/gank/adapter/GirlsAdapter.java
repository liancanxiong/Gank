package com.brilliantbear.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brilliantbear.gank.R;
import com.brilliantbear.gank.custom.RadioImageView;
import com.brilliantbear.gank.db.Image;
import com.brilliantbear.gank.utils.ImageUtils;
import com.brilliantbear.gank.view.PicViewerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cx.lian on 2016/4/29.
 */
public class GirlsAdapter extends RecyclerView.Adapter<GirlsAdapter.GirlsViewHolder> {

    private Context context;
    private List<Image> images;

    public GirlsAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public GirlsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_girls, parent, false);
        return new GirlsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GirlsViewHolder holder, final int position) {
        Image image = images.get(position);
        holder.ivRadio.setOriginalSize(image.getWidth(), image.getHeight());
//        Glide.with(context).load(image.getUrl()).into(holder.ivRadio);
        ImageUtils.display(context, holder.ivRadio, image.getUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> urls = new ArrayList<>();
                for (Image image : images) {
                    urls.add(image.getUrl());
                }
                PicViewerActivity.gotoViewer(context, urls, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    static class GirlsViewHolder extends RecyclerView.ViewHolder {

        RadioImageView ivRadio;

        public GirlsViewHolder(View itemView) {
            super(itemView);
            ivRadio = (RadioImageView) itemView.findViewById(R.id.iv_radio);
        }
    }
}
