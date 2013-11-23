package com.swipentap.cordovachromiumsample;

import android.app.Activity;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import org.apache.cordova.CordovaActivity;
import org.chromium.base.PathUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MainActivity extends CordovaActivity {

    private static final String TARGET_FOLDER = "CordovaSample/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        /* Copying the www folder to the data folder at the start of the application */
        String dataDirectoryPath = PathUtils.getDataDirectory(this);
        File f = new File(dataDirectoryPath);
        try {
            AssetManager assetManager = getAssets();
            InputStream is = assetManager.open("www.zip");
            if (!f.exists()) {
                f.mkdirs();
            }
            File targetFile = new File(f.getAbsolutePath() + "/" + "www.zip");
            OutputStream io = new FileOutputStream(targetFile);
            copyFile(is, io);

            unzipFile(targetFile.getAbsolutePath(), f.getAbsolutePath() + "/");

        } catch (IOException e) {
            e.printStackTrace();
        }

        String startupUrl = Uri.fromFile(new File(f.getAbsolutePath() + "/www/index.html")).toString();
        this.setStartupUrl(startupUrl);

        super.onCreate(savedInstanceState);


        /**
         * This line when used crashes the app with Exception
         * java.lang.ClassCastException: android.widget.LinearLayout$LayoutParams
         * cannot be cast to android.widget.FrameLayout$LayoutParams
         * I have no idea why this happens until now
         */
        //this.loadUrl("http://google.com");

    }

    public Activity getActivity() {
        return this;
    }

    private void unzipFile(String filePath, String loc) {
        try {
            FileInputStream fin = new FileInputStream(filePath);
            ZipInputStream zin = new ZipInputStream(fin);
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {
                Log.v("Decompress", "Unzipping " + ze.getName());

                if (ze.isDirectory()) {
                    dirChecker(ze.getName(), loc);

                } else {
                    FileOutputStream fout = new FileOutputStream(loc + ze.getName());
                    copyFile(zin, fout);

                    zin.closeEntry();
                    fout.close();
                }

            }
            zin.close();
        } catch (Exception e) {
            Log.e("Decompress", "unzip", e);
        }

    }

    // Method used by copyAssets() on purpose to copy a file.
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[4096];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    private void dirChecker(String dir, String location) {
        File f = new File(location + dir);

        if(!f.isDirectory()) {
            f.mkdirs();
        }
    }
}
