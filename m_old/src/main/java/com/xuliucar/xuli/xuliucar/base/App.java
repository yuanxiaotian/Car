package com.xuliucar.xuli.xuliucar.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.cangmaomao.network.request.config.Config;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.dataHandlers.ACache;

import java.io.File;
import java.util.List;


/**
 * Created by skyward on 2016/7/12.
 *
 */
public class App extends Application {
    public static int size;
    public static String from ="";
    public static String user="";
    public static String userp="";//管理权限
    public static String cookie="";
    public static int compid;
    public static String loginid="";
    public static String ctype="";//是否三证合一
    public static String utype="";//用户类型
    public static int sdkVsesion;//获取sdk版本号
    public static String brand;//获取手机品牌
    public static ACache mCache;//定义成静态方便调用
    public static String pushCount="";
    public static String tag1="";
    public static String tag2="";
    public static ImageLoader imageLoader = ImageLoader.getInstance();
    public static DisplayImageOptions options;
    public static DisplayImageOptions  RoundeOptions;
    public static DisplayImageOptions RoundeWithoutCache;
    public static App myContext;


    public static Application getContext(){
        return myContext;
    }


    private boolean isMainProcess() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        myContext = this;

        Config.S_HTTP_ROOT_URL = "http://www.gzxlxx.com:8866/index.php/Home/App/";

        initCache();
        initImageLoader(myContext);
        // 在主进程设置信鸽相关的内容
        if (isMainProcess()) {
            // 为保证弹出通知前一定调用本方法，需要在application的onCreate注册
            // 收到通知时，会调用本回调函数。
            // 相当于这个回调会拦截在信鸽的弹出通知之前被截取
            // 一般上针对需要获取通知内容、标题，设置通知点击的跳转逻辑等等
//            XGPushManager.setNotifactionCallback(new XGPushNotifactionCallback() {
//
//                        @Override
//                        public void handleNotify(XGNotifaction xGNotifaction) {
//                            Log.i("search_light_result", "处理信鸽通知：" + xGNotifaction);
//                            // 获取标签、内容、自定义内容
//                            String title = xGNotifaction.getTitle();
//                            //String content = xGNotifaction.getContent();
//                           // String customContent = xGNotifaction.getCustomContent();
//                            Log.i("myLog","App title"+ title);
////                            Log.i("myLog","App content"+ content);
////                            ToastUtil.showShortToast(getApplicationContext(),"有通知");
//                            // 其它的处理
//                            // 如果还要弹出通知，可直接调用以下代码或自己创建Notifaction，否则，本通知将不会弹出在通知栏中。
//                            xGNotifaction.doNotify();
//                        }
//                    });
        }
    }

    private void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context,"imageloader/Cache");
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800)
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2* 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheSize(100)
                .diskCache(new UnlimitedDiscCache(cacheDir))
                .diskCacheSize(50 * 1024 * 1024)
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context,5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .writeDebugLogs() // Remove for releaseapp
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);


        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loading)
                .showImageForEmptyUri(R.drawable.loading)
                .showImageOnFail(R.drawable.loading)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .resetViewBeforeLoading(true)
                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成

        RoundeOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loading)
                .showImageForEmptyUri(R.drawable.loading)
                .showImageOnFail(R.drawable.add_default_photo)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .resetViewBeforeLoading(true)
                .displayer(new RoundedBitmapDisplayer(25))//是否设置为圆角，弧度为多少
                .build();//构建完成
        RoundeWithoutCache = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(25))//是否设置为圆角，弧度为多少
                .build();//构建完成
    }


    //初始化缓存
    private void initCache() {
        mCache = ACache.get(myContext);

    }
}
