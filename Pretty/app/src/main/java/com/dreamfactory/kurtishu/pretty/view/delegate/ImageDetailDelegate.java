package com.dreamfactory.kurtishu.pretty.view.delegate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;

import com.dreamfactory.kurtishu.pretty.R;
import com.dreamfactory.kurtishu.pretty.event.NavigatorEvent;
import com.dreamfactory.kurtishu.pretty.view.activity.ImageViewerActivity;
import com.dreamfactory.kurtishu.pretty.widget.CustomWebView;

import java.net.URI;

import de.greenrobot.event.EventBus;

/**
 * Created by kurtishu on 12/1/15.
 */
public class ImageDetailDelegate extends BaseAppDelegate implements Toolbar.OnClickListener {

    private CustomWebView mWebView;
    private String imageUrl;
    private String title;
    private int id;
    private Context mContext;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_image_detail;
    }

    @Override
    public void initViews(Context context, Intent mIntent) {
        mWebView = get(R.id.image_webview);

        Toolbar toolbar = get(R.id.toolbar);
        toolbar.setTitle(R.string.title_image_detail);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back);
        toolbar.setNavigationOnClickListener(this);

        imageUrl = mIntent.getStringExtra("img");
        title = mIntent.getStringExtra("title");
        id = mIntent.getExtras().getInt("id");
        mContext = context;

        mWebView.setWebViewCallback(callback);
        WebSettings webSettings = mWebView.getSettings();
        //webSettings.setSavePassword(false);
        //webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);

        mWebView.loadUrl("file:///android_asset/index.html");

    }

    private CustomWebView.WebViewCallback callback = new CustomWebView.WebViewCallback() {

        @Override
        public void loadingUrl(URI uri) {
          Intent intent = new Intent(mContext, ImageViewerActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("img", imageUrl);
            intent.putExtra("title", title);
            mContext.startActivity(intent);
        }

        @Override
        public void pageStarted() {
            String javascript = String.format("javascript:loadImg('%s', '%s')", imageUrl, title);
            mWebView.loadUrl(javascript);
        }
    };

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new NavigatorEvent(null, true));
    }
}
