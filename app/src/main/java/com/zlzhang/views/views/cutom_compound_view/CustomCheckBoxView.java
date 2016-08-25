package com.zlzhang.views.views.cutom_compound_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zlzhang.views.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhilaizhang on 8/25/16.
 */
public class CustomCheckBoxView extends LinearLayout {
    public static final String TAG = "CustomCheckView";
    private Context mContext;

    private int                mSum;
    private int                mRow;
    private ArrayList<String> mNameList;
    private CustomGridView     mGridView;
    private CustomCheckAdapter mCustomCheckAdapter;
    private OnCheckListener    mOnCheckListener;

    private HashMap<Integer, Boolean> mCheckMap;
    private int mCheckPosition;

    public CustomCheckBoxView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public CustomCheckBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LinearLayout mainLayout = new LinearLayout(mContext);
        addView(mainLayout);
        mGridView = new CustomGridView(mContext);
        mainLayout.addView(mGridView);
        mCustomCheckAdapter = new CustomCheckAdapter();
        mGridView.setAdapter(mCustomCheckAdapter);
        initListener();
    }

    public void setData(ArrayList<String> nameList, int row) {
        mCheckPosition = -1;
        mRow = row;
        mNameList = nameList;
        mGridView.setNumColumns(row);
        mCustomCheckAdapter.notifyDataSetChanged();
    }

    public void setPosition(ArrayList<Integer> positions) {
        if (positions != null) {
            for (int i = 0; i < positions.size(); i++) {
                mCustomCheckAdapter.check(positions.get(i));
            }
        }
    }

    public void setOnCheckListener(OnCheckListener onCheckListener) {
        mOnCheckListener = onCheckListener;
    }

    private void initListener() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCustomCheckAdapter.check(position);
                mOnCheckListener.onCheck(CustomCheckBoxView.this, position);
            }
        });
    }

    public interface OnCheckListener {
        void onCheck(CustomCheckBoxView customCheckBoxView, int position);
    }

    private class CustomGridView  extends GridView {

        public CustomGridView(Context context) {
            super(context);
        }

        public CustomGridView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public CustomGridView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }
    }

    public class CustomCheckAdapter extends BaseAdapter {
        public CustomCheckAdapter(){
            mCheckMap = new HashMap<>();
        }

        public void check(int position){
            mCheckPosition = position;
            if(mCheckMap.get(position) != null){
                if(mCheckMap.get(position)){
                    mCheckMap.put(position, false);
                } else {
                    mCheckMap.put(position, true);
                }
            } else {
                mCheckMap.put(position, true);
            }
            notifyDataSetChanged();
        }

        public boolean isChecked(int position){
            if(mCheckMap != null && mCheckMap.get(position)){
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int getCount() {
            if(mNameList != null){
                return mNameList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if(mNameList != null){
                return mNameList.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null){
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_custom_radio_view, null);
                viewHolder.checkImage = (ImageView) convertView.findViewById(R.id.check_imagge);
                viewHolder.nameText = (TextView) convertView.findViewById(R.id.name_text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder)convertView.getTag();
            }

            String name = mNameList.get(position);
            viewHolder.nameText.setText(name);
            if(mCheckMap.get(position) != null){
                if(mCheckMap.get(position)){
                    viewHolder.checkImage.setSelected(true);
                } else {
                    viewHolder.checkImage.setSelected(false);
                }
            } else {
                viewHolder.checkImage.setSelected(false);
            }
            return convertView;
        }

        public class ViewHolder{
            ImageView checkImage;
            TextView  nameText;
        }
    }
}
