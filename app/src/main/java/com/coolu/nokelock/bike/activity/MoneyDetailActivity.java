package com.coolu.nokelock.bike.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;


public class MoneyDetailActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_money_detail);
        recyclerView = (RecyclerView)findViewById(R.id.id_recycleView);
        layoutManager = new LinearLayoutManager(MoneyDetailActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
