package com.swipentap.cordovachromiumsample;

import android.app.Application;

import org.chromium.base.PathUtils;
import org.chromium.content.app.LibraryLoader;
import org.chromium.content.browser.ResourceExtractor;

/**
 * Created by macbookpro on 10/31/13.
 */
public class CordovaChromiumApplication extends Application {
    private static final String[] MANDATORY_PAK_FILES = new String[] {"content_shell.pak"};
    private static final String PRIVATE_DATA_DIRECTORY_SUFFIX = "cordova_chromium";
    private static final String[] LIBRARIES = new String[] {"content_shell_content_view"};

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationParameters();
    }

    public static void initializeApplicationParameters() {
        ResourceExtractor.setMandatoryPaksToExtract(MANDATORY_PAK_FILES);
        PathUtils.setPrivateDataDirectorySuffix(PRIVATE_DATA_DIRECTORY_SUFFIX);
        LibraryLoader.setLibraryToLoad(LIBRARIES);
    }
}
