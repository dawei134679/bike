package com.coolu.nokelock.bike.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.bean.RedDetailBean;

import java.util.List;

/**
 * Created by admin on 2017/8/17.
 */

public class MoneyDetailAdapter extends RecyclerView.Adapter<MoneyDetailAdapter.ViewHodler> {

    private List<RedDetailBean.Result> list;
    private Context context;

    public MoneyDetailAdapter(Context context,List<RedDetailBean.Result> list){
        this.context=context;
        this.list=list;

    }


    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.red_detail, null);
        ViewHodler hodler = new ViewHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        if (list.size()>0){
            if (list.get(position).getPayWhat()!=null){
                holder.getAddress.setText(list.get(position).getPayWhat());
            }
            if (list.get(position).getMoney()!=null){
                holder.money.setText(list.get(position).getMoney());
            }
            if (list.get(position).getSysTime()!=null){
                holder.getRedTime.setText(list.get(position).getSysTime());
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder{

        TextView money;
        TextView getred;
        TextView getRedTime;
        TextView getAddress;
        public ViewHodler(View view) {
            super(view);
            money=(TextView)view.findViewById(R.id.id_getMoney);
            getred=(TextView)view.findViewById(R.id.id_getRed);
            getRedTime=(TextView)view.findViewById(R.id.id_getTime);
            getAddress=(TextView)view.findViewById(R.id.id_getAddress);

        }
    }


    //修改列表
    public void updateList(List<RedDetailBean.Result> ll){
        if (list.size()!=0){
            list.clear();
        }
        list.addAll(ll);

        notifyDataSetChanged();
    }
}
