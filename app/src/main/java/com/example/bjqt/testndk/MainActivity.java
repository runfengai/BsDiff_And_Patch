package com.example.bjqt.testndk;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bjqt.testndk.util.BsPatch;
import com.example.bjqt.testndk.util.IncremUpgrade;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "MainActivity";
    private String newApkPath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText("测试数据源改变");
    }

    /**
     * 修补
     *
     * @param view
     */
    public void patch(View view) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        } else {
            doBspatch();
        }

    }


    private void doBspatch() {

        File newApk = new File(Environment.getExternalStorageDirectory(), "new.apk");
        File patch = new File(Environment.getExternalStorageDirectory(), "patch.patch");
        if (!patch.exists()) {
            Toast.makeText(MainActivity.this, "patch文件不存在", Toast.LENGTH_LONG).show();
            Log.e(TAG, "patch:" + patch.exists());
            return;
        }
        String local = IncremUpgrade.getApkPath(MainActivity.this);
        Log.d(TAG, "local:" + local);
        new BsPatch().patch(local, newApk.getAbsolutePath(), patch.getAbsolutePath());
        if (newApk.exists()) {
            newApkPath = newApk.getAbsolutePath();
            Toast.makeText(MainActivity.this, "合并完成，可以安装了", Toast.LENGTH_LONG).show();
            //安装
            Log.d(TAG, "合并完成，可以安装了");
        } else {
            Toast.makeText(MainActivity.this, "合并失败", Toast.LENGTH_LONG).show();
            Log.d(TAG, "合并失败");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WRITE_SDCARD_REQUESTCODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doBspatch();
                }
                break;
            case INSTALL_PACKAGES_REQUESTCODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    install();
                } else {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                    startActivityForResult(intent, GET_UNKNOWN_APP_SOURCES);
                }
                break;
        }
    }

    public static final int INSTALL_PACKAGES_REQUESTCODE = 1;
    public static final int WRITE_SDCARD_REQUESTCODE = 2;

    public void install(View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            boolean b = getPackageManager().canRequestPackageInstalls();
            if (b) {
                install();
            } else { //请求安装未知应用来源的权限
                Toast.makeText(MainActivity.this, "请求授权允许安装应用", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, INSTALL_PACKAGES_REQUESTCODE);
            }
        } else {
            install();
        }
    }

    public void install() {
        IncremUpgrade.installApk(MainActivity.this, newApkPath);
    }


    public static final int GET_UNKNOWN_APP_SOURCES = 2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GET_UNKNOWN_APP_SOURCES:
                install(null);
                break;
            default:
                break;
        }
    }
}
