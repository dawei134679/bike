package com.coolu.nokelock.bike.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.bean.CardBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/7/28.
 */
public class RedAdapter extends RecyclerView.Adapter<RedAdapter.ViewHolder> {

    private List<CardBean.ResultBean> list;
    private Context context;
    RelativeLayout.LayoutParams params;



    public RedAdapter(Context context,List<CardBean.ResultBean> list){
        this.context=context;
        this.list=list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.red_layout, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("zz","onBindViewHolder");
            if (list.size()>0){
                if (position==1){
                    holder.re.setBackgroundResource(R.mipmap.coupon2);

                }else if (position==2){
                    holder.re.setBackgroundResource(R.mipmap.coupon3);

                }



               holder.name.setText(list.get(position).getCardName());
                holder.money.setText(list.get(position).getCardMoney()+"");
                holder.user.setText("满20可用");
                holder.date.setText(list.get(position).getSysTime());
            }
    }

    @Override
    public int getItemCount() {
        return list.size()==0?0:list.size();
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

    //修改列表
    public void updateList(List<CardBean.ResultBean> ll){
        if (list.size()!=0){
            list.clear();
        }
        list.addAll(ll);
        notifyDataSetChanged();
    }
}
