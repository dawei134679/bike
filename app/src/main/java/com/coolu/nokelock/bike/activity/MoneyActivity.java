package com.coolu.nokelock.bike.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.bean.ReturnBalanceBean;
import com.coolu.nokelock.bike.bean.UserEntity;
import com.coolu.nokelock.bike.bean.UserEntityBean;
import com.coolu.nokelock.bike.prensenter.MainPresenter;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.ConditionsUtils;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.TimeUtil;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.LoadingLayoutProxy;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.internal.Util;

public class MoneyActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout red;
    private RelativeLayout balance_rela;
    private TextView cz;
    private ImageView back;
    private TextView money_item;
    private TextView safe;
    private ImageView exanchge;
    private ImageView title_img;
    @BindView(R.id.id_money)
    TextView money;
    @BindView(R.id.id_recharge_tv)
    TextView recharge;
    @BindView(R.id.id_menber_time)
    TextView menber_time;
    @BindView(R.id.id_red_bouns)
    TextView bouns;
    @BindView(R.id.ptr_sv_body)
    PullToRefreshScrollView ptr_sv_body;

    //    Leaarning add
    @BindView(R.id.tv_return_balance)
    TextView tv_return_balance;
    @BindView(R.id.rl_deposit)
    RelativeLayout rl_deposit;
    @BindView(R.id.tv_card_buy)
    TextView tv_card_buy;
    @BindView(R.id.rl_buy)
    RelativeLayout rl_buy;

    private UserEntity.ResultBean.UserBean entity;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 101:
                    if (App.getInstance().getUserEntityBean() != null && App.getInstance().getUserEntityBean().getUserMoney() != null) {
                        money.setText(App.getInstance().getUserEntityBean().getUserMoney());
                    }
                    if (App.getInstance().getUserEntityBean() != null && App.getInstance().getUserEntityBean().getDeposit() != null) {
                        if (Double.parseDouble(App.getInstance().getDeposit()) > 0) {
                            safe.setText("退保证金");
                        } else {
                            safe.setText("交保证金");
                        }
                        recharge.setText(App.getInstance().getUserEntityBean().getDeposit());
                    }
                    break;
                case 102:

                    break;
            }

        }
    };
    private LoadingLayoutProxy layoutProxy;
    private String hour = "无记录";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_money);
        ButterKnife.bind(this);
        initPullToRefresh();
        initView();
    }


    private void initView() {
        if (App.getInstance().getUserEntityBean() != null && "0".equals(App.getInstance().getUserEntityBean().getUserLevel())) {
            menber_time.setVisibility(View.GONE);
        } else {
            menber_time.setVisibility(View.VISIBLE);
        }
        if (App.getInstance().getUserEntityBean() != null && App.getInstance().getUserEntityBean().getUserLevelEndTime() != null) {
            menber_time.setText("到期时间:" + App.getInstance().getUserEntityBean().getUserLevelEndTime());
        }
        //红包
        if (App.getInstance().getUserEntityBean() != null && App.getInstance().getUserEntityBean().getUserBonus() != null) {
            bouns.setText(App.getInstance().getUserEntityBean().getUserBonus());
        }
        back = (ImageView) findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoneyActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        title_img = (ImageView) findViewById(R.id.id_money_title);
        title_img.setOnClickListener(this);

        //兑换
        exanchge = (ImageView) findViewById(R.id.id_exchange);
        exanchge.setOnClickListener(this);

        //我的红包
        red = (RelativeLayout) findViewById(R.id.id_red_content);
        red.setOnClickListener(this);
        //充值
        cz = (TextView) findViewById(R.id.id_cz);
        cz.setOnClickListener(this);
        //退保证金
        safe = (TextView) findViewById(R.id.recharge);
        safe.setOnClickListener(this);

        //，明细
        money_item = (TextView) findViewById(R.id.id_money_item);
        money_item.setOnClickListener(this);

        tv_return_balance.setOnClickListener(this);
        tv_card_buy.setOnClickListener(this);
    }


    @Override
    protected void onResume() { //最好请求人员表
        super.onResume();
        if (App.getInstance().getUserEntityBean() != null && "1".equals(App.getInstance().getUserEntityBean().getUserLevel())) {
            title_img.setImageResource(R.mipmap.card23);
            rl_buy.setVisibility(View.GONE);
        }else {
            title_img.setImageResource(R.mipmap.card20);
            rl_buy.setVisibility(View.VISIBLE);
        }

        handler.sendEmptyMessage(101);
        pushMoney(0);
        getinfo();

    }

    //请求人员表
    private void getinfo() {
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            Intent intent = new Intent(MoneyActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_red_content:
                Intent intent = new Intent(MoneyActivity.this, RedDetailsActivity.class);
                intent.putExtra("title", "我的红包明细");
                intent.putExtra("flag", "0");
                startActivity(intent);
                break;

            case R.id.id_cz:
                //充值余额
                Intent recharge = new Intent(MoneyActivity.this, RechargeActivity.class);
                recharge.putExtra("flag", 1);
                startActivity(recharge);
                break;
            case R.id.id_money_item:
                Intent money_item = new Intent(MoneyActivity.this, RedDetailsActivity.class);
                money_item.putExtra("title", "我的钱包明细");
                money_item.putExtra("flag", "1");
                startActivity(money_item);
                break;
            case R.id.recharge:
                //退保证金和交保证金
                if ("退保证金".equals(safe.getText().toString().trim())) {

                    if (App.getInstance().getUserEntityBean() != null && Double.parseDouble(App.getInstance().getUserEntityBean().getDeposit()) <= 0) {
                        ToastUtils.showMessage("没有充保证金，不能退款");
                        Intent re = new Intent(MoneyActivity.this, RechargeActivity.class);
                        //充余额
                        re.putExtra("flag", 0);
                        startActivity(re);
                        return;
                    }
                    if (App.getInstance().getUserEntityBean() != null && Double.parseDouble(App.getInstance().getUserEntityBean().getUserMoney()) < 0) {


//                        ToastUtils.showMessage("余额不能为负，请充值");
//                        Intent rechargee=new Intent(MoneyActivity.this,RechargeActivity.class);
//                        //充余额
//                        rechargee.putExtra("flag",1);
//                        startActivity(rechargee);
                        Intent rechargee = new Intent(MoneyActivity.this, BalanceIsNegativeActivity.class);
                        startActivity(rechargee);
                        return;

                    }
                    if (App.getInstance().getUserEntityBean() != null && TextUtils.equals(App.getInstance().getUserEntityBean().getUserStatus(), "1")) {
                        ToastUtils.showMessage("车辆正在使用，不能退款");
                        return;
                    }

                    pushMoney(1);
//                    if (!("0".equals(App.getInstance().getUserEntityBean().getUserStatus()))){
//                        ToastUtils.showMessage("当前用户状态异常,请联系客服后重试！");
//                        return;
//                    }
//                    Intent safe=new Intent(MoneyActivity.this,SafeActivity.class);
//                    safe.putExtra("flag","safe");
//                    startActivity(safe);
                }
                //交保证金
                else if ("交保证金".equals(safe.getText().toString().toString())) {
                    Intent charge = new Intent(MoneyActivity.this, RechargeActivity.class);
                    charge.putExtra("flag", 0);
                    startActivity(charge);
                }

                break;
            case R.id.id_exchange: //兑换
                Intent ex = new Intent(MoneyActivity.this, ActionActivity.class);
                startActivity(ex);
                break;
//                退余额
            case R.id.tv_return_balance:
                returnBalance();
                break;
//                会员卡
            case R.id.id_money_title:
                if (App.getInstance().getUserEntityBean() != null) {
//                判断是否是会员
                    String userLevel = App.getInstance().getUserEntityBean().getUserLevel();
                    if (!TextUtils.equals("1", userLevel)) {
                        Intent intentAction = new Intent(MoneyActivity.this, ActionActivity.class);
                        startActivity(intentAction);
                    }
                }
                break;
            case R.id.tv_card_buy:
                if (App.getInstance().getUserEntityBean() != null) {
//                判断是否是会员
                    String userLevel = App.getInstance().getUserEntityBean().getUserLevel();
                    if (!TextUtils.equals("1", userLevel)) {
                        Intent intentAction = new Intent(MoneyActivity.this, ActionActivity.class);
                        startActivity(intentAction);
                    }
                }
                break;
        }
    }

    private void initPullToRefresh() {
        //获取带有刷新的对应控件
        ptr_sv_body.getRefreshableView();
        /**
         * 设置刷新的模式：常用的有三种
         * PullToRefreshBase.Mode.BOTH  //上下拉刷新都可以
         * PullToRefreshBase.Mode.PULL_FROM_START  //只允许下拉刷新
         * PullToRefreshBase.Mode.PULL_FROM_END   //只允许上拉刷新
         *
         */
        ptr_sv_body.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        //设置是否允许刷新的时候可以滑动
        ptr_sv_body.setScrollingWhileRefreshingEnabled(true);
        layoutProxy = (LoadingLayoutProxy) ptr_sv_body.getLoadingLayoutProxy(true, false);
        ptr_sv_body.setOnPullEventListener(new PullToRefreshBase.OnPullEventListener<ScrollView>() {
            @Override
            public void onPullEvent(PullToRefreshBase<ScrollView> refreshView, PullToRefreshBase.State state, PullToRefreshBase.Mode direction) {
                //下拉的时候显示的文本
                layoutProxy.setPullLabel("最后更新时间：" + hour);
                //可以放开刷新的时候显示的文本
                layoutProxy.setReleaseLabel("最后更新时间：" + hour);
                hour = TimeUtil.currentHour();
                //执行刷新的时候显示的文本
                layoutProxy.setRefreshingLabel("最新更新时间：" + hour);
                //设置加载的图片
                //layoutProxy.setLoadingDrawable(getResources().getDrawable(R.drawable.animatorss));
            }
        });
        ptr_sv_body.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                pushMoney(0);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });
    }

    private void pushMoney(int witch) {
        HashMap<String, String> map = VolleyUtils.getCommomParameter();
        map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            postUserInfo(Config.PERSON, Url.PEOPLE, map, "getPerson", witch);
        }
    }


    private void postUserInfo(final String action, String url, HashMap<String, String> map, String flag, final int witch) {
        VolleyUtils.deStringPost(App.getInstance().getApplicationContext(), url, map, flag, new VolleyUtils.volleyListener() {
            @Override
            public void onResponse(JSONObject response) {

            }

            @Override
            public void onErrorResponse(String errorMessage) {

            }

            @Override
            public void onResponse(String response) {
//                Log.e("TAG", "钱包界面的结果是："+response);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    String errorCode = jsonObject.getString("errorCode");
                    String messageCode = jsonObject.getString("message");

                    if ("200".equals(errorCode)) {
                        Gson gson = new Gson();
                        try {
                            resolveJson(response, gson, witch);
                        } catch (NullPointerException e) {

                        }
                    } else if ("301".equals(errorCode)) {
                        App.getInstance().getIBLE().disconnect();
                        UtilSharedPreference.saveString(ConditionsUtils.getContext(), Config.PHONE, "");
                        UtilSharedPreference.saveString(ConditionsUtils.getContext(), Config.TOKEN, "");
                        App.getInstance().getDaoSession().getUseBeanDao().deleteAll();
                        App.getInstance().getDaoSession().getSearchBeanDao().deleteAll();
                        App.getInstance().getDaoSession().getBikeOrderBeanDao().deleteAll();
                        App.getInstance().getDaoSession().getOrderBeanDao().deleteAll();
                        //个人信息清空
                        App.getInstance().setUserEntityBean(null);
                        ToastUtils.showMessage("登陆失效，请重新登陆");
                    } else {
//                        implMain.errorMessage(action, Config.NO_JSON);
//                        if (TextUtils.equals(action,Config.GETMAC)){//查询订单信息有误，开锁的对话框消失
//                            implMain.CancleDiolag();
//                            ToastUtils.showMessage(messageCode);
//                        }
                    }
                } catch (JSONException e) {
//                    implMain.errorMessage(action, Config.HTTP_NO_CONNECT);
                    e.printStackTrace();
                }
                //  resolveJson(response, action);
            }
        });

    }

    //人员信息表
    private void resolveJson(String response, Gson gson, int witch) {
        UserEntity entity = gson.fromJson(response, UserEntity.class);
//        UserEntityBean user=new UserEntityBean();

        App.getInstance().getUserEntityBean().setUserId(entity.getResult().getUser().getUserId() + "");
        App.getInstance().getUserEntityBean().setNicName(entity.getResult().getUser().getNicName());

        App.getInstance().getUserEntityBean().setIdcheck(entity.getResult().getUser().getIdcheck() + "");
        App.getInstance().getUserEntityBean().setPinNo(entity.getResult().getUser().getPinNo());
        App.getInstance().getUserEntityBean().setPinTime(entity.getResult().getUser().getPinTime());
        App.getInstance().getUserEntityBean().setUserToken(entity.getResult().getUser().getUserToken());
        App.getInstance().getUserEntityBean().setLoginTime(entity.getResult().getUser().getLoginTime());
        App.getInstance().getUserEntityBean().setDeposit(entity.getResult().getUser().getDeposit() + "");
        App.getInstance().setDeposit(entity.getResult().getUser().getDeposit());  //保证金
        App.getInstance().getUserEntityBean().setDefaultDeposit(entity.getResult().getUser().getDefaultDeposit() + "");
        App.getInstance().getUserEntityBean().setOrderNo(entity.getResult().getUser().getOrderNo());
        App.getInstance().getUserEntityBean().setUserMoney(entity.getResult().getUser().getUserMoney());
        App.getInstance().getUserEntityBean().setUserBonus(entity.getResult().getUser().getUserBonus());
        App.getInstance().getUserEntityBean().setUserType(entity.getResult().getUser().getUserType() + "");
        App.getInstance().getUserEntityBean().setUserStatus(entity.getResult().getUser().getUserStatus());
        App.getInstance().getUserEntityBean().setUserCredit(entity.getResult().getUser().getUserCredit());
        App.getInstance().getUserEntityBean().setIdealMoney(entity.getResult().getUser().getIdealMoney());
        App.getInstance().getUserEntityBean().setUserLevel(entity.getResult().getUser().getUserLevel());
        App.getInstance().getUserEntityBean().setUserFrom(entity.getResult().getUser().getUserFrom());
        App.getInstance().getUserEntityBean().setShebieId(entity.getResult().getShebeiid());
        App.getInstance().getUserEntityBean().setRidingprice(entity.getResult().getRidingprice());
        App.getInstance().getUserEntityBean().setLockid(entity.getResult().getLockid());
        App.getInstance().getUserEntityBean().setUserPic(entity.getResult().getUser().getUserPic());
        App.getInstance().getUserEntityBean().setOpenmoney(entity.getResult().getOpenmoney());
        App.getInstance().getUserEntityBean().setNewopenmoney(entity.getResult().getNewopenmoney());
        App.getInstance().getUserEntityBean().setUserLevelEndTime(entity.getResult().getUser().getUserLevelEndTime());
        App.getInstance().getUserEntityBean().setBarCode(entity.getResult().getBarcode());
        App.getInstance().getUserEntityBean().setLockmac(entity.getResult().getLockmac());
        App.getInstance().getUserEntityBean().setLockdata(entity.getResult().getLockdata());
        App.getInstance().getUserEntityBean().setStarttime(entity.getResult().getStarttime());
        App.getInstance().getUserEntityBean().setLocktype(entity.getResult().getLocktype());
        App.getInstance().getUserEntityBean().setUserName(entity.getResult().getUser().getUserName());
        App.getInstance().getUserEntityBean().setIdno(entity.getResult().getUser().getIdno());
        App.getInstance().getUserEntityBean().setForcemoney(entity.getResult().getForcemoney()); //调度费20
        App.getInstance().setForceMoney(entity.getResult().getForcemoney());
        App.getInstance().getUserEntityBean().setUserCredit(entity.getResult().getUser().getUserCredit());
        App.getInstance().getUserEntityBean().setCardprice(entity.getResult().getCardprice()); //优惠券你的金额
        App.getInstance().getUserEntityBean().setUserBonus(entity.getResult().getUser().getUserBonus());
        App.getInstance().getUserEntityBean().setIsbuyridingcard(entity.getResult().getIsbuyridingcard());
        App.getInstance().getUserEntityBean().setWarningmoney(entity.getResult().getWarningmoney());

//        App.getInstance().setUserEntityBean(user);

        if (App.getInstance().getUserEntityBean() != null && "0".equals(App.getInstance().getUserEntityBean().getUserLevel())) {
            menber_time.setVisibility(View.GONE);
        } else {
            menber_time.setVisibility(View.VISIBLE);
        }
        if (App.getInstance().getUserEntityBean() != null && App.getInstance().getUserEntityBean().getUserLevelEndTime() != null) {
            menber_time.setText("到期时间:" + App.getInstance().getUserEntityBean().getUserLevelEndTime());
        }
        //红包
        if (App.getInstance().getUserEntityBean() != null && App.getInstance().getUserEntityBean().getUserBonus() != null) {
            bouns.setText(App.getInstance().getUserEntityBean().getUserBonus());
        }
        if (App.getInstance().getUserEntityBean() != null && App.getInstance().getUserEntityBean().getUserMoney() != null) {
            money.setText(App.getInstance().getUserEntityBean().getUserMoney());
        }
        if (App.getInstance().getUserEntityBean() != null && App.getInstance().getUserEntityBean().getDeposit() != null) {
            if (Double.parseDouble(App.getInstance().getDeposit()) > 0) {
                safe.setText("退保证金");
            } else {
                safe.setText("交保证金");
            }
//            double deposit = Double.parseDouble(App.getInstance().getUserEntityBean().getDeposit());
//            Log.e("TAG","保证金是："+App.getInstance().getUserEntityBean().getDeposit());
            recharge.setText(App.getInstance().getUserEntityBean().getDeposit());
        }
        ptr_sv_body.onRefreshComplete();
        if (1 == witch) {
            if (!("0".equals(App.getInstance().getUserEntityBean().getUserStatus()))) {
                ToastUtils.showMessage("当前用户状态异常,请联系客服后重试！");
                return;
            }
            Intent safe = new Intent(MoneyActivity.this, SafeActivity.class);
            safe.putExtra("flag", "safe");
            startActivity(safe);
        }
    }

    private void returnBalance() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MoneyActivity.this);
        builder.setMessage("余额在29元以下将无法骑行，确定要退余额吗？");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                returnBalanceOk();
            }
        });
        builder.show();
    }

    private void returnBalanceOk() {
        HashMap<String, String> map = VolleyUtils.getCommomParameter();
        map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.deStringPost(MoneyActivity.this, Url.RETURNBALANCE, map, null, new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {

                }

                @Override
                public void onErrorResponse(String errorMessage) {
                    Log.e("TAG", "错误信息是：" + errorMessage);

                }

                @Override
                public void onResponse(String response) {
//                    Log.e("TAG","退余额成功："+response);
                    ReturnBalanceBean returnBalanceBean = VolleyUtils.parseJsonWithGson(response, ReturnBalanceBean.class);
                    if (returnBalanceBean.getErrorCode() == 200) {
                        money.setText("0.00");
                    }
                    Toast.makeText(MoneyActivity.this, returnBalanceBean.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
