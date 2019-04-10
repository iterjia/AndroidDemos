package com.demo.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mButton;
    private HandlerThread mSubThread;
    private Handler mSubHandler;

    private Handler mMainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextView.setText("World");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.tv_tip);
        mButton = findViewById(R.id.btn1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubHandler.sendEmptyMessage(0);
            }
        });

        startHandlerThread();
    }

    private void startHandlerThread() {
        mSubThread = new HandlerThread("testHandler");
        mSubThread.start();

        mSubHandler = new Handler(mSubThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                // can not refresh ui
                super.handleMessage(msg);
                try {
                    Thread.sleep(1000);
                    mMainHandler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSubHandler.removeCallbacksAndMessages(null);
        mMainHandler.removeCallbacksAndMessages(null);
        mSubThread.quit();
    }
}
