package com.swipentap.chromium;

import android.webkit.WebSettings;

import org.chromium.content.browser.ContentSettings;

/**
 * Created by macbookpro on 10/30/13.
 */
public class ChromiumSettingsProxy extends ChromiumSettings {

    private CordovaChromiumView mChromiumView;
    private ContentSettings mContentSettings;

    public ChromiumSettingsProxy(CordovaChromiumView chromiumView) {
        mChromiumView = chromiumView;
        mContentSettings = chromiumView.getContentView().getContentViewCore().getContentSettings();
    }
    @Override
    public void setSupportZoom(boolean support) {

    }

    @Override
    public boolean supportZoom() {
        return false;
    }

    @Override
    public void setMediaPlaybackRequiresUserGesture(boolean require) {

    }

    @Override
    public boolean getMediaPlaybackRequiresUserGesture() {
        return false;
    }

    @Override
    public void setBuiltInZoomControls(boolean enabled) {

    }

    @Override
    public boolean getBuiltInZoomControls() {
        return false;
    }

    @Override
    public void setDisplayZoomControls(boolean enabled) {

    }

    @Override
    public boolean getDisplayZoomControls() {
        return false;
    }

    @Override
    public void setAllowFileAccess(boolean allow) {

    }

    @Override
    public boolean getAllowFileAccess() {
        return true;
    }

    @Override
    public void setAllowContentAccess(boolean allow) {

    }

    @Override
    public boolean getAllowContentAccess() {
        return true;
    }

    @Override
    public void setLoadWithOverviewMode(boolean overview) {

    }

    @Override
    public boolean getLoadWithOverviewMode() {
        return false;
    }

    @Override
    public void setSaveFormData(boolean save) {

    }

    @Override
    public boolean getSaveFormData() {
        return true;
    }

    @Override
    public void setSavePassword(boolean save) {

    }

    @Override
    public boolean getSavePassword() {
        return true;
    }

    @Override
    public void setTextZoom(int textZoom) {

    }

    @Override
    public int getTextZoom() {
        return 0;
    }

    @Override
    public void setDefaultZoom(WebSettings.ZoomDensity zoom) {

    }

    @Override
    public WebSettings.ZoomDensity getDefaultZoom() {
        return null;
    }

    @Override
    public void setLightTouchEnabled(boolean enabled) {

    }

    @Override
    public boolean getLightTouchEnabled() {
        return false;
    }

    @Override
    public void setUseWideViewPort(boolean use) {

    }

    @Override
    public boolean getUseWideViewPort() {
        return false;
    }

    @Override
    public void setSupportMultipleWindows(boolean support) {

    }

    @Override
    public boolean supportMultipleWindows() {
        return false;
    }

    @Override
    public void setLayoutAlgorithm(WebSettings.LayoutAlgorithm l) {

    }

    @Override
    public WebSettings.LayoutAlgorithm getLayoutAlgorithm() {
        return null;
    }

    @Override
    public void setStandardFontFamily(String font) {

    }

    @Override
    public String getStandardFontFamily() {
        return null;
    }

    @Override
    public void setFixedFontFamily(String font) {

    }

    @Override
    public String getFixedFontFamily() {
        return null;
    }

    @Override
    public void setSansSerifFontFamily(String font) {

    }

    @Override
    public String getSansSerifFontFamily() {
        return null;
    }

    @Override
    public void setSerifFontFamily(String font) {

    }

    @Override
    public String getSerifFontFamily() {
        return null;
    }

    @Override
    public void setCursiveFontFamily(String font) {

    }

    @Override
    public String getCursiveFontFamily() {
        return null;
    }

    @Override
    public void setFantasyFontFamily(String font) {

    }

    @Override
    public String getFantasyFontFamily() {
        return null;
    }

    @Override
    public void setMinimumFontSize(int size) {

    }

    @Override
    public int getMinimumFontSize() {
        return 0;
    }

    @Override
    public void setMinimumLogicalFontSize(int size) {

    }

    @Override
    public int getMinimumLogicalFontSize() {
        return 0;
    }

    @Override
    public void setDefaultFontSize(int size) {

    }

    @Override
    public int getDefaultFontSize() {
        return 0;
    }

    @Override
    public void setDefaultFixedFontSize(int size) {

    }

    @Override
    public int getDefaultFixedFontSize() {
        return 0;
    }

    @Override
    public void setLoadsImagesAutomatically(boolean flag) {

    }

    @Override
    public boolean getLoadsImagesAutomatically() {
        return true;
    }

    @Override
    public void setBlockNetworkImage(boolean flag) {

    }

    @Override
    public boolean getBlockNetworkImage() {
        return false;
    }

    @Override
    public void setBlockNetworkLoads(boolean flag) {

    }

    @Override
    public boolean getBlockNetworkLoads() {
        return false;
    }

    @Override
    public void setJavaScriptEnabled(boolean flag) {

    }

    @Override
    public void setAllowUniversalAccessFromFileURLs(boolean flag) {

    }

    @Override
    public void setAllowFileAccessFromFileURLs(boolean flag) {

    }

    @Override
    public void setPluginState(WebSettings.PluginState state) {

    }

    @Override
    public void setDatabasePath(String databasePath) {

    }

    @Override
    public void setGeolocationDatabasePath(String databasePath) {

    }

    @Override
    public void setAppCacheEnabled(boolean flag) {

    }

    @Override
    public void setAppCachePath(String appCachePath) {

    }

    @Override
    public void setAppCacheMaxSize(long appCacheMaxSize) {

    }

    @Override
    public void setDatabaseEnabled(boolean flag) {

    }

    @Override
    public void setDomStorageEnabled(boolean flag) {

    }

    @Override
    public boolean getDomStorageEnabled() {
        return false;
    }

    @Override
    public String getDatabasePath() {
        return null;
    }

    @Override
    public boolean getDatabaseEnabled() {
        return false;
    }

    @Override
    public void setGeolocationEnabled(boolean flag) {

    }

    @Override
    public boolean getJavaScriptEnabled() {
        return mContentSettings.getJavaScriptEnabled();
    }

    @Override
    public boolean getAllowUniversalAccessFromFileURLs() {
        return true;
    }

    @Override
    public boolean getAllowFileAccessFromFileURLs() {
        return true;
    }

    @Override
    public WebSettings.PluginState getPluginState() {
        return null;
    }

    @Override
    public void setJavaScriptCanOpenWindowsAutomatically(boolean flag) {

    }

    @Override
    public boolean getJavaScriptCanOpenWindowsAutomatically() {
        return false;
    }

    @Override
    public void setDefaultTextEncodingName(String encoding) {

    }

    @Override
    public String getDefaultTextEncodingName() {
        return null;
    }

    @Override
    public void setUserAgentString(String ua) {

    }

    @Override
    public String getUserAgentString() {
        return null;
    }

    @Override
    public void setNeedInitialFocus(boolean flag) {

    }

    @Override
    public void setRenderPriority(WebSettings.RenderPriority priority) {

    }

    @Override
    public void setCacheMode(int mode) {

    }

    @Override
    public int getCacheMode() {
        return 0;
    }
}
