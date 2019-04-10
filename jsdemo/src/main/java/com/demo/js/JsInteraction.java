package com.demo.js;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JsInteraction {
    private Context mContext;
    public JsInteraction(Context context) {
        mContext = context;
    }

    @JavascriptInterface
    public void callNative() {
        Toast.makeText(mContext, "hello js", Toast.LENGTH_SHORT).show();
    }
}
