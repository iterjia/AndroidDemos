package com.demo.js;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CustomWebViewClient extends WebViewClient {
    public CustomWebViewClient() {
        super();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Log.d("xwj", "CustomWebViewClient shouldOverrideUrlLoading, url = " + request.getUrl() + ", message = " + request.getMethod());
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.d("xwj", "CustomWebViewClient onPageStarted, url = " + url);
    }

    @Override
    public void onPageFinished(final WebView view, String url) {
        super.onPageFinished(view, url);
        Log.d("xwj", "CustomWebViewClient onPageFinished, url = " + url);
//        view.loadUrl("javascript:callJs()");
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
        Log.d("xwj", "CustomWebViewClient onLoadResource, url = " + url);
    }

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        Log.d("xwj", "CustomWebViewClient shouldInterceptRequest, url = " + request.getUrl());
        return super.shouldInterceptRequest(view, request);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        Log.d("xwj", "CustomWebViewClient onReceivedError, error = " + error.getDescription());
    }

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
        Log.d("xwj", "CustomWebViewClient onReceivedHttpError, errorResponse = " + errorResponse.getReasonPhrase());
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        Log.d("xwj", "CustomWebViewClient onReceivedSslError, error = " + error.toString());
    }

    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        return super.shouldOverrideKeyEvent(view, event);
    }
}
