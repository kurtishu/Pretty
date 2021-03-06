/*
 *
 *  * Copyright 2016 Kurtis <kurtis_hu@hotmail.com>
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.dreamfactory.kurtishu.pretty.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.dreamfactory.kurtishu.pretty.R;

import java.net.URI;

/**
 * Author：kurtishu on 2/15/16
 * Eevery one should have a dream, what if one day it comes true!
 */
public class CustomWebView extends WebView {

    private static final String SCHEMA = "pretty";

    private WebViewCallback callback;
    private ProgressBar mProgressbar;

    public CustomWebView(Context context) {
        super(context);
        init(context);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        mProgressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        mProgressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 4, 0, 0));
        mProgressbar.setMax(100);
        mProgressbar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar_layer));
        addView(mProgressbar);

        setWebChromeClient(new WebChromeClient());

        this.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                URI uri = URI.create(url);
                String schema = uri.getScheme();
                if (SCHEMA.equals(schema)) {
                    if (null != callback) {
                        callback.loadingUrl(uri);
                    }
                    return false;
                }

                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                if (null != callback) {
                    callback.pageStarted();
                }
            }
        });
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressbar.setVisibility(GONE);
            } else {
                if (mProgressbar.getVisibility() == GONE)
                    mProgressbar.setVisibility(VISIBLE);
                mProgressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) mProgressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        mProgressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void setWebViewCallback(WebViewCallback callback) {
        this.callback = callback;
    }


    public static interface WebViewCallback {

        void loadingUrl(URI uri);

        void pageStarted();
    }
}
