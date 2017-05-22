//
// Created by Administrator on 2017/5/22.
//导报的时候路劲一定要是全路径:#include "com_example_administrator_appuistudy_NdkJniUtils.h"
//
#include "com_example_administrator_appuistudy_NdkJniUtils.h"

JNIEXPORT jstring JNICALL Java_com_example_administrator_appuistudy_NdkJniUtils_getCLanguageString
        (JNIEnv *env, jobject obj) {
    return (*env)->NewStringUTF(env, "我是.c文件中的代码");
}
//.c文件的方法名必须和.h文件的方法名相同
//Java_com_example_administrator_appuistudy_NdkJniUtils_getCLanguageString