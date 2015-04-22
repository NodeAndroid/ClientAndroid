package com.cometous.graduation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.cometous.graduation.R;

/**
 * Created by Devilsen on 2015/4/20.
 */
public class InitiateActivity extends BaseActivity {

    private mClickListener mOnclickListener;
    private LinearLayout mLocationLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMyContentView(R.layout.initiate_activity_layout);

        mOnclickListener = new mClickListener();
        init();
    }

    private void init() {
        mLocationLayout = (LinearLayout) findViewById(R.id.activity_location_layout);
        mLocationLayout.setOnClickListener(mOnclickListener);


    }


    class mClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch ( v.getId() ){
                case R.id.activity_location_layout:
                    Intent location = new Intent(InitiateActivity.this,SetLocationActivity.class);
                    startActivity(location);
                    break;
            }


        }
    }
}
