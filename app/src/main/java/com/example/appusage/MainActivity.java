package com.example.appusage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import bot.box.appusage.contract.UsageContracts;
import bot.box.appusage.handler.Monitor;
import bot.box.appusage.model.AppData;
import bot.box.appusage.utils.Duration;
import bot.box.appusage.utils.UsageUtils;

public class MainActivity extends AppCompatActivity implements UsageContracts.View, AdapterView.OnItemSelectedListener {

    private RecyclerView mRecycler;
    private AppAdapter mAdapter;

    private TextView tvUsageStatus, tv_totalUsage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Monitor.hasUsagePermission()) {
            Monitor.scan().getAppLists(this).fetchFor(Duration.TODAY);
            init();
        } else {
            Monitor.requestUsagePermission();
        }
    }

    private void init() {
        mRecycler = findViewById(R.id.recycler);
        tvUsageStatus = findViewById(R.id.tv_UsageStatus);
        mAdapter = new AppAdapter(this);
        tv_totalUsage = findViewById(R.id.tv_totalUsage);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setVisibility(View.VISIBLE);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.duration));
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setAdapter(mAdapter);
    }

    /**
     * @param usageData   list of application that has been within the duration for which query has been made.
     * @param mTotalUsage a sum total of the usage by each and every app with in the request duration.
     * @param duration    the same duration for which query has been made i.e.fetchFor(Duration...)
     */

    @Override
    public void getUsageData(List<AppData> usageData, long mTotalUsage, int duration) {
        if (usageData.size() > 0) {
            tv_totalUsage.setText("Total Usage is : " + UsageUtils.humanReadableMillis(mTotalUsage));
            mRecycler.setVisibility(View.VISIBLE);
            tvUsageStatus.setVisibility(View.GONE);
            mAdapter.updateData(usageData);
        } else {
            mRecycler.setVisibility(View.GONE);
            tvUsageStatus.setVisibility(View.VISIBLE);
            tvUsageStatus.setText(getResources().getString(R.string.not_active) + Duration.getCurrentReableDay(duration));
        }
    }


    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Monitor.scan().getAppLists(this).fetchFor(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
