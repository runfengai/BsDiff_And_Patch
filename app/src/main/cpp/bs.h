//
// Created by BJQT on 2018/7/10.
//

#ifndef HELLO_JNICALLBACK_BS_H
#define HELLO_JNICALLBACK_BS_H

#endif //HELLO_JNICALLBACK_BS_H

#include <jni.h>
#include <malloc.h>


int mydiff(int argc,char *argv[]);
int mypatch(int argc,char * argv[]);
JNIEXPORT jint JNICALL
Java_com_example_hellojnicallback_BsDiffUtils_patch(JNIEnv *env, jclass type, jstring oldpath_,
                                                    jstring newpath_, jstring patch_);
JNIEXPORT jint JNICALL
Java_com_example_hellojnicallback_BsDiffUtils_diff(JNIEnv *env, jclass type, jstring oldpath_,
                                                   jstring newpath_, jstring patch_);
JNIEXPORT jint JNICALL
Java_com_example_hellojnicallback_BsDiffUtils_patch_1no_1static(JNIEnv *env, jobject instance,
                                                                jstring oldpath_, jstring newpath_,
                                                                jstring patch_);
