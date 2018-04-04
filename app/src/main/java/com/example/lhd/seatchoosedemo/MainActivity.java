package com.example.lhd.seatchoosedemo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
    private sectorView sectorView;
    private SeekBar seekBar;
    private TextView angeleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initListener();

    }
    private  void initView(){
        sectorView= (com.example.lhd.seatchoosedemo.sectorView) findViewById(R.id.cccc);
        seekBar= (SeekBar) findViewById(R.id.seekbar);
        angeleText= (TextView) findViewById(R.id.angleText);

        seekBar.setMax(180);
        seekBar.setProgress(80);
        sectorView.setDegree(80);

    }
    private  void initListener(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sectorView.setDegree(seekBar.getProgress());
                angeleText.setText(i+"°");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
//    public BroadcastReceiver mScreenSizeChangeReceiver = new BroadcastReceiver() {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.d(TAG, "mScreenSizeChangeReceiver =action" + intent.getAction());
//            if (intent.getAction().equals("android.intent.action.SPLIT_WINDOW_HAS_CHANGED")) {
//                ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//                int leftStackId = am.getLeftStackId();
//                int rightStackId = am.getRightStackId();
//                if (leftStackId == -1) {
//                    leftStackId = 0;
//                }
//                if (rightStackId > 0 && am.getWindowSizeStatus(rightStackId) == 0) {
//                    Log.d(TAG, " on left");
//
//
//                } else if (leftStackId > 0 && am.getWindowSizeStatus(leftStackId) == 0) {
//                    Log.d(TAG, " on right");
//
//                } else if (am.getWindowSizeStatus(leftStackId) == 1) {
//                    Log.d(TAG, " on full");
//
//
//                } else if (am.getWindowSizeStatus(leftStackId) == 3) {
//                    Log.d("TAG", "on full");
//
//                } else {
//
//
//                }
//
//                /**
//                 * 判断全屏分屏满屏
//                 */
//
//                if (intent.getAction().equals("WINDOW_STATUS_CHANGED")) {
//                    int size = intent.getIntExtra("windowStatus", -1);
//                    boolean isOnLeft = intent.getBooleanExtra("isOnLeft", true);
//
//
//                    if (isOnLeft) {
//                        if (size == 1) {
//
//                        } else if (size == 0) {
//                            //隐藏
//
//                        } else if (size == 3) {
//
//
//                        }
//
//
//                    }
//
//
//                }
//
//
//            }
//        }
//    };


}
