package com.demo;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.demo.com.demo.network.NetworkTest;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

//        NetworkTest.printInfo(mContext);

        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.hmct.config.provider");
        Log.d("xwj", "uri = " + uri);
        resolver.call(uri, "initConfigTable", "com.hmct.aitouch:show_ner", null);
        Bundle bundle1 = resolver.call(uri, "getConfig", "com.hmct.aitouch:show_card", null);
        Log.d("xwj", "bundle = " + bundle1.getInt("FLAG"));

        Bundle bundle2 = resolver.call(uri, "isAvailable", "com.hmct.aitouch:show_ner", null);
        Log.d("xwj", "bundle = " + bundle2.getInt("FLAG"));
    }
}
