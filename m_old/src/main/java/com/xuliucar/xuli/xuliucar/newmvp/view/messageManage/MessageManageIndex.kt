package com.xuliucar.xuli.xuliucar.newmvp.view.messageManage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.CustomPeopleBean
import com.xuliucar.xuli.xuliucar.newmvp.contract.MessageMangeContract
import com.xuliucar.xuli.xuliucar.newmvp.view.messageManage.child.HistorySend
import com.xuliucar.xuli.xuliucar.newmvp.view.messageManage.child.SendMessage
import kotlinx.android.synthetic.main.message_manage_index.*

class MessageManageIndex : BaseNewFragment<MessageMangeContract.MainPresenter>(), MessageMangeContract.MainView {

    override fun layViewId(): Int = R.layout.message_manage_index
    override fun addViewId(): Int = 0

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initToolbar(getString(R.string.message_manager), null)
        val mSendMessage = SendMessage.newInstance(
                initArguments<MutableList<CustomPeopleBean>>("customList"),
                initArguments<String>("sendTag"),
                initArguments<String>("phoneNum"),
                initArguments<String>("target"),
                initArguments<String>("targetType"),
                initArguments<String>("sendtype"),
                initArguments<String>("ptple"))
        val titleList = listOf(getString(R.string.sendMessage_title), getString(R.string.message_histroy_send))
        val fragment = listOf<Fragment>(mSendMessage, HistorySend())
        viewPager.adapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return fragment[position]
            }

            override fun getCount(): Int {
                return fragment.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return titleList[position]
            }
        }
        tabLayout.setupWithViewPager(viewPager)
    }
}