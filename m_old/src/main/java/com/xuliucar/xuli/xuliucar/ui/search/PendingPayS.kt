package com.xuliucar.xuli.xuliucar.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.adapter.PendingPayAdapter
import com.xuliucar.xuli.xuliucar.adapter.SearchAdapter
import com.xuliucar.xuli.xuliucar.bean.PendingPayBean
import com.xuliucar.xuli.xuliucar.bean.SearchLightBean
import com.xuliucar.xuli.xuliucar.utils.SearchLightUtil
import com.xuliucar.xuli.xuliucar.widget.SearchView
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*

class PendingPayS : BaseNewFragment<BasePresenter>(), SearchView.SearchViewListener {
    override fun layViewId(): Int = R.layout.activity_search

    override fun initView(savedInstanceState: Bundle?, view: View) {
        main_search_layout.setSearchViewListener(this)
        main_search_layout.setAutoCompleteAdapter(autoCompleteAdapter, autoCompleteData)
    }

    override fun addViewId(): Int = 0

    private var resultAdapter: PendingPayAdapter? = null
    private var mInfoBeanList: MutableList<PendingPayBean.DataBean.InfoBean>? = null
    private var resultData: MutableList<PendingPayBean.DataBean.InfoBean>? = null
    private var autoCompleteAdapter: SearchAdapter? = null
    private var autoCompleteData: MutableList<SearchLightBean>? = null
    private var allData: MutableList<String>? = null
    private var filterAutoCompleteData: List<String>? = null

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
            autoCompleteAdapter = SearchAdapter(_mActivity, autoCompleteData)
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
            resultAdapter = PendingPayAdapter(R.layout.car_loan_item)
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

        //第一次获取结果 还未配置适配器
        if (recyclerView.getAdapter() == null) {
            //获取搜索数据 设置适配器
            recyclerView.setAdapter(resultAdapter);

        } else {
            //更新搜索数据
            resultAdapter!!.notifyDataSetChanged();

        }
    }

}
