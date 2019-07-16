package com.coolu.nokelock.bike.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageExcessiveActivity extends BaseActivity {

    @BindView(R.id.iv_mes_exc_bg)
    ImageView ivMesExcBg;
    @BindView(R.id.iv_mes_exc_go)
    ImageView ivMesExcGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_message_excessive);
        ButterKnife.bind(this);

        try {
            Intent intent =getIntent();
            Uri uri =intent.getData();
            String host = uri.getHost();
            String port=uri.getPort()+"";
            String path=uri.getPath();
            String query=uri.getQuery();
            if ("".equals(host)||host==null){
//                FeiLogUtil.logE(TAG, "host: "+host);
            }else {


            }
        }catch (NullPointerException e){

//            FeiLogUtil.logE("fei",e.toString());
        }
    }

    @OnClick(R.id.iv_mes_exc_go)
    public void onViewClicked() {
    }
}
