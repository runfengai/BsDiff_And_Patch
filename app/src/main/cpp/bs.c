//
// Created by BJQT on 2018/7/10.
//
#include "bs.h"

JNIEXPORT jint JNICALL
Java_com_jarry_bspatch_util_BsPatch_patch(JNIEnv *env, jclass type, jstring oldpath_,
                                                    jstring newpath_, jstring patch_) {
    const char *oldpath = (*env)->GetStringUTFChars(env, oldpath_, 0);
    const char *newpath = (*env)->GetStringUTFChars(env, newpath_, 0);
    const char *patch = (*env)->GetStringUTFChars(env, patch_, 0);

    const char *argv[4];
    argv[0] = "bspatch";
    argv[1] = oldpath;
    argv[2] = newpath;
    argv[3] = patch;
    mypatch(4, argv);


    (*env)->ReleaseStringUTFChars(env, oldpath_, oldpath);
    (*env)->ReleaseStringUTFChars(env, newpath_, newpath);
    (*env)->ReleaseStringUTFChars(env, patch_, patch);
//    free(argv[0]);
//    free(argv);
    return 0;
}
