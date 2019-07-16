package com.coolu.nokelock.bike.util;

import android.app.Application;

import com.fitsleep.sunshinelibrary.utils.ToolsUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/21 0021.
 * time 2016-05-13
 */
public class ConditionsUtils {
    private static Application context;
    private static String appType = "ANDROID";

    public static void init(Application context) {
        ConditionsUtils.context = context;
    }

    public static Application getContext() {
        return context;
    }

    public static Map<String,String> getHeader(){
        Map<String,String> map = new HashMap<>();
        String languages = ToolsUtils.getLanguages(context);
        if (languages.endsWith("zh")){
            languages = "zh-CN";
        }else if(languages.endsWith("ja")){
            languages = "ja-JP";
        }else {
            languages = "en-US";
        }
        String appVersion = ToolsUtils.getVersion(context);
        String sysVersion = android.os.Build.VERSION.RELEASE;
        map.put("appLang",languages);
        map.put("appType",appType);
        map.put("appVersion",appVersion);
        map.put("sysVersion",sysVersion);
        return map;
    }

    public static String convert() {
        Date date = new Date(System.currentTimeMillis());
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            strs = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }

//    /**
//     * 显示错误信息
//     * @param msg
//     */
//    public static void showErrorMsg(int msg){
//        switch (msg){
//            case 1:
//                ToastUtils.showMessage(context,context.getString(R.string.system_error));
//                break;
//            case 2:
//                ToastUtils.showMessage(context,context.getString(R.string.fail));
//                break;
//            case 3:
//                ToastUtils.showMessage(context,context.getString(R.string.login_error));
//                break;
//            case 4:
//                ToastUtils.showMessage(context,context.getString(R.string.no_token));
//                break;
//            case 5:
//                ToastUtils.showMessage(context,context.getString(R.string.token_error));
//                break;
//            case 6:
//                ToastUtils.showMessage(context,context.getString(R.string.param_error));
//                break;
//            case 7:
//                Logger.e("okhttp","提交的数据已存在");
//                break;
//            case 8:
//                ToastUtils.showMessage(context,context.getString(R.string.head_comp));
//                break;
//        }
//    }

//    public static void logoutAlertDialog(){
////        AlertDialog.Builder builder = new AlertDialog.Builder(context);
////        builder.setMessage(context.getString(R.string.login_other)).setPositiveButton(android.R.string.yes,
////                new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int id) {
////                        // 强制登出,清除数据
//////                        UserDao dao = new UserDao(context.getApplicationContext());
//////                        dao.deleteAll();
//////                        UserManager.getInstance(context.getApplicationContext()).logout();
////                        dialog.dismiss();
//////                        ActManager.getAppManager().finishAllActivity();
////                    }
////                });
////        builder.create().show();
//        Intent inte = new Intent(BroadcastConfig.LOGOUT);
//        context.sendBroadcast(inte);
//    }
//
//    public static void showDialog(){
//        Intent inte = new Intent(BroadcastConfig.SHOW_DIALOG);
//        context.sendBroadcast(inte);
//    }
//
//    public static void dismissDialog(){
//        Intent inte = new Intent(BroadcastConfig.DISMISS_DIALOG);
//        context.sendBroadcast(inte);
//    }
}
