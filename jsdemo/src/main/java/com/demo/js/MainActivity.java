package com.demo.js;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.demo.j2v8.J2V8Activity;
import com.eclipsesource.v8.V8;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private WebView mWebView;
    private Button mJ2V8Button;
    private V8 mV8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        mWebView = findViewById(R.id.webview);
        WebSettings settings = mWebView.getSettings();
        // 设置与Js交互的权限
        settings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        String userAgent = WebSettings.getDefaultUserAgent(this);
        Log.d("xwj", "userAgent = " + userAgent);

        mWebView.addJavascriptInterface(new JsInteraction(this), "test");
        mWebView.setWebViewClient(new CustomWebViewClient());
        mWebView.setWebChromeClient(new CustomWebChromeClient());
        mWebView.loadUrl("file:///android_asset/demo.html");

        mV8 = V8.createV8Runtime();
        int result = mV8.executeIntegerScript(""
                + "var hello = 'hello, ';\n"
                + "var world = 'world!';\n"
                + "hello.concat(world).length;\n");
        Log.d("xwj", "JS result = " + result);

        mJ2V8Button = findViewById(R.id.btn_j2v8);
        mJ2V8Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mV8.executeScript("var hello = 'hello'");
//                mWebView.evaluateJavascript("javascript:callJs()", new ValueCallback<String>() {
//                    @Override
//                    public void onReceiveValue(String value) {
//                        Log.d("xwj", "evaluateJavascript value = " + value);
//                    }
//                });
                Intent intent = new Intent();
                intent.setClass(mContext, J2V8Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mV8.release();
    }
}
