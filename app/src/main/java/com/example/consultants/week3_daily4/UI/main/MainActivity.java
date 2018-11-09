package com.example.consultants.week3_daily4.UI.main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.consultants.week3_daily4.R;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    MainPresenter mainPresenter;
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter();

    }

    @Override
    public void onStart() {
        super.onStart();
        mainPresenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mainPresenter.detachView();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

    public void onPlayMusic(View view) {
        mainPresenter.setContext(getApplicationContext());
        Log.d(TAG, "onPlayMusic: "+getApplicationContext());
        mainPresenter.playMusic();
    }

    public void onStopMusic(View view) {
        mainPresenter.stopMusic();
    }
}
