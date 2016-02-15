package com.zlzhang.views.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zlzhang.views.R;
import com.zlzhang.views.model.ItemModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;
    private Button mNavViewButton;

    public static ArrayList<ItemModel> mItemModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        initListener();
        initData();
    }

    private void initData() {
        mItemModelList = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            ItemModel itemModel = new ItemModel();
            itemModel.setId(i);
            itemModel.setName("name" + i);
            itemModel.setDescription("description" + i);
            mItemModelList.add(itemModel);
        }
    }

    private void initView() {
        mNavViewButton = (Button) findViewById(R.id.nav_view_button);

    }

    private void initListener() {
        mNavViewButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nav_view_button:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NavViewActivity.class);
                startActivity(intent);
                break;
        }
    }
}
