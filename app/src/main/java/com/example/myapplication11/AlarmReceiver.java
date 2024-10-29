package com.example.myapplication11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    private MediaPlayer mediaPlayer;

    @Override
    public void onReceive(Context context, Intent intent) {
        mediaPlayer = MediaPlayer.create(context, R.raw.alarm_sound);
        mediaPlayer.start();

        Toast.makeText(context, "Alarm is ringing!", Toast.LENGTH_LONG).show();
    }

    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}