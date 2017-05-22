package com.example.administrator.appuistudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.util.EncodingUtils;

import java.io.InputStream;

import application.MyApp;
import utils.TextScaleUtils;

/**
 *
 */
public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextScaleUtils.scaleTextSize(this,MyApp.size);
        setContentView(R.layout.activity_text);
        initView();
    }

    private void initView() {
        TextView textView = (TextView) findViewById(R.id.tv);
        textView.setText(readFromAsset("privacyPolicy.txt"));
    }

    /**
     * 从asset中获取文件并读取数据（资源文件只能读不能写）
     *
     * @param fileName
     * @return
     */
    public String readFromAsset(String fileName) {
        String res = "";
        try {
            InputStream in = getResources().getAssets().open(fileName);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            res = EncodingUtils.getString(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
