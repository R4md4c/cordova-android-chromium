package com.swipentap.chromium;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;

import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.content.browser.ContentView;
import org.chromium.content.browser.ContentViewCore;
import org.chromium.content.browser.ContentViewRenderView;
import org.chromium.content.browser.ContentViewStatics;
import org.chromium.content.browser.LoadUrlParams;
import org.chromium.content.browser.NavigationEntry;
import org.chromium.content.browser.NavigationHistory;
import org.chromium.content_shell.Shell;
import org.chromium.ui.WindowAndroid;

/**
 * Created by macbookpro on 10/29/13.
 */
@JNINamespace("content")
public class CordovaChromiumView extends Shell {

    private String mStartupUrl;
    private ChromiumSettings mSettings = null;


    private ChromiumWebViewClient mWebViewClient;
    private ChromiumWebChromeClient mWebChromeClient;

    public CordovaChromiumView(Context context) {
        super(context, null);
    }

    /**
     * Constructor for inflating via XML.
     */
    public CordovaChromiumView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CordovaChromiumView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
    }

    public CordovaChromiumView(Context context, AttributeSet attrs, int defStyle, boolean privateBrowsing) {
        super(context, attrs);
    }


    public ChromiumSettings getSettings() {
        if (mSettings == null) {
            mSettings = new ChromiumSettingsProxy(this);
        }
        return mSettings;
    }

    public void addJavascriptInterface(Object object, String name) {
        this.getContentView().getContentViewCore().addJavascriptInterface(object, name);
    }

    public void setWebViewClient(ChromiumWebViewClient client) {
        mWebViewClient = client;
    }

    public void setWebChromeClient(ChromiumWebChromeClient client) {
        mWebChromeClient = client;
    }

    public void setInitialScale(int scale) {

    }

    public Shell getActiveShell() {
        return this;
    }

    public void loadUrl(String url) {
        mStartupUrl = url;
        if (url.startsWith("javascript:")) {
            this.getContentView().evaluateJavaScript(url);
        } else {
            this.getContentView().loadUrl(new LoadUrlParams(url));
        }
    }

    public void stopLoading() {
        this.getContentView().stopLoading();
    }

    public boolean canGoBack() {
        return this.getContentView().canGoBack();
    }

    public void goBack() {
        this.getContentView().goBack();
    }

    public void clearHistory() {
        this.getContentView().clearHistory();
    }

    public void pauseTimers() {
        ContentViewStatics.setWebKitSharedTimersSuspended(true);
    }

    public void resumeTimers() {
        ContentViewStatics.setWebKitSharedTimersSuspended(false);
    }

    public void clearCache(boolean includeDiskFiles) {

    }

    public String getUrl() {
        return this.getContentView().getUrl();
    }

    @SuppressWarnings("unused")
    @CalledByNative
    protected void onLoadProgressChanged(double progress) {
        super.onLoadProgressChanged(progress);
        if (mWebViewClient != null) {
            if (progress == 0) {
                NavigationHistory history = this.getContentView().getContentViewCore().getNavigationHistory();
                NavigationEntry entry = null;
                int currentIndex = history.getCurrentEntryIndex();
                if (currentIndex > -1) {
                    entry = history.getEntryAtIndex(history.getCurrentEntryIndex());
                }
                mWebViewClient.onPageStarted(this, getUrl(), entry != null ? entry.getFavicon() : null);
            } else if (progress == 1.0 ) {
                mWebViewClient.onPageFinished(this, getUrl());
            }
        }

        if (mWebChromeClient != null) {
            mWebChromeClient.onProgressChanged(this, (int) (progress * 100));
        }
    }
}
