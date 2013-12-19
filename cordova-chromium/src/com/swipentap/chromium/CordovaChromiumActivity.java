package com.swipentap.chromium;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.chromium.content.app.ContentMain;
import org.chromium.content.app.LibraryLoader;
import org.chromium.content.browser.ActivityContentVideoViewClient;
import org.chromium.content.browser.ContentVideoViewClient;
import org.chromium.content.browser.ContentView;
import org.chromium.content.browser.ContentViewClient;
import org.chromium.content.browser.DeviceUtils;
import org.chromium.content.common.CommandLine;
import org.chromium.content.common.ProcessInitException;
import org.chromium.content_shell.Shell;
import org.chromium.content_shell.ShellManager;
import org.chromium.ui.WindowAndroid;

public class CordovaChromiumActivity extends Activity {

    // The webview for our app
    protected CordovaChromiumView appView;
    protected ShellManager mShellManager;
    protected WindowAndroid mWindowAndroid;

    public static final String COMMAND_LINE_FILE = "/data/local/tmp/content-shell-command-line";
    private static final String TAG = "CordovaChromiumActivity";
    public static final String COMMAND_LINE_ARGS_KEY = "commandLineArgs";
    private static final String ACTIVE_SHELL_URL_KEY = "activeUrl";

    @Override
    protected void onCreate(final Bundle savedInstance) {
        super.onCreate(savedInstance);

        // Initializing the command line must occur before loading the library.
        if (!CommandLine.isInitialized()) {
            CommandLine.initFromFile(COMMAND_LINE_FILE);
            String[] commandLineParams = getCommandLineParamsFromIntent(getIntent());
            if (commandLineParams != null) {
                CommandLine.getInstance().appendSwitchesAndArguments(commandLineParams);
            }
        }
        waitForDebuggerIfNeeded();

        DeviceUtils.addDeviceSpecificUserAgentSwitch(this);
        try {
            LibraryLoader.ensureInitialized();
        } catch (ProcessInitException e) {
            Log.e(TAG, "ContentView initialization failed.", e);
            finish();
            return;
        }

        CommandLine.getInstance().appendSwitch("disable-web-security");
    }

    private void waitForDebuggerIfNeeded() {
        if (CommandLine.getInstance().hasSwitch(CommandLine.WAIT_FOR_JAVA_DEBUGGER)) {
            Log.e(TAG, "Waiting for Java debugger to connect...");
            android.os.Debug.waitForDebugger();
            Log.e(TAG, "Java debugger connected. Resuming execution.");
        }
    }

    private static String[] getCommandLineParamsFromIntent(Intent intent) {
        return intent != null ? intent.getStringArrayExtra(COMMAND_LINE_ARGS_KEY) : null;
    }

    protected void finishInitialization(Bundle savedInstanceState) {
        String shellUrl;
        if (savedInstanceState != null
                && savedInstanceState.containsKey(ACTIVE_SHELL_URL_KEY)) {
            shellUrl = savedInstanceState.getString(ACTIVE_SHELL_URL_KEY);
        }
        Shell activeShell = getActiveShell();
        if (activeShell == null) {
            ContentMain.start();
        }
        getActiveContentView().setContentViewClient(new ContentViewClient() {
            @Override
            public ContentVideoViewClient getContentVideoViewClient() {
                return new ActivityContentVideoViewClient(CordovaChromiumActivity.this);
            }
        });
    }

    protected void initializationFailed() {
        Log.e(TAG, "ContentView initialization failed.");
        Toast.makeText(CordovaChromiumActivity.this,
                "Initialization Failed",
                Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * @return The {@link ContentView} owned by the currently visible {@link Shell} or null if one
     * is not showing.
     */
    public ContentView getActiveContentView() {
        Shell shell = getActiveShell();
        return shell != null ? shell.getContentView() : null;
    }

    public Shell getActiveShell() {
        return mShellManager != null ? mShellManager.getActiveShell() : null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Shell activeShell = getActiveShell();
        if (activeShell != null) {
            outState.putString(ACTIVE_SHELL_URL_KEY, activeShell.getContentView().getUrl());
        }

        mWindowAndroid.saveInstanceState(outState);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mWindowAndroid.onActivityResult(requestCode, resultCode, data);
    }
}
