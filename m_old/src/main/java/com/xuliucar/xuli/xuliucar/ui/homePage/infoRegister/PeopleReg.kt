package com.xuliucar.xuli.xuliucar.ui.homePage.infoRegister

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.config.GALLERY_REQUEST_CODE
import com.cangmaomao.lib.utils.height
import com.cangmaomao.lib.utils.width
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.config.UrlConfig
import com.xuliucar.xuli.xuliucar.dataHandlers.ShowImageDialog
import com.xuliucar.xuli.xuliucar.ui.cutPic
import com.xuliucar.xuli.xuliucar.utils.ToastUtil
import kotlinx.android.synthetic.main.people_reg.*


class PeopleReg : BaseNewFragment<BasePresenter>(), View.OnClickListener {

    private var imgUrl: String? = null
    private var tag: Int = 0
    private var uid: String? = null

    override fun addViewId(): Int = 0
    override fun layViewId(): Int = R.layout.people_reg

    override fun initView(savedInstanceState: Bundle?, view: View) {
        backToolbar()
        setToolbarTitle(getString(R.string.people_reg))

        people_idcart.setOnClickListener(this)
        people_Certificate.setOnClickListener(this)
        people_Safety_Officer.setOnClickListener(this)

        val num = 1000 + App.compid
        uid = "NU-" + num + "-" + people_from!!.text.toString()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                val num = 1000 + App.compid
                val uid = "NU-" + num + "-" + people_from!!.text.toString()
                val intent = Intent(_mActivity, cutPic::class.java)
                intent.data = data.data
                intent.putExtra("angle", -90)
                intent.putExtra("uid", uid)
                intent.putExtra("url", imgUrl)
                intent.putExtra("type", "1")
                startActivityForResult(intent, tag)
            }

        }
        if (data != null) {
            if (requestCode == 101 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(people_idcart_img, data)
            } else if (requestCode == 102 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(people_Certificate_img, data)
            } else if (requestCode == 103 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(people_Safety_Officer_img, data)
            } else if (requestCode == 101 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(people_idcart_img, data)
            } else if (requestCode == 102 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(people_Certificate_img, data)
            } else if (requestCode == 103 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(people_Safety_Officer_img, data)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onClick(view: View) {
        val type = "1"
        val angle = -90
        val i = view.id
        if (i == R.id.people_idcart) {
            if (people_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 101
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.userid1_url, type, uid, angle, width(), height(), tag)
            }

        } else if (i == R.id.people_Certificate) {
            if (people_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 102
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.mc1_url, type, uid, angle, width(), height(), tag)
            }

        } else if (i == R.id.people_Safety_Officer) {
            if (people_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 103
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.sd1_url, type, uid, angle, width(), height(), tag)
            }

        }
    }

}
