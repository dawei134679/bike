package com.coolu.nokelock.bike.util;

import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chuanglan.shanyan_sdk.listener.ShanYanCustomInterface;
import com.chuanglan.shanyan_sdk.tool.ShanYanUIConfig;
import com.chuanglan.shanyan_sdk.utils.AbScreenUtils;
import com.coolu.nokelock.bike.activity.LoginActivity;


public class ConfigUtils {
    /**
     * 闪验三网运营商授权页配置类
     *
     * @param context
     * @return
     */
    public static ShanYanUIConfig getUiConfig(Context context) {
        /************************************************自定义控件**************************************************************/
        //其他方式登录
        TextView otherTV = new TextView(context);
        otherTV.setText("其他方式登录");
        otherTV.setTextColor(0xff3a404c);
        otherTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        RelativeLayout.LayoutParams mLayoutParams1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mLayoutParams1.setMargins(0, AbScreenUtils.dp2px(context, 280), 0, 0);
        mLayoutParams1.addRule(RelativeLayout.CENTER_HORIZONTAL);
        otherTV.setLayoutParams(mLayoutParams1);
        //标题栏下划线
     /*   View view = new View(context);
        view.setBackgroundColor(0xffe8e8e8);
        RelativeLayout.LayoutParams mLayoutParams3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, AbScreenUtils.dp2px(context, 1));
        mLayoutParams3.setMargins(0, AbScreenUtils.dp2px(context, 0), 0, 0);
        view.setLayoutParams(mLayoutParams3);*/

        /****************************************************设置授权页*********************************************************/
        ShanYanUIConfig uiConfig = new ShanYanUIConfig.Builder()
                //授权页导航栏：
                .setNavColor(0xffffffff)  //设置导航栏颜色
                .setNavText("免密登录")  //设置导航栏标题文字
                .setNavTextColor(0xff080808) //设置标题栏文字颜色
                .setNavReturnImgPath("sy_sdk_left")  //设置导航栏返回按钮图标

                //授权页logo（logo的层级在次底层，仅次于自定义控件）
                .setLogoImgPath("oauth_logo")  //设置logo图片
                .setLogoWidth(70)   //设置logo宽度
                .setLogoHeight(70)   //设置logo高度
                .setLogoOffsetY(100)  //设置logo相对于标题栏下边缘y偏移
                .setLogoHidden(false)   //是否隐藏logo

                //授权页号码栏：
                .setNumberColor(0xff333333)  //设置手机号码字体颜色
                .setNumFieldOffsetY(140)    //设置号码栏相对于标题栏下边缘y偏移

                //授权页登录按钮：
                .setLogBtnText("本机号码一键登录")  //设置登录按钮文字
                .setLogBtnTextColor(0xffffffff)   //设置登录按钮文字颜色
                .setLogBtnImgPath("onekeybackground")   //设置登录按钮图片
                .setLogBtnOffsetY(220)   //设置登录按钮相对于标题栏下边缘y偏移

                //授权页隐私栏：
                /*.setAppPrivacyOne("用户自定义协议条款", "https://www.253.com")  //设置开发者隐私条款1名称和URL(名称，url)
                .setAppPrivacyTwo("用户服务条款", "https://www.253.com")  //设置开发者隐私条款2名称和URL(名称，url)*/
                .setAppPrivacyOne("注册协议", "https://ws.coolubike.com/shop/html/reguser.html")  //用户注册协议
                .setAppPrivacyColor(0xff666666, 0xff0085d0)   //	设置隐私条款名称颜色(基础文字颜色，协议文字颜色)
                .setPrivacyOffsetY(30)//设置隐私条款相对于标题栏下边缘y偏移

                //授权页slogan：
                .setSloganTextColor(0xff999999)  //设置slogan文字颜色
                .setSloganOffsetY(195)  //设置slogan相对于标题栏下边缘y偏移

                // 添加自定义控件:
                //其他方式登录及监听
                .addCustomView(otherTV, true, false, new ShanYanCustomInterface() {
                    @Override
                    public void onClick(Context context, View view) {
//                        Toast.makeText(context, "其他方式登录", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context,LoginActivity.class));
                    }
                })
                //标题栏下划线，可以不写
                //.addCustomView(view, true, false, null)
                .build();
        return uiConfig;

    }

}
