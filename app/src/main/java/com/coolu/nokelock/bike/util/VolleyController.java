package com.coolu.nokelock.bike.util;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;


public class VolleyController {

	// 创建一个TAG，方便调试或Log
	private static final String TAG = "VolleyController";

	// 创建一个全局的请求队列
	private RequestQueue reqQueue;
	private ImageLoader imageLoader;

	// 创建一个static VolleyController对象，便于全局访问
	private static VolleyController mInstance;
	
	private Context mContext;

	private VolleyController(Context context) {
		mContext=context;
	}

	/**
	 * 以下为需要我们自己封装的添加请求取消请求等方法
	 */

	// 用于返回一个VolleyController单例
	public static VolleyController getInstance(Context context) {
		if (mInstance == null) {
			synchronized(VolleyController.class)
			{
				if (mInstance == null) {
					mInstance = new VolleyController(context);
				}
			}
		}
		return mInstance;
	}

	// 用于返回全局RequestQueue对象，如果为空则创建它
	public RequestQueue getRequestQueue() {
		if (reqQueue == null){
			synchronized(VolleyController.class)
			{
				if (reqQueue == null){
					//生成SSLSocketFactory
//					SSLSocketFactory sslSocketFactory = initSSLSocketFactory();
					SSL sslSocketFactory = new SSL(trustAllCert);
					//HurlStack两个参数默认都是null,如果传入SSLSocketFactory，那么会以Https的方式来请求网络
					HurlStack stack = new HurlStack(null, sslSocketFactory);
					reqQueue = Volley.newRequestQueue(mContext,stack);
				}
			}
		}
		return reqQueue;
	}
	
	



	/**
	 * 将Request对象添加进RequestQueue，由于Request有*StringRequest,JsonObjectResquest...
	 * 等多种类型，所以需要用到*泛型。同时可将*tag作为可选参数以便标示出每一个不同请求
	 */

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		// 如果tag为空的话，就是用默认TAG
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	// 通过各Request对象的Tag属性取消请求
	public void cancelPendingRequests(Object tag) {
		if (reqQueue != null) {
			reqQueue.cancelAll(tag);
		}
	}












	//2
	//定义一个信任所有证书的TrustManager
	final static X509TrustManager trustAllCert = new X509TrustManager() {
		@Override
		public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}
	};

}