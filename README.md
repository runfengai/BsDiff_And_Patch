## 声明 ##
增量升级功能，参考hongyang的开源项目，生成的合并功能开源库。生成增量包，参考博客：
> [https://blog.csdn.net/lmj623565791/article/details/52761658](https://blog.csdn.net/lmj623565791/article/details/52761658)
## 增量升级流程说明 ##
参考app/demoapk/文件夹下三个文件old.apk、new.apk、patch.patch  

1. **准备工作：新老版本的apk包，如old.apk,new.apk。**
1. **通过使用工具bsdiff-4.3.tar.gz的diff命令生成增量包，如命名为patch.patch。**
1. **安装老版本apk，同时将patch.patch文件导入到sdcard根目录（测试方便）。**
2. **点击合成按钮，会在sdcard根目录下生成new.apk，点击安装将新apk安装完成，增量升级完成。**



## 引入方式 ##
gradle引入方式:
    
    compile 'com.jarry:bspatchlib:1.0.0'
或者

    implementation 'com.jarry:bspatchlib:1.0.0'

## 调用方式 ##
    
    File patch = new File(Environment.getExternalStorageDirectory(), "patch.patch");
    if (!patch.exists()) {
    Toast.makeText(MainActivity.this, "patch文件不存在", Toast.LENGTH_LONG).show();
    return;
    }
    String local = IncremUpgrade.getApkPath(MainActivity.this);
    new BsPatch().patch(local, newApk.getAbsolutePath(), patch.getAbsolutePath());

### 方法说明： ###
    /**
     * 老的apk包与patch文件合并，最后生成新的apk包
     *
     * @param oldpath 老的apk包目录
     * @param newpath 新生成的apk包目录
     * @param patch   patch文件
     * @return
     */
    public static native int patch(String oldpath, String newpath, String patch);
