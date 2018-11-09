package com.example.consultants.week3_daily4.UI.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.consultants.week3_daily4.UI.Services.Foreground_service;

public class MainPresenter implements MainContract.Presenter {

    public static String STARTFOREGROUND_ACTION = "com.example.consultants.week3_daily4.UI.Services.startforeground";
    public static String STOPFOREGROUND_ACTION = "com.example.consultants.week3_daily4.UI.Services.stopforeground";

    private static final String TAG = MainPresenter.class.getSimpleName() + "_TAG";
    MainContract.View view;
    private Context context;

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public void setContext(Context context){
        this.context = context;
    }

    @Override
    public void playMusic() {

        Intent startIntent = new Intent(context, Foreground_service.class);
        startIntent.setAction(STARTFOREGROUND_ACTION);
        context.startService(startIntent);
        Log.d(TAG, "playMusic:  mainContext.startService(startIntent);");

    }

    @Override
    public void stopMusic() {
        Log.d(TAG, "onClick: ");
        Intent stopIntent = new Intent(context, Foreground_service.class);
        stopIntent.setAction(STOPFOREGROUND_ACTION);
        context.startService(stopIntent);

    }
}
