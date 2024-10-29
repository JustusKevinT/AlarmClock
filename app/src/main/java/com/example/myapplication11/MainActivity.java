package com.example.myapplication11;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;
    private TimePicker alarmTimePicker;
    private PendingIntent pendingIntent;
    private ToggleButton alarmToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);

        setAlarmTime(alarmTimePicker.getHour(), alarmTimePicker.getMinute());

        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean checked) {
                if (checked) {
                    setAlarm(alarmTimePicker.getHour(), alarmTimePicker.getMinute());
                } else {
                    cancelAlarm();
                }
            }
        });
    }

    private void setAlarm(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, pendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        Toast.makeText(this, "Alarm is set!", Toast.LENGTH_LONG).show();
    }

    private void cancelAlarm() {
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm is cancelled!", Toast.LENGTH_LONG).show();
    }

    private void setAlarmTime(int hour, int minute) {
        alarmTimePicker.setHour(hour);
        alarmTimePicker.setMinute(minute);
    }
}