package com.cometous.graduation.http;

import java.util.HashMap;
import java.util.Map;

import com.cometous.graduation.http.volley.Request.Method;
import com.cometous.graduation.http.volley.Response.ErrorListener;
import com.cometous.graduation.http.volley.Response.Listener;
import com.cometous.graduation.util.Log4Utils;

public class Task {
	
	public static final String HOST = "http://192.168.119.85:9055/crmmobile/";

	/** 请求地址前缀 */
	public static final String API_HOST_URL = HOST + "opportunity/";

	public static final String API_HOST_URL_PASSPORT = HOST + "passport/";
	
	public static final String API_HOST_URL_OA = HOST + "flow/";
	
	public static final String API_HOST_URL_FILTER = HOST + "crmdic/";

	/** 请求地址前缀 */
	public static final String API_HOST_URL_ABOUT = HOST + "about/";
	/**bi请求前缀*/
	public static final String API_HOST_URL_BI = HOST + "report/";
	/** 请求消息 */
	public static final String API_HOST_URL_MESSAGE = HOST + "message/";

	/** 商机基本信息 */
	public static final String ACTION_GET_OPP_BASE = "0";

	/** 商机联系人信息 */
	public static final String ACTION_GET_OPP_CONTACT = "1";

	/** 商机跟进记录信息 */
	public static final String ACTION_GET_OPP_FOLLOW_INFO = "2";

	/** 商机其他信息 */
	public static final String ACTION_GET_OPP_OTHER_INFO = "3";

	/** 获取商机列表信息 */
	public static final String ACTION_GET_OPP_LIST_BY_TYPE = "4";

	/** 跟进商机 */
	public static final String ACTION_FOLLOW_UP_OPP = "5";

	
	/** 存放所有请求地址 */
	public static Map<String, String> API_URLS = new HashMap<String, String>();
	


	public static void init() {
		API_URLS.put(ACTION_GET_OPP_BASE, "getOpportunityBasic");
		API_URLS.put(ACTION_GET_OPP_CONTACT, "getOpportunityContacts");
		API_URLS.put(ACTION_GET_OPP_FOLLOW_INFO, "getOpportunityFollowUpPage");
		
		
		
	}
	
	
	/**
	 * 获取商机基本信息
	 * 
	 * @param params
	 * @param listener
	 * @param errorListener
	 */
	public static void followUpOpp25(HashMap<String, String> params, Listener<String> listener,
			ErrorListener errorListener) {
//		StringJsonRequest request = new StringJsonRequest(Method.POST,
//				API_HOST_URL + API_URLS.get(ACTION_FOLLOW_UP_4_25), params, listener, errorListener);
//		printRequestUri(API_HOST_URL + API_URLS.get(ACTION_FOLLOW_UP_4_25), params);
//		RequestManager.getRequestQueue().add(request);
	}
	
	
	/**
	 * 
	 * @param params
	 * @param listener
	 * @param errorListener
	 */
	public static void getOppFollowUpPage25(HashMap<String, String> params, Listener<String> listener,
			ErrorListener errorListener) {
//		StringJsonRequest request = new StringJsonRequest(Method.POST,
//				API_HOST_URL + API_URLS.get(ACTION_GET_FOLLOW_UP_PAGE_25), params, listener, errorListener);
//		printRequestUri(API_HOST_URL + API_URLS.get(ACTION_GET_FOLLOW_UP_PAGE_25), params);
//		RequestManager.getRequestQueue().add(request);
	}


}
