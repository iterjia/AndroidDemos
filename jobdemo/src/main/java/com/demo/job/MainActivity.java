package com.demo.job;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    AlarmManager mAlarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The Alarm Manager holds a CPU wake lock as long as the alarm receiver's onReceive() method is executing.
        // This guarantees that the phone will not sleep until you have finished handling the broadcast.
        // Once onReceive() returns, the Alarm Manager releases this wake lock.
        mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        addAlarm("alarm1", 5000, 100);
//        addAlarm("alarm2", 10000, 101);
    }

    private void addAlarm(String tag, int delayMills, int reqCode) {
        // The Alarm Manager is intended for cases where you want to have your application code
        // run at a specific time, even if your application is not currently running.
        // For normal timing operations (ticks, timeouts, etc) it is easier and much more efficient
        // to use Handler.
        long curMills = System.currentTimeMillis();
        Intent intent = new Intent(this, TestAlarmReceiver.class);
        intent.putExtra("tag", tag);
        intent.putExtra("mills", curMills);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, reqCode, intent, 0);
//        mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, curMills, AlarmManager.INTERVAL_HOUR, pendingIntent);
        mAlarmManager.setExact(AlarmManager.RTC_WAKEUP, curMills + delayMills, pendingIntent);
//        mAlarmManager.set(AlarmManager.RTC_WAKEUP, curMills + delayMills, pendingIntent);
//        mAlarmManager.setExact(AlarmManager.RTC, curMills + delayMills, pendingIntent);//delivery until the device is wake up
//        mAlarmManager.set(AlarmManager.RTC, curMills + delayMills, pendingIntent);
//        mAlarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + delayMills, pendingIntent);
        Log.w("xwj", "addAlarm " + tag + " delay " + delayMills + "ms");
    }

    @SuppressLint("InvalidWakeLockTag")
    public void acquireWakeLock() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock lock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK
                | PowerManager.ACQUIRE_CAUSES_WAKEUP, "com.hmct.scheduler");
        if (lock != null) {
            lock.acquire(1000);
        }
        if (lock != null) {
            lock.release();
        }
    }
}
