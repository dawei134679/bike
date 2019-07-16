package com.coolu.nokelock.bike.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.amap.api.maps.model.MyLocationStyle;
import com.coolu.nokelock.bike.R;


/**
 * Created by admin on 2017/5/26.
 * 开锁后的对话框
 */
public class MyAlertDialogFragment extends DialogFragment {
    private static MyAlertDialogFragment mdf=null;

    private int count=0;
    private Dialog dialog;
    private Thread thread;
    private Thread thread1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    //pd.dismiss();
                dialog.cancel();
                    count=0;

                    break;
            }

        }
    };


    public static MyAlertDialogFragment getInstace(){
        if (mdf==null){
            mdf=new MyAlertDialogFragment();
        }
        return mdf;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


         dialog=new Dialog(getActivity(), R.style.CorDialog);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.progress_dialog, null);
        final ProgressBar p = (ProgressBar) inflate.findViewById(R.id.progress);

        dialog.setContentView(inflate);
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
              while (count<70){
                  try {
                      Thread.sleep(200);
                      p.setProgress(count++);
//                      if (count>70){
//                          p.setProgress(70);
//                          count=1;
//                      }
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }

              }
            }
        });
        thread1.start();

        //6秒后，对话框消失
        //开启一个线程来延时
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20000);
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });
        thread.start();

        Window dialogWindow = dialog.getWindow();

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width=700;
        lp.height=400;
        dialogWindow.setAttributes(lp);

        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("yy","Altet+Destroy");

        if (thread!=null){
            thread.interrupt();
            thread=null;
        }
        if (thread1!=null){
            thread1.interrupt();
            thread1=null;
        }

    }


}
