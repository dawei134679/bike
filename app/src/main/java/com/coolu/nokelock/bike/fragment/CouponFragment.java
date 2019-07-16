package com.coolu.nokelock.bike.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.activity.ActivationActivity;
import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.activity.CaptureActivity;
import com.coolu.nokelock.bike.activity.MainActivity;
import com.coolu.nokelock.bike.activity.RechargeActivity;
import com.coolu.nokelock.bike.base.BaseFragment;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;

/**
 * Created by admin on 2017/7/21.
 */
public class CouponFragment extends BaseFragment implements View.OnClickListener{
    private LinearLayout card;
    private EditText card_edit;
    private Button card_button;
    private LinearLayout activation;
    private Button activation_button;
    private LinearLayout conform_linear;
    private Button conform_button;
    private TextView title;
    private ImageView back;

    private int FLAG=-1;  //用来控制点击"返回按钮"时的界面跳转
    private TextWatcher textWatcher;
    private TextView card_title;
    private TextView card_content;
    private TextView card_time;
    private TextView card_rights;
    private LinearLayout format_member;
    private Toolbar toolbar;
    private AlertDialog alertDialog;
    private EditText edit;
    private ImageView card_img;
    private ImageView member_img;
    private TextView menber_time;
    private ImageView scan;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // TextView textView=new TextView(getActivity());
        //textView.setText("骑行劵");
        View view = inflater.inflate(R.layout.couponfragment, null);
       // initView(view);
        edit = (EditText) view.findViewById(R.id.id_card_edit);
        card_img = (ImageView)view.findViewById(R.id.id_card_button);
        card_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (!TextUtils.isEmpty(edit.getText().toString().trim())){
//                    Intent intent=new Intent(getActivity(), ActivationActivity.class);
//                    intent.putExtra("card",edit.getText().toString().trim());
//
//                    startActivity(intent);
//                }
                ToastUtils.showMessage("无效的兑换码");
            }
        });
        scan = (ImageView)view.findViewById(R.id.id_coupon_scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("card_flag","card");
               IntentUtils.startActivityForResult(getActivity(),CaptureActivity.class,0x105,bundle);

            }
        });
        member_img = (ImageView)view.findViewById(R.id.id_member_img);
        menber_time = (TextView)view.findViewById(R.id.id_menber_time);


        return view;
    }


    private void initView(View view) {
        //toolbar
        // toolbar = (Toolbar)findViewById(R.id.id_toolbar);
       // title = (TextView)view.findViewById(R.id.id_title_toolbar);
        //setSupportActionBar(toolbar);
      //  back = (ImageView)view.findViewById(R.id.id_back);
       // back.setOnClickListener(this);
        //我的卡卷
//        card = (LinearLayout)view.findViewById(R.id.id_card);
//        card_edit = (EditText)view.findViewById(R.id.id_card_edit);
//      //  card_edit.addTextChangedListener(textWatcher);
//        card_button = (Button) view.findViewById(R.id.id_card_button);
//        //card_button.setVisibility(View.INVISIBLE);
//        card_button.setOnClickListener(this);
//        card_title = (TextView)view.findViewById(R.id.id_card_title);
//        card_content = (TextView)view.findViewById(R.id.id_card_content);
//        card_time = (TextView)view.findViewById(R.id.id_card_time);
//
//        card_rights = (TextView)view.findViewById(R.id.id_card_rights);//激活权益
//        card_rights.setOnClickListener(this);
//        //激活界面
//        activation = (LinearLayout)view.findViewById(R.id.id_activation);
//        activation_button = (Button)view.findViewById(R.id.id_activation_button);
//        activation_button.setOnClickListener(this);
//        //确认成功界面
//        conform_linear = (LinearLayout)view.findViewById(R.id.id_confirm);
//        conform_button = (Button)view.findViewById(R.id.id_confirm_button);
//        conform_button.setOnClickListener(this);
//        //酷游会员
//        format_member = (LinearLayout)view.findViewById(R.id.id_format_member);
    }

    /*
     * 创建对话框
     */
    public void createDialog(String content){
        alertDialog = new AlertDialog.Builder(getActivity()).setMessage("                        "+content).create();
        alertDialog.show();
    }

    /**
     * 延迟操作
     * @param time
     */
    public void delay_operation(long time){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                alertDialog.dismiss();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (activation!=null)activation.setVisibility(View.GONE);
                if(card!=null) card.setVisibility(View.VISIBLE);
               // if (title!=null)title.setText("我的卡卷");
                if (card_edit!=null){
                    card_edit.setText("");
                    //card_edit.setVisibility(View.GONE);
                }

                card_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                card_content.setVisibility(View.GONE);
                card_time.setVisibility(View.VISIBLE);
                card_rights.setVisibility(View.VISIBLE);

            }
        }, time);
    }

    @Override
    public void onResume() {
        super.onResume();
//         if(App.getInstance().isMember()){
//             if (card_edit!=null){
//                 card_edit.setText("");
//                 //card_edit.setVisibility(View.GONE);
//             }
//
//             card_title.setTextColor(getResources().getColor(R.color.colorPrimary));
//             card_content.setVisibility(View.GONE);
//             card_time.setVisibility(View.VISIBLE);
//             card_rights.setVisibility(View.VISIBLE);
//        }

        if (App.getInstance().getUserEntityBean()!=null&&"1".equals(App.getInstance().getUserEntityBean().getUserLevel())){
           member_img.setImageResource(R.mipmap.card23);
            edit.setText("");
            menber_time.setVisibility(View.VISIBLE);
        }else {
            member_img.setImageResource(R.mipmap.card20);
            edit.setText("");
            menber_time.setVisibility(View.GONE);
        }

        if (App.getInstance().getUserEntityBean()!=null&&App.getInstance().getUserEntityBean().getUserLevelEndTime()!=null){
            menber_time.setText("到期时间:"+App.getInstance().getUserEntityBean().getUserLevelEndTime());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_card_button:  //我的卡卷  按钮
//                if (!card_edit.getText().toString().trim().equals("")){
//                    if (card!=null){
//                        card.setVisibility(View.GONE);
//                        FLAG=1;
//                        if (activation!=null)
//                            activation.setVisibility(View.VISIBLE);
//                        if (title!=null){
//                            title.setText("酷游会员激活");
//                        }
//                    }
//                if (!card_edit.getText().toString().trim().equals("")){
//                    Intent active=new Intent(getActivity(), ActivationActivity.class);
//                    startActivity(active);
//                }



                break;
            case R.id.id_activation_button://会员激活 按钮
//                if (activation_button!=null){
//                    // activation.setVisibility(View.GONE);
//                    FLAG=0;
//                    //conform_linear.setVisibility(View.VISIBLE);
//                    //format_member.setVisibility(View.GONE);
//                   // createDialog("兑换成功");
//                  //  delay_operation(3000);
//
//
//                }
                break;
//                case R.id.id_confirm_button:  //会员成功  按钮
//                    if (conform_button!=null){
//                        conform_linear.setVisibility(View.GONE);
//                        FLAG=0;
//                        if (card!=null)
//                        card.setVisibility(View.VISIBLE);
//                        card_title.setTextColor(getResources().getColor(R.color.colorPrimary));
//                        card_content.setVisibility(View.GONE);
//                        card_time.setVisibility(View.VISIBLE);
//                        card_rights.setVisibility(View.VISIBLE);
//                        card_edit.setText("");
//                        title.setText("我的卡卷");
//                    }
//                    break;
            case R.id.id_card_rights:  //会员详情界面

//                if (card!=null){
//                    card.setVisibility(View.GONE);
//                }
//                if (format_member!=null)
//                    format_member.setVisibility(View.VISIBLE);
//                if (title!=null){
//                    title.setText("酷游会员");
//                }
//                FLAG=3;
                break;

            case R.id.id_back:
//                switch (FLAG){
//                    case 0:   //我的卡卷
//                        getActivity().finish();
//
//                        break;
//                    case 1:   //酷游会员激活
//                        if (card!=null)
//                            card.setVisibility(View.VISIBLE);
//                        if (activation!=null)
//                            activation.setVisibility(View.GONE);
//                        if (conform_linear!=null)
//                            conform_linear.setVisibility(View.GONE);
//                        if (title!=null)
//                            title.setText("我的卡卷");
//                        FLAG=0;
//                        break;
//                    case 2:   //兑换成功
//                        if (card!=null)
//                            card.setVisibility(View.GONE);
//                        if (activation!=null)
//                            activation.setVisibility(View.VISIBLE);
//                        if (activation!=null)
//                            conform_linear.setVisibility(View.GONE);
//                        if (title!=null)
//                            title.setText("酷游会员激活");
//                        FLAG=1;
//                        break;
//
////                        case 3:  //酷游会员
////                            if (card!=null)
////                            card.setVisibility(View.VISIBLE);
////                           if (format_member!=null)
////                            if (title!=null){
////                                title.setText("我的卡卷");
////                            }
////                               FLAG=0;
////                            break;
//                }
//                break;
        }

    }












}
