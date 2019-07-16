package com.coolu.nokelock.bike.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class RedDetailsAdapter extends RecyclerView.Adapter<RedDetailsAdapter.ViewHodler> {

    private List<RedDetailBean.Result> list;
    private Context context;

    public RedDetailsAdapter(Context context,List<RedDetailBean.Result> list){
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
            int payStatus = list.get(position).getPayStatus();
            if (0==payStatus){
                holder.id_getStatus.setText("处理中");
            }else if (1==payStatus){
                holder.id_getStatus.setText("支付成功");
            }else{
                holder.id_getStatus.setText("支付失败");
            }
            if (list.get(position).getPayWhat()!=null){
                holder.getAddress.setText(list.get(position).getPayWhat());
            }
            if (list.get(position).getMoney()!=null){
                holder.money.setText(list.get(position).getMoney());
            }
            if (list.get(position).getSysTime()!=null){
                holder.getRedTime.setText(list.get(position).getSysTime());
            }
            Log.e("pppp",list.get(position).getPayType()+"");
            if (list.get(position).getPayType()!=-1){
                //1微信2支付宝
                if (list.get(position).getPayType()==1){
                    holder.getred.setText("微信");
                }else if (list.get(position).getPayType()==2){
                    holder.getred.setText("支付宝");
                }else if (list.get(position).getPayType()==0){
                    holder.getred.setText("红包");
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder{

        TextView id_getStatus;
        TextView money;
        TextView getred;
        TextView getRedTime;
        TextView getAddress;
        public ViewHodler(View view) {
            super(view);
            id_getStatus = view.findViewById(R.id.id_getStatus);
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
//        //倒叙
//        for (int i=ll.size()-1;i>=0;i--){
//            if (ll.get(i).getPayStatus()==1)
//            list.add(ll.get(i));
//        }
        list.addAll(ll);

        notifyDataSetChanged();
    }
}
