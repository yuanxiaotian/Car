package com.xuliucar.xuli.xuliucar.ui.homePage.allCar


import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.bean.AssistantDriverBean
import com.xuliucar.xuli.xuliucar.config.UrlConfig
import com.xuliucar.xuli.xuliucar.dataHandlers.ShowImageDialog
import com.xuliucar.xuli.xuliucar.mvp.presenter.AssisstantDriverPresenter
import com.xuliucar.xuli.xuliucar.mvp.view.CarDetailView
import com.xuliucar.xuli.xuliucar.myCamera.RectCameraActivity
import com.xuliucar.xuli.xuliucar.myCamera2.CameraActivity
import com.xuliucar.xuli.xuliucar.permission.PermissionUtils
import com.xuliucar.xuli.xuliucar.ui.cutPic
import com.xuliucar.xuli.xuliucar.utils.SavePic
import com.xuliucar.xuli.xuliucar.utils.ToastUtil
import kotlinx.android.synthetic.main.driver.*
import kotlinx.android.synthetic.main.driver_header.*

class AssistantDriver : BaseNewFragment<BasePresenter>(), View.OnClickListener, CarDetailView<AssistantDriverBean.DataBean> {


    private var dialog: Dialog? = null
    private var imgUrl: String? = null
    private var uid: String? = null
    private var width: Int = 0
    private var height: Int = 0
    private var screenHeight: Int = 0
    private var tag: Int = 0
    private var savePic: SavePic? = null
    private var d2infoBean: AssistantDriverBean.DataBean.D2infoBean? = null
    private lateinit var mPresenter: AssisstantDriverPresenter
    private var mCid: String? = null

    override fun layViewId(): Int = R.layout.driver
    override fun addViewId(): Int = 0

    override fun initView(savedInstanceState: Bundle?, view: View) {
        //定义DisplayMetrics 对象;
        val dm = DisplayMetrics()
        //取得窗口属性
        activity!!.windowManager.defaultDisplay.getMetrics(dm)
        //窗口的宽度
        val screenWidth = dm.widthPixels
        //窗口高度
        screenHeight = dm.heightPixels
        width = (screenWidth * 0.8).toInt()
        savePic = SavePic()

        driver_idcart_z.setOnClickListener(this)
        driver_idcart_f.setOnClickListener(this)
        driver_temp_hotel.setOnClickListener(this)
        driver_temp_hotel_F.setOnClickListener(this)
        driver_Qualification_certificate.setOnClickListener(this)
        driver_Qualification_certificate_F.setOnClickListener(this)
        driver_Driver_license.setOnClickListener(this)
        driver_c_Driver_license.setOnClickListener(this)
        driver_c_Driver_license_F.setOnClickListener(this)
        md_Driver_safety_undertaking.setOnClickListener(this)

        mPresenter = AssisstantDriverPresenter(this)
        mCid = arguments!!.getString("str")
        mPresenter.getData(mCid)
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unSubcrible()
    }


    override fun getData(bean: AssistantDriverBean.DataBean) {
        uid = bean.uid
        d2infoBean = bean.d2info
        driver_name.text = d2infoBean!!.name
        driver_sex.text = d2infoBean!!.sex
        driver_id_card.text = d2infoBean!!.didnum
        driver_phone_one.text = d2infoBean!!.dphonenum
        driver_phone_two.text = d2infoBean!!.dphone2num
        driver_license.text = d2infoBean!!.dlicnum
        Quasi_driving_type.text = d2infoBean!!.dcartype
        driver_license_begin.text = d2infoBean!!.dlicsdate
        driver_license_end.text = d2infoBean!!.dlicedate
        Certificate_num.text = d2infoBean!!.dctnum
        type_certificate.text = d2infoBean!!.dcttype
        certificate_end.text = d2infoBean!!.dctetime
        certificate_ia.text = d2infoBean!!.dctstr
        id_car_address.text = d2infoBean!!.didaddr
        now_address.text = d2infoBean!!.didnowaddr

        if (!d2infoBean!!.p1_thumb.isEmpty()) {
            App.imageLoader.displayImage(d2infoBean!!.p1_thumb, driver_idcart_z_img, App.RoundeOptions)
        }
        if (!d2infoBean!!.p2_thumb.isEmpty()) {
            App.imageLoader.displayImage(d2infoBean!!.p2_thumb, driver_idcart_f_img, App.RoundeOptions)
        }
        if (!d2infoBean!!.p3_thumb.isEmpty()) {
            App.imageLoader.displayImage(d2infoBean!!.p3_thumb, driver_temp_hotel_img, App.RoundeOptions)
        }
        if (!d2infoBean!!.p4_thumb.isEmpty()) {
            App.imageLoader.displayImage(d2infoBean!!.p4_thumb, driver_Qualification_certificate_img, App.RoundeOptions)
        }
        if (!d2infoBean!!.p5_thumb.isEmpty()) {
            App.imageLoader.displayImage(d2infoBean!!.p5_thumb, driver_Driver_license_img, App.RoundeOptions)
        }
        if (!d2infoBean!!.p6_thumb.isEmpty()) {
            App.imageLoader.displayImage(d2infoBean!!.p6_thumb, driver_c_Driver_license_img, App.RoundeOptions)
        }
        if (!d2infoBean!!.p7_thumb.isEmpty()) {
            App.imageLoader.displayImage(d2infoBean!!.p7_thumb, driver_temp_hotel_F_img, App.RoundeOptions)
        }
        if (!d2infoBean!!.p8_thumb.isEmpty()) {
            App.imageLoader.displayImage(d2infoBean!!.p8_thumb, driver_Qualification_certificate_F_img, App.RoundeOptions)
        }
        if (!d2infoBean!!.p9_thumb.isEmpty()) {
            App.imageLoader.displayImage(d2infoBean!!.p9_thumb, driver_c_Driver_license_F_img, App.RoundeOptions)
        }
        if (!d2infoBean!!.p10_thumb.isEmpty()) {
            App.imageLoader.displayImage(d2infoBean!!.p10_thumb, md_Driver_safety_undertaking_img, App.RoundeOptions)
        }
    }

