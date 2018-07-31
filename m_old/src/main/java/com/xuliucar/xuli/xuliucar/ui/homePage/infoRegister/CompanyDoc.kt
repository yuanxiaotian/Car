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
import kotlinx.android.synthetic.main.company_doc.*


class CompanyDoc : BaseNewFragment<BasePresenter>(), View.OnClickListener {

    private var imgUrl: String? = null
    private var tag: Int = 0
    private var uid: String? = null

    override fun addViewId(): Int = 0
    override fun layViewId(): Int = R.layout.company_doc

    override fun initView(savedInstanceState: Bundle?, view: View) {
        backToolbar()
        setToolbarTitle(getString(R.string.company_zj))
        c_Organizational_institution.setOnClickListener(this)
        c_Local_tax.setOnClickListener(this)
        c_State_tax.setOnClickListener(this)
        c_Road_transport_permit.setOnClickListener(this)

        val num = 1000 + App.compid
        uid = "CO-$num-1"
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                val num = 1000 + App.compid
                val uid = "CO-$num-1"
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
            if (requestCode == 102 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(c_Organizational_institution_img, data)
            } else if (requestCode == 103 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(c_Local_tax_img, data)
            } else if (requestCode == 104 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(c_State_tax_img, data)
            } else if (requestCode == 105 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(c_Road_transport_permit_img, data)
            } else if (requestCode == 102 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(c_Organizational_institution_img, data)
            } else if (requestCode == 103 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(c_Local_tax_img, data)
            } else if (requestCode == 104 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(c_State_tax_img, data)
            } else if (requestCode == 105 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(c_Road_transport_permit_img, data)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onClick(view: View) {
        val type = "1"
        val angle = -90
        val i = view.id
        if (i == R.id.c_Organizational_institution) {
            tag = 102
            imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.compstr_url, type, uid, angle, width(), height(), tag)

        } else if (i == R.id.c_Local_tax) {
            tag = 103
            imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.ltax_url, type, uid, angle, width(), height(), tag)

        } else if (i == R.id.c_State_tax) {
            tag = 104
            imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.ctax_url, type, uid, angle, width(), height(), tag)

        } else if (i == R.id.c_Road_transport_permit) {
            tag = 105
            imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.tpermit_url, type, uid, angle, width(), height(), tag)

        }
    }
}
