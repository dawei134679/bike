package com.coolu.nokelock.bike.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.bean.BlurtoothBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/5/18.
 */
public class BlueAdapter extends BaseAdapter   {
    private  LayoutInflater inflater;
    private Context context;
    private List<BlurtoothBean> list;

   // private ArrayFilter filter;
    private ArrayList<BlurtoothBean> mUnfilteredData;


    public BlueAdapter(Context context,List<BlurtoothBean> list){
        this.context=context;
        this.list=list;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list==null? 0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler hodler;
        if (convertView==null){
            hodler=new ViewHodler();
            convertView = inflater.inflate(R.layout.bluetooth_item, null);
            hodler.name=(TextView) convertView.findViewById(R.id.name);
            hodler.address=(TextView)convertView.findViewById(R.id.address);

            convertView.setTag(hodler);
        }else {
            hodler =(ViewHodler) convertView.getTag();
        }
        //设置数据
        if (list.get(position).getName()!=null)
        hodler.name.setText(list.get(position).getName());
        if (list.get(position).getAddress()!=null){
            hodler.address.setText(list.get(position).getAddress());
        }
        return convertView;
    }







    public class ViewHodler{
        TextView name,address;


    }

}
