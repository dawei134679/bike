package com.coolu.nokelock.bike.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.activity.BalanceAddActivity;
import com.coolu.nokelock.bike.bean.BalanceAddBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Learning
 * @date 2019/3/13
 */
public class BalanceAddAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<BalanceAddBean.CardInfo> mList;
    public BalanceAddAdapter(Context context, ArrayList<BalanceAddBean.CardInfo> result) {
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=View.inflate(mContext,R.layout.item_balance_add,null);
            ButterKnife.bind(holder,view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        BalanceAddBean.CardInfo cardInfo = mList.get(i);
        holder.tv_balance_add_tip.setText(cardInfo.getCardMoney()+"元"+cardInfo.getCardContext()+cardInfo.getCardName());
        return view;
    }
    class ViewHolder{
        @BindView(R.id.tv_balance_add_tip)
        TextView tv_balance_add_tip;
    }
}
