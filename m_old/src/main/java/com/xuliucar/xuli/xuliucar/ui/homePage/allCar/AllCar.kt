package com.xuliucar.xuli.xuliucar.ui.homePage.allCar

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.Toolbar
import android.view.View

import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.view.CustomViewPager
import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.base.BaseFileObserver
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.ui.search.UsedCarSearch
import com.xuliucar.xuli.xuliucar.ui.search.carListSearch
import com.xuliucar.xuli.xuliucar.ui.search.newCarSearch
import kotlinx.android.synthetic.main.all_car.*

import java.io.File
import java.util.ArrayList

import okhttp3.Response
import okhttp3.ResponseBody


class AllCar : BaseNewFragment<BasePresenter>() {

    private val fragmentList = ArrayList<Fragment>()
    override fun layViewId(): Int = R.layout.all_car
    override fun addViewId(): Int = 0

    override fun initView(savedInstanceState: Bundle?, view: View) {
        fragmentList.add(CarList())
        fragmentList.add(InfoSum())
        fragmentList.add(NewCar())
        fragmentList.add(UsedCar())

        val title = arrayOf("车辆列表", "信息总览", "新车", "二手车")

        setToolbarTitle("所有车辆")
        backToolbar()

        viewPager.adapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return fragmentList[position]
            }

            override fun getCount(): Int {
                return fragmentList.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return title[position]
            }
        }
        viewPager.offscreenPageLimit = title.size
        tl!!.setupWithViewPager(viewPager)
    }


    private fun toSearch(num: Int) {
        when (num) {
            0 -> {
                val intent = Intent(_mActivity, carListSearch::class.java)
                intent.putExtra("ids", "carList")
                startActivity(intent)
            }
            2 -> {
                val intent2 = Intent(_mActivity, newCarSearch::class.java)
                startActivity(intent2)
            }
            3 -> {
                val intent3 = Intent(_mActivity, UsedCarSearch::class.java)
                startActivity(intent3)
            }
        }
    }
}
