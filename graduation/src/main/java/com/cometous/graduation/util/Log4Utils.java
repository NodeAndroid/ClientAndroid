package com.cometous.graduation.util;

import java.util.Calendar;

import android.content.Context;
import android.util.Log;

/**
 * 
* @ClassName: Log4Utils 
* @Description: log manager
* @author gengxin01@58.com
* @date 2014-9-15 下午3:41:09 
*
 */
public class Log4Utils {
	
	/** debug swith**/
	public static boolean D = true;
	
	/** info swith**/
	public static boolean I = true;
	
	/** error swith**/
	public static boolean E = true;

	/**starttime of log*/
	public static long startLogTimeMillis = 0;
	
	
	/**
	 * debug log
	 * @param tag
	 * @param message
	 */
	public static void d(String tag, String message){
		if(D) Log.d(tag, message);
	}
	
	
	/**
	 * debug log
	 * @param context
	 * @param message
	 */
	public static void d(Context context,String message) {
		String tag = context.getClass().getSimpleName();
		d(tag, message);
	}
	
	/**
	 * debug log
	 * @param clazz
	 * @param message
	 */
	public static void d(Class<?> clazz,String message) {
		String tag = clazz.getSimpleName();
		d(tag, message);
	}
	
	/**
	 * info log
	 * @param tag
	 * @param message
	 */
	public static void i(String tag, String message){
		if(I) Log.d(tag, message);
	}
	
	/**
	 * info log
	 * @param context
	 * @param message
	 */
	public static void i(Context context,String message) {
		String tag = context.getClass().getSimpleName();
		i(tag, message);
	}
	
	
	/**
	 * info log
	 * @param clazz
	 * @param message
	 */
	public static void i(Class<?> clazz,String message) {
		String tag = clazz.getSimpleName();
		i(tag, message);
	}
	
	
	/**
	 * error log
	 * @param tag
	 * @param message
	 */
	public static void e(String tag, String message){
		if(E) Log.d(tag, message);
	}
	
	/**
	 * error log
	 * @param context
	 * @param message
	 */
	public static void e(Context context,String message) {
		String tag = context.getClass().getSimpleName();
		e(tag, message);
	}
	
	
	/**
	 * error log
	 * @param clazz
	 * @param message
	 */
	public static void e(Class<?> clazz,String message) {
		String tag = clazz.getSimpleName();
		e(tag, message);
	}
	
	/**
	 * current time
	 * @param tag
	 */
	public static void prepareLog(String tag) {
		Calendar current = Calendar.getInstance();
		startLogTimeMillis = current.getTimeInMillis();
		Log.d(tag,"log starttime  ："+startLogTimeMillis);
	}
	
	
	/**
	 * current time
	 * @param tag
	 */
	public static void prepareLog(Context context) {
		String tag = context.getClass().getSimpleName();
		prepareLog(tag);
	}
	
	/**
	 * current time
	 * @param tag
	 */
	public static void prepareLog(Class<?> clazz) {
		String tag = clazz.getSimpleName();
		prepareLog(tag);
	}
	
	
	/**
	 * 打印这次的执行时间毫秒，需要首先调用prepareLog().
	 *
	 * @param tag 标记
	 * @param message 描述
	 * @param printTime 是否打印时间
	 */
	public static void d(String tag, String message,boolean printTime) {
		Calendar current = Calendar.getInstance();
		long endLogTimeInMillis = current.getTimeInMillis();
		Log.d(tag,message+":"+(endLogTimeInMillis-startLogTimeMillis)+"ms");
	}
	
	
	/**
	 * 打印这次的执行时间毫秒，需要首先调用prepareLog().
	 *
	 * @param tag 标记
	 * @param message 描述
	 * @param printTime 是否打印时间
	 */
	public static void d(Context context,String message,boolean printTime) {
		String tag = context.getClass().getSimpleName();
	    d(tag,message,printTime);
	}
	
	/**
	 * 打印这次的执行时间毫秒，需要首先调用prepareLog().
	 *
	 * @param clazz 标记
	 * @param message 描述
	 * @param printTime 是否打印时间
	 */
	public static void d(Class<?> clazz,String message,boolean printTime) {
		String tag = clazz.getSimpleName();
		d(tag,message,printTime);
	}

	/**
	 *  set the swith of debug log
	 * @param d
	 */
	public static void debug(boolean d) {
		D  = d;
	}
	
	/**
	 *  set the swith of info log
	 * @param i
	 */
	public static void info(boolean i) {
		I  = i;
	}
	
	/**
	 * set the swith of error log
	 * @param e
	 */
	public static void error(boolean e) {
		E  = e;
	}
	
	/**
	 * set the swith of log
	 * @param d
	 * @param i
	 * @param e
	 */
	public static void setVerbose(boolean d,boolean i,boolean e) {
		D  = d;
		I  = i;
		E  = e;
	}
	
	/**
	 * open all logs
	 */
	public static void openAll() {
		D  = true;
		I  = true;
		E  = true;
	}
	
	/**
	 * close all logs
	 */
	public static void closeAll() {
		D  = false;
		I  = false;
		E  = false;
	}
	
	
	
}
