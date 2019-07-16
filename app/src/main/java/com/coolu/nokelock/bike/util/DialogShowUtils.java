package com.coolu.nokelock.bike.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.activity.CaptureActivity;
import com.coolu.nokelock.bike.activity.LoginActivity;
import com.coolu.nokelock.bike.activity.MainActivity;
import com.coolu.nokelock.bike.adapter.CardAdapter;
import com.coolu.nokelock.bike.adapter.CloseClockAdapter;
import com.coolu.nokelock.bike.bean.BikeClockBean;
import com.coolu.nokelock.bike.bean.CardTipBean;
import com.coolu.nokelock.bike.youzan.YouzanActivity;
import com.fitsleep.sunshinelibrary.inter.OnDialogClickListener;
import com.fitsleep.sunshinelibrary.utils.ImageUtils;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.ScreenUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.squareup.picasso.Picasso;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/9/1.
 */

public class DialogShowUtils {



    private static ShanYanLoginUtil shanYanLoginUtil;

    public static Dialog showDialogResult(Context context, int res, int width){
        View dialogView = View.inflate(context, res, null);
        final Dialog dialog = new Dialog(context, com.fitsleep.sunshinelibrary.R.style.no_title);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setContentView(dialogView);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        if (width==0){
            layoutParams.width= ViewGroup.LayoutParams.WRAP_CONTENT;
        }else {
            layoutParams.width= ViewGroup.LayoutParams.MATCH_PARENT;
        }
        layoutParams.height= ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        return dialog;
    }

