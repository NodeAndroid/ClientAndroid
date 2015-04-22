package com.cometous.graduation.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.cometous.graduation.R;
import com.cometous.graduation.activity.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.cards.actions.BaseSupplementalAction;
import it.gmariotti.cardslib.library.cards.actions.TextSupplementalAction;
import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.view.CardViewNative;

/**
 * Created by lenovo on 2015/4/10.
 */
public class MainListAdapter extends BaseAdapter{

    private final LayoutInflater mInflater;

    private Context mContext;
    private List<String> list;

    public MainListAdapter(Context context, List<String> list) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            viewHolder.imageViewIcon = (CardViewNative) convertView.findViewById(R.id.carddemo_largeimage_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        cardView(viewHolder.imageViewIcon);
        return convertView;
    }

    private void cardView(CardViewNative cardViewNative){
        ArrayList<BaseSupplementalAction> subText = new ArrayList<BaseSupplementalAction>();

        TextSupplementalAction text1 = new TextSupplementalAction(mContext,R.id.text1);
        TextSupplementalAction text2 = new TextSupplementalAction(mContext,R.id.text2);
        text1.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(mContext, " Click on Text SHARE ", Toast.LENGTH_SHORT).show();
            }
        });
        text2.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(mContext, " Click on Text SHARE ", Toast.LENGTH_SHORT).show();
            }
        });
        subText.add(text1);
        subText.add(text2);

        MaterialLargeImageCard card = MaterialLargeImageCard.with(mContext)
                .setTextOverImage("Come wiht us")
                .setTitle("The graduation design")
                .setSubTitle("let's begin")
                .useDrawableId(R.drawable.sea)
                .setupSupplementalActions(R.layout.card_sub_text,  subText)
                .build();
        card.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Intent intent = new Intent(mContext,DetailActivity.class);
                mContext.startActivity(intent);
            }
        });

        cardViewNative.setCard(card);

    }

    class ViewHolder {
        CardViewNative imageViewIcon;
    }
}
