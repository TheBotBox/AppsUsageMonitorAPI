package com.example.appusage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import bot.box.appusage.model.AppData;
import bot.box.appusage.utils.UsageUtils;

/**
 * Created by Anon on 16,March,2019
 */
public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {


    private List<AppData> mData;
    private WeakReference<Context> mContext;

    AppAdapter(Context mContext) {
        this.mContext = new WeakReference(mContext);
    }

    void updateData(List<AppData> data) {
        mData = data;
        notifyDataSetChanged();
    }

    private AppData getUsageByPosition(int position) {
        if (mData.size() > position) {
            return mData.get(position);
        }
        return null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_app_usage, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppData item = getUsageByPosition(position);

        holder.mName.setText(item.mName);

        holder.mUsage.setText(UsageUtils.humanReadableMillis(item.mUsageTime));

        holder.mTime.setText(String.format(Locale.getDefault(),
                "%s · %d %s ·",
                new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()).format(new Date(item.mEventTime)),
                item.mCount,
                mContext.get().getResources().getQuantityString(R.plurals.times_launched, item.mCount))
        );

        Glide.with(this.mContext.get())
                .load(UsageUtils.parsePackageIcon(item.mPackageName, R.mipmap.ic_launcher)).
                transition(new DrawableTransitionOptions().crossFade())
                .into(holder.mIcon);
    }

    @Override
    public int getItemCount() {
        if (mData != null)
            return mData.size();
        else return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout parent;
        private TextView mName;
        private TextView mUsage;
        private TextView mTime;
        private ImageView mIcon;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            mName = itemView.findViewById(R.id.app_name);
            mUsage = itemView.findViewById(R.id.app_usage);
            mTime = itemView.findViewById(R.id.app_time);
            mIcon = itemView.findViewById(R.id.app_image);
        }
    }
}
