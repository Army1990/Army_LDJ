package com.ichoice.egan.eganview;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.ichoice.egan.eganview.GestureDetector.GestureDetectorActivity;
import com.ichoice.egan.eganview.NineSpaceGridView.NineSpaceGridViewActivity;
import com.ichoice.egan.eganview.QQLoginActivity.QQLoginInActivity;
import com.ichoice.egan.eganview.RecyclerViewActivity.RecyclerViewActivity;
import com.ichoice.egan.eganview.SwipeRefreshLayoutActivity.GitHubSwipeRefreshLayoutActivity;
import com.ichoice.egan.eganview.UmengActivity.UmengActivity;
import com.ichoice.egan.eganview.Utils.ToastUtil;
import com.ichoice.egan.eganview.WeiMaiActivity.WeiMaiActivity;
import com.ichoice.egan.eganview.listview.ListViewActivity;
import com.ichoice.egan.eganview.view.BadgeView;
import com.ichoice.egan.eganview.viewpager.ViewPager_Activity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.textView_ZhenDong)
    TextView textViewZhenDong;
    @Bind(R.id.textView_badgeView)
    TextView textViewBadgeView;
    @Bind(R.id.textView_GridView)
    TextView textViewGridView;
    @Bind(R.id.textView_GitHub_SwipeRefreshLayout)
    TextView textViewGitHubSwipeRefreshLayout;
    @Bind(R.id.textView_QQ_LoginIn)
    TextView textViewQQLoginIn;
    @Bind(R.id.textView_Umeng)
    TextView textViewUmeng;
    @Bind(R.id.textView_PlayPoolSound)
    TextView textViewPlayPoolSound;
    @Bind(R.id.textView_recycleView)
    TextView textViewRecycleView;
    @Bind(R.id.textView_Baidu_Location)
    TextView textViewBaiduLocation;
    @Bind(R.id.textView_wemayDemo)
    TextView textViewWemayDemo;
    @Bind(R.id.textView_viewPager)
    TextView textViewViewPager;
    @Bind(R.id.textView_listView)
    TextView textViewListView;
    @Bind(R.id.textView_GestureDetector)
    TextView textViewGestureDetector;
    @Bind(R.id.textView_LockScreen)
    TextView textViewLockScreen;
    @Bind(R.id.textView_RichScan)
    TextView textViewRichScan;
    @Bind(R.id.textView_ui)
    TextView textViewUi;
    @Bind(R.id.textView_BaiDu)
    TextView textViewBaiDu;
    @Bind(R.id.textView_Push)
    TextView textViewPush;
    @Bind(R.id.textView_Pay)
    TextView textViewPay;
    @Bind(R.id.textView_ShiPin)
    TextView textViewShiPin;
    private BadgeView badgeView;
    private SoundPool soundPool;
    private int soundID;
    private LocationClient mLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();


        initView();
        initLocationOption();
    }

    /**
     * 设置监听事件
     */
    private void initView() {
        //初始化百度定位相关信息
        mLocationClient = new LocationClient(this);
        mLocationClient.registerLocationListener(new MyLocationListener());


        soundPool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 0);
        textViewBadgeView.setOnClickListener(this);
        textViewGridView.setOnClickListener(this);
        textViewGitHubSwipeRefreshLayout.setOnClickListener(this);
        textViewQQLoginIn.setOnClickListener(this);
        textViewUmeng.setOnClickListener(this);
        textViewPlayPoolSound.setOnClickListener(this);
        textViewZhenDong.setOnClickListener(this);
        textViewRecycleView.setOnClickListener(this);
        textViewBaiduLocation.setOnClickListener(this);
        textViewWemayDemo.setOnClickListener(this);
        textViewViewPager.setOnClickListener(this);
        textViewListView.setOnClickListener(this);
        textViewGestureDetector.setOnClickListener(this);
        textViewLockScreen.setOnClickListener(this);
        textViewRichScan.setOnClickListener(this);
    }

    /**
     * 初始化定位参数
     */
    private void initLocationOption() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("gcj02");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.textView_badgeView:
                setBadge(v, "2");
                break;
            case R.id.textView_GridView:
                intent.setClass(this, NineSpaceGridViewActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_GitHub_SwipeRefreshLayout:
                intent.setClass(this, GitHubSwipeRefreshLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_QQ_LoginIn:
                intent.setClass(this, QQLoginInActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_Umeng:
                intent.setClass(this, UmengActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_wemayDemo:
                intent.setClass(this, WeiMaiActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_recycleView:
                intent.setClass(this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_viewPager:
                intent.setClass(this, ViewPager_Activity.class);
                startActivity(intent);
                break;
            case R.id.textView_listView:
                intent.setClass(this, ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_GestureDetector:
                intent.setClass(this, GestureDetectorActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_LockScreen:
                intent.setClass(this, GestureDetectorActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_RichScan:
                // TODO: 2015/11/18 二维码扫描
                break;
            case R.id.textView_PlayPoolSound:
                playPoolSound();
                break;
            case R.id.textView_ZhenDong:
                zhengDong();
                break;
            case R.id.textView_Baidu_Location:
                mLocationClient.start();
                break;
        }
    }

    /**
     * 定位成功，获得定位信息
     */
    private void getLocationInfo(BDLocation location) {
        //可以通过反地理编码方法根据坐标获得具体的地理位置
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            mLocationClient.stop();
            ToastUtil.showShortTime("定位成功-->" + location.getLongitude() + " : " + location.getAltitude());
            ToastUtil.showShortTime("位置语义化信息-->" + location.getLocationDescribe());
            getLocationInfo(location);
            //Receive Location
            /*StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());*/
        }
    }


    /**
     * 点击震动
     */
    private void zhengDong() {
        Vibrator vibrator = (Vibrator) this.getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    /**
     * 播放点击音效
     */
    private void playPoolSound() {
        // 参数1：id
        // 参数2、3：左右声道的音量控制
        // 参数4：优先级
        // 参数5：是否循环播放，0为不循环，-1为循环
        // 参数6：播放比率，取值0.5到2，一般为1，表示正常播放
        soundID = soundPool.load(this, R.raw.sound, 1);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(sampleId, 1, 1, 0, 0, 1);
            }
        });
//        soundPool.release();
//      soundPool.play(soundID, 1, 1, 0, 0, 1);
    }

    /**
     * 设置角标
     *
     * @param view   角标所依赖的控件
     * @param string 角标显示的字符串
     */
    private void setBadge(View view, String string) {
        badgeView = new BadgeView(this, view);
        if (badgeView.isShown()) {
            badgeView.hide();
        } else {
            badgeView.setText(string);
            badgeView.setBadgePosition(BadgeView.POSITION_BOTTOM_LEFT);
            badgeView.toggle();

        }
    }
}
