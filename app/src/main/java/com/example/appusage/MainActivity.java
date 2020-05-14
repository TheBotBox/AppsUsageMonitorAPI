package com.example.appusage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.time.chrono.MinguoChronology;
import java.util.List;

import bot.box.appusage.contract.PackageContracts;
import bot.box.appusage.contract.UsageContracts;
import bot.box.appusage.handler.Monitor;
import bot.box.appusage.handler.UsageGenerator;
import bot.box.appusage.model.AppData;
import bot.box.appusage.presenter.UsagePresenter;
import bot.box.appusage.utils.Duration;
import bot.box.appusage.utils.UsageUtils;

public class MainActivity extends AppCompatActivity implements UsageContracts.View,
        PackageContracts.View, AdapterView.OnItemSelectedListener {

    private AppAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 100);
        }

        Monitor.scan().queryFor(this).whichPackage("com.whatsapp").
                fetchFor(Duration.TODAY);//check usage for specific package.
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
        RecyclerView mRecycler = findViewById(R.id.recycler);
        mAdapter = new AppAdapter(this);

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Monitor.scan().getAppLists(this).fetchFor(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }


    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
    }

    /**
     * @param usageData   list of application that has been within the duration for which query has been made.
     * @param mTotalUsage a sum total of the usage by each and every app with in the request duration.
     * @param duration    the same duration for which query has been made i.e.fetchFor(Duration...)
     */

    @Override
    public void getUsageData(List<AppData> usageData, long mTotalUsage, int duration) {
        mAdapter.updateData(usageData);
    }

    @Override
    public void getUsageForPackage(String mPackage, long mTotalUsage, int duration) {
        System.out.println("Usage for " + mPackage + " is : " + UsageUtils.humanReadableMillis(mTotalUsage)
                + " for " + Duration.getCurrentReadableDay(duration));
    }
}
