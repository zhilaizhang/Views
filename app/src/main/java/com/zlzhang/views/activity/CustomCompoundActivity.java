package com.zlzhang.views.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.zlzhang.views.R;
import com.zlzhang.views.views.cutom_compound_view.CustomCheckBoxView;
import com.zlzhang.views.views.cutom_compound_view.CustomRadioView;

import java.util.ArrayList;

/**
 * Created by zhilaizhang on 8/25/16.
 */
public class CustomCompoundActivity extends Activity{

    private CustomRadioView mCustomRadioView;
    private CustomCheckBoxView mCustomCheckBoxView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mCustomRadioView = (CustomRadioView) findViewById(R.id.custom_radio_view);
        mCustomCheckBoxView = (CustomCheckBoxView) findViewById(R.id.custom_check_boxView);
    }

    private void initData() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("上海");
        strings.add("北京");
        strings.add("武汉");
        strings.add("深圳");
        mCustomRadioView.setData(strings, 2);

        mCustomCheckBoxView.setData(strings, 2);
    }

    private void initListener() {
        mCustomRadioView.setOnCheckListener(new CustomRadioView.OnCheckListener() {
            @Override
            public void onCheck(CustomRadioView customRadioView, int position) {
                Log.d("position", "position: " + position);
            }
        });

        mCustomCheckBoxView.setOnCheckListener(new CustomCheckBoxView.OnCheckListener() {
            @Override
            public void onCheck(CustomCheckBoxView customCheckBoxView, int position) {
                Log.d("position", "position: " + position);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
