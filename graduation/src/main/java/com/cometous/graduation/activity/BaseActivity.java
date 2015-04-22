package com.cometous.graduation.activity;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cometous.graduation.R;
import com.cometous.graduation.exception.BusinessError;
import com.cometous.graduation.exception.NetCallback;
import com.cometous.graduation.http.volley.NetworkError;
import com.cometous.graduation.http.volley.NoConnectionError;
import com.cometous.graduation.http.volley.ParseError;
import com.cometous.graduation.http.volley.Response;
import com.cometous.graduation.http.volley.ServerError;
import com.cometous.graduation.http.volley.TimeoutError;
import com.cometous.graduation.http.volley.VolleyError;
import com.cometous.graduation.util.Log4Utils;
import com.yalantis.phoenix.PullToRefreshView;
import android.widget.LinearLayout.LayoutParams;

import org.apache.http.conn.ConnectTimeoutException;


import java.io.IOException;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Devilsen on 2015/4/11.
 */
public class BaseActivity extends SwipeBackActivity {

    private static final int REFRESH_DELAY = 2000;

    private LayoutInflater inflater;
    /** LinearLayout.LayoutParams，FILL_PARENT, FILL_PARENT */
    public LinearLayout.LayoutParams mLayoutParamsFF;

    /** LinearLayout.LayoutParams，FILL_PARENT, WRAP_CONTENT */
    public LinearLayout.LayoutParams mLayoutParamsFW;

    /** LinearLayout.LayoutParams，WRAP_CONTENT, FILL_PARENT */
    public LinearLayout.LayoutParams mLayoutParamsWF;

    /** LinearLayout.LayoutParams，WRAP_CONTENT, WRAP_CONTENT */
    public LinearLayout.LayoutParams mLayoutParamsWW;

    /** 主界面 */
    protected RelativeLayout baseLayout;
    /** 加载页 */
    protected View lodingView;
    /** 内容页 */
    protected RelativeLayout contentLayout;
    /** 错误页 */
    protected View errorView;

    protected ActionBar actionBar;
    protected PullToRefreshView mPullToRefresh;

    /**下拉刷新监听*/
    private mOnRefreshListener mRefreshListener;

    //错误处理
    protected Handler exph;
    public NetCallback callback;
    public ErrorListener errorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = LayoutInflater.from(this);
        mLayoutParamsFF = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mLayoutParamsFW = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mLayoutParamsWF = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLayoutParamsWW = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //主界面，容器
        baseLayout = new RelativeLayout(this);
        baseLayout.setBackgroundColor(this.getResources().getColor(R.color.actionbar_color));
        //加载页
        lodingView = inflater.inflate(R.layout.loding_layout,null);
        //内容页
        contentLayout = new RelativeLayout(this);
        baseLayout.setBackgroundColor(this.getResources().getColor(R.color.actionbar_color));
        //错误页
        errorView = inflater.inflate(R.layout.error_layout,null);

        baseLayout.addView(contentLayout);
        baseLayout.addView(lodingView);
        baseLayout.addView(errorView);
        lodingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);

        setContentView(baseLayout,mLayoutParamsFF);


        actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



    }

    public void setMyContentView(View contentView){
        contentLayout.removeView(errorView);
        contentLayout.addView(contentView,mLayoutParamsFF);
    }

    public void setMyContentView(int resId){
        setMyContentView(inflater.inflate(resId,null));
    }


    /**
     * 初始化手势返回
     */
    protected void initSwipeBackLayout(){
        SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    /**
     * 初始化下拉刷新
     * @param id 下拉组件id
     */
    protected void initPullRefresh(int id) {
        try{
            mRefreshListener = new mOnRefreshListener();
            mPullToRefresh = (PullToRefreshView) findViewById(id);
            mPullToRefresh.setOnRefreshListener(mRefreshListener);
        }catch (Exception e){
            Log4Utils.i("缺少组件","没有pullRefresh或者使用错误");
        }

        initErrorAndNetCall();
    }

    /**
     *  当使用下拉刷新时，必定有网络请求，这时候再new出来
     */
    private void initErrorAndNetCall(){
        exph = new Handler();
        callback = new DefaultCallback(exph);
        errorListener = new ErrorListener();
    }



    public class DefaultCallback implements NetCallback{

        private Handler excptionHandler;
        private String errorString = "未知错误";

        public DefaultCallback(Handler excptionHandler) {
            super();
            this.excptionHandler = excptionHandler;
        }

        @Override
        public void onException(Exception e) {
            if (e instanceof ConnectTimeoutException){
                errorString = "网络好像不给力哦，检查网络吧";
            }else if (e instanceof java.net.SocketException){
                errorString = "网络好像不给力哦，检查网络吧";
            }else if (e instanceof IOException){
                errorString = "网络好像不给力哦，检查网络吧";
            }else if (e instanceof NoConnectionError){
                errorString = "网络好像不给力哦，检查网络吧";
            }else if (e instanceof TimeoutError){
                errorString = "网络好像不给力哦，超时了";
            }else if (e instanceof ServerError){
                errorString = "服务器出错了";
            }else if (e instanceof ParseError){
                errorString = "网络好像不给力哦，检查网络吧";
            }else if (e instanceof NetworkError){
                errorString = "网络好像不给力哦，检查网络吧";
            }else if (e instanceof BusinessError){
                JSONObject json = JSON.parseObject(e.getMessage());
                errorString = json.getString("res_message");
            }
            if( mPullToRefresh.isActivated()){
                mPullToRefresh.setRefreshing(false);
            }
        }
        @Override
        public void makeToast() {
            excptionHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),errorString,Toast.LENGTH_SHORT);
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.layout_enter_anim, R.anim.layout_exit_anim);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.layout_enter_anim, R.anim.layout_exit_anim);
    }

    /**
     * 下拉刷新监听
     */
    class mOnRefreshListener implements PullToRefreshView.OnRefreshListener{
        @Override
        public void onRefresh() {
            mPullToRefresh.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPullToRefresh.setRefreshing(false);
                }
            },REFRESH_DELAY);
        }
    }

    class ErrorListener implements Response.ErrorListener{
        @Override
        public void onErrorResponse(VolleyError error) {
            callback.onException(error);
        }
    }
}
