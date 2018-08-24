#include <jni.h>
#include <string>
#include "bspatch.c"

extern "C"
JNIEXPORT jint
JNICALL
Java_com_example_bjqt_testndk_util_BsPatch_patch(
        JNIEnv *env,
        jclass type, jstring oldpath_,
        jstring newpath_, jstring patch_) {
    const char *oldApk = env->GetStringUTFChars(oldpath_, 0);
    const char *newApk = env->GetStringUTFChars(newpath_, 0);
    const char *patchApk = env->GetStringUTFChars(patch_, 0);
    int argc = 4;
    char *argv[argc];
    argv[0] = "bspatch";
    argv[1] = const_cast<char *>(oldApk);
    argv[2] = const_cast<char *>(newApk);
    argv[3] = const_cast<char *>(patchApk);
    int res = mypatch(argc, argv);
    return res;
}