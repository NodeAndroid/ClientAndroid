package com.cometous.graduation.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cometous.graduation.R;

/**
 * Created by Devilsen on 2015/4/17.
 */
public class DrawerAdapter extends BaseAdapter {

    private String[] textList  = new String[]{
            "活动",
            "发起",
            "通知",
            "好友",
            "分享"
    };
    private Context mContext;

    public DrawerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return textList.length;
    }

    @Override
    public Object getItem(int position) {
        return textList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.darwer_list_item,null);
            viewHolder.itemImage = (ImageView) convertView.findViewById(R.id.darwer_item_img);
            viewHolder.itemText = (TextView) convertView.findViewById(R.id.darwer_item_txt);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.itemImage.setImageResource(R.drawable.octocat_gray);
        viewHolder.itemText.setText(textList[position]);


        return convertView;
    }


    class ViewHolder{
        ImageView itemImage;
        TextView itemText;
    }
}
