package com.demo.job;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class TestJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.w("xwj", "TestService onStartJob " + jobParameters.getExtras().getString("tag"));
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.w("xwj", "TestService onStopJob");
        return false;
    }
}
