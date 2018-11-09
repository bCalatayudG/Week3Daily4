package com.example.consultants.week3_daily4.UI.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.example.consultants.week3_daily4.R;
import com.example.consultants.week3_daily4.UI.main.MainActivity;



public class Foreground_service extends Service {

    MediaPlayer mediaPlayer;
    private static final String TAG = Foreground_service.class.getSimpleName() + "_TAG";

    public static String MAIN_ACTION = "com.example.consultants.week3_daily4.UI.Services.action.main";
    public static String PREV_ACTION = "com.example.consultants.week3_daily4.UI.Services.prev";
    public static String PLAY_ACTION = "com.example.consultants.week3_daily4.UI.Services.action.play";
    public static String NEXT_ACTION = "com.example.consultants.week3_daily4.UI.Services.action.next";
    public static String STOP_ACTION = "com.example.consultants.week3_daily4.UI.Services.stop";
    public static String STARTFOREGROUND_ACTION = "com.example.consultants.week3_daily4.UI.Services.startforeground";
    public static String STOPFOREGROUND_ACTION = "com.example.consultants.week3_daily4.UI.Services.stopforeground";
    public static int FOREGROUND_SERVICE = 101;

    public static final String CHANNEL_ID = "channel";

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.songone);

    }

    @Override
    public void onDestroy() {
        mediaPlayer.release();
        super.onDestroy();
    }
    

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        if (intent.getAction().equals(STARTFOREGROUND_ACTION)) {
            Log.d(TAG, "Received Start Foreground Intent ");
            Intent notificationIntent = new Intent(this, MainActivity.class);
            notificationIntent.setAction(MAIN_ACTION);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);

            Intent previousIntent = new Intent(this, Foreground_service.class);
            previousIntent.setAction(PREV_ACTION);
            PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                    previousIntent, 0);

            Intent playIntent = new Intent(this, Foreground_service.class);
            playIntent.setAction(PLAY_ACTION);
            PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                    playIntent, 0);

            Intent nextIntent = new Intent(this, Foreground_service.class);
            nextIntent.setAction(NEXT_ACTION);
            PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                    nextIntent, 0);

            Intent stopIntent = new Intent(this, Foreground_service.class);
            stopIntent.setAction(STOP_ACTION);
            PendingIntent pStopIntent = PendingIntent.getService(this, 0,
                    stopIntent, 0);

            Notification notification = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                notification = new Notification.Builder(this, CHANNEL_ID)
                        .setContentTitle("Music Player")
                        .setTicker("Music Player")
                        .setContentText("Music")
                        .setSmallIcon(R.drawable.ic_android)
                        .setContentIntent(pendingIntent)
                        .setOngoing(true)
                        .addAction(android.R.drawable.ic_media_previous,
                                "Previous", ppreviousIntent)
                        .addAction(R.drawable.ic_play, "Play",
                                pplayIntent)
                        .addAction(android.R.drawable.ic_media_next, "Next",
                                pnextIntent)
                        .addAction(R.drawable.ic_stop, "Stop",
                                pStopIntent).build();
                mediaPlayer.start();
            }
            startForeground(FOREGROUND_SERVICE,
                    notification);
        } else if (intent.getAction().equals(PREV_ACTION)) {
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.songtwo);
            mediaPlayer.start();
            Log.d(TAG, "Clicked Previous");
        } else if (intent.getAction().equals(PLAY_ACTION)) {
            Log.d(TAG, "Clicked Play");
        } else if (intent.getAction().equals(NEXT_ACTION)) {
            Log.d(TAG, "Clicked Next");
        } else if (intent.getAction().equals(NEXT_ACTION)) {
            mediaPlayer.release();
            Log.d(TAG, "Clicked Stop");
        } else if (intent.getAction().equals(
                STOPFOREGROUND_ACTION)) {
            Log.d(TAG, "Received Stop Foreground Intent");
            stopForeground(true);
            stopSelf();
        }
       return START_STICKY;
    }


    //@Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
