package com.example.lhd.seatchoosedemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;

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
        sectorView=findViewById(R.id.cccc);
        seekBar=findViewById(R.id.seekbar);
        angeleText=findViewById(R.id.angleText);

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
             //   sectorView.setDegree(seekBar.getProgress());
            }
        });
    }
}
