package com.xuliucar.xuli.xuliucar.ui.search

import android.os.Bundle
import android.os.Handler
import android.text.SpannableString
import android.util.Log
import android.widget.ListView

import com.google.gson.Gson
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.adapter.PassPayAdapter
import com.xuliucar.xuli.xuliucar.adapter.SearchAdapter
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.base.BaseActivity
import com.xuliucar.xuli.xuliucar.bean.PassPayBean
import com.xuliucar.xuli.xuliucar.bean.SearchLightBean
import com.xuliucar.xuli.xuliucar.config.CacheName
import com.xuliucar.xuli.xuliucar.utils.SearchLightUtil
import com.xuliucar.xuli.xuliucar.widget.SearchView

import java.util.ArrayList
import java.util.HashSet

class PassPayS : BaseActivity(), SearchView.SearchViewListener {
    private var resultAdapter: PassPayAdapter? = null
    private var mInfoBeanList: MutableList<PassPayBean.DataBean>? = null
    private var resultData: MutableList<PassPayBean.DataBean>? = null
    private var autoCompleteAdapter: SearchAdapter? = null
    private var autoCompleteData: MutableList<SearchLightBean>? = null
    private var allData: MutableList<String>? = null
    private var filterAutoCompleteData: List<String>? = null
    private var mGson: Gson? = null

    override fun initContentView(savedInstanceState: Bundle) {
        setContentView(R.layout.activity_search)
        initData()
    }

    public override fun initView() {
        //设置监听
        mGson = Gson()
    }

    protected fun initData() {
        getData()
        //初始化自动补全数据
        getAutoCompleteData(null)
        //初始化搜索结果数据
        getResultData(null)
    }

    private fun getData() {
        mInfoBeanList = ArrayList()
        allData = ArrayList()

    }



    /**
     * 获取自动补全data 和adapter
     */
    private fun getAutoCompleteData(text: String?) {

        Log.i("myLog", "输入 " + text!!)
        val hintSize = 20
        if (autoCompleteData == null) {
            //初始化
            autoCompleteData = ArrayList(hintSize)
        } else {
            // 根据text 获取auto data
            autoCompleteData!!.clear()
            var i = 0
            var count = 0
            while (i < filterAutoCompleteData!!.size && count < hintSize) {
                if (filterAutoCompleteData!![i].contains(text.trim { it <= ' ' })) {
                    val s = SearchLightUtil.setKeyWordColor(filterAutoCompleteData!![i], text)
                    autoCompleteData!!.add(SearchLightBean(s))
                    count++
                }
                i++
            }
        }
        if (autoCompleteAdapter == null) {
            autoCompleteAdapter = SearchAdapter(this@PassPayS, autoCompleteData)
        } else {
            autoCompleteAdapter!!.notifyDataSetChanged()
        }
    }

    /**
     * 获取搜索结果data和adapter
     */
    private fun getResultData(text: String?) {
        if (resultData == null) {
            // 初始化
            resultData = ArrayList()
        } else {
            resultData!!.clear()
            for (i in mInfoBeanList!!.indices) {
                if (mInfoBeanList!![i].itemname.contains(text!!.trim { it <= ' ' })) {
                    resultData!!.add(mInfoBeanList!![i])
                }
            }
        }
        if (resultAdapter == null) {
            resultAdapter = PassPayAdapter(R.layout.car_loan_item)
        } else {
            resultAdapter!!.notifyDataSetChanged()
        }
    }

    override fun onRefreshAutoComplete(text: String) {
        getAutoCompleteData(text)
    }

    override fun onSearch(text: String) {
        //更新result数据
        getResultData(text)

        //        //第一次获取结果 还未配置适配器
        //        if (recyclerView.getAdapter() == null) {
        //            //获取搜索数据 设置适配器
        //            recyclerView.setAdapter(resultAdapter);
        //
        //        } else {
        //            //更新搜索数据
        //            resultAdapter.notifyDataSetChanged();
        //
        //        }
    }
}