    /**
     * 分享（图片）
     */
    public static SendMessageToWX.Req share(Context context, Bitmap b,int i){
       // Bitmap bmp= BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon);

        //初始化WXIMageObject和WXMediaMessage对象
        WXImageObject imageObject=new WXImageObject(b);
        WXMediaMessage msg=new WXMediaMessage();
        msg.mediaObject=imageObject;
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(b, 100, 100, true);
        b.recycle();
        msg.thumbData=bmpToByteArray(scaledBitmap,true);
        //构造一个Req
        SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction=buildTransaction("img");
        req.message=msg;
        req.scene = i == 1 ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        return req;

    }

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    //对话框
    public static void getProgressDialog(Context context, String message,int view, final OnDialogClickListener onDialogClickListener) {
        View dialogView = View.inflate(context, view, null);
        final Dialog dialog = new Dialog(context, com.fitsleep.sunshinelibrary.R.style.no_title);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(dialogView);
        ((TextView) dialogView.findViewById(R.id.tv_tishi)).setText(Html.fromHtml("您当前没有在酷游专用停车位停车，系统将收取您<font color='#EE5676' size='8'>"+message+"</font>元调度费用"));
        ((TextView) dialogView.findViewById(R.id.id_return_bike)).setText(Html.fromHtml("是否继续还车？"));
        ((TextView) dialogView.findViewById(R.id.id_one)).setText(Html.fromHtml("1.若您<font color='#3BE777'>已经进入</font>酷游停车位，请点击”否“，再次还车"));

        dialogView.findViewById(R.id.dialog_forced_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                onDialogClickListener.OnClickListener();
            }
        });
        dialogView.findViewById(R.id.dialog_forced_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    //登录
    public static void getProgressDialogg(Context context, String message,int view, final OnDialogClickListener onDialogClickListener) {
        View dialogView = View.inflate(context, view, null);
        final Dialog dialog = new Dialog(context, com.fitsleep.sunshinelibrary.R.style.no_title);
        ((TextView) dialogView.findViewById(R.id.tv_dialog_content)).setText(message);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(dialogView);
        dialogView.findViewById(R.id.tv_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                onDialogClickListener.OnClickListener();
            }
        });
        dialogView.findViewById(R.id.tv_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     *
     * @param context
     * @param view
     * @param url
     * @return
     */
    public static Dialog showTipsDialog(final Activity context, int view, String url, final String openUrl){
       // View dialogView = View.inflate(context, view, null);
        final Dialog dialog = new Dialog(context, com.fitsleep.sunshinelibrary.R.style.no_title);
        View inflate = LayoutInflater.from(context).inflate(view,null);
        ImageView pop = (ImageView) inflate.findViewById(R.id.id_popup);
        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.PHONE))) {
                    ToastUtils.showMessage("请先登录");
//                    Intent intent=new Intent(context,LoginActivity.class);
//                    context.startActivity(intent);

//                    Learning add
                    if (shanYanLoginUtil==null){
                        shanYanLoginUtil=new ShanYanLoginUtil(context);
                    }
                    shanYanLoginUtil.shanYanLogin();

                    return;
                }
                try {
                    if ("1".equals(App.getInstance().getUserEntityBean().getUserStatus())){
                        //有订单，返回
                        Log.e("kkk","有订单");
                        ToastUtils.showMessage("已有订单");
                        return;
                    }
                }catch (NullPointerException e){

                }


                Intent agreememt=new Intent(context,YouzanActivity.class);
                Bundle b=new Bundle();
                b.putString("url",openUrl);//"https://h5.youzan.com/v2/feature/7CPxzF7hcq"
                //  b.putString("route","用户协议");
                //  b.putInt("flag",3);
                agreememt.putExtras(b);
                context.startActivity(agreememt);

            }
        });
        //设置图片
        if (pop!=null&&!TextUtils.isEmpty(url)) {
            //Picasso.with(getActivity()).invalidate(Uri.parse(outUrl));

            Picasso.with(context).load(url).into(pop);


        }
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        dialog.show();
        return  dialog;
    }

    /**
     *  开锁提示对话框
     * @param context

     */
    public static Dialog showOpenDialog(final Context context){
      //   View dialogView = View.inflate(context,R.layout.openclocktips, null);
        final Dialog dialog = new Dialog(context, com.fitsleep.sunshinelibrary.R.style.no_title);
        View inflate = LayoutInflater.from(context).inflate(R.layout.openclocktips,null);
        ImageView pop = (ImageView) inflate.findViewById(R.id.id_clock);
        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if (dialog!=null)
                 dialog.dismiss();

            }
        });

        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        dialog.show();
        return  dialog;
    }


    public static Dialog showTipsDialog(final Context context,List<BikeClockBean.ResultBean.YouhuiBean> youhui){
        final Dialog dialog = new Dialog(context, R.style.no_title);
        View view = View.inflate(context, R.layout.card_tip_dailog, null);
        RecyclerView    recyclerView = (RecyclerView)view.findViewById(R.id.id_recyclerView);
        LinearLayoutManager    layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        //适配器
        CloseClockAdapter redAdapter = new CloseClockAdapter(context,youhui);
        recyclerView.setAdapter(redAdapter);




        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);
      //  dialog.show();
        return  dialog;
    }

    public static Dialog showNewCardTipsDialog(final Context context,List<CardTipBean.ResultBean.YouhuiBean> youhui){
        final Dialog dialog = new Dialog(context, R.style.no_title);
        View view = View.inflate(context, R.layout.card_tip_dailog, null);
        RecyclerView    recyclerView = (RecyclerView)view.findViewById(R.id.id_recyclerView);
        LinearLayoutManager    layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        //适配器
        CardAdapter redAdapter = new CardAdapter(context,youhui);
        recyclerView.setAdapter(redAdapter);




        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);
        //  dialog.show();
        return  dialog;
    }

    private static final String APP_CACHE_DIRNAME = "/webcache"; // web缓存目录

