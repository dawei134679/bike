package com.coolu.nokelock.bike.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.activity.UserActivity;
import com.coolu.nokelock.bike.base.BaseFragment;
import com.coolu.nokelock.bike.bean.PalyItemBean;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.ImageSlideshow;
import com.coolu.nokelock.bike.util.RoundedCornersTransformation;
import com.coolu.nokelock.bike.util.VolleyController;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 吃喝玩乐中的推荐
 * Created by admin on 2017/8/14.
 */
public class PlayItemFragment extends BaseFragment {




    private ImageSlideshow imageSlideshow;
    private List<String> imageUrlList;
    private List<String> titleList;
    private ListView listView;
    private View inflate;
    private int flag=-1; //用于区分那页
    private MyAdapter adapter;
    private PalyItemBean playbean=null;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101:
                    playbean = (PalyItemBean)msg.obj;
                    if ("1101".equals(playbean.getCode())){
                        adapter.updateList(playbean.getDatas().getArticleList());
                        //设置轮播图
                        for (int i=0;i<5;i++){
                            imageSlideshow.addImageUrl(Url.IMAGE+playbean.getDatas().getArticleList().get(i).getTitleImg());
                        }

                        imageSlideshow.commit();
                    }
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag = getArguments().getInt("flag");
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playfragment,null);
        listView = (ListView)view.findViewById(R.id.id_listView);
        //轮播
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.mageslideshow,null);
        imageSlideshow = (ImageSlideshow)inflate.findViewById(R.id.is_gallery);
        imageUrlList = new ArrayList<>();
        titleList = new ArrayList<>();

        // 初始化数据
      //  initData();
        // 为ImageSlideshow设置数据
        imageSlideshow.setDotSpace(20);
        imageSlideshow.setDotWidth(25);
        imageSlideshow.setDotHeight(6);
        imageSlideshow.setDelay(3000);
        //下面可以写监听器
        imageSlideshow.setDelay(3000);
        imageSlideshow.setOnItemClickListener(new ImageSlideshow.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        //  startActivity(new Intent(MainActivity.this, Activity_1.class));
                        break;
                    case 1:
                        //  startActivity(new Intent(MainActivity.this, Activity_2.class));
                        break;
                    case 2:
                        // startActivity(new Intent(MainActivity.this, Activity_3.class));
                        break;
                    case 3:
                        // startActivity(new Intent(MainActivity.this, Activity_4.class));
                        break;
                    case 4:
                        // startActivity(new Intent(MainActivity.this, Activity_5.class));
                        break;
                }
            }
        });



        //添加到listview
        listView.addHeaderView(inflate);
     //   List<String> strings = new ArrayList<>();
//        for (int i=0;i<10;i++){
//            strings.add("第"+i);
//        }
        //创建适配器
       adapter =new MyAdapter(getActivity(),new ArrayList<PalyItemBean.Datas.ArticleList>());
        listView.setAdapter(adapter);
        listView.setDivider(null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
                //由于轮播伦比图作为第一个，postion-1才是你点击的item
                   // Log.e("pp","pos"+playbean.getDatas().getArticleList().get(postion-1).getId());
                Intent intent=new Intent(getActivity(), UserActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("url",playbean.getDatas().getArticleList().get(postion-1).getId());
                bundle.putString("route",playbean.getDatas().getArticleList().get(postion-1).getTitle());
                bundle.putInt("flag",2);  //2表示吃喝玩乐
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        initListDate();
        return view;
    }
    //设置列表的数据
    private void initListDate() {
        HashMap<String,String> map=new HashMap<>();
        map.put("typeid",String.valueOf(flag+1));
        map.put("pager",0+"");
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.deStringPost(getActivity(), Url.GETLIST, map, "play" + flag, new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {

                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {
                    //  Log.e("pp","on"+response.toString());
                    PalyItemBean palyItemBean = VolleyUtils.parseJsonWithGson(response.toString(), PalyItemBean.class);
                    // Log.e("pp",palyItemBean.getCode()+"");

                    Message message = new Message();
                    message.obj = palyItemBean;
                    message.what = 101;
                    handler.sendMessage(message);
                }
            });

        }
    }



    private void initData() {

//        String[] imageUrls = {"http://pic3.zhimg.com/b5c5fc8e9141cb785ca3b0a1d037a9a2.jpg",
//                "http://pic2.zhimg.com/551fac8833ec0f9e0a142aa2031b9b09.jpg",
//                "http://pic2.zhimg.com/be6f444c9c8bc03baa8d79cecae40961.jpg",
//                "http://pic1.zhimg.com/b6f59c017b43937bb85a81f9269b1ae8.jpg",
//                "http://pic2.zhimg.com/a62f9985cae17fe535a99901db18eba9.jpg"};
//        String[] titles = {"读读日报 24 小时热门 TOP 5 · 余文乐和「香港贾玲」乌龙绯闻",
//                "写给产品 / 市场 / 运营的数据抓取黑科技教程",
//                "学做这些冰冰凉凉的下酒宵夜，简单又方便",
//                "知乎好问题 · 有什么冷门、小众的爱好？",
//                "欧洲都这么发达了，怎么人均收入还比美国低"};
//        for (int i = 0; i < 5; i++) {
//            imageSlideshow.addImageTitle(imageUrls[i], titles[i]);
//        }
    }



    /**
     * listView的适配器
     *
     */
    public  class MyAdapter extends BaseAdapter {
        private List<PalyItemBean.Datas.ArticleList> list;
        private Context context;
        private TextView tv;

        public MyAdapter(Context context,List<PalyItemBean.Datas.ArticleList> list){
            this.context=context;
            this.list=list;

        }
        @Override
        public int getCount() {
            return list.size();
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
            ViewHodler viewHodler;
            if (convertView==null){
                convertView=LayoutInflater.from(context).inflate(R.layout.play_item,null);
                viewHodler=new ViewHodler();
                viewHodler.title = (TextView) convertView.findViewById(R.id.id_title);
                viewHodler.content=(TextView)convertView.findViewById(R.id.id_content);
                viewHodler.img=(ImageView)convertView.findViewById(R.id.id_img);
                convertView.setTag(viewHodler);
            }else {
                viewHodler=(ViewHodler)convertView.getTag();
            }
            //设置数据
            PalyItemBean.Datas.ArticleList articleListBean = list.get(position);
            if (articleListBean.getSummary()!=null||!"".equals(articleListBean.getContent())){
                viewHodler.content.setText(articleListBean.getSummary());
            }
            if (articleListBean.getTitle()!=null||!"".equals(articleListBean.getTitle())){
                viewHodler.title.setText(articleListBean.getTitle());
            }
            if (articleListBean.getTitleImg()!=null||!"".equals(articleListBean.getTitleImg())){
                Picasso.with(context).load(Url.IMAGE+articleListBean.getTitleImg()).fit().transform(new RoundedCornersTransformation(8,0)).into(viewHodler.img);
            }

            return convertView;
        }

        /**
         * 修改数据
         * @param ll
         */
        public void updateList(List<PalyItemBean.Datas.ArticleList> ll){
            list.clear();
            list.addAll(ll);
            notifyDataSetChanged();
        }

        class ViewHodler{
            private TextView title;
            private TextView content;
            private ImageView img;
        }
    }



    @Override
    public void onDestroy() {
        // 释放资源
        imageSlideshow.releaseResource();
        VolleyController.getInstance(getActivity()).cancelPendingRequests("play"+flag);
        super.onDestroy();
    }
}
