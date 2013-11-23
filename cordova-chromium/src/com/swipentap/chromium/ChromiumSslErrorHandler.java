package com.swipentap.chromium;

/**
 * ChromeView equivalent of SslErrorHandler.
 *
 * This is necessary because SslErrorHandler's constructor is package-private,
 * so it is impossible to extend the class, which would have been cleaner.
 *
 * @see android.webkit.SslErrorHandler
 */
public interface ChromiumSslErrorHandler {
  // Mostly mirrors
  //    platform/frameworks/base/ ./core/java/android/webkit/SslErrorHandler
  /**
   * Proceed with the SSL certificate.
   */
  public void proceed();

  /**
   * Cancel this request and all pending requests for the WebView that had
   * the error.
   */
  public void cancel();
}
