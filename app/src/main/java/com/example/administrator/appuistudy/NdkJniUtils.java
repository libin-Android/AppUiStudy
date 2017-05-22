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
