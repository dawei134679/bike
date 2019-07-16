package com.coolu.nokelock.bike.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.bean.QxrouteBean;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by admin on 2017/8/29.
 */

public class RouteAdatpter extends RecyclerView.Adapter<RouteAdatpter.ViewHodler> {

    private Context context;
    private List<QxrouteBean.DatasBean.RouteListBean>list=null;

    public RouteAdatpter(Context context,List<QxrouteBean.DatasBean.RouteListBean>list){
        this.context=context;
        this.list=list;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.route_item, null);

        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        if (list.size()>0){
            Log.e("kop",list.get(position).getTitle());
            if (list.get(position).getTitleImg()!=null)
            Picasso.with(context).load(Url.IMAGE+list.get(position).getTitleImg()).transform(new RoundedCornersTransformation(10,0)).into(holder.img);
            if (list.get(position).getTitle()!=null){
                holder.title.setText(list.get(position).getTitle());
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void  updateList(List<QxrouteBean.DatasBean.RouteListBean> ll){

        list.addAll(ll);
        notifyDataSetChanged();
    }
    //清空
    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }

    public class ViewHodler extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView title;
        public ViewHodler(View view) {
            super(view);
            img=(ImageView)view.findViewById(R.id.id_img);
            title=(TextView)view.findViewById(R.id.id_tui_title);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRouteItemListener.routeItemListener(view,getPosition());
                }
            });


        }
    }
    public OnRouteItemListener  onRouteItemListener;
    public void setOnRouteItemListener(OnRouteItemListener onRouteItemListener){
        this.onRouteItemListener=onRouteItemListener;
    }

    public interface OnRouteItemListener{
         void routeItemListener(View view, int postion);
    }
}
