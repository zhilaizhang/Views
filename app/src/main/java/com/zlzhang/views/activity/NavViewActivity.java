package com.zlzhang.views.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.zlzhang.views.R;
import com.zlzhang.views.views.nav_view.NavView;

/**
 * Created by zhilaizhang on 2/3/16.
 */
public class NavViewActivity extends Activity{

    private NavView mNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_view);
        mNavView = (NavView)findViewById(R.id.nav_view);
        mNavView.setData(MainActivity.mItemModelList);
        mNavView.setOnItemChooseListener(new NavView.OnItemChooseListener() {
            @Override
            public void onItemChoose(int position) {
                Log.d("tag", "tag" + position);
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
