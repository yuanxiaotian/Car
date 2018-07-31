package com.xuliucar.car.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.cangmaomao.network.request.config.Config
import com.cangmaomao.network.request.cookie.AbsCookieJar
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.*
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.xuliucar.car.R
import java.util.*


class App : MultiDexApplication(), Application.ActivityLifecycleCallbacks {

    val activitys = Collections.synchronizedList(LinkedList<Activity>())!!

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    override fun onCreate() {
        super.onCreate()

        Config.S_HTTP_ROOT_URL = "http://www.gzxlxx.com:8866/index.php/Home/App/"

        AbsCookieJar.mContext = applicationContext

        val config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build()
        Fresco.initialize(this, config)

        registerActivityLifecycleCallbacks(this)
    }


    override fun onActivityDestroyed(p0: Activity?) {
        activitys.remove(p0)
    }

    override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
        activitys.add(p0)
    }

    override fun onActivityPaused(p0: Activity?) {
    }

    override fun onActivityResumed(p0: Activity?) {
    }

    override fun onActivityStarted(p0: Activity?) {
    }

    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
    }

    override fun onActivityStopped(p0: Activity?) {
    }


    init {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator {
            override fun createRefreshHeader(context: Context, layout: RefreshLayout): RefreshHeader {
                layout.setPrimaryColorsId(android.R.color.white, R.color.colorPrimary)//全局设置主题颜色
                return ClassicsHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        })

        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator {
            override fun createRefreshFooter(context: Context, layout: RefreshLayout): RefreshFooter {
                return ClassicsFooter(context).setDrawableSize(20f)
            }
        })

    }

}
