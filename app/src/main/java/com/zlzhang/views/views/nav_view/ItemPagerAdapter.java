package com.zlzhang.views.views.nav_view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zlzhang.views.R;
import com.zlzhang.views.model.ItemModel;

import java.util.ArrayList;

/**
 * Created by zhilaizhang on 2/3/16.
 */
public class ItemPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<ItemModel> mItemModelList;

    public ItemPagerAdapter(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<ItemModel> itemModels) {
        mItemModelList = itemModels;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mItemModelList != null) {
            return mItemModelList.size();
        }
        return 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_pager, container, false);
        TextView idText = (TextView) view.findViewById(R.id.id_text);
        TextView nameText = (TextView) view.findViewById(R.id.name_text);
        TextView descriptionText = (TextView) view.findViewById(R.id.description_text);
        ItemModel itemModel = mItemModelList.get(position);

        idText.setText("" + itemModel.getId());
        nameText.setText(itemModel.getName());
        descriptionText.setText(itemModel.getDescription());
        container.addView(view);

        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
