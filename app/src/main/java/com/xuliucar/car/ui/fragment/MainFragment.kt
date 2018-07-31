package com.xuliucar.car.ui.fragment

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import com.cangmaomao.lib.action.f_login
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.event.MainEvent
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.car.R
import com.xuliucar.car.app.App
import com.xuliucar.car.contract.MainContract
import com.xuliucar.car.presenter.MainPresenter
import com.xuliucar.me.ui.fragment.Me
import kotlinx.android.synthetic.main.a_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainFragment : BaseNewFragment<MainContract.Presenter>(), MainContract.View {

    override fun layViewId(): Int = R.layout.a_main
    override fun addViewId(): Int = R.id.main_frameLayout
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    private lateinit var homePage: HomePage
    private lateinit var notice: Notice
    private lateinit var me: Me


    override fun initView(savedInstanceState: Bundle?, view: View) {
        homePage = HomePage()
        notice = Notice()
        me = Me()
        MainPresenter(this)
        p.start()
    }

    override fun loginOverdue(overdue: Boolean) {
        if (overdue) {
            _mActivity.create("userInfo").removeAll()
            EventBus.getDefault().post(AppEvent(f_login, null))
        } else {
            loadMultipleRootFragment(addViewId(), 0, homePage, notice, me)
            val state = arrayOf(intArrayOf(-android.R.attr.state_checked), intArrayOf(android.R.attr.state_checked))
            val colors = intArrayOf(resources.getColor(R.color.c_7c), resources.getColor(R.color.c_1e))
            val colorStateList = ColorStateList(state, colors)
            navigation.itemTextColor = colorStateList
            navigation.itemIconTintList = colorStateList
            navigation.setOnNavigationItemSelectedListener(mOnNavigationListener)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MainEvent) {
        when (event.type) {
            0 -> {
                val a = (_mActivity.application as App).activitys
                for (activity in a) {
                    activity.finish()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private val mOnNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                showHideFragment(homePage)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_gg -> {
                showHideFragment(notice)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_me -> {
                showHideFragment(me)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun showFail(e: Throwable) {
    }

}
