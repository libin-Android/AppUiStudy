package com.example.administrator.appuistudy;

import android.content.Intent;
import android.graphics.Camera;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import org.apache.http.util.EncodingUtils;

import java.io.InputStream;

import application.MyApp;
import views.AutoFitTextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private AutoFitTextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
//        ((Button) findViewById(R.id.bt)).setOnClickListener(this);
//        ((Button) findViewById(R.id.bt2)).setOnClickListener(this);
        textView1 = ((AutoFitTextView) findViewById(R.id.tv1));
//        textView1.setContent(readFromAsset("privacyPolicy.txt"));
//        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
//        seekBar.setOnSeekBarChangeListener(this);
//        seekBar.setMax(21);
        //调用.c文件中的代码
        NdkJniUtils jniUtils = new NdkJniUtils();
        String text = jniUtils.getCLanguageString();
        textView1.setText(text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt:
                MyApp.size=!MyApp.size;
                break;
            case R.id.bt2:
                Intent intent=new Intent(this,TextActivity.class);
                startActivity(intent);
                break;
        }
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

    /**
     * seekBar的监听
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (progress<5){
            textView1.upDateView(5,readFromAsset("privacyPolicy.txt"));
            return;
        }
        textView1.upDateView(progress,readFromAsset("privacyPolicy.txt"));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
