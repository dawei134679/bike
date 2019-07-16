package com.coolu.nokelock.bike.broadcast;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.coolu.nokelock.bike.util.ConditionsUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;


import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 下载监听广播
 * Created by sunshine on 2017/3/24.
 */

public class DownLoadCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            installApk(context, id);
        } else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
            ToastUtils.showMessage("正在下载...");
        }
    }

    private void installApk(Context context, long downloadApkId) {
        DownloadManager dManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadFileUri = dManager.getUriForDownloadedFile(downloadApkId);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri contentUri = null;
                contentUri = FileProvider.getUriForFile(ConditionsUtils.getContext(), "com.nokelock.bike.m.fileprovider", new File(new URI(downloadFileUri.toString())));
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            if (ConditionsUtils.getContext().getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                ConditionsUtils.getContext().startActivity(intent);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
