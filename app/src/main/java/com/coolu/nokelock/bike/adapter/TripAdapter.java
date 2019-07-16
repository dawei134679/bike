package com.coolu.nokelock.bike.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.bean.TripBean;

import java.net.InterfaceAddress;
import java.util.List;

/**
 * Created by admin on 2017/8/18.
 */

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHodler> {
    private List<TripBean.Result> list;
    private Context context;

    public TripAdapter(Context context, List<TripBean.Result> list){
            this.context=context;
            this.list=list;
    }
    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trip_item, null);
        ViewHodler viewHodler = new ViewHodler(view);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {

       if (list.get(position)!=null){
           if (list.get(position).getSysTime()!=null){
               holder.date.setText(list.get(position).getSysTime());
           }
           if (list.get(position).getBarCode()!=null){

               holder.number.setText("单车编号: "+list.get(position).getBarCode().substring(list.get(position).getBarCode().lastIndexOf("=")+1));
           }

           if (list.get(position).getUseMinute()!=null){
               String time="骑行时间: <font color='#00E275'>"+list.get(position).getUseMinute()+"</font>分钟";

               holder.time.setText(Html.fromHtml(time));
           }

//           if (list.get(position).getUserMoney()!=null){
               String mm="骑行花费: <font color='#EE5676'>"+list.get(position).getUserMoney()+"</font>元";
               holder.money.setText(Html.fromHtml(mm));
//           }
       }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder{
        TextView date,time,number,money;

        public ViewHodler(View view) {
            super(view);
            date=(TextView)view.findViewById(R.id.id_trip_date);
            number=(TextView)view.findViewById(R.id.id_trip_number);
            time=(TextView)view.findViewById(R.id.id_trip_time);
            money=(TextView)view.findViewById(R.id.id_trip_money);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.trip(view,getPosition());
                }
            });

        }
    }

    private OnTripListener  listener;
    public interface OnTripListener{
        public void  trip(View view, int postion);
    }

    public void setTripListener(OnTripListener listener){
        this.listener=listener;
    }

    //修改列表
    public void updateList(List<TripBean.Result> ll){
        if (list.size()!=0){
            list.clear();
        }

        //倒叙
        for (int i=ll.size()-1;i>=0;i--){
            list.add(ll.get(i));
        }
      //  list.addAll(ll);

        notifyDataSetChanged();
    }
}
