package com.jarry.bspatch.util;

/**
 * 所在包名：com.example.bjqt.testndk.com.jarry.bspatch.util
 * 描述：
 * 作者：biantong
 * 创建时间：2018/8/23
 * 修改人：
 * 修改时间：
 * 修改描述：
 */
public class BsPatch {
    static {
        System.loadLibrary("bspatch");
    }

    public static native int patch(String oldpath, String newpath, String patch);
}
