package com.coolu.nokelock.bike.url;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

/**
 * Created by admin on 2017/8/4.  192.168.1.120
 * https://w.coolubike.com/onetriptech-bike-app/user/register.json
 * https://w.coolubike.com/onetriptech-bike-app/user/register.json
 */
public class Url {
    public static final  String actionurl="https://w.coolubike.com/onetriptech-bike-app/activity/topActivity.json";
    public static final String redurl="https://w.coolubike.com/onetriptech-bike-app/bike/getRedBike.json";
    public static final String pile="https://w.coolubike.com/onetriptech-bike-app/stopPointBiao/selectStopPointBiaoAllJson.json";
    //登录注册
    public static final String LOGIN="https://w.coolubike.com/onetriptech-bike-app/user/register.json";
    //获得图形验证码
    public static final String imgCode="https://w.coolubike.com/onetriptech-bike-app/user/getImageCode.json";
    //获得code
//    public static final String getCode="https://w.coolubike.com/onetriptech-bike-app/user/getCode.json";
//    新的获取手机验证码
    public static final String NEWGETCODE="https://w.coolubike.com/onetriptech-bike-app/user/getPhoneCode.json";
    //红包明细
    public static final String RED="https://w.coolubike.com/onetriptech-bike-app/payment/selectPayment.json";
    public static final String TRIP="https://w.coolubike.com/onetriptech-bike-app/riding/selectRiding.json";
    //个人信息
    public static final String PEOPLE="https://w.coolubike.com/onetriptech-bike-app/user/selectUserInfo.json";

    //吃喝玩乐
    public static final String GETLIST="https://info.coolubike.com/appservice.asmx/getArticleList";
    //吃喝玩乐图片的基网址
    public static final String IMAGE="https://cdn.coolubike.com/";
    //推荐骑行
    public static final String QIXING="https://info.coolubike.com/appservice.asmx/getRouteList";
    //活动列表
    public static final String ACTION_LIST="https://w.coolubike.com/onetriptech-bike-app/activity/activityList.json";
    //活动参与
    public static final String ACTION_JION="https://w.coolubike.com/onetriptech-bike-app/activity/takeActivity.json";
    //我的卡卷
    public static final String CARD="https://w.coolubike.com/onetriptech-bike-app/userCard/selectUserCard.json";

    public static final String USER="https://w.coolubike.com/onetriptech-bike-app/user/selectUserInfo.json";

    //开锁
    public static final String OPENCOLK="https://w.coolubike.com/onetriptech-bike-app/bike/openBike.json";
    //订单
    public static final String ORDER="https://w.coolubike.com/onetriptech-bike-app/riding/upBleState.json";

    //个人信息
    public static final String PERSION="https://w.coolubike.com/onetriptech-bike-app/user/selectUserInfo.json";

    //支付宝   https://w.coolubike.com/onetriptech-bike-app/alipay/payDeposit.json
    //https://192.168.1.122/onetriptech-bike-app/alipay/payDeposit.json
    public static final String ZF="https://w.coolubike.com/onetriptech-bike-app/alipay/payDeposit.json";

    //微信
    public static final String WX="https://w.coolubike.com/onetriptech-bike-app/wxpay/topay.json";
    //微信退款
    public static final String YAJIN="https://w.coolubike.com/onetriptech-bike-app/wxpay/refundPay.json";
    public static final String TUI="https://w.coolubike.com/onetriptech-bike-app/pay/refund.json";
    //上传头像
    public static final String TOU="https://w.coolubike.com/onetriptech-bike-app/upload/uploadImg.json";
    //绑定卡卷
    public static final String EXCARD="https://w.coolubike.com/onetriptech-bike-app/activityOffline/userBindCard.json";

    //身份认证
    public static final String SHENFEN="https://w.coolubike.com/onetriptech-bike-app/user/updateIDCheck.json";
    //修改昵称
    public static final String NICK="https://w.coolubike.com/onetriptech-bike-app/user/updateNicName.json";
    public static final String NC="https://w.coolubike.com/onetriptech-bike-app/user/updateNicName.json";

    //版本更新
    public static final String VERSION="https://w.coolubike.com/onetriptech-bike-app/version/selectVersion.json";

    //新锁开锁接口
    public static final String opp="https://w.coolubike.com/onetriptech-bike-app/NokeLock/send.json";

    public static final String SHOP="https://w.coolubike.com/onetriptech-bike-app/user/getShopAdd.json";

    //骑行列表接口
    public static  final String ROUTEURL="https://info.coolubike.com/appservice.asmx/getRouteListBylnglat";
    //商铺地址的接口
    public static final String SHOPURL="https://w.coolubike.com/onetriptech-bike-app/user/getShopUrl.json";

    //开屏广告
    public static final String OPENIMAGE="https://w.coolubike.com/user/getAdUrl.json?name=";

//    Learning add
//    退余额
    public static final String RETURNBALANCE="https://w.coolubike.com/onetriptech-bike-app/pay/reBalance.json";
//    余额不足界面
    public static final String LESSBALANCE="https://w.coolubike.com/onetriptech-bike-app/card/selectCard.json";
//    闪验获取手机号
    public static final String GETPHONENUMBER="https://w.coolubike.com/onetriptech-bike-app/user/flashtest.json";
//    一键登录/注册
    public static final String FLASHREGISTER="https://w.coolubike.com/onetriptech-bike-app/user/flashregister.json";













    public static boolean isWifiProxy(Context context) {
        final boolean IS_ICS_OR_LATER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
        String proxyAddress;
        int proxyPort;
        if (IS_ICS_OR_LATER) {
            proxyAddress = System.getProperty("https.proxyHost");
            String portStr = System.getProperty("https.proxyPort");
            proxyPort = Integer.parseInt((portStr != null ? portStr : "-1"));
        } else {
            proxyAddress = android.net.Proxy.getHost(context);
            proxyPort = android.net.Proxy.getPort(context);
        }
        return (!TextUtils.isEmpty(proxyAddress)) && (proxyPort != -1);
    }




}
