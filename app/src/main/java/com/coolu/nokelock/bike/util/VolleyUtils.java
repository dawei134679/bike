package com.coolu.nokelock.bike.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.activity.LoginActivity;
import com.coolu.nokelock.bike.bean.UserEntity;
import com.coolu.nokelock.bike.bean.UserEntityBean;
import com.coolu.nokelock.bike.url.Url;
import com.fitsleep.sunshinelibrary.utils.DeviceUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.ToolsUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.google.gson.Gson;

/**
 * 网络请求工具类
 * @author Javen
 * 
 */
public class VolleyUtils {
	private final static String TAG=VolleyUtils.class.getSimpleName();

	public interface volleyListener{
		void onResponse(JSONObject response);
		void onErrorResponse(String errorMessage);
		void onResponse(String response);
	}

	public static HashMap<String,String> getCommomParameter(){
		HashMap<String,String> map=new HashMap<>();
		map.put("phoneSystem","android");
		map.put("phoneVesion", DeviceUtils.getModel());
		map.put("appVersion", ToolsUtils.getVersion(App.getInstance().getApplicationContext()));

		map.put("phoneBrand",DeviceUtils.getManufacturer());

		return map;
	}
	
	/**
	 * 
	 * @param context
	 * @param url
	 * @param params
	 *            用来保存post参数
	 */
	public static void doPost(Context context, String url,HashMap<String, String> params,String tag,final volleyListener listener) {
		// 优先级有LOW，NORMAL,HIGH,IMMEDIATE
		// 设置请求的优先级别通过覆写getPrioriity()方法
		final Priority priority = Priority.HIGH;
		Log.e("kk",params.get("phone"));
		JSONObject ob = new JSONObject();
		try {
			ob.put("phone","18813151324");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST,url,ob, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// 正确响应时回调此函数
				listener.onResponse(response);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				// 正确响应时回调此函数
				Log.e("kk",error.getMessage());
				listener.onErrorResponse(error.getMessage());

			}
		}) {
			// 设置请求级别
			@Override
			public Priority getPriority() {
				return priority;
			}

			// 设置请求头
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				// TODO Auto-generated method stub
				return super.getHeaders();
			}
		};
		// 第一个代表超时时间：即超过20S认为超时，第三个参数代表最大重试次数，这里设置为1.0f代表如果超时，则不重试
		req.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
		// 可以通过setTag方法为每一个Request添加tag
		//req.setTag(tag);
		// 也可以在我们实现的添加进RequestQueue的时候设置
		VolleyController.getInstance(context).addToRequestQueue(req,tag);

		// 取消Request
		// VolleyController.getInstance(context).getRequestQueue().cancelAll("My Tag");
		// 或者我们前面实现的方法
		// VolleyController.getInstance(context).cancelPendingRequests("My Tag");
	}

	public static void doGet(Context context, String url,String tag,final volleyListener listener){

		JsonObjectRequest request=new JsonObjectRequest(url, null, new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject jsonObject) {
				listener.onResponse(jsonObject);
			}

		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				listener.onErrorResponse(volleyError.getMessage());
			}
		}){
			@Override
			public Priority getPriority() {
				return super.getPriority();
			}

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				return super.getHeaders();
			}
		};

		//设置超时
		request.setRetryPolicy(new DefaultRetryPolicy(5*1000,1,1.0f));
		//request.setTag(tag);
		VolleyController.getInstance(context).addToRequestQueue(request,tag);

	}


		public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
			Gson gson = new Gson();
			T result = gson.fromJson(jsonData, type);
			return result;
		}

		//Volley的StringRequest请求
		public static void  deStringPost(final Context context, String url, final Map<String,String>params, String tag, final volleyListener listener){
			FakeX509TrustManager.allowAllSSL();
			StringRequest request=new StringRequest(Request.Method.POST,url, new Listener<String>() {
				@Override
				public void onResponse(String s) {
					listener.onResponse(s);
				}
			}, new ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError volleyError) {
					if (volleyError!=null)
					Log.e("ppp",volleyError.toString());
					String err = volleyError.toString();
					String subErr = err.substring(err.length() - 24, err.length());
					if ("(Network is unreachable)".equals(subErr)){
						Toast.makeText(context,context.getResources().getString(R.string.net_un_work),Toast.LENGTH_LONG).show();
					}
//					listener.onErrorResponse(volleyError.toString());
				}
			}){
				@Override
				protected Map<String, String> getParams() throws AuthFailureError {
				//	Log.e("tt","getParams");
//					HashMap<String, String> map = new HashMap<>();
//					map.put("token","d37260f243df1ea0114401f2dfefcde7");
//					map.put("status","0");

					return params;
				}
			};


			//设置超时
			request.setRetryPolicy(new DefaultRetryPolicy(5*1000,1,1.0f));
			//request.setTag(tag);
			VolleyController.getInstance(context).addToRequestQueue(request,tag);

		}


		//请求个人信息

	public static void GetPerson(){
	Log.e("zzzz","token"+UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(),Config.TOKEN));
		//再次请求人员信息表
		HashMap<String,String> map=new HashMap<>();
		map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(),Config.TOKEN));
		if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
			VolleyUtils.deStringPost(App.getInstance().getApplicationContext(), Url.PEOPLE, map, "person", new volleyListener() {
				@Override
				public void onResponse(JSONObject response) {

				}

				@Override
				public void onErrorResponse(String errorMessage) {

				}

				@Override
				public void onResponse(String response) {
					Log.e("nnnn", response.toString());
					try {
						JSONObject jsonObject = new JSONObject(response.toString());
						String errorCode = jsonObject.getString("errorCode");

						if ("200".equals(errorCode)) {
							UserEntity entity = VolleyUtils.parseJsonWithGson(response.toString(), UserEntity.class);
							UserEntityBean user = new UserEntityBean();
							user.setUserId(entity.getResult().getUser().getUserId() + "");
							user.setNicName(entity.getResult().getUser().getNicName());

							user.setIdcheck(entity.getResult().getUser().getIdcheck() + "");
							user.setPinNo(entity.getResult().getUser().getPinNo());
							user.setPinTime(entity.getResult().getUser().getPinTime());
							user.setUserToken(entity.getResult().getUser().getUserToken());
							user.setLoginTime(entity.getResult().getUser().getLoginTime());
							user.setDeposit(entity.getResult().getUser().getDeposit() + "");
							App.getInstance().setDeposit(entity.getResult().getUser().getDeposit());  //保证金
							user.setDefaultDeposit(entity.getResult().getUser().getDefaultDeposit() + "");
							user.setOrderNo(entity.getResult().getUser().getOrderNo());
							user.setUserMoney(entity.getResult().getUser().getUserMoney());
							user.setUserBonus(entity.getResult().getUser().getUserBonus());
							user.setUserType(entity.getResult().getUser().getUserType() + "");
							user.setUserStatus(entity.getResult().getUser().getUserStatus());
							user.setUserCredit(entity.getResult().getUser().getUserCredit());
							user.setIdealMoney(entity.getResult().getUser().getIdealMoney());
							user.setUserLevel(entity.getResult().getUser().getUserLevel());
							user.setUserFrom(entity.getResult().getUser().getUserFrom());
							user.setShebieId(entity.getResult().getShebeiid());
							user.setRidingprice(entity.getResult().getRidingprice());
							user.setLockid(entity.getResult().getLockid());
							user.setUserPic(entity.getResult().getUser().getUserPic());
							user.setOpenmoney(entity.getResult().getOpenmoney());
							user.setNewopenmoney(entity.getResult().getNewopenmoney());
							user.setUserLevelEndTime(entity.getResult().getUser().getUserLevelEndTime());
							user.setBarCode(entity.getResult().getBarcode());
							user.setLockmac(entity.getResult().getLockmac());
							user.setLockdata(entity.getResult().getLockdata());
							user.setStarttime(entity.getResult().getStarttime());
							user.setLocktype(entity.getResult().getLocktype());
							user.setUserName(entity.getResult().getUser().getUserName());
							user.setIdno(entity.getResult().getUser().getIdno());
							user.setForcemoney(entity.getResult().getForcemoney()); //调度费20
							App.getInstance().setForceMoney(entity.getResult().getForcemoney());
							user.setUserCredit(entity.getResult().getUser().getUserCredit());
							user.setCardprice(entity.getResult().getCardprice()); //优惠券你的金额
							user.setIsbuyridingcard(entity.getResult().getIsbuyridingcard());
							user.setUserBonus(entity.getResult().getUser().getUserBonus());
							user.setWarningmoney(entity.getResult().getWarningmoney());
							App.getInstance().setUserEntityBean(user);
						} else if ("301".equals(errorCode)) {
							App.getInstance().getIBLE().disconnect();
							UtilSharedPreference.saveString(ConditionsUtils.getContext(), Config.PHONE, "");
							UtilSharedPreference.saveString(ConditionsUtils.getContext(), Config.TOKEN, "");
							App.getInstance().getDaoSession().getUseBeanDao().deleteAll();
							App.getInstance().getDaoSession().getSearchBeanDao().deleteAll();
							App.getInstance().getDaoSession().getBikeOrderBeanDao().deleteAll();
							App.getInstance().getDaoSession().getOrderBeanDao().deleteAll();
							//个人信息清空
							App.getInstance().setUserEntityBean(null);
							ToastUtils.showMessage("登陆失效，请重新登陆");
						} else {
							ToastUtils.showMessage("人员信息更新失败");
						}
					} catch (JSONException e) {

						e.printStackTrace();
					}


					//  resolveJson(response, action);
				}
			});
		}

	}


	public static void GetPerson(final View view){

		//再次请求人员信息表
		HashMap<String,String> map=new HashMap<>();
		map.put("token",UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(),Config.TOKEN));
		if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
			VolleyUtils.deStringPost(App.getInstance().getApplicationContext(), Url.PEOPLE, map, "person", new volleyListener() {
				@Override
				public void onResponse(JSONObject response) {

				}

				@Override
				public void onErrorResponse(String errorMessage) {
					if (view != null)
						view.clearAnimation();
					view.setVisibility(View.GONE);
				}

				@Override
				public void onResponse(String response) {
					Log.e("nnnn", response.toString());
					if (view != null) {
						view.clearAnimation();
						view.setVisibility(View.GONE);
					}
					try {
						JSONObject jsonObject = new JSONObject(response.toString());
						String errorCode = jsonObject.getString("errorCode");

						if ("200".equals(errorCode)) {
							UserEntity entity = VolleyUtils.parseJsonWithGson(response.toString(), UserEntity.class);
							UserEntityBean user = new UserEntityBean();
							user.setUserId(entity.getResult().getUser().getUserId() + "");
							user.setNicName(entity.getResult().getUser().getNicName());

							user.setIdcheck(entity.getResult().getUser().getIdcheck() + "");
							user.setPinNo(entity.getResult().getUser().getPinNo());
							user.setPinTime(entity.getResult().getUser().getPinTime());
							user.setUserToken(entity.getResult().getUser().getUserToken());
							user.setLoginTime(entity.getResult().getUser().getLoginTime());
							user.setDeposit(entity.getResult().getUser().getDeposit() + "");
							App.getInstance().setDeposit(entity.getResult().getUser().getDeposit());  //保证金
							user.setDefaultDeposit(entity.getResult().getUser().getDefaultDeposit() + "");
							user.setOrderNo(entity.getResult().getUser().getOrderNo());
							user.setUserMoney(entity.getResult().getUser().getUserMoney());
							user.setUserBonus(entity.getResult().getUser().getUserBonus());
							user.setUserType(entity.getResult().getUser().getUserType() + "");
							user.setUserStatus(entity.getResult().getUser().getUserStatus());
							user.setUserCredit(entity.getResult().getUser().getUserCredit());
							user.setIdealMoney(entity.getResult().getUser().getIdealMoney());
							user.setUserLevel(entity.getResult().getUser().getUserLevel());
							user.setUserFrom(entity.getResult().getUser().getUserFrom());
							user.setShebieId(entity.getResult().getShebeiid());
							user.setRidingprice(entity.getResult().getRidingprice());
							user.setLockid(entity.getResult().getLockid());
							user.setUserPic(entity.getResult().getUser().getUserPic());
							user.setOpenmoney(entity.getResult().getOpenmoney());
							user.setNewopenmoney(entity.getResult().getNewopenmoney());
							user.setUserLevelEndTime(entity.getResult().getUser().getUserLevelEndTime());
							user.setBarCode(entity.getResult().getBarcode());
							user.setLockmac(entity.getResult().getLockmac());
							user.setLockdata(entity.getResult().getLockdata());
							user.setStarttime(entity.getResult().getStarttime());
							user.setLocktype(entity.getResult().getLocktype());
							user.setUserName(entity.getResult().getUser().getUserName());
							user.setIdno(entity.getResult().getUser().getIdno());
							user.setForcemoney(entity.getResult().getForcemoney()); //调度费20
							App.getInstance().setForceMoney(entity.getResult().getForcemoney());
							user.setUserCredit(entity.getResult().getUser().getUserCredit());
							user.setCardprice(entity.getResult().getCardprice()); //优惠券你的金额
							user.setIsbuyridingcard(entity.getResult().getIsbuyridingcard());
							user.setUserBonus(entity.getResult().getUser().getUserBonus());
							user.setWarningmoney(entity.getResult().getWarningmoney());
							App.getInstance().setUserEntityBean(user);
						} else if ("301".equals(errorCode)) {
							App.getInstance().getIBLE().disconnect();
							UtilSharedPreference.saveString(ConditionsUtils.getContext(), Config.PHONE, "");
							UtilSharedPreference.saveString(ConditionsUtils.getContext(), Config.TOKEN, "");
							App.getInstance().getDaoSession().getUseBeanDao().deleteAll();
							App.getInstance().getDaoSession().getSearchBeanDao().deleteAll();
							App.getInstance().getDaoSession().getBikeOrderBeanDao().deleteAll();
							App.getInstance().getDaoSession().getOrderBeanDao().deleteAll();
							//个人信息清空
							App.getInstance().setUserEntityBean(null);
							ToastUtils.showMessage("登陆失效，请重新登陆");
						} else {
							ToastUtils.showMessage("人员信息更新失败");
						}
					} catch (JSONException e) {

						e.printStackTrace();
					}


					//  resolveJson(response, action);
				}
			});
		}

	}

	
}
