package com.cometous.graduation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cometous.graduation.R;
import com.cometous.graduation.view.ProgressGenerator;
import com.dd.processbutton.iml.ActionProcessButton;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardViewNative;

/**
 * Created by lenovo on 2015/4/11.
 */
public class DetailActivity extends BaseActivity implements ProgressGenerator.OnCompleteListener{

    private ActionProcessButton joinButton;
    private ProgressGenerator progressGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMyContentView(R.layout.detials_layout);


        initSwipeBackLayout();
        initPullRefresh(R.id.base_pull_to_refresh);

        init();
        init_card_inner_layout();

    }

    private void init(){
         progressGenerator = new ProgressGenerator(this);
         joinButton = (ActionProcessButton) findViewById(R.id.join_btn);



        joinButton.setMode(ActionProcessButton.Mode.ENDLESS);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressGenerator.start(joinButton);

            }
        });
    }


    private void init_card_inner_layout() {
        //Create a Card
        Card card = new Card(this,R.layout.detail_custom_card);
        //Create a CardHeader
        CardHeader header = new CardHeader(this);
        //Set the header title
        header.setTitle("this is a title");
        card.addCardHeader(header);
        //Set the card inner text
        card.setTitle("this is a introduce this is a introducethis is a introducethis is a introducethis is a introducethis is a introducethis is a introducethis is a introducethis is a");
        //Set card in the cardView
        CardViewNative cardView = (CardViewNative) this.findViewById(R.id.detail_title);
        cardView.setCard(card);
    }

    @Override
    public void onComplete() {
//        Toast.makeText(DetailActivity.this,"报名成功",Toast.LENGTH_SHORT).show();
    }
}
