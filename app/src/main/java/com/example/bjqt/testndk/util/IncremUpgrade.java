package com.example.bjqt.testndk.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import java.io.File;

/**
 * 所在包名：com.example.bjqt.testndk.util
 * 描述：增量升级
 * 作者：jarry
 * 创建时间：2018/8/23
 * 修改人：
 * 修改时间：
 * 修改描述：
 */
public class IncremUpgrade {
    /**
     * 获取apk路径
     *
     * @param context
     * @return
     */
    public static String getApkPath(Context context) {
        Context appContext = context.getApplicationContext();
        ApplicationInfo applicationInfo = appContext.getApplicationInfo();
        String sourceDir = applicationInfo.sourceDir;
        return sourceDir;
    }

    /**
     * 安装apk
     *
     * @param context
     * @param apkPath
     */
    public static void installApk(Context context, String apkPath) {
        if (context == null || TextUtils.isEmpty(apkPath)) {
            return;
        }
        File file = new File(apkPath);
        Intent intent = new Intent(Intent.ACTION_VIEW);

        //判读版本是否在7.0以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //provider authorities
            Uri apkUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
            //Granting Temporary Permissions to a URI
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }
}
