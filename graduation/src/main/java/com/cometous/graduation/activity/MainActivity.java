package com.cometous.graduation.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cometous.graduation.R;
import com.cometous.graduation.adapter.DrawerAdapter;
import com.cometous.graduation.adapter.MainListAdapter;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;
import com.squareup.picasso.Picasso;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import de.j4velin.picturechooser.Main;


public class MainActivity extends Activity {

    public static final int REFRESH_DELAY = 2000;
    //actionBar
    private ActionBar actionBar;
    //返回箭头
    private DrawerArrowDrawable drawerArrow;
    //抽屉菜单开关
    private ActionBarDrawerToggle mDrawerToggle;
    //抽屉界面
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    //下拉刷新
    private PullToRefreshView mPullToRefreshView;
    //列表页item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.drawer_close);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);
        //初始化抽屉菜单
        initDrawer();
        initPullToRefresh();



    }

    private void initPullToRefresh() {
        List<String> sampleList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            sampleList.add("test" + i);
        }

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new MainListAdapter(this, sampleList));

        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });
    }

    private void initDrawer(){

        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                drawerArrow, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
                actionBar.setTitle(R.string.drawer_close);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                actionBar.setTitle(R.string.drawer_open);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        DrawerAdapter drawerAdapter = new DrawerAdapter(this);
        mDrawerList.setAdapter(drawerAdapter);
        RelativeLayout headlayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.darwer_head_layout,null);
        Picasso.with(MainActivity.this).load("http://c2i.zhuoxiu.com.cn//upload/desk/576x373/1210/1351510593_4035.jpg").into((ImageView) headlayout.findViewById(R.id.head_img));
        mDrawerList.addHeaderView(headlayout);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(MainActivity.this,"这是我",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;
                    case 2:
                        Intent initiate = new Intent(MainActivity.this,InitiateActivity.class);
                        mDrawerLayout.closeDrawer(mDrawerList);
                        startActivity(initiate);
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this,"这是通知",Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(MainActivity.this,"这是好友",Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.setType("text/plain");
                        share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                        share.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_description) + "\n" +
                                "GitHub Page :  https://www.baidu.com\n" +
                                "Sample App : https://play.google.com/store/apps/details?id=" +
                                getPackageName());
                        startActivity(Intent.createChooser(share, getString(R.string.app_name)));
                        break;

                }

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}
