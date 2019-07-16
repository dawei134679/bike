package com.coolu.nokelock.bike.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.activity.VipComboActivity;
import com.coolu.nokelock.bike.bean.BalanceAddBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Learning
 * @date 2019/3/14
 */
public class VipComboAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<BalanceAddBean.CardInfo> mList;
    private int selectedPosition=0;
    public VipComboAdapter(Context context, ArrayList<BalanceAddBean.CardInfo> result) {
        mContext=context;
        mList=result;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public void setSelectedPosition(int position){
        selectedPosition=position;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=View.inflate(mContext,R.layout.item_vip_combo,null);
            ButterKnife.bind(holder,view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        BalanceAddBean.CardInfo cardInfo = mList.get(i);
        if (selectedPosition==i){
            holder.rl_vip_combo.setBackground(mContext.getResources().getDrawable(R.drawable.combo_bg1));
        }else {
            holder.rl_vip_combo.setBackground(mContext.getResources().getDrawable(R.drawable.combo_bg0));
        }
        holder.tv_card_name.setText(cardInfo.getCardName());
        holder.tv_card_context.setText(cardInfo.getCardContext());
        holder.tv_card_money.setText("￥"+cardInfo.getCardMoney());
        return view;
    }
    class ViewHolder{
        @BindView(R.id.rl_vip_combo)
        RelativeLayout rl_vip_combo;
        @BindView(R.id.tv_card_name)
        TextView tv_card_name;
        @BindView(R.id.tv_card_context)
        TextView tv_card_context;
        @BindView(R.id.tv_card_money)
        TextView tv_card_money;
    }
}