    override fun toastMsg(msg: String) {
        when (msg) {
            "already logout" -> {
                ToastUtil.showShortToast(activity, msg)
                mPresenter.toLogin(activity)
            }
            "未登陆" -> {
                ToastUtil.showShortToast(activity, msg)
                mPresenter.toLogin(activity)
            }
            "已登出,或在其它设备上登陆!" -> {
                ToastUtil.showShortToast(activity, msg)
                mPresenter.alreadyLogin(activity)
            }
        }
    }

    override fun showError() {
        Snackbar.make(md_Driver_safety_undertaking_img!!, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试") { mPresenter.getData(mCid) }.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                val intent = Intent(activity, cutPic::class.java)
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
                ShowImageDialog.getInstance().ImageWithRound(driver_idcart_z_img, data, dialog)
            } else if (requestCode == 102 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(driver_idcart_f_img, data, dialog)
            } else if (requestCode == 103 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(driver_temp_hotel_img, data, dialog)
            } else if (requestCode == 104 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(driver_Qualification_certificate_img, data, dialog)
            } else if (requestCode == 105 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(driver_Driver_license_img, data, dialog)
            } else if (requestCode == 106 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(driver_c_Driver_license_img, data, dialog)
            } else if (requestCode == 107 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(driver_temp_hotel_F_img, data, dialog)
            } else if (requestCode == 108 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(driver_Qualification_certificate_F_img, data, dialog)
            } else if (requestCode == 109 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(driver_c_Driver_license_F_img, data, dialog)
            } else if (requestCode == 110 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(md_Driver_safety_undertaking_img, data, dialog)
            } else if (requestCode == 101 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(driver_idcart_z_img, data, dialog)
            } else if (requestCode == 102 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(driver_idcart_f_img, data, dialog)
            } else if (requestCode == 103 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(driver_temp_hotel_img, data, dialog)
            } else if (requestCode == 104 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(driver_Qualification_certificate_img, data, dialog)
            } else if (requestCode == 105 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(driver_Driver_license_img, data, dialog)
            } else if (requestCode == 106 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(driver_c_Driver_license_img, data, dialog)
            } else if (requestCode == 107 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(driver_temp_hotel_F_img, data, dialog)
            } else if (requestCode == 108 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(driver_Qualification_certificate_F_img, data, dialog)
            } else if (requestCode == 109 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(driver_c_Driver_license_F_img, data, dialog)
            } else if (requestCode == 110 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(md_Driver_safety_undertaking_img, data, dialog)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun showOriginalPic(Picurl: String, uploadUrl: String) {
        dialog = Dialog(activity!!, R.style.dialog)
        val layout = View.inflate(activity, R.layout.alert_img, null)
        dialog!!.setContentView(layout)
        dialog!!.show()
        val preImage = layout.findViewById<ImageView>(R.id.preImage)
        val img_detail = layout.findViewById<ImageView>(R.id.img_detail)
        val takePhotoBtn = layout.findViewById<TextView>(R.id.takePhotoBtn)
        val pickPhotoBtn = layout.findViewById<TextView>(R.id.pickPhotoBtn)
        if (Picurl.isEmpty()) {
            preImage.setImageResource(R.drawable.hang)
            preImage.setOnClickListener { dialog!!.dismiss() }
        } else {
            preImage.visibility = View.GONE
            img_detail.visibility = View.VISIBLE
            App.imageLoader.displayImage(Picurl, img_detail, App.options)
        }
        takePhotoBtn.setOnClickListener {
            if (PermissionUtils.hasPermissions(activity, Manifest.permission.CAMERA)) {
                openCamera(uploadUrl)
            } else {
                PermissionUtils.requestPermissions(activity, getString(R.string.rationale_cameras), REQUEST_CAMERA_PERMISSION, Manifest.permission.CAMERA)
            }
        }
        pickPhotoBtn.setOnClickListener {
            val intent1 = Intent(Intent.ACTION_GET_CONTENT)
            intent1.type = "image/*"
            startActivityForResult(intent1, GALLERY_REQUEST_CODE)
            imgUrl = uploadUrl
        }
        img_detail.setOnClickListener { dialog!!.dismiss() }
        img_detail.setOnLongClickListener {
            savePic!!.getDownload(Picurl)
            ToastUtil.showShortToast(activity, "保存成功!")
            true
        }
    }

    private fun openCamera(uploadUrl: String) {
        if (App.sdkVsesion >= 21 && App.brand == "HONOR") {
            val intent1 = Intent(activity, CameraActivity::class.java)
            intent1.putExtra("width", width)
            intent1.putExtra("height", height)
            intent1.putExtra("angle", -90)
            intent1.putExtra("uid", uid)
            intent1.putExtra("url", uploadUrl)
            intent1.putExtra("type", "1")
            startActivityForResult(intent1, tag)
        } else {
            val intent = Intent(activity, RectCameraActivity::class.java)
            intent.putExtra("width", width)
            intent.putExtra("height", height)
            intent.putExtra("angle", -90)
            intent.putExtra("uid", uid)
            intent.putExtra("url", uploadUrl)
            intent.putExtra("type", "1")
            startActivityForResult(intent, tag)
        }
    }

    override fun onClick(views: View) {
        val i = views.id
        if (i == R.id.driver_idcart_z) {
            showOriginalPic(d2infoBean!!.p1, UrlConfig.driver2id1_url)
            height = (screenHeight * 0.78).toInt()
            tag = 101

        } else if (i == R.id.driver_idcart_f) {
            showOriginalPic(d2infoBean!!.p2, UrlConfig.driver2id2_url)
            height = (screenHeight * 0.78).toInt()
            tag = 102

        } else if (i == R.id.driver_temp_hotel) {
            showOriginalPic(d2infoBean!!.p3, UrlConfig.driver2tmpid_url)
            height = (screenHeight * 0.78).toInt()
            tag = 103

        } else if (i == R.id.driver_Qualification_certificate) {
            showOriginalPic(d2infoBean!!.p4, UrlConfig.driver2cert_url)
            height = (screenHeight * 0.78).toInt()
            tag = 104

        } else if (i == R.id.driver_Driver_license) {
            showOriginalPic(d2infoBean!!.p5, UrlConfig.driver2lic1_url)
            height = (screenHeight * 0.73).toInt()
            tag = 105

        } else if (i == R.id.driver_c_Driver_license) {
            showOriginalPic(d2infoBean!!.p6, UrlConfig.driver2lic3_url)
            height = (screenHeight * 0.73).toInt()
            tag = 106

        } else if (i == R.id.driver_temp_hotel_F) {
            showOriginalPic(d2infoBean!!.p7, UrlConfig.driver2tmpidb_url)
            height = (screenHeight * 0.78).toInt()
            tag = 107

        } else if (i == R.id.driver_Qualification_certificate_F) {
            showOriginalPic(d2infoBean!!.p8, UrlConfig.driver2certb_url)
            height = (screenHeight * 0.78).toInt()
            tag = 108

        } else if (i == R.id.driver_c_Driver_license_F) {
            showOriginalPic(d2infoBean!!.p9, UrlConfig.driver2lic4_url)
            height = (screenHeight * 0.73).toInt()
            tag = 109

        } else if (i == R.id.md_Driver_safety_undertaking) {
            showOriginalPic(d2infoBean!!.p10, UrlConfig.driver2safe_url)
            height = (screenHeight * 0.78).toInt()
            tag = 110

        }
    }

    override fun companyId(): String = create("userInfo").get("compid", "") as String

    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    companion object {
        private val GALLERY_REQUEST_CODE = 2//相册
        private val REQUEST_CAMERA_PERMISSION = 0x01

        fun newInstance(str: String): AssistantDriver {
            val args = Bundle()
            args.putString("str", str)
            val mainDriver = AssistantDriver()
            mainDriver.arguments = args
            return mainDriver
        }
    }
}
