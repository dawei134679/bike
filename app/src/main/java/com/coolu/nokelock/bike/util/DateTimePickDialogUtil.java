package com.coolu.nokelock.bike.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;


import com.coolu.nokelock.bike.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间选择控件 使用方法： private EditText inputDate;//需要设置的日期时间文本编辑框 private String
 * initDateTime="2012年9月3日 14:44",//初始日期时间值 在点击事件中使用：
 * inputDate.setOnClickListener(new OnClickListener() {
 * 
 * @Override public void onClick(View v) { DateTimePickDialogUtil
 *           dateTimePicKDialog=new
 *           DateTimePickDialogUtil(SinvestigateActivity.this,initDateTime);
 *           dateTimePicKDialog.dateTimePicKDialog(inputDate);
 * 
 *           } });
 * 
 * @author
 */
public class DateTimePickDialogUtil implements OnDateChangedListener,
		OnTimeChangedListener {
	private DatePicker datePicker;
	private TimePicker timePicker;
	private AlertDialog ad;
	private String dateTime;
	private String initDateTime;
	private Activity activity;
	private SimpleDateFormat sdf;
	/**
	 * 日期时间弹出选择框构造函数
	 * 
	 * @param activity
	 *            ：调用的父activity
	 * @param initDateTime
	 *            初始日期时间值，作为弹出窗口的标题和日期时间初始值
	 */
	@SuppressLint("SimpleDateFormat")
	public DateTimePickDialogUtil(Activity activity, String initDateTime) {
		this.activity = activity;
		this.initDateTime = initDateTime;
		sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	}

	/**
	 * 弹出日期时间选择框方法
	 * 
	 * @param inputDate
	 *            :为需要设置的日期时间文本编辑框
	 * @return
	 */
	@SuppressLint("InflateParams")
	public AlertDialog dateTimePicKDialog(final TextView inputDate) {
		LinearLayout dateTimeLayout = (LinearLayout) activity
				.getLayoutInflater().inflate(R.layout.date_time_picker_dialog, null);
		datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
		timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
		timePicker.setIs24HourView(true);
		init(datePicker, timePicker);
		timePicker.setOnTimeChangedListener(this);
//		ad = new AlertDialog.Builder(activity)
//				.setTitle(initDateTime)
//				.setView(dateTimeLayout)
//				.setPositiveButton(Util.getString(activity, R.string.ok), new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int whichButton) {
//						inputDate.setText(dateTime);
//					}
//				})
//				.setNegativeButton(Util.getString(activity, R.string.clean), new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int whichButton) {
//						inputDate.setText("");
//					}
//				}).setNeutralButton(Util.getString(activity, R.string.cancel), null).show();

				ad = new AlertDialog.Builder(activity)
				.setTitle(initDateTime)
				.setView(dateTimeLayout)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						//inputDate.setText(dateTime.);
						inputDate.setText(dateTime.substring(0,10));
					}
				}).setNeutralButton("取消", null).show();
		onDateChanged(null, 0, 0, 0);
		return ad;
	}
	
	public void init(DatePicker datePicker, TimePicker timePicker) {
		Calendar calendar = Calendar.getInstance();
		if (!(null == initDateTime || "".equals(initDateTime))) {
			try {
				Date date = sdf.parse(initDateTime);
				calendar.setTime(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			initDateTime = sdf.format(calendar.getTime());
		}

		datePicker.init(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), this);
		timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
		timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
	}

	

	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		onDateChanged(null, 0, 0, 0);
	}

	public void onDateChanged(DatePicker view, int year, int monthOfYear,
							  int dayOfMonth) {
		// 获得日历实例
		Calendar calendar = Calendar.getInstance();

		calendar.set(datePicker.getYear(), datePicker.getMonth(),
				datePicker.getDayOfMonth());
		dateTime = sdf.format(calendar.getTime());

		ad.setTitle(dateTime);
	}

}