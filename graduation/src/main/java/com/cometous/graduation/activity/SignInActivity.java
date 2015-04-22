package com.cometous.graduation.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cometous.graduation.R;
import com.cometous.graduation.view.ProgressGenerator;
import com.dd.processbutton.iml.ActionProcessButton;

/**
 * Created by Devilsen on 2015/4/18.
 */
public class SignInActivity extends Activity implements ProgressGenerator.OnCompleteListener{

    private ActionBar actionBar;
    private EditText usernameEdit;
    private EditText usernamePasswordEdit;
    private ProgressGenerator progressGenerator;
    private ActionProcessButton signInBtn;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);

        actionBar = getActionBar();
        actionBar.hide();

        usernameEdit = (EditText) findViewById(R.id.username_edit);
        usernamePasswordEdit = (EditText) findViewById(R.id.user_password_edit);
        progressGenerator = new ProgressGenerator(this);
        signInBtn = (ActionProcessButton) findViewById(R.id.btnSignIn);
        signInBtn.setMode(ActionProcessButton.Mode.ENDLESS);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = usernameEdit.getText().toString().trim();
                password = usernamePasswordEdit.getText().toString().trim();

                check(username,password);
            }
        });


    }

    @Override
    public void onComplete() {

    }

    private void check(String username,String password){

        if (username.isEmpty()){
            Toast.makeText(this,"用户名为空",Toast.LENGTH_SHORT).show();
        }else if (password.isEmpty()){
            Toast.makeText(this,"密码为空",Toast.LENGTH_SHORT).show();
        }else if (true){
            progressGenerator.start(signInBtn);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.small_to_big, R.anim.big_to_small);
    }

    @Override
    public void onStop() {
        super.onStop();
        overridePendingTransition(R.anim.small_to_big, R.anim.big_to_small);
    }
}
