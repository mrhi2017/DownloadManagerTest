package com.mrhi2017.downloadmanagertest;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    long mDownloadId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View v){

        String fUrl = "http://upload.wikimedia.org/wikipedia/commons/c/cf/Frog_on_river_4000x3000_26-09-2010_11-01am_2mb.jpg";
        fUrl="http://mrhi2017.dothome.co.kr/Koala.jpg";
        String newFilename = System.currentTimeMillis()+ fUrl.substring( fUrl.lastIndexOf(".") );

        // Make a request
        DownloadManager.Request request
                = new DownloadManager.Request(Uri.parse(fUrl))
                .setAllowedOverRoaming(false)
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
                .setTitle(newFilename)
                .setDescription("ProcessUpdaterExmpale");

        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            File file= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), newFilename);
            Uri uri= Uri.fromFile(file);

            request.setDestinationUri(uri);
        }

        // you can hide download status
        // request.setVisibleInDownloadsUi(false);
        // request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);

        // enqueue
        DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        mDownloadId = dm.enqueue(request);

    }//clickBtn Method...


}


