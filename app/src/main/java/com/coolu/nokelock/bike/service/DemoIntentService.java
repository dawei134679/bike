package com.coolu.nokelock.bike.service;

/**
 * Created by admin on 2017/11/2.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.util.Log;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.bean.BikeNewClockBeasn;
import com.coolu.nokelock.bike.util.Config;
import com.google.gson.Gson;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.FeedbackCmdMessage;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.igexin.sdk.message.SetTagCmdMessage;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */
public class DemoIntentService extends GTIntentService {

    public DemoIntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {

        Log.e("mpm","佛啊哈佛");
        Log.e(TAG, "onReceiveClientId -> " + "clientid =0");
        String appid = msg.getAppid();
        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();
        byte[] payload = msg.getPayload();
        String pkg = msg.getPkgName();
        String cid = msg.getClientId();
        Log.e("mpm","啊哈哈");
        boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
        Log.e("kok", "call sendFeedbackMessage = " + (result ? "success" : "failed"));
        if (payload == null) {
            Log.e("mpm", "onReceiveClientId -> " + "clientid =啊哈哈");
        } else {
            String data = new String(payload);
            Log.e("mpm", "onReceiveClientId -> " + "clientid =0"+data);

            handlerData(data,context);
        }
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
        App.getInstance().setCid(clientid);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
      //  Log.e(TAG, "onReceiveClientId -> " + "clientid = 1" );
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = 2");
    }


    /**
     * 处理透传内容
     * @param data 数据
     */
    private void handlerData(String data,Context context) {
        Log.e("mpm","来来来");
        try {
            //  App.getInstance().getIBLE().disconnect();
            Gson gson = new Gson();
            BikeNewClockBeasn bikeNewClockBeasn = gson.fromJson(data, BikeNewClockBeasn.class);
            Intent intent = new Intent(Config.PROGRESS_DIALOG);
            intent.putExtra("command", "4");
            App.getInstance().getDaoSession().getBikeOrderBeanDao().deleteAll();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("骑行总时长:");
            stringBuffer.append(bikeNewClockBeasn.getRiding().getUseMinute());
            stringBuffer.append("分钟\n");
            stringBuffer.append("行程花费:");
            stringBuffer.append(bikeNewClockBeasn.getRiding().getMoney());
            stringBuffer.append("元\n");
            String time="骑行时长： <font color='#EE5676'>"+bikeNewClockBeasn.getRiding().getUseMinute()+"</font> 分钟";
            String money="骑行花费： <font color='#EE5676'>"+bikeNewClockBeasn.getRiding().getMoney()+"</font> 元";
            //  intent.putExtra("json",stringBuffer.toString());
            intent.putExtra("time",time);
            intent.putExtra("money",money);

            sendBroadcast(intent);
        }catch (Exception e){
            e.printStackTrace();


            //获取NotificationManager实例
            NotificationManager notifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            //实例化NotificationCompat.Builde并设置相关属性
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    //设置小图标
                    .setSmallIcon(R.drawable.push)
                    //设置通知标题
                    .setContentTitle("酷游单车")
                    //设置这个标志当用户单击面板就可以让通知将自动取消
                    .setAutoCancel(true)
                    //向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：Notification.DEFAULT_ALL就是3种全部提醒
                    .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS)
                    //设置通知内容
                    .setContentText(data);

            NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle(builder);
            style.bigText(data);
            style.setBigContentTitle("酷游单车");
            //SummaryText没什么用 可以不设置
//            style.setSummaryText("");
            builder.setStyle(style);

            //设置通知时间，默认为系统发出通知的时间，通常不用设置
            //.setWhen(System.currentTimeMillis());
            //通过builder.build()方法生成Notification对象,并发送通知,id=1
            int msgId = App.msgId++;
            notifyManager.notify(msgId, builder.build());
        }
    }






}