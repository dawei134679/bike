
package com.coolu.nokelock.bike.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Poi;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.adapter.BlueAdapter;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.bean.BlurtoothBean;
import com.coolu.nokelock.bike.util.BaseUtil;


import java.util.ArrayList;
import java.util.List;

/**
 * 根据关键字地图检索
 */

public class PoiActivity extends BaseActivity implements TextWatcher,Inputtips.InputtipsListener {


    private AMap aMap;
    private AutoCompleteTextView searchText;// 输入搜索关键字
    private String keyWord = "";// 要输入的poi搜索关键字



    private PoiResult poiResult; // poi返回的结果

    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;// POI搜索
    private ImageView back;
    private ListView listview;
    private  List<BlurtoothBean> listString;
    private String city;
    private int currentPage = 0;// 当前页面，从0开始计数

    private static PoiActivity activity=null;//单例
    private String latitude;
    private String longitude;

//    public  static PoiActivity getInstacne(){
//        if (activity==null){
//            activity=new PoiActivity();
//        }
//        return activity;
//    }
//
//    public String getLatitude(){
//        return latitude;
//    }
//    public String getLongitude(){
//        return longitude;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_poi);
        activity=this;
        listString = new ArrayList<BlurtoothBean>();
        //得到城市


        init();
    }

    private  void init(){
        back = (ImageView)findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchText=(AutoCompleteTextView)findViewById(R.id.keyWord);
        searchText.addTextChangedListener(this);
        listview = (ListView) findViewById(R.id.id_search_list);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String keyWord = listString.get(position).getName().toString().trim();
                query = new PoiSearch.Query(keyWord, "", App.getInstance().getaMapLocation()!=null?App.getInstance().getaMapLocation().getCity():"北京");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
                Log.i("ll",keyWord);
                query.setPageSize(1);// 设置每页最多返回多少条poiitem
                query.setPageNum(currentPage);// 设置查第一页

                poiSearch = new PoiSearch(PoiActivity.this, query);
                //利用回调得到请求的结果
                poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
                    @Override
                    public void onPoiSearched(PoiResult result, int rCode) {
                        doSearchQuery(result,rCode);

                    }

                    @Override
                    public void onPoiItemSearched(PoiItem poiItem, int i) {

                    }
                });
                //异步发起请求
                poiSearch.searchPOIAsyn();



            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String newText = s.toString().trim();
        if (!BaseUtil.IsEmptyOrNullString(newText)) {
            InputtipsQuery inputquery = new InputtipsQuery(newText,"北京");
            Inputtips inputTips = new Inputtips(PoiActivity.this, inputquery);
            inputTips.setInputtipsListener(this);
            inputTips.requestInputtipsAsyn();
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    /**
     * 检索后的 AutoCompleteTextView结果
     * @param tipList
     * @param rCode
     */
    @Override
    public void onGetInputtips(List<Tip>tipList, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {// 正确返回
            if (listString.size()>0){
                listString.clear();
            }
            for (int i = 0; i < tipList.size(); i++) {
               BlurtoothBean bean=new BlurtoothBean();
                bean.setName(tipList.get(i).getName());
                bean.setAddress(tipList.get(i).getAddress());
                listString.add(bean);
                Log.i("ll",tipList.get(i).getName());

            }
//            ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(
//                    getApplicationContext(),
//                    R.layout.route_inputs, listString);
//            searchText.setAdapter(aAdapter);
//            aAdapter.notifyDataSetChanged();

            BlueAdapter adapter=new BlueAdapter(PoiActivity.this,listString);

           // searchText.setAdapter(adapter);
            listview.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } else {
           // ToastUtil.showerror(this, rCode);
        }
    }
    //POI检索到关键字的经纬度
    public void doSearchQuery(PoiResult result, int rCode){
        if (rCode==AMapException.CODE_AMAP_SUCCESS){
            if (result.getQuery().equals(query)){
                poiResult=result;
                ArrayList<PoiItem> poiItems = poiResult.getPois();
                List<SuggestionCity> searchSuggestionCitys = poiResult.getSearchSuggestionCitys();
                if (poiItems!=null&&poiItems.size()>0){
                    for (int i=0;i<poiItems.size();i++){
                        LatLonPoint latLonPoint = poiItems.get(i).getLatLonPoint();
                        latitude = latLonPoint.getLatitude()+"";
                        longitude = latLonPoint.getLongitude()+"";
//                        Log.i("ll","latitude"+latitude);
//                        Log.i("ll","longitude"+longitude);
                        App.getInstance().setLatitude(latitude);
                        App.getInstance().setLongitude(longitude);
                       // this.finish();
//                        //设置group为自由骑行
//                        Intent intent=new Intent(PoiActivity.this,MainActivity.class);
//                        startActivity(intent);
//                          MainActivity.getInstacne().ToFreeFragment(0);


                        setResult(100);
                        finish();




                    }
                }
            }
        }
    }


}
