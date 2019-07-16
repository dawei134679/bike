package com.coolu.nokelock.bike.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.net.Uri;

import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.CircleTransform;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.OkHttpClientManager;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.File;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;


public class Personctivity extends BaseActivity implements View.OnClickListener {


    private String picturePath;
    private ImageView back;

    private Bitmap bitmap;
    private  Bitmap bitmap1;
    private Bitmap bitmap2;
    @BindView(R.id.id_nc)
    public TextView nc;
    @BindView(R.id.id_name)
    public TextView name;
    @BindView(R.id.id_phone)
    public TextView phone;
    @BindView(R.id.id_sm)
    public TextView renzheng;
    @BindView(R.id.id_wx)
    public TextView weixin;
    @BindView(R.id.id_person_renzheng)
    public RelativeLayout renzheng_re;
    @BindView(R.id.id_nc_rela)
    public RelativeLayout nc_re;
    @BindView(R.id.id_name_re)
    public RelativeLayout name_re;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 101:
//                    UserInfo userInfo = (UserInfo) msg.obj;
//                    if (userInfo!=null&&userInfo.getErrorCode().equals("200")){
//
//                       if (userInfo.getResult().getNicName()!=null){
//                           nc.setText(userInfo.getResult().getNicName());
//                       }
//
//
//
//                    }
                    break;

            }
        }
    };
    private ImageView tou;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_personctivity);
        ButterKnife.bind(this);
        back=(ImageView) findViewById(R.id.id_back);
        back.setOnClickListener(this);
        tou = (ImageView)findViewById(R.id.id_circle);
