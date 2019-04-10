package com.demo.js;

import android.graphics.Bitmap;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class CustomWebChromeClient extends WebChromeClient {
    public CustomWebChromeClient() {
        super();
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        Log.d("xwj", "CustomWebChromeClient onProgressChanged, newProgress = " + newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        Log.d("xwj", "CustomWebChromeClient onReceivedTitle, title = " + title);
    }

    @Override
    public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
        super.onReceivedTouchIconUrl(view, url, precomposed);
        Log.d("xwj", "CustomWebChromeClient onReceivedTouchIconUrl, url = " + url + ", precomposed = " + precomposed);
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        super.onShowCustomView(view, callback);
        Log.d("xwj", "CustomWebChromeClient onShowCustomView");
    }

    @Override
    public void onHideCustomView() {
        super.onHideCustomView();
        Log.d("xwj", "CustomWebChromeClient onHideCustomView");
    }

    @Override
    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
        Log.d("xwj", "CustomWebChromeClient onCreateWindow, isDialog = " + isDialog + ", isUserGesture = " + isUserGesture + ", resultMsg = " + resultMsg);
        return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
    }

    @Override
    public void onCloseWindow(WebView window) {
        super.onCloseWindow(window);
        Log.d("xwj", "CustomWebChromeClient onCloseWindow");
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        Log.d("xwj", "CustomWebChromeClient onJsAlert, url = " + url + ", message = " + message + ", result = " + result);
        return super.onJsAlert(view, url, message, result);
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        Log.d("xwj", "CustomWebChromeClient onJsConfirm, url = " + url + ", message = " + message + ", result = " + result);
        return super.onJsConfirm(view, url, message, result);
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        Log.d("xwj", "CustomWebChromeClient onJsPrompt, url = " + url + ", message = " + message + ", result = " + result);
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    @Override
    public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
        Log.d("xwj", "CustomWebChromeClient onJsBeforeUnload, url = " + url + ", message = " + message + ", result = " + result);
        return super.onJsBeforeUnload(view, url, message, result);
    }

    @Override
    public void onPermissionRequest(PermissionRequest request) {
        super.onPermissionRequest(request);
    }
}
