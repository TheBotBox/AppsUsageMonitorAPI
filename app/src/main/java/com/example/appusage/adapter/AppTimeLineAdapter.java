package com.example.appusage.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appusage.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bot.box.appusage.model.TimeLine;
import bot.box.appusage.utils.UsageUtils;

/**
 * Created by Anon on 12,March,2019
 */
public class AppTimeLineAdapter extends RecyclerView.Adapter<AppTimeLineAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<List<TimeLine>> mDividedList;
    private String[] paletteArray;

    public AppTimeLineAdapter(Context c) {
        this.inflater = LayoutInflater.from(c);
        this.context = c;
        paletteArray = c.getResources().getStringArray(R.array.array_palette);
    }

    public void updateData(List<List<TimeLine>> data) {
        this.mDividedList = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_timeline, parent, false);
        return new MyViewHolder(view);
    }

    private String readDate(long eventTime) {
        return new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date(eventTime));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        List<TimeLine> item = mDividedList.get(position);

        long valuestart = 0, valueEnd = 0;
        if (item.size() >= 1 && item.get(0) != null) {
            String startTime = readDate(item.get(0).mEventTime);
            valuestart = item.get(0).mEventTime;
            holder.launchTime.setText(startTime);
        } else {
            holder.launchTime.setText(context.getResources().getString(R.string.in_use));
        }

        if (item.size() >= 2 && item.get(1) != null) {
            String endTime = readDate(item.get(1).mEventTime);
            valueEnd = item.get(1).mEventTime;
            holder.exitTime.setText(endTime);
        } else {
            holder.exitTime.setText(context.getResources().getString(R.string.in_use));
        }

        if (valuestart > 0 && valueEnd > 0) {
            long container = valueEnd - valuestart;
            holder.usageTime.setText(UsageUtils.humanReadableMillis(container));
        }

        holder.palette.setBackgroundColor(Color.parseColor(paletteArray[new Random().nextInt(paletteArray.length)]));
    }

    @Override
    public int getItemCount() {
        return this.mDividedList != null ? this.mDividedList.size() : 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView launchTime, exitTime, usageTime;
        LinearLayout mLayout;
        View dotted_line;
        LinearLayout palette;

        MyViewHolder(View itemView) {
            super(itemView);
            launchTime = itemView.findViewById(R.id.launch_time);
            mLayout = itemView.findViewById(R.id.layout);
            exitTime = itemView.findViewById(R.id.exit_time);
            usageTime = itemView.findViewById(R.id.usage_time);
            dotted_line = itemView.findViewById(R.id.dotted_line);
            palette = itemView.findViewById(R.id.palette);
        }
    }

}
