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

/**
 * Created by zhilaizhang on 8/25/16.
 */
public class CustomRadioView extends LinearLayout{

    private Context mContext;

    private int                mSum;
    private int                mRow;
    private ArrayList<String> mNameList;
    private CustomGridView     mGridView;
    private CustomRadioAdapter mCustomRadioAdapter;
    private OnCheckListener    mOnCheckListener;

    private int mCheckPosition = -1;

    public CustomRadioView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public CustomRadioView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LinearLayout mainLayout = new LinearLayout(mContext);
        addView(mainLayout);
        mGridView = new CustomGridView(mContext);
        mainLayout.addView(mGridView);
        mCustomRadioAdapter = new CustomRadioAdapter();
        mGridView.setAdapter(mCustomRadioAdapter);
        initListener();
    }

    /**
     *
     * @param nameList radioview中需要显示的文字
     * @param row      每行显示的个数
     */
    public void setData(ArrayList<String> nameList, int row){
        mCheckPosition = -1;
        mRow = row;
        mNameList = nameList;
        mGridView.setNumColumns(row);
        mCustomRadioAdapter.notifyDataSetChanged();
    }

    /**
     * 设置选中的选项
     * @param position
     */
    public void setPosition(int position){
        mCheckPosition = position;
        mCustomRadioAdapter.notifyDataSetChanged();
    }

    public void setOnCheckListener(OnCheckListener onCheckListener){
        mOnCheckListener = onCheckListener;
    }

    private void initListener() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCheckPosition = position;
                if(mOnCheckListener != null){
                    mOnCheckListener.onCheck(CustomRadioView.this, position);
                }
                if(mCustomRadioAdapter != null){
                    mCustomRadioAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public interface OnCheckListener{
        void onCheck(CustomRadioView customRadioView, int position);
    }

    private class CustomRadioAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            if(mNameList != null){
                return mNameList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int i) {
            if(mNameList != null){
                return mNameList.get(i);
            }
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
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

            if(mCheckPosition == position){
                viewHolder.checkImage.setSelected(true);
            } else {
                viewHolder.checkImage.setSelected(false);
            }
            return convertView;
        }

        public class ViewHolder{
            ImageView checkImage;
            TextView nameText;
        }
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

}
