package com.brilliantbear.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brilliantbear.gank.R;
import com.brilliantbear.gank.bean.NewsEntity;
import com.brilliantbear.gank.view.WebActivity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by cx.lian on 2016/4/28.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private List<NewsEntity> news;

    public NewsAdapter(Context context, List<NewsEntity> news) {
        this.context = context;
        this.news = news;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final NewsEntity entity = news.get(position);
        holder.tvWho.setText(entity.getWho());
        holder.tvTitle.setText(entity.getDesc());
        holder.tvType.setText(entity.getType());
        holder.tvDate.setText(entity.getPublishedAt().substring(0, 10));

        setTypeBackground(holder.tvType);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebActivity.gotoWebActivity(context, entity.getUrl(), entity.getDesc());
            }
        });
    }

    private void setTypeBackground(TextView tvType) {
        String content = tvType.getText().toString().trim();
        if (TextUtils.equals(content, "Android")) {
            tvType.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.tag_bg_bule));
        } else if (TextUtils.equals(content, "iOS")) {
            tvType.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.tag_bg_pink));
        } else if (TextUtils.equals(content, "休息视频")) {
            tvType.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.tag_bg_green));
        } else if (TextUtils.equals(content, "拓展资源")) {
            tvType.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.tag_bg_grey));
        } else if (TextUtils.equals(content, "前端")) {
            tvType.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.tag_bg_purple));
        } else if (TextUtils.equals(content, "瞎推荐")) {
            tvType.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.tag_bg_orange));
        } else if (TextUtils.equals(content, "App")) {
            tvType.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.tag_bg_yellow));
        } else {
            tvType.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.tag_bg_red));
        }
    }

    @Override
    public int getItemCount() {
        return news == null ? 0 : news.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView tvType;
        TextView tvTitle;
        TextView tvDate;
        TextView tvWho;

        public NewsViewHolder(View itemView) {
            super(itemView);
            tvType = ButterKnife.findById(itemView, R.id.tv_type);
            tvTitle = ButterKnife.findById(itemView, R.id.tv_title);
            tvDate = ButterKnife.findById(itemView, R.id.tv_date);
            tvWho = ButterKnife.findById(itemView, R.id.tv_who);
        }
    }
}
