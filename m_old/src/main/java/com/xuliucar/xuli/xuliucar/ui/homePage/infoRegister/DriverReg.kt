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
import kotlinx.android.synthetic.main.driver_reg.*


class DriverReg : BaseNewFragment<BasePresenter>(), View.OnClickListener {

    private var imgUrl: String? = null
    private var tag: Int = 0
    private var uid: String? = null

    override fun layViewId(): Int = R.layout.driver_reg

    override fun initView(savedInstanceState: Bundle?, view: View) {
        backToolbar()
        setToolbarTitle(getString(R.string.Driver_registration))

        driverReg_temp_F.setOnClickListener(this)
        d_Qualification_certificate_F.setOnClickListener(this)
        d_c_Driver_license_F.setOnClickListener(this)
        d_Driver_safe_book.setOnClickListener(this)

        driverReg_idcart_z.setOnClickListener(this)
        driverReg_idcart_f.setOnClickListener(this)
        driverReg_temp.setOnClickListener(this)
        d_Qualification_certificate.setOnClickListener(this)
        d_Driver_license.setOnClickListener(this)
        d_c_Driver_license.setOnClickListener(this)

        val num = 1000 + App.compid
        uid = "ND-" + num + "-" + driverReg_from!!.text.toString()
    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                val num = 1000 + App.compid
                val uid = "ND-" + num + "-" + driverReg_from!!.text.toString()
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
                ShowImageDialog.getInstance().ImageWithoutCacheRound(driverReg_idcart_z_img, data)
            } else if (requestCode == 102 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(driverReg_idcart_f_img, data)
            } else if (requestCode == 103 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(driverReg_temp_img, data)
            } else if (requestCode == 104 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(d_Qualification_certificate_img, data)
            } else if (requestCode == 105 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(d_Driver_license_img, data)
            } else if (requestCode == 106 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(d_reg_c_Driver_license_img, data)
            } else if (requestCode == 107 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(driverReg_temp_F_img, data)
            } else if (requestCode == 108 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(d_Qualification_certificate_F_img, data)
            } else if (requestCode == 109 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(d_c_Driver_license_F_img, data)
            } else if (requestCode == 110 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(d_Driver_safe_book_img, data)
            } else if (requestCode == 101 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(driverReg_idcart_z_img, data)
            } else if (requestCode == 102 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(driverReg_idcart_f_img, data)
            } else if (requestCode == 103 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(driverReg_temp_img, data)
            } else if (requestCode == 104 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(d_Qualification_certificate_img, data)
            } else if (requestCode == 105 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(d_Driver_license_img, data)
            } else if (requestCode == 106 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(d_reg_c_Driver_license_img, data)
            } else if (requestCode == 107 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(driverReg_temp_F_img, data)
            } else if (requestCode == 108 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(d_Qualification_certificate_F_img, data)
            } else if (requestCode == 109 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(d_c_Driver_license_F_img, data)
            } else if (requestCode == 110 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithoutCacheRound(d_Driver_safe_book_img, data)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onClick(view: View) {
        val type = "1"
        val angle = -90
        val i = view.id
        if (i == R.id.driverReg_idcart_z) {
            if (driverReg_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 101
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.driverid1_url, type, uid, angle, width(), height(), tag)
            }

        } else if (i == R.id.driverReg_idcart_f) {
            if (driverReg_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 102
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.driverid2_url, type, uid, angle, width(), height(), tag)
            }

        } else if (i == R.id.driverReg_temp) {
            if (driverReg_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 103
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.drivertmpid_url, type, uid, angle, width(), height(), tag)
            }

        } else if (i == R.id.d_Qualification_certificate) {
            if (driverReg_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 104
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.drivercert_url, type, uid, angle, width(), height(), tag)
            }

        } else if (i == R.id.d_Driver_license) {
            if (driverReg_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 105
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.driverlic1_url, type, uid, angle, width(), height(), tag)
            }

        } else if (i == R.id.d_c_Driver_license) {
            if (driverReg_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 106
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.driverlic3_url, type, uid, angle, width(), height(), tag)
            }

        } else if (i == R.id.driverReg_temp_F) {
            if (driverReg_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 107
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.drivertmpidb_url, type, uid, angle, width(), height(), tag)
            }

        } else if (i == R.id.d_Qualification_certificate_F) {
            if (driverReg_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 108
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.drivercertb_url, type, uid, angle, width(), height(), tag)
            }

        } else if (i == R.id.d_c_Driver_license_F) {
            if (driverReg_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 109
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.driverlic4_url, type, uid, angle, width(), height(), tag)
            }

        } else if (i == R.id.d_Driver_safe_book) {
            if (driverReg_from!!.text.toString().isEmpty()) {
                ToastUtil.showShortToast(_mActivity, "没有填写表单号")
            } else {
                tag = 110
                imgUrl = ShowImageDialog.getInstance().showDialog(_mActivity, UrlConfig.driversafe_url, type, uid, angle, width(), height(), tag)
            }

        }
    }

    override fun addViewId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
