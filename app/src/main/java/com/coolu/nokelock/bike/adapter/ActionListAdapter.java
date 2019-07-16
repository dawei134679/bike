package com.coolu.nokelock.bike.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.bean.ActionListBean;
import com.coolu.nokelock.bike.util.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.R.id.list;

/**
 * Created by admin on 2017/8/22.
 */

public class ActionListAdapter extends RecyclerView.Adapter<ActionListAdapter.ViewHodler> {

        private List<ActionListBean.ResultBean> list;
        private Context context;

    ActionListListener listListener;
    public  interface ActionListListener{
        void ItemListener(View view, int postion);
    }

    public void setListListener(ActionListListener listListener){
        this.listListener=listListener;
    }

    public ActionListAdapter(Context context,List<ActionListBean.ResultBean> list){
            this.context=context;
            this.list=list;


        }


        @Override
        public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.actionlist_item, null);
          ViewHodler hodler = new ViewHodler(view);
            return hodler;
        }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        if (list.size()>0){
            if (list.get(position).getActivityUrl()!=null&&holder.img!=null){
                Picasso.with(context).load(list.get(position).getActivityUrl()).transform(new RoundedCornersTransformation(10,0)).into(holder.img);
            }
            if (list.get(position).getActivityName()!=null){
                holder.title.setText(list.get(position).getActivityName());
            }
        }
    }



        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHodler extends RecyclerView.ViewHolder{

            ImageView img;
            TextView title;

            public ViewHodler(View view) {
                super(view);
               img=(ImageView)view.findViewById(R.id.id_action_img);
                title=(TextView)view.findViewById(R.id.id_action_title);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listListener.ItemListener(view,getPosition());
                    }
                });

            }
        }


        //修改列表
    public void updateList(List<ActionListBean.ResultBean> ll){
        if (list.size()!=0){
            list.clear();
        }
        list.addAll(ll);

        notifyDataSetChanged();
    }

}
