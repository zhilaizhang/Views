package com.zlzhang.views.views.nav_view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

/**
 * Created by zhilaizhang on 2/3/16.
 */
public class ItemViewPager extends ViewPager{

    private DisplayMetrics displayMetrics;

    public ItemViewPager(Context context){
        super(context);
        init();
    }

    public ItemViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        displayMetrics = getContext().getResources().getDisplayMetrics();  //A structure describing general information about a display
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels - getPageMargin() * 4, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
