package com.coolu.nokelock.bike.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.bean.CardTipBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/2/26.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private  List<CardTipBean.ResultBean.YouhuiBean> list;

    private Context context;
    public CardAdapter(Context context, List<CardTipBean.ResultBean.YouhuiBean> list){
        this.context=context;
        this.list=list;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_tip_item, null);
        CardAdapter.ViewHolder holder=new CardAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (list.size()>0){



            if (list.get(position).getValue().substring(0,list.get(position).getValue().indexOf(".")).length()==1){
                holder.money.setText(list.get(position).getValue().substring(0,1));
            }else if (list.get(position).getValue().substring(0,list.get(position).getValue().indexOf(".")).length()>2){
                holder.money.setText(list.get(position).getValue().substring(0,2));
            }else{
                holder.money.setText(list.get(position).getValue().substring(0,3));
            }

            holder.name.setText(list.get(position).getTitle());
            holder.date.setText("有效期:"+list.get(position).getEnd_at());
            holder.user.setText(list.get(position).getCondition());

        }
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name,money,date,user;
        private RelativeLayout re;
        public ViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.id_gift_name);
            money=(TextView)itemView.findViewById(R.id.id_gift_money);
            date=(TextView)itemView.findViewById(R.id.id_gift_date);
            user=(TextView)itemView.findViewById(R.id.id_gift_user);
            re=(RelativeLayout)itemView.findViewById(R.id.id_gift_relative);

        }
    }

}
