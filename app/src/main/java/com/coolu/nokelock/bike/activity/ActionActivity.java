

package com.coolu.nokelock.bike.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.fragment.CouponFragment;
import com.coolu.nokelock.bike.fragment.RedFragment;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.coolu.nokelock.bike.R;

public class ActionActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_back_card)
    ImageView iv_back_card;
    @BindView(R.id.tv_card_help)
    TextView tv_card_help;
    @BindView(R.id.iv_card)
    ImageView iv_card;
    @BindView(R.id.tv_vip_time)
    TextView tv_vip_time;
    @BindView(R.id.tv_buy_vip)
    TextView tv_buy_vip;

    private String userLevel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        ButterKnife.bind(this);
        iv_back_card.setOnClickListener(this);
        tv_card_help.setOnClickListener(this);
        iv_card.setOnClickListener(this);
        tv_buy_vip.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        if (App.getInstance().getUserEntityBean() != null) {
//            Log.e("TAG","会员是："+App.getInstance().getUserEntityBean().getUserLevel());
//            Log.e("TAG","会员到期时间是："+App.getInstance().getUserEntityBean().getUserLevelEndTime());
//            是否是会员，0不是会员，1是会员
            userLevel = App.getInstance().getUserEntityBean().getUserLevel();
            String userLevelEndTime = App.getInstance().getUserEntityBean().getUserLevelEndTime();
            if (TextUtils.equals("1", userLevel)) {
                iv_card.setImageResource(R.mipmap.card23);
                tv_vip_time.setVisibility(View.VISIBLE);
                tv_vip_time.setText("到期时间：" + userLevelEndTime);
                tv_buy_vip.setVisibility(View.GONE);
            } else {
                iv_card.setImageResource(R.mipmap.card20);
                tv_vip_time.setVisibility(View.GONE);
                tv_buy_vip.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.iv_back_card:
                ActionActivity.this.finish();
                break;
            case R.id.tv_card_help:
                Intent user = new Intent(ActionActivity.this, UserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", Config.getCardUrl);
                bundle.putString("route", "卡劵使用帮助");
                bundle.putInt("flag", 3);
                user.putExtras(bundle);
                startActivity(user);
                break;
            case R.id.iv_card:
            case R.id.tv_buy_vip:
                if (!TextUtils.equals("1", userLevel)) {
                    intent = new Intent(ActionActivity.this, VipComboActivity.class);
                    intent.putExtra("tag","action");
                    startActivity(intent);
                }
                break;
        }

    }
    //    private TabLayout tabLayout;
//    private ViewPager viewpager;
//    private ArrayList<Fragment> fragments;
//    private String title[]=new String[]{"红包","骑行劵"};
//    private FrameLayout frame;
//    //   记录当前的Fragment
//    private int currentPosition;
//    private CouponFragment couponFragment;
//    private RedFragment redFragment;
//    private ImageView back;
//    private String flag;
//    private TextView userHelp;
//
////    private WebView webview;
////    private WebSettings settings;
////    private String url = "http://www.baidu.com/";
////    private RelativeLayout prog;
////    private SeekBar p;
////    private int pp;
////    private ImageView action_photo;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //设置状态栏文字颜色及图标为深色
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        setContentView(R.layout.activity_action);
//        flag = getIntent().getStringExtra("flag");
//
//        back = (ImageView)findViewById(R.id.id_back);
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if ("member_success".equals(flag)){
//                    Intent member=new Intent(ActionActivity.this,MainActivity.class);
//                    startActivity(member);
//                }else {
//                    finish();
//                }
//
//            }
//        });
//        //使用帮助
//        userHelp = (TextView)findViewById(R.id.id_money_item);
//        userHelp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent user=new Intent(ActionActivity.this,UserActivity.class);
//                Bundle bundle=new Bundle();
//                bundle.putString("url", Config.getCardUrl);
//                bundle.putString("route","卡劵使用帮助");
//                bundle.putInt("flag",3);
//                user.putExtras(bundle);
//                startActivity(user);
//            }
//        });
//
//
//        /*
//        tabLayout = (TabLayout)findViewById(R.id.tablayout);
//        viewpager = (ViewPager)findViewById(R.id.viewpager);
//        //数据
//        fragments = new ArrayList<Fragment>();
//        fragments.add(new RedFragment());
//        fragments.add(new CouponFragment());
//
//        //设置监听器
//        TablayoutAdapter adapter = new TablayoutAdapter(getSupportFragmentManager(), title, fragments);
//        viewpager.setAdapter(adapter);
//
//        tabLayout.setupWithViewPager(viewpager);
//        */
//
//
//
//        initFragment();
//        initTabLayout();
//
//
//
//    }
//    private void initFragment(){
//        frame = (FrameLayout)findViewById(R.id.id_action_frameLayout);
//        couponFragment = new CouponFragment();
//        redFragment = new RedFragment();
//
//        fragments=new ArrayList<Fragment>();
//        fragments.add(couponFragment);
//      //  fragments.add(redFragment);
//        //默认提交第一个
//
//        getSupportFragmentManager().beginTransaction().add(R.id.id_action_frameLayout, fragments.get(0)).commit();
//    }
//    private void initTabLayout() {
//        tabLayout=(TabLayout)findViewById(R.id.tablayout);
//      //  tabLayout.addTab(tabLayout.newTab().setText("会员卡"));
//       // tabLayout.addTab(tabLayout.newTab().setText("礼品卷"));
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            private int position;
//
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                position = tab.getPosition();
//                replace(position);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//    }
//
//
//    //fragment的替换
//    private void replace(int targetPosition) {
//        //   事务职能commit 一次  所以 每次调用该方法的时候 都需要实例化一次事务
//        FragmentTransaction transaction = getSupportFragmentManager()
//                .beginTransaction();
//
//        // 获取当前显示的Fragment
//        Fragment cuFragment = fragments.get(currentPosition);
//        //获取目标的 Fragment
//        Fragment tarFragment = fragments.get(targetPosition);
//
//        //  判断 目标Fragment 是否被添加过
//        if (!tarFragment.isAdded()) {
//            // 没有添加过 则添加 并将当前Fragment 隐藏
//            transaction.add(R.id.id_action_frameLayout, tarFragment).hide(cuFragment)
//                    .commit();
//        } else {
//            //如果添加过 则将当前Fragment 隐藏 将目标Fragment 展示出来
//            transaction.show(tarFragment).hide(cuFragment).commit();
//        }
//
//        currentPosition = targetPosition;
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            Intent intent=new Intent(ActionActivity.this,MainActivity.class);
//            startActivity(intent);
//          //  finish();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//
//    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode ==0x105 && resultCode == Activity.RESULT_OK) {
//            Bundle extras = data.getExtras();
//            String code = extras.getString("result");
//
//            if ("".equals(code)) {
//                ToastUtils.showMessage("二维码错误");
//                return;
//            }
//            Log.e("lpl","二维码"+code);
//            //扫描商家二维码
//            if (code.contains("http://fx.coolubike.com/share.html?ChannelId")) {
//                //调用支付接口
//                payCard(code.substring(code.lastIndexOf("=")+1));
//                return;
//            }else {
//                ToastUtils.showMessage("二维码错误");
//            }
//
//
//        }
//    }
//
//    //充骑行卡
//    private void payCard(String cardId) {
//
//        Intent intent=new Intent(ActionActivity.this,ActivationActivity.class);
//        intent.putExtra("cardId",cardId);
//        //骑行卡激活界面
//        intent.putExtra("flag",2);
//        startActivity(intent);
//
//
//    }

}

