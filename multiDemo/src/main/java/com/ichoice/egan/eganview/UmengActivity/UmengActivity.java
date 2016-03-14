package com.ichoice.egan.eganview.UmengActivity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ichoice.egan.eganview.R;
import com.ichoice.egan.eganview.Utils.ImageLoaderUtil;
import com.ichoice.egan.eganview.Utils.LogUtil;
import com.ichoice.egan.eganview.Utils.MessageNotifyTools;
import com.ichoice.egan.eganview.Utils.ToastUtil;
import com.ichoice.egan.eganview.Utils.ViewUtil;
import com.ichoice.egan.eganview.base.BaseActivity;
import com.umeng.scrshot.UMScrShotController;
import com.umeng.scrshot.adapter.UMAppAdapter;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.SmsShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sensor.UMSensor;
import com.umeng.socialize.sensor.controller.UMShakeService;
import com.umeng.socialize.sensor.controller.impl.UMShakeServiceFactory;
import com.umeng.socialize.sensor.strategy.UMSensorStrategy;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SmsHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UmengActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.button_share)
    Button buttonShare;
    @Bind(R.id.button_QQ_Login)
    Button buttonQQLogin;
    @Bind(R.id.button_QQ_User)
    Button buttonQQUser;
    @Bind(R.id.imageView_QQ_UserIcon)
    ImageView imageViewQQUserIcon;
    @Bind(R.id.textView_QQ_UserName)
    TextView textViewQQUserName;
    @Bind(R.id.button_QQ_Logout)
    Button buttonQQLogout;
    @Bind(R.id.button_MyShare)
    Button buttonMyShare;
    @Bind(R.id.button_shake_share)
    Button buttonShakeShare;
    @Bind(R.id.parent)
    RelativeLayout parent;
    @Bind(R.id.tx_qq)
    TextView txQq;
    @Bind(R.id.xl_weibo)
    TextView xlWeibo;
    @Bind(R.id.tx_wechat)
    TextView txWechat;
    private UMSocialService mController;
    private Dialog dialog;
    private PopupWindow sharePopupWindow;
    private UMShakeService mShakeController;
    private UMSocialService mControllerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umeng);
        ButterKnife.bind(this);
        initUmengShare();
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        dialog = new Dialog(this);

        buttonShare.setOnClickListener(this);
        buttonQQLogin.setOnClickListener(this);
        buttonQQUser.setOnClickListener(this);
        buttonQQLogout.setOnClickListener(this);
        buttonMyShare.setOnClickListener(this);
        buttonShakeShare.setOnClickListener(this);
        txQq.setOnClickListener(this);
        xlWeibo.setOnClickListener(this);
        txWechat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN);
        switch (v.getId()) {
            case R.id.button_share:
                //参数二:是用来限定是否可以免登录分享 true:登陆，false：免登录
                mController.openShare(this, false);
                break;
            case R.id.button_MyShare:
                showCustomShare();
                break;
            case R.id.button_QQ_Login:
                qqLogin();
                break;
            case R.id.button_QQ_User:
                qqGetUserIfo();
                break;
            case R.id.button_QQ_Logout:
                qqLogout();
                break;
            case R.id.button_shake_share:
                shakeShare();
                break;

            //自定义-----分享面板中的按钮响应事件
            case R.id.textView_QQ_CustomShare:
                customShareButtonClick(1);
                break;
            case R.id.textView_QZone_CustomShare:
                customShareButtonClick(2);
                break;
            case R.id.textView_WeiXin1_CustomShare:
                customShareButtonClick(3);
                break;
            case R.id.textView_WeiXin2_CustomShare:
                customShareButtonClick(4);
                break;
            case R.id.textView_Sms_CustomShare:
                customShareButtonClick(5);
                break;

            //第三方登录
            case R.id.tx_qq:
                txQqLogin();
                break;
            case R.id.xl_weibo:
                xlWbLogin();
                break;
            case R.id.tx_wechat:
                txWxLogin();
                break;
        }
    }

    private void txWxLogin() {

    }

    private void xlWbLogin() {

    }

    private void txQqLogin() {
        mControllerLogin.doOauthVerify(this, SHARE_MEDIA.QQ, new SocializeListeners.UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                MessageNotifyTools.showToast("开始授权");
            }

            @Override
            public void onComplete(Bundle bundle, SHARE_MEDIA share_media) {

                if(bundle!=null && !TextUtils.isEmpty(bundle.getString("uid"))){
                    MessageNotifyTools.showToast("授权成功");
                }else {
                    MessageNotifyTools.showToast("授权失败");

                }
            }

            @Override
            public void onError(SocializeException e, SHARE_MEDIA share_media) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {

            }
        });
    }

    /**
     * 摇一摇截屏分享
     */
    private void shakeShare() {

    }

    /**
     * 自定义面板的按钮响应事件综合处理
     *
     * @param flag
     */
    private void customShareButtonClick(int flag) {
        //根据flag参数来判断分享类型（运用三目运算符）
        SHARE_MEDIA share_media = flag == 1 ? SHARE_MEDIA.QQ : (flag == 2 ? SHARE_MEDIA.QZONE :
                (flag == 3 ? SHARE_MEDIA.WEIXIN : (flag == 4 ? SHARE_MEDIA.WEIXIN_CIRCLE : SHARE_MEDIA.SMS)));

        mController.postShare(this, share_media, new SocializeListeners.SnsPostListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, SocializeEntity socializeEntity) {
                if (i == 200) {
                    ToastUtil.showShortTime("分享成功");
                } else {
                    String eMsg = "";
                    if (i == -101) {
                        eMsg = "没有授权";
                    }
                    ToastUtil.showShortTime("分享失败[" + i + "] " + eMsg);
                }
            }
        });
    }

    /**
     * 展示自定义的分享面板
     */
    private void showCustomShare() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_custom_share_view, null);
        sharePopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ViewUtil.getItemChildView(contentView, R.id.textView_QQ_CustomShare).setOnClickListener(this);
        ViewUtil.getItemChildView(contentView, R.id.textView_QZone_CustomShare).setOnClickListener(this);
        ViewUtil.getItemChildView(contentView, R.id.textView_WeiXin1_CustomShare).setOnClickListener(this);
        ViewUtil.getItemChildView(contentView, R.id.textView_WeiXin2_CustomShare).setOnClickListener(this);
        ViewUtil.getItemChildView(contentView, R.id.textView_Sms_CustomShare).setOnClickListener(this);
        // TODO: 2015/11/4
        sharePopupWindow.setContentView(contentView);
        sharePopupWindow.setTouchable(true);
        sharePopupWindow.setFocusable(true);
        sharePopupWindow.setOutsideTouchable(true);
        sharePopupWindow.setBackgroundDrawable(new BitmapDrawable());
        sharePopupWindow.showAtLocation(LayoutInflater.from(this).inflate(R.layout.activity_umeng, null), Gravity.BOTTOM, 0, 0);
        sharePopupWindow.isShowing();
    }

    /**
     * QQ注销登陆
     */
    private void qqLogout() {
        mController.deleteOauth(this, SHARE_MEDIA.QQ, new SocializeListeners.SocializeClientListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onComplete(int status, SocializeEntity entity) {
                if (status == 200) {
                    ToastUtil.showShortTime("注销成功");
                } else {
                    ToastUtil.showShortTime("注销失败");
                }
            }
        });
    }

    /**
     * qq登陆获得用户信息
     */
    private void qqGetUserIfo() {
        mController.doOauthVerify(this, SHARE_MEDIA.QQ, new SocializeListeners.UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                ToastUtil.showShortTime("QQ授权开始");
            }

            @Override
            public void onComplete(Bundle bundle, SHARE_MEDIA share_media) {
                ToastUtil.showShortTime("QQ授权完成");
                mController.getPlatformInfo(UmengActivity.this, SHARE_MEDIA.QQ, new SocializeListeners.UMDataListener() {
                    @Override
                    public void onStart() {
                        ToastUtil.showShortTime("获取平台数据开始");
                    }

                    @Override
                    public void onComplete(int status, Map<String, Object> info) {
                        if (status == 200 && info != null) {
                            String imageUrl = (String) info.get("profile_image_url");
                            ImageLoaderUtil.getSingleImageLoaderutil().setBitmap(imageUrl, imageViewQQUserIcon);
                            textViewQQUserName.setText(((String) info.get("screen_name")));
                            StringBuilder sb = new StringBuilder();
                            Set<String> keys = info.keySet();
                            for (String key : keys) {
                                sb.append(key + "=" + info.get(key).toString() + "\r\n");
                            }
                            LogUtil.logMessage(sb.toString());
                        } else {
                            LogUtil.logMessage("发生错误：" + status);
                        }
                    }
                });
            }

            @Override
            public void onError(SocializeException e, SHARE_MEDIA share_media) {
                ToastUtil.showShortTime("QQ授权失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                ToastUtil.showShortTime("QQ授权取消");
            }
        });
    }

    /**
     * QQ有盟 三方的登陆实现
     */
    private void qqLogin() {
        mController.doOauthVerify(this, SHARE_MEDIA.QQ, new SocializeListeners.UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                ToastUtil.showShortTime("QQ授权开始");
            }

            @Override
            public void onComplete(Bundle bundle, SHARE_MEDIA share_media) {
                ToastUtil.showShortTime("QQ授权完成");
                if (bundle != null && TextUtils.isEmpty(bundle.getString("uid"))) {
                    ToastUtil.showShortTime("QQ授权成功");
                } else {
                    ToastUtil.showShortTime("QQ授权失败");
                }
            }

            @Override
            public void onError(SocializeException e, SHARE_MEDIA share_media) {
                ToastUtil.showShortTime("QQ授权失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                ToastUtil.showShortTime("QQ授权取消");
            }
        });
    }

    //定义一个摇一摇分享监听
    private UMSensor.OnSensorListener onSensorListener = new UMSensor.OnSensorListener() {
        @Override
        public void onActionComplete(SensorEvent sensorEvent) {
            //摇一摇动作完成后回调
        }

        @Override
        public void onButtonClick(UMSensor.WhitchButton whitchButton) {
            //点击分享窗口的取消和分享按钮触发的回调
            if (whitchButton == UMSensor.WhitchButton.BUTTON_CANCEL) {
                ToastUtil.showShortTime("摇一摇分享取消");
            } else {
                // 分享中, ( 用户点击了分享按钮 )
            }
        }

        @Override
        public void onStart() {
            //开始分享
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, SocializeEntity socializeEntity) {
            //分享完成后回调
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        //为不使用surfaceView的应用在这里注册摇一摇截图分享
        UMAppAdapter appAdapter = new UMAppAdapter(this);
        // 配置摇一摇截屏分享时用户可选的平台，最多支持五个平台
        List<SHARE_MEDIA> platforms = new ArrayList<SHARE_MEDIA>();
        platforms.add(SHARE_MEDIA.QZONE);
        platforms.add(SHARE_MEDIA.QQ);
        platforms.add(SHARE_MEDIA.WEIXIN);
        platforms.add(SHARE_MEDIA.SMS);
        // 设置摇一摇分享的文字内容
        mShakeController.setShareContent("美好瞬间");
        // 设置分享内容类型, PLATFORM_SCRSHOT代表使用摇一摇的截图，而文字内容为开发者预设的平台独立的内容
        // 例如WeiXinShareContent, SinaShareContent等.      一般情况可不设置.
        // mShakeController.setShakeMsgType(ShakeMsgType.PLATFORM_SCRSHOT);
        // 注册摇一摇截屏分享功能,mSensorListener在2.1.2中定义
        mShakeController.registerShakeListender(this, appAdapter,
                platforms, onSensorListener);

        //下面代码块是 仅仅 注册摇一摇截屏
        // 参数1为用户当前的activity; 参数2为截屏适配器; 参数3为截屏监听器
        mShakeController.registerShakeToScrShot(this, new UMAppAdapter(this), new UMScrShotController.OnScreenshotListener() {
            @Override
            public void onComplete(Bitmap bitmap) {
                // TODO: 2015/11/5 获得截屏对象进行处理
            }
        });

        mShakeController.registerShake(this, new UMSensorStrategy() {

            // 在shakeComplete编写你所需要的功能代码即可.
            @Override
            public void shakeComplete() {
                ToastUtil.showShortTime("摇一摇要执行的自定义操作");
            }
        });

//        //在onResume中注册该功能后，用户只需要摇一摇手机，即可打开友盟的分享面板，不需要用户点击相关的按钮调出分享面板，用户体验较好。
//        // 参数1为用户所在的activity, 参数2为摇一摇时是否开启音效
//        mShakeController.registerShakeToOpenShare(this, false);
    }

    /**
     * 初始化有盟分享
     */
    private void initUmengShare() {
        //用于分享的控制器
        mController = UMServiceFactory.getUMSocialService("com.umeng.share");
        //用于摇一摇的控制器,参数1为sdk 控制器描述符
        mShakeController = UMShakeServiceFactory.getShakeService("write.your.content");
        initLogin();
        initWeiXinShare();
        initQZoneShare();
        initQQShare();
        initSmsShare();

//        //设置分享的内容
//        mController.setShareContent("分享的内容");
//        //设置分享的图片，参数2为本地图片地址的资源引用
//        mController.setShareMedia(new UMImage(this, R.mipmap.ic_launcher));
//        //设置分享的音乐
//        UMusic uMusic = new UMusic("http://sns.whalecloud.com/test_music.mp3");
//        uMusic.setAuthor("GuGu");//音乐作者
//        uMusic.setTitle("天籁之音");//音乐名称
//        uMusic.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");//设置音乐缩略图
//        mController.setShareMedia(uMusic);//将音乐添加到分享列表中
//        // 设置分享视频
////        UMVideo umVideo = new UMVideo("http://v.youku.com/v_show/id_XNTE5ODAwMDM2.html?f=19001023");
////         设置视频缩略图
////        umVideo.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
////        umVideo.setTitle("友盟社会化分享!");
    }

    private void initLogin() {
        mControllerLogin = UMServiceFactory.getUMSocialService("com.umeng.login");
    }

    private void initSmsShare() {
        // 添加短信
        SmsHandler smsHandler = new SmsHandler();
        smsHandler.addToSocialSDK();

        SmsShareContent smsShareContent = new SmsShareContent();
        smsShareContent.setShareContent("这个是有盟的短信分享（分享的文字）");
        smsShareContent.setShareImage(new UMImage(this, R.mipmap.ic_launcher));
//        UMVideo umVideo = new UMVideo("http://v.youku.com/v_show/id_XNTE5ODAwMDM2.html?f=19001023");
//        设置分享的视频缩略图
//        umVideo.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
//        umVideo.setTitle("友盟社会化分享!");
//        smsShareContent.setShareVideo(umVideo);
        mController.setShareMedia(smsShareContent);
    }

    private void initQQShare() {
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this, "100424468",
                "c7394704798a158208a74ab60104f0ba");
        qqSsoHandler.addToSocialSDK();

        QQShareContent qqShareContent = new QQShareContent();
        //设置分享文字
        qqShareContent.setShareContent("友盟分享 -- QQ");
        //设置分享title
        qqShareContent.setTitle("测试测试，title");
        //设置分享图片
        qqShareContent.setShareImage(new UMImage(this, R.mipmap.ic_launcher));
        //设置点击分享内容的跳转链接
        qqShareContent.setTargetUrl("https://www.baidu.com/");
        mController.setShareMedia(qqShareContent);
    }

    private void initQZoneShare() {
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(this, "你的APP ID", "你的APP KEY");
        qZoneSsoHandler.addToSocialSDK();

        QZoneShareContent qZoneShareContent = new QZoneShareContent();
        qZoneShareContent.setShareContent("这个是有盟分享 -- qZone");
        qZoneShareContent.setShareImage(new UMImage(this, R.mipmap.ic_launcher));
        qZoneShareContent.setTitle("有盟分享到q空间，title");
        qZoneShareContent.setTargetUrl("https://www.baidu.com/");
        mController.setShareMedia(qZoneShareContent);
    }

    private void initWeiXinShare() {
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(this, "你的APP ID", "你的APP KEY");
        wxHandler.addToSocialSDK();
        // 添加微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(this, "你的APP ID", "你的APP KEY");
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();

        WeiXinShareContent weiXinShareContent = new WeiXinShareContent();
        weiXinShareContent.setShareImage(new UMImage(this, R.mipmap.ic_launcher));
        weiXinShareContent.setTitle("有盟分享到微信 title");
//        weiXinShareContent.setShareVideo();
        weiXinShareContent.setTargetUrl("https://www.baidu.com/");
        mController.setShareMedia(weiXinShareContent);
    }

}
