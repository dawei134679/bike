package com.coolu.nokelock.bike.util;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * log日志统计保存
 * 
 * @author way
 * 
 */

public class LogcatHelper {

	private static LogcatHelper INSTANCE = null;
	private static String PATH_LOGCAT;
	private LogDumper mLogDumper = null;
	private int mPId;
	private File fileDir;

	/**
	 * 
	 * 初始化目录
	 * 
	 * */
	public void init(Context context) {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {// 优先保存到SD卡中
			PATH_LOGCAT = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ File.separator + "coolu-log";
		}else {// 如果SD卡不存在，就保存到本应用的目录下
			PATH_LOGCAT = context.getFilesDir().getAbsolutePath() +
					File.separator
					+ "coolu-log";
		}
	
		fileDir = new File(PATH_LOGCAT);

		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
	}

	public static LogcatHelper getInstance(Context context) {
		if (INSTANCE == null) {
			INSTANCE = new LogcatHelper(context);
		}
		return INSTANCE;
	}

	private LogcatHelper(Context context) {
		init(context);
		mPId = android.os.Process.myPid();
	}

	public void start() {
		if (mLogDumper == null)
			mLogDumper = new LogDumper(String.valueOf(mPId), fileDir);
		mLogDumper.start();
	}

	public void stop() {
		if (mLogDumper != null) {
			mLogDumper.stopLogs();
			mLogDumper = null;
		}
	}

	private class LogDumper extends Thread {

		private Process logcatProc;
		private BufferedReader mReader = null;
		private boolean mRunning = true;
		String cmds = null;
		private String mPID;
		private FileOutputStream out = null;

		public LogDumper(String pid, File dir) {
			mPID = pid;
			try {

				File file = new File(dir,
						new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()) + ".txt");

				out = new FileOutputStream(file);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
//			catch (@SuppressWarnings("hiding") IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			/**
			 * 
			 * 日志等级：*:v , *:d , *:w , *:e , *:f , *:s
			 * 
			 * 显示当前mPID程序的 E和W等级的日志.
			 * 
			 * */

			// cmds = "logcat *:e *:w | grep \"(" + mPID + ")\"";
//			cmds = "logcat  | grep \"(" + mPID + ")\"";// 打印所有日志信息
			// cmds = "logcat -s way";//打印标签过滤信息
			 cmds = "logcat *:e | grep \"(" + mPID + ")\"";
		}

		public void stopLogs() {
			mRunning = false;
		}

		@Override
		public void run() {
			try {
				logcatProc = Runtime.getRuntime().exec(cmds);
				mReader = new BufferedReader(new InputStreamReader(logcatProc.getInputStream()),
						1024);
				String line = null;
				while (mRunning && (line = mReader.readLine()) != null) {
					if (!mRunning) {
						break;
					}
					if (line.length() == 0) {
						continue;
					}
					if (out != null && line.contains(mPID)) {
						out.write((TimeUtil.currentTime() + "  " + line + "\n").getBytes());
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (logcatProc != null) {
					logcatProc.destroy();
					logcatProc = null;
				}
				if (mReader != null) {
					try {
						mReader.close();
						mReader = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					out = null;
				}

			}

		}

	}

}
