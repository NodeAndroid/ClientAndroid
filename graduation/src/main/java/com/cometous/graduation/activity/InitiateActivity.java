package com.cometous.graduation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cometous.graduation.R;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Devilsen on 2015/4/20.
 */
public class InitiateActivity extends BaseActivity {

    private SimpleDateFormat mFormatter = new SimpleDateFormat("MM月dd日 aa hh:mm");
    private Date startDate = null;

    private mClickListener mOnclickListener;
    private LinearLayout mLocationLayout;
    private LinearLayout mTimeLayout;
    private TextView startTimeTxt;
    private TextView continuedTxt;
    private TextView continuedTimeTxt;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMyContentView(R.layout.initiate_activity_layout);

        mOnclickListener = new mClickListener();
        init();
    }

    private void init() {
        mLocationLayout = (LinearLayout) findViewById(R.id.activity_location_layout);
        mTimeLayout = (LinearLayout) findViewById(R.id.initiate_time_layout);
        startTimeTxt = (TextView) findViewById(R.id.start_time_txt);
        continuedTxt = (TextView) findViewById(R.id.initiate_continued_txt);
        continuedTimeTxt = (TextView) findViewById(R.id.initiate_continued_time_txt);

        mLocationLayout.setOnClickListener(mOnclickListener);
        mTimeLayout.setOnClickListener(mOnclickListener);


    }


    /**
     * 点击监听
     */
    class mClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch ( v.getId() ){
                case R.id.activity_location_layout:
                    Intent location = new Intent(InitiateActivity.this,SetLocationActivity.class);
                    startActivity(location);
                    break;
                case R.id.initiate_time_layout:
                    if (startDate == null){
                        startDate = new Date();
                    }
                    new SlideDateTimePicker.Builder(getSupportFragmentManager())
                            .setListener(listener)
                            .setInitialDate(startDate)
                            .build()
                            .show();
                    break;
                case R.id.initiate_continued_txt:
                    break;
            }


        }
    }

    /**
     * 时间选取监听
     */
    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date)
        {
            startDate = date;
            startTimeTxt.setText(mFormatter.format(date));
        }

        // Optional cancel listener
        @Override
        public void onDateTimeCancel()
        {
//            Toast.makeText(InitiateActivity.this,
//                    "Canceled", Toast.LENGTH_SHORT).show();
        }
    };
}
