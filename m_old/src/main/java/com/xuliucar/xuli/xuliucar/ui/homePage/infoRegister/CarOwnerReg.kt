package com.xuliucar.xuli.xuliucar.ui.homePage.infoRegister

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.utils.height
import com.cangmaomao.lib.utils.width
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.config.UrlConfig
import com.xuliucar.xuli.xuliucar.dataHandlers.ShowImageDialog
import com.xuliucar.xuli.xuliucar.ui.cutPic
import com.xuliucar.xuli.xuliucar.utils.ToastUtil
import kotlinx.android.synthetic.main.car_owner_reg.*

class CarOwnerReg : BaseNewFragment<BasePresenter>(), View.OnClickListener {

    private var imgUrl: String? = null
    private var tag: Int = 0
    private var uid: String? = null

    companion object {
        private val GALLERY_REQUEST_CODE = 2//相册
    }

    override fun layViewId(): Int {
        return R.layout.car_owner_reg
    }

    override fun initView(savedInstanceState: Bundle?, view: View) {
        backToolbar()
        setToolbarTitle(getString(R.string.car_owner_reg))


        carOwner_idcartReg_z.setOnClickListener(this)
        carOwner_idcartReg_f.setOnClickListener(this)
        carOwner_Temp.setOnClickListener(this)
        carOwner_Temp_F.setOnClickListener(this)

        val num = 1000 + App.compid
        uid = "NO-" + num + "-" + carOwner_from.text.toString()
    }

    override fun addViewId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                val num = 1000 + App.compid
                val uid = "NO-" + num + "-" + carOwner_from!!.text.toString()
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
                ShowImageDialog.getInstance().ImageWithoutCacheRound(carOwner_idcartReg_img, data)
            } else if (requestCode == 102 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(carOwner_idcartReg_f_img, data)
            } else if (requestCode == 103 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(carOwner_Temp_img, data)
            } else if (requestCode == 104 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(carOwner_Temp_img_F, data)
            } else if (requestCode == 101 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(carOwner_idcartReg_img, data)
            } else if (requestCode == 102 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(carOwner_idcartReg_f_img, data)
            } else if (requestCode == 103 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(carOwner_Temp_img, data)
            } else if (requestCode == 104 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(carOwner_Temp_img_F, data)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onClick(view: View) {
        val type = "1"
        val angle = -90
        val i = view.id
        if (i == R.id.carOwner_idcartReg_z) {
            if (carOwner_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 101
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.ownerid1_url, type, uid, angle, width(), height(), tag)
            }

        } else if (i == R.id.carOwner_idcartReg_f) {
            if (carOwner_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 102
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.ownerid2_url, type, uid, angle, width(), height(), tag)
            }

        } else if (i == R.id.carOwner_Temp) {
            if (carOwner_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 103
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.ownertmpid_url, type, uid, angle, width(), height(), tag)
            }

        } else if (i == R.id.carOwner_Temp_F) {
            if (carOwner_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 104
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.ownertmpidbb_url, type, uid, angle, width(), height(), tag)
            }

        }
    }

}
