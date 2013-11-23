package org.apache.cordova;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.swipentap.chromium.*;

import org.chromium.content.browser.ContentView;
import org.chromium.content_shell.Shell;
import org.chromium.content_shell.ShellManager;

/**
 * Created by macbookpro on 11/3/13.
 */
public class CordovaShellManager extends ShellManager {


    public CordovaShellManager(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
    }

    @Override
    protected Object createShell() {
        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CordovaWebView shellView = (CordovaWebView) inflater.inflate(R.layout.cordova_shell_view, null);
        shellView.setWindow(mWindow);
        //shellView.setup();
        if (mActiveShell != null) closeShell(mActiveShell);

        shellView.setContentViewRenderView(mContentViewRenderView);
        addView(shellView, new FrameLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mActiveShell = shellView;
        ContentView contentView = mActiveShell.getContentView();
        if (contentView != null) {
            mContentViewRenderView.setCurrentContentView(contentView);
            contentView.onShow();
        }
        //shellView.setup();
        return shellView;
    }
}
