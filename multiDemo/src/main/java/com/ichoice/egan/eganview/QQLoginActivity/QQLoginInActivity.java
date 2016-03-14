package com.ichoice.egan.eganview.QQLoginActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ichoice.egan.eganview.R;
import com.ichoice.egan.eganview.base.BaseActivity;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QQLoginInActivity extends BaseActivity implements View.OnClickListener {
    private static final String APP_ID = "1104860953";

    @Bind(R.id.button_Login)
    Button buttonLogin;
    @Bind(R.id.imageView_UserIcon)
    ImageView imageViewUserIcon;
    @Bind(R.id.textView_UserName)
    TextView textViewUserName;
    private Tencent tencent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqlogin_in);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        tencent = Tencent.createInstance(APP_ID, this);
    }

    /**
     * 初始化试图
     */
    private void initView() {
        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_Login:
                //进行登陆或者分享
                tencent.login(this,"all",new BaseUiListener());
                break;
        }
    }

    private class BaseUiListener implements IUiListener{
        @Override
        public void onComplete(Object o) {
            Toast.makeText(QQLoginInActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }

//    /**
//     * 登陆
//     */
//    private void login(){
//        tencent.login(this, "all", new IUiListener() {
//            @Override
//            public void onComplete(Object response) {
//                Log.d("ysk",response.toString());
//                /*String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
//                String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
//                String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);*/
//            }
//
//            @Override
//            public void onError(UiError uiError) {
//
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//        });
//    }
}