package com.coolu.nokelock.bike.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 共享参数工具类
 * @author apple
 *
 */
public class SPUtils {
	public static final String SP_NAME="config_yi18";
	
	public static SharedPreferences getSP(Context context){
		return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
	}
	
	//获取boolean类型参数
	public static boolean get(Context context,String pname){
		return getSP(context).getBoolean(pname, false);
	}
	
	//设置boolean类型参数
	public static void set(Context context,String pname,boolean pvalue){
		SharedPreferences.Editor editor=getSP(context).edit();
		editor.putBoolean(pname, pvalue);
		editor.commit();
	}
	
	public static double getLocate(Context context,String name){
		return Double.parseDouble(getSP(context).getString(name,"0.0"));
	}
	
	public static void setLocate(Context context,String name,String value){
		SharedPreferences.Editor editor=getSP(context).edit();
		editor.putString(name, value);
		editor.commit();
	}
	
}
