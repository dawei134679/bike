package com.coolu.nokelock.bike.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;

import java.util.List;

/**
 * Created by admin on 2017/10/25.
 */

public class MainAdapter  extends RecyclerView.Adapter<MainAdapter.ViewHodler> {

    private List<String> list;
    private Context context;

    public MainAdapter(Context context,List<String>list){
        this.context=context;
        this.list=list;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_listview_item, null);
        ViewHodler hodler = new ViewHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
            if (list.size()>0){
                holder.money.setText(list.get(position));
            }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder {

        TextView money;


        public ViewHodler(View view) {
            super(view);
            money =(TextView) view.findViewById(R.id.id_listView_item);
        }
    }
}
