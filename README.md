
Ndk的初步学习
=============================
对NDK的初步学习，最近打算学习一下NDK的开发，学习NDK开发也是为了给自己一点信念，杜绝懒惰。
-------------------------------------------------------------------------------
## 参考的文章有：
1》Android开发学习之路--NDK、JNI之初体验，http://blog.csdn.net/eastmoon502136/article/details/50759209；

2》android studio下的NDK开发详解(一)，http://www.th7.cn/Program/Android/201509/550864.shtml
再次感谢他们的技术分享。

java层怎么就能访问到c/c++层并将数据传输过去：c/c++又怎么能将数据传回java层呢。出来在java层通过native标记某个方法是本地方法外，重要是需要NDK这个android 本地开发工具集 
什么是NDK(android native develop kits ):android 本地开发工具集 ,可以把c/c++ ->编译成一个 linux下可以执行的二进制文件 java代码里面就可以通过jni 调用执行二进制的文件.
什么是JNI ：java本地开发接口,JNI是一个协议这个协议用来沟通java代码和外部的本地代码(c/c++).通过这个协议,java代码就可以调用外部的c/c++，代码外部的c/c++代码也可以调用java代码。
JNI开发用途：驱动开发 (wifi-hotspot) 2.3无线热点共享  ，Native code效率高,数学运算,实时渲染的游戏上,音视频处理(极品飞车,opengl,ffmpeg)，复用代码(文件压缩,人脸识别…)等。

转入正题：
--------------------
JNI开发环境配置及简单的程序实现。
----------------------------
## 1.安转NDk,目前studio已结很好的支持NDK，可以直接下载NDK。不会的直接问百度。。。。。
## 2.头文件生成：
    第一步：像平时一样将一个空的android工程。
    第二部：前面说了，要用native来标识一个方法，告诉程序这是一个本地方法。代码如下：
 package com.example.administrator.appuistudy;
 

/**
 * Created by Administrator on 2017/5/22.
 */
 
public class NdkJniUtils  {

    static {
    
        System.loadLibrary("emJniLibName"); //defaultConfig.ndk.moduleName
        
    }

    public native String getCLanguageString();
    
}

    第三步：然后make project一下，目的就是编译成对应的class文件。到app/build/intermediates/classes/debug目录下查看是否生成了.class文件。
    
    第四步：据生成的class文件，利用javah生成对应的 .h头文件。
    
    打开studio的Terminal，输入命令：cd app/src/main 回车，切换到main目录下；
    
    输入javah -d jni -classpath C:\Users\Administrator\AppData\Local\Android\sdk\platforms\
    android-23\android.jar;../../build/intermediates/classes/debug com.example.administrator.appuistudy.NdkJniUtils
    
    studio会自动生成jni文件夹，在jni文件夹下会生成.h文件
## 3.这里就会生产一个需要c实现的函数接口了。接着在main目录下新建jni目录，新建一个c文件，命名为jnitest.c。编写jnitest.c如下：
    
    #include "com_jared_emjnistudy_NdkJniUtils.h"  //注意路劲要正确
  
    JNIEXPORT jstring JNICALL Java_com_jared_emjnistudy_NdkJniUtils_getCLanguageString  

        (JNIEnv *env, jobject obj) {  
        
    return (*env)->NewStringUTF(env, "This is Jni test!!!");  
    
    } 
    
## 4.库文件和jni接口都准备好了，接着呢我们需要来配置下编译的gradle了。

    第一步：在gradle.properties  中添加android.useDeprecatedNdk=true 
    
    第二步：接着修改app目录下的build.gradle：
    
    apply plugin: 'com.android.application'

android {

    compileSdkVersion 24
    buildToolsVersion "25.0.0"
    useLibrary 'org.apache.http.legacy'
    defaultConfig {
        applicationId "com.example.administrator.appuistudy"
        minSdkVersion 17
        targetSdkVersion 24
        versionCode 1
        versionName "1.0"
        ndk {
            moduleName "emJniLibName"                    //生成的so名字
            abiFilters "armeabi", "armeabi-v7a", "x86"  //输出指定三种abi体系结构下的so库。目前可有可无。
        }
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    
    testCompile 'junit:junit:4.12'
    
    compile 'com.android.support:appcompat-v7:24.2.1'
    
}

## 5.在MainActivity中调用jni的方法 
     //调用.c文件中的代码
        NdkJniUtils jniUtils = new NdkJniUtils();
        
        String text = jniUtils.getCLanguageString();
        
        textView1.setText(text);
        
    逻辑是：MainActivity中调用NdkJniUtils.getCLanguageString();的方法---》.h文件----》.c文件
    