//    learning add
    public static Dialog dialog;
    public static void showTipsDialog(final Context context){
        if (dialog==null){
            dialog = new Dialog(context, R.style.no_title);
        }
        if (!dialog.isShowing()){
            View view = View.inflate(context, R.layout.dialog_layout, null);
            // ((TextView) view.findViewById(R.id.tv_tips)).setText(Html.fromHtml(context.getText(R.string.tips).toString()));
            view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // dialog.dismiss();
                    //if (!TextUtils.isEmpty(UtilSharedPreference.getStringValue(context.getApplicationContext(), Config.PHONE))){
                    //  HttpHelper.getInstance().getVersion(context);
                    openBrowserUpdate("http://a.app.qq.com/o/simple.jsp?pkgname=com.coolu.nokelock.bike");
                    //  }
                }
            });
            Log.e("klk","好啊好啊");
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.setContentView(view);
            dialog.show();
        }
    }

    /**
     * 打开浏览器更新下载新版本apk
     * @param apkUrl    apk托管地址
     */
    private static void openBrowserUpdate(String apkUrl) {

        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("android.intent.action.VIEW");
        Uri apk_url = Uri.parse(apkUrl);
        intent.setData(apk_url);
        App.getInstance().getApplicationContext().startActivity(intent);//打开浏览器

    }


    /**
     *
     * @param context
     * @param view
     * @param url
     * @return
     */
    public static Dialog showWeiXinDialog(final Context context, int view, String url){
        // View dialogView = View.inflate(context, view, null);
        final Dialog dialog = new Dialog(context, com.fitsleep.sunshinelibrary.R.style.no_title);
        View inflate = LayoutInflater.from(context).inflate(view,null);
        final ImageView pop = (ImageView) inflate.findViewById(R.id.id_popup);

//        ImageView iv_main_close = (ImageView) inflate.findViewById(R.id.iv_main_close);
//        iv_main_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });

        pop.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setItems(new String[]{context.getResources().getString(R.string.save_picture)}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pop.setDrawingCacheEnabled(true);
                        Bitmap imageBitmap = pop.getDrawingCache();
                        if (imageBitmap != null) {
                            new SaveImageTask(context,pop).execute(imageBitmap);
                        }
                    }
                });
                builder.show();

                return true;
            }
        });


//        pop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                if (TextUtils.isEmpty(UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.PHONE))) {
//                    ToastUtils.showMessage("请先登录");
//                    Intent intent=new Intent(context,LoginActivity.class);
//                    context.startActivity(intent);
//
//                    return;
//                }
//                try {
//                    if ("1".equals(App.getInstance().getUserEntityBean().getUserStatus())){
//                        //有订单，返回
//                        Log.e("kkk","有订单");
//                        ToastUtils.showMessage("已有订单");
//                        return;
//                    }
//                }catch (NullPointerException e){
//
//                }
//
//            }
//        });
        //设置图片
        if (pop!=null&&!TextUtils.isEmpty(url)) {
            //Picasso.with(getActivity()).invalidate(Uri.parse(outUrl));
            Picasso.with(context).load(url).transform(new RoundedCornersTransformation(20,0)).into(pop);
        }
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        dialog.show();
        return  dialog;
    }



    private static class SaveImageTask extends AsyncTask<Bitmap, Void, String> {
        private Context context;
        private ImageView pop;
        public SaveImageTask(Context context, ImageView pop) {
            this.context=context;
            this.pop=pop;

        }

        @Override
        protected String doInBackground(Bitmap... params) {
            String result = context.getResources().getString(R.string.save_picture_failed);
            try {
                String sdcard = Environment.getExternalStorageDirectory().toString();

                File file = new File(sdcard + "/Download");
                if (!file.exists()) {
                    file.mkdirs();
                }

                File imageFile = new File(file.getAbsolutePath(),new Date().getTime()+".jpg");
                FileOutputStream outStream = null;
                outStream = new FileOutputStream(imageFile);
                Bitmap image = params[0];
                image.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                outStream.flush();
                outStream.close();
                result = context.getResources().getString(R.string.save_picture_success);//,  file.getAbsolutePath()
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(context.getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            pop.setDrawingCacheEnabled(false);
        }
    }




}
