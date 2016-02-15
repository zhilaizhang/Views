package com.zlzhang.views.views.nav_view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.zlzhang.views.R;
import com.zlzhang.views.model.ItemModel;

import java.util.ArrayList;

/**
 * Created by zhilaizhang on 2/3/16.
 */
public class NavView extends LinearLayout{

    public final String TAG = "NavView";

    private Context mContext;
    private ItemViewPager mItemViewPager;
    private ItemPagerAdapter mItemPagerAdapter;

    private OnItemChooseListener mOnItemChooseListener;

    public NavView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public NavView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public void setData(ArrayList<ItemModel> itemModels){
        mItemPagerAdapter.setData(itemModels);
        mItemViewPager.setCurrentItem(0);
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_nav, this);
        mItemViewPager = (ItemViewPager) findViewById(R.id.item_view_pager);
        mItemViewPager.setPageMargin(20);
        mItemPagerAdapter = new ItemPagerAdapter(mContext);
        mItemViewPager.setAdapter(mItemPagerAdapter);
        initListener();
    }

    public void setOnItemChooseListener(OnItemChooseListener onItemChooseListener){
        mOnItemChooseListener = onItemChooseListener;
    }

    public void setItemPosition(int position){
        mItemViewPager.setCurrentItem(position);
    }

    private void initListener() {
        mItemViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mOnItemChooseListener.onItemChoose(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public interface OnItemChooseListener{
        void onItemChoose(int position);
    }

    public void onResume(){
    }

    public void onDestroy(){
        mItemViewPager.clearOnPageChangeListeners();
    }
}
