package com.demo.job;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.util.Log;

public class TestAlarmReceiver extends BroadcastReceiver {

    // If your alarm receiver called Context.startService(),
    // it is possible that the phone will sleep before the requested service is launched.
    // To prevent this, your BroadcastReceiver and Service
    // will need to implement a separate wake lock policy to ensure
    // that the phone continues running until the service becomes available.
    @Override
    public void onReceive(Context context, Intent intent) {
        String tag = intent.getStringExtra("tag");
        long mills = intent.getLongExtra("mills", 0);
        long pastMills = System.currentTimeMillis() - mills;
//        Log.w("xwj", tag + " : " + pastMills);

        // While a job is running, the system holds a wakelock on behalf of your app.
        // For this reason, you do not need to take any action to guarantee
        // that the device stays awake for the duration of the job.
        int jobId = (int) pastMills;
        PersistableBundle bundle = new PersistableBundle();
        bundle.putString("tag", tag);
        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(context, TestJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, componentName);
        //setBackoffCriteria:job执行出错之后的重试时间和策略
        //setPeriodic最小周期是15分钟，设置值小于15分钟不会生效，可以查看JobInfo类源码
        //BACKOFF_POLICY_EXPONENTIAL:retry_time(current_time, num_failures) = current_time + initial_backoff_millis * 2 ^ (num_failures - 1), num_failures >= 1
        //BACKOFF_POLICY_LINEAR:retry_time(current_time, num_failures) = current_time + initial_backoff_millis * num_failures, num_failures >= 1
        builder.setExtras(bundle)
                .setPeriodic(5000)
//                .setMinimumLatency(5000)
//                .setOverrideDeadline(10000)
                .setBackoffCriteria(30 * 1000, JobInfo.BACKOFF_POLICY_LINEAR)
                .setRequiresBatteryNotLow(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        int result = scheduler.schedule(builder.build());
        Log.w("xwj", tag + " onReceive schedule result = " + result);
//        scheduler.cancelAll();
    }
}