//        tou.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //initData();
        if (App.getInstance().getUserEntityBean()!=null&&!"".equals(App.getInstance().getUserEntityBean().getNicName())){
            nc.setText(App.getInstance().getUserEntityBean().getNicName());
        }

        if (App.getInstance().getUserEntityBean()!=null&&(App.getInstance().getUserEntityBean().getUserName()!=null)){
            name.setText(App.getInstance().getUserEntityBean().getUserName());
        }
        if (App.getInstance().getUserEntityBean()!=null&&(App.getInstance().getUserEntityBean().getIdno()!=null)){
            Log.e("nnnn",App.getInstance().getUserEntityBean().getIdno());
             String showCard = App.getInstance().getUserEntityBean().getIdno().substring(0,5)+"******"+App.getInstance().getUserEntityBean().getIdno().substring(App.getInstance().getUserEntityBean().getIdno().length()-5);
            renzheng.setText(showCard);

        }
        if (App.getInstance().getUserEntityBean()!=null&&App.getInstance().getUserEntityBean().getUserPic()!=null){
            Log.e("nnnn","ooooo"+App.getInstance().getUserEntityBean().getUserPic());

            Picasso.with(Personctivity.this).load(App.getInstance().getUserEntityBean().getUserPic()).resize(200,200).transform(new CircleTransform()).into(tou);
        }


    }

    private void initData() {
        HashMap<String, String> map = new HashMap<>();
       // map.put("token", EncryptUtils.stringToMD5("18813151324"+"20150515"));
        map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.deStringPost(Personctivity.this, Url.PEOPLE, map, "people", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {

                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {
                    Log.e("mm", response.toString());
//                UserInfo userInfo = VolleyUtils.parseJsonWithGson(response.toString(), UserInfo.class);
//                Message message=new Message();
//                message.obj=userInfo;
//                message.what=101;
//                handler.sendMessage(message);

                }
            });
        }
    }


    @OnClick(R.id.id_person_renzheng)
    void ren(){
        if (App.getInstance().getUserEntityBean()!=null&&(App.getInstance().getUserEntityBean().getIdno()!=null)){
            String showCard = App.getInstance().getUserEntityBean().getIdno().substring(0,5)+"******"+App.getInstance().getUserEntityBean().getIdno().substring(App.getInstance().getUserEntityBean().getIdno().length()-5);

            //认证已完成，不让点击
            renzheng.setClickable(false);
        }else{
            Intent intent=new Intent(Personctivity.this,RenZhengActivity.class);
            startActivity(intent);
        }

    }

    @OnClick(R.id.id_name_re)
    void name(){
        Log.e("kkk","wwwww");
        if (App.getInstance().getUserEntityBean()!=null&&App.getInstance().getUserEntityBean().getUserName()!=null){

            name.setText(App.getInstance().getUserEntityBean().getUserName());
            //认证已完成，不让点击
            nc.setClickable(false);
        }else{
            Intent intent=new Intent(Personctivity.this,RenZhengActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.id_nc_rela)
    void nc(){
        Intent intent=new Intent(Personctivity.this,NcActivity.class);
        startActivity(intent);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==Activity.RESULT_OK){
            switch (requestCode){
                case 0x101:
                  //  startPhotoZoom(data.getData());
                    Uri selectImage = data.getData();
                    String[] filePathLolumn={MediaStore.Images.Media.DATA};
                    Cursor cursor=getContentResolver().query(selectImage,filePathLolumn,null,null,null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathLolumn[0]);
                    picturePath = cursor.getString(columnIndex);
                    cursor.close();



                    if (picturePath!=null){

//                         bitmap = BitmapFactory.decodeFile(picturePath);
//                        if (bitmap!=null)
//                         bitmap1 = BaseUtil.centerSquarScaleBitmap(bitmap, 200);
//                        if (bitmap1!=null)
//                         bitmap2 = BaseUtil.toCircleBitmap(bitmap1);
//                        if (bitmap2!=null)
                       // tou.setImageBitmap(bitmap2);

                        //保存头像到服务器
                        Log.e("nnnn","path"+ Environment.getExternalStorageDirectory().getAbsolutePath()+picturePath);
                      // postHead(ImageUtils.bitmapToBase64(bitmap2));
                        postHead(picturePath);
                        //下面是这只主界面的头像
                        Intent resultIntent = new Intent();
                        Bundle bundle = new Bundle();
		                bundle.putString("result", picturePath);
			            resultIntent.putExtras(bundle);
			            this.setResult(RESULT_OK, resultIntent);
                    }



                    break;
                case 0x102:
                    if (data!=null){
                        getImageToView(data);
                    }
                    break;

            }
        }
    }
    //保存头像图片
    private void postHead(String bitmap) {


        File file = new File(bitmap);
        if (!file.exists())
        {
            return;
        }
        Log.e("nnnn","Name"+file.getName());
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            try {
                OkHttpClientManager.postAsyn(
                        Url.TOU,
                        new OkHttpClientManager.StringCallback() {
                            @Override
                            public void onFailure(Request request, IOException e) {
                                Log.e("nnnn", e.toString());
                            }

                            @Override
                            public void onResponse(String response) {

                                Log.e("nnnn", response.toString());

                                try {
                                    JSONObject jsonObject = new JSONObject(response.toString());
                                    String errorCode = jsonObject.getString("errorCode");
                                    Log.e("nnnn", "errorCode" + errorCode);
                                    if ("200".equals(errorCode)) {
                                        ToastUtils.showMessage("上传成功");
                                        JSONObject result = jsonObject.getJSONObject("result");
                                        String userPic = result.getString("userPic");
                                        if ("".equals(userPic) || userPic != null) {
                                            Picasso.with(Personctivity.this).load(userPic).resize(200, 200).transform(new CircleTransform()).into(tou);
                                        }

                                    } else {
                                        ToastUtils.showMessage("上传失败");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        file,
                        "file",
                        new OkHttpClientManager.Param[]{
                                new OkHttpClientManager.Param("moKuai", "userpic"),
                                new OkHttpClientManager.Param("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN))
                        }
                );


            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap!=null){
            bitmap.recycle();
            }
        if (bitmap1!=null){
            bitmap1.recycle();
        }

        if (bitmap2!=null){
            bitmap2.recycle();
        }
    }

    private void getImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras!=null){
            Bitmap bm= extras.getParcelable("data");
            if (bm==null)Log.i("tt","bm");
               Bitmap bitmap = BaseUtil.toCircleBitmap(bm);
           // ByteArrayOutputStream stream = new ByteArrayOutputStream();
           // bm.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
            tou.setImageBitmap(bitmap);
        }
    }
    //这里是裁剪图片
    private void startPhotoZoom(Uri uri) {
        Intent photo=new Intent("com.android.camera.action.CROP");
        photo.setDataAndType(uri,"image/*");
        photo.putExtra("crop","true"); //设置裁剪
        //宽高的比例
        photo.putExtra("aspectX",1);
        photo.putExtra("aspectY",1);
        //裁剪图片的宽高
        photo.putExtra("outputX",200);
        photo.putExtra("outputY",200);
        photo.putExtra("return-data",true);
        startActivityForResult(photo,0x102);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_circle:
                //打开相册

                Intent  intentFromGallery= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

               // intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);// 相机自带的Activity界面是固定的参数  
                intentFromGallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intentFromGallery,0x101);
//
//                Intent intent = new Intent();
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                intent.setType("image/*");
//                if (Build.VERSION.SDK_INT <19) {
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                }else {
//                    intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
//                }
//                startActivityForResult(intent, 0x103);
                break;
            case R.id.id_back:
                this.finish();
                break;
        }
    }
}
