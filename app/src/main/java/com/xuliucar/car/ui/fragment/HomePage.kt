package com.xuliucar.car.ui.fragment


import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.AppCompatTextView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import com.cangmaomao.lib.action.*
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.config.AppPower
import com.cangmaomao.lib.config.REQUEST_CAMERA_PERMISSION
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.utils.GlideImageLoader
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.lib.utils.StatusBarUtil
import com.cangmaomao.lib.utils.toast
import com.google.gson.Gson
import com.xuliucar.car.R
import com.xuliucar.car.contract.HomePageContract
import com.xuliucar.car.presenter.HomePagePresenter
import com.xuliucar.xuli.xuliucar.bean.Counts
import com.xuliucar.xuli.xuliucar.bean.HomePageBean
import com.xuliucar.xuli.xuliucar.bean.NoticeBean
import com.xuliucar.xuli.xuliucar.newmvp.view.messageManage.MessageManageIndex
import com.xuliucar.xuli.xuliucar.permission.PermissionUtils
import com.xuliucar.xuli.xuliucar.ui.homePage.CompanyDetail
import com.xuliucar.xuli.xuliucar.ui.homePage.allCar.AllCar
import com.xuliucar.xuli.xuliucar.utils.ToastUtil
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.home_middle.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by skyward on 2016/6/29.
 */
class HomePage : BaseNewFragment<HomePageContract.Presenter>(), HomePageContract.View {


    private var slideshowView: Banner? = null
    private var mHomePageBean: HomePageBean? = null

    override fun context(): Context = _mActivity
    override fun layViewId(): Int = R.layout.home
    override fun addViewId(): Int = 0
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun initView(savedInstanceState: Bundle?, view: View) {
        slideshowView = view.findViewById(R.id.slideshowView)
        StatusBarUtil.transparencyBar(activity)
        HomePagePresenter(this)
        p.start()

        setListener()
    }


    private fun setListener() {
        whole_car_layout.setOnClickListener {
            if (AppPower.app_pow1 == "0") {
                toast(getString(R.string.power_tips))
            } else {
                val bundle = Bundle()
                bundle.putString("num", "0")
                EventBus.getDefault().post(AppEvent(f_allCar, bundle))
            }
        }

        information_reg_layout.setOnClickListener {
            //此模块为拍照模块，在API6.0以后需要动态申请权限，因此进入前先申请
            if (PermissionUtils.hasPermissions(activity, Manifest.permission.CAMERA)) {
                EventBus.getDefault().post(AppEvent(f_informationreg, null))
            } else {
                PermissionUtils.requestPermissions(activity, getString(R.string.rationale_cameras), REQUEST_CAMERA_PERMISSION, Manifest.permission.CAMERA)
            }
        }

        Inventory_vehicle_layout!!.setOnClickListener { EventBus.getDefault().post(AppEvent(f_illegalindex, null)) }


        financial_management_layout.setOnClickListener { view ->
            if (AppPower.app_pow7 == "0" && AppPower.app_pow8 == "0" && AppPower.app_pow9 == "0" && AppPower.app_pow10 == "0" && AppPower.app_pow11 == "0" && AppPower.app_pow12 == "0") {
                toast(getString(R.string.power_tips))
            } else {
                EventBus.getDefault().post(AppEvent(f_moneyManager, null))
            }
        }

        schedule_layout.setOnClickListener { EventBus.getDefault().post(AppEvent(f_doitemsindex, null)) }

        //过期事项
        past_events_layout.setOnClickListener {
            if (AppPower.app_pow24 == "0") {
                toast(getString(R.string.power_tips))
            } else {
                EventBus.getDefault().post(AppEvent(f_passItemsIndex, null))
            }
        }

        marketing_manager_layout.setOnClickListener {
            if (AppPower.app_pow25 == "0" && AppPower.app_pow26 == "0" && AppPower.app_pow27 == "0") {
                toast(getString(R.string.power_tips))
            } else {
                EventBus.getDefault().post(AppEvent(f_saleManager, null))
            }
        }

        message_manager_layout.setOnClickListener {
            if (AppPower.app_pow28 == "0" && AppPower.app_pow29 == "0") toast(getString(R.string.power_tips)) else EventBus.getDefault().post(AppEvent(f_messageManageIndex, null))
        }

//        carCounts.setOnClickListener {
//            val intent7 = Intent(activity, AllCar::class.java)
//            intent7.putExtra("num", "1")
//            startActivity(intent7)
//        }
//
//
//        big_company_name.setOnClickListener { toCompanyDetail() }
//
//
//        car_icon.setOnClickListener { toCompanyDetail() }
//
//        company_type_layout.setOnClickListener { toCompanyDetail() }
//        more!!.setOnClickListener { toCompanyDetail() }
//
//        Relation_company.setOnClickListener {
//            val intent = Intent(activity, MessageManageIndex::class.java)
//            startActivity(intent)
//        }
    }

    private fun toCompanyDetail() {
        if (!TextUtils.isEmpty(AppPower.app_pow30) && AppPower.app_pow30 == "0") {
            ToastUtil.showShortToast(activity, "抱歉，您没有权限进入该模块！")
        } else {
            val intent8 = Intent()
            val gson = Gson()
            intent8.putExtra("homePageData", gson.toJson(mHomePageBean))
            intent8.setClass(activity!!, CompanyDetail::class.java)
            startActivity(intent8)
        }
    }


    override fun showBanner(bannerList: MutableList<String>) {
        slideshowView!!.setImageLoader(GlideImageLoader())
        slideshowView!!.setBannerAnimation(Transformer.DepthPage)
        slideshowView!!.update(bannerList)
        slideshowView!!.setDelayTime(5000)
        slideshowView!!.isAutoPlay(true)
        slideshowView!!.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        slideshowView!!.start()
    }

    override fun showNotice(noticeList: MutableList<NoticeBean.NewNoticeData>) {
        noticeList.forEach {
            val view = LayoutInflater.from(context()).inflate(R.layout.item_home_viewflipper, null)
            view.findViewById<AppCompatTextView>(R.id.tv_zf).text = it.title_zf
            view.findViewById<AppCompatTextView>(R.id.tv_xl).text = it.title_xl
            view.findViewById<AppCompatTextView>(R.id.tv_xt).text = it.title_xt
            viewFlipper.addView(view)
        }
        viewFlipper.setOnClickListener { EventBus.getDefault().post(AppEvent(f_notice, null)) }
    }


    override fun showFail(e: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDate(data: Counts.DataBean.InfoBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toItemFragment(flag: String, appPower: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResume() {
        super.onResume()
        if (slideshowView != null) {
            slideshowView!!.startAutoPlay()
        }
        viewFlipper.startFlipping()
    }

    override fun onStop() {
        super.onStop()
        if (slideshowView != null) {
            slideshowView!!.stopAutoPlay()
        }
        viewFlipper.stopFlipping()
    }

}

