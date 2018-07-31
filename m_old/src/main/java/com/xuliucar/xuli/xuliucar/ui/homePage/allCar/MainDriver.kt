package com.xuliucar.xuli.xuliucar.ui.homePage.allCar


import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.cangmaomao.lib.utils.SPUtils.create

import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.base.BaseFragment
import com.xuliucar.xuli.xuliucar.bean.MainDirverBean
import com.xuliucar.xuli.xuliucar.config.UrlConfig
import com.xuliucar.xuli.xuliucar.permission.AfterPermissionGranted
import com.xuliucar.xuli.xuliucar.permission.PermissionUtils
import com.xuliucar.xuli.xuliucar.widget.MatrixImageView
import com.xuliucar.xuli.xuliucar.dataHandlers.ShowImageDialog
import com.xuliucar.xuli.xuliucar.mvp.presenter.MainDriverPresenter
import com.xuliucar.xuli.xuliucar.mvp.view.CarDetailView
import com.xuliucar.xuli.xuliucar.myCamera.RectCameraActivity
import com.xuliucar.xuli.xuliucar.myCamera2.CameraActivity
import com.xuliucar.xuli.xuliucar.ui.cutPic
import com.xuliucar.xuli.xuliucar.utils.SavePic
import com.xuliucar.xuli.xuliucar.utils.ToastUtil

class MainDriver : BaseFragment(), View.OnClickListener, CarDetailView<MainDirverBean.DataBean> {

    private var driver_name: TextView? = null
    private var driver_sex: TextView? = null
    private var driver_id_card: TextView? = null
    private var driver_phone_one: TextView? = null
    private var driver_phone_two: TextView? = null
    private var driver_license: TextView? = null
    private var Quasi_driving_type: TextView? = null
    private var driver_license_begin: TextView? = null
    private var driver_license_end: TextView? = null
    private var Certificate_num: TextView? = null
    private var type_certificate: TextView? = null
    private var certificate_end: TextView? = null
    private var certificate_ia: TextView? = null
    private var id_car_address: TextView? = null
    private var now_address: TextView? = null
    private var driver_idcart_z_img: ImageView? = null
    private var driver_idcart_f_img: ImageView? = null
    private var driver_temp_hotel_img: ImageView? = null
    private var driver_temp_hotel_F_img: ImageView? = null
    private var driver_Qualification_certificate_img: ImageView? = null
    private var driver_Qualification_certificate_F_img: ImageView? = null
    private var driver_Driver_license_img: ImageView? = null
    private var driver_c_Driver_license_img: ImageView? = null
    private var driver_c_Driver_license_F_img: ImageView? = null
    private var md_Driver_safety_undertaking_img: ImageView? = null

    private var dialog: Dialog? = null
    private var imgUrl: String? = null
    private var uid: String? = null
    private var width: Int = 0
    private var height: Int = 0
    private var screenHeight: Int = 0
    private var tag: Int = 0
    private var savePic: SavePic? = null
    private var dinfoBean: MainDirverBean.DataBean.DinfoBean? = null
    private var mPresenter: MainDriverPresenter? = null
    private var mCid: String? = null
    private var driver_idcart_z: RelativeLayout? = null
    private var driver_idcart_f: RelativeLayout? = null
    private var driver_temp_hotel: RelativeLayout? = null
    private var driver_temp_hotel_f: RelativeLayout? = null
    private var driver_qualification_certificate: RelativeLayout? = null
    private var driver_qualification_certificate_f: RelativeLayout? = null
    private var driver_driver_license: RelativeLayout? = null
    private var driver_c_driver_license_f: RelativeLayout? = null
    private var driver_c_driver_license: RelativeLayout? = null
    private var md_driver_safety_undertaking: RelativeLayout? = null


    override fun setContentView() {
        setContentView(R.layout.driver)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter = MainDriverPresenter(this)
        mCid = arguments!!.getString("str")
        mPresenter!!.getData(mCid)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.unSubcrible()
    }

    override fun initView() {
        driver_name = getViewById(R.id.driver_name)
        driver_sex = getViewById(R.id.driver_sex)
        driver_id_card = getViewById(R.id.driver_id_card)
        driver_phone_one = getViewById(R.id.driver_phone_one)
        driver_phone_two = getViewById(R.id.driver_phone_two)
        driver_license = getViewById(R.id.driver_license)
        Quasi_driving_type = getViewById(R.id.Quasi_driving_type)
        driver_license_begin = getViewById(R.id.driver_license_begin)
        driver_license_end = getViewById(R.id.driver_license_end)
        Certificate_num = getViewById(R.id.Certificate_num)
        type_certificate = getViewById(R.id.type_certificate)
        certificate_end = getViewById(R.id.certificate_end)
        certificate_ia = getViewById(R.id.certificate_ia)
        id_car_address = getViewById(R.id.id_car_address)
        now_address = getViewById(R.id.now_address)

        driver_idcart_z = getViewById(R.id.driver_idcart_z)
        driver_idcart_f = getViewById(R.id.driver_idcart_f)
        driver_temp_hotel = getViewById(R.id.driver_temp_hotel)
        driver_temp_hotel_f = getViewById(R.id.driver_temp_hotel_F)
        driver_qualification_certificate = getViewById(R.id.driver_Qualification_certificate)
        driver_qualification_certificate_f = getViewById(R.id.driver_Qualification_certificate_F)
        driver_driver_license = getViewById(R.id.driver_Driver_license)
        driver_c_driver_license_f = getViewById(R.id.driver_c_Driver_license_F)
        driver_c_driver_license = getViewById(R.id.driver_c_Driver_license)
        md_driver_safety_undertaking = getViewById(R.id.md_Driver_safety_undertaking)

        driver_idcart_z_img = getViewById(R.id.driver_idcart_z_img)
        driver_idcart_f_img = getViewById(R.id.driver_idcart_f_img)
        driver_temp_hotel_img = getViewById(R.id.driver_temp_hotel_img)
        driver_temp_hotel_F_img = getViewById(R.id.driver_temp_hotel_F_img)
        driver_Qualification_certificate_img = getViewById(R.id.driver_Qualification_certificate_img)
        driver_Qualification_certificate_F_img = getViewById(R.id.driver_Qualification_certificate_F_img)
        driver_Driver_license_img = getViewById(R.id.driver_Driver_license_img)
        driver_c_Driver_license_img = getViewById(R.id.driver_c_Driver_license_img)
        driver_c_Driver_license_F_img = getViewById(R.id.driver_c_Driver_license_F_img)
        md_Driver_safety_undertaking_img = getViewById(R.id.md_Driver_safety_undertaking_img)


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
    }

    override fun setListener() {
        driver_idcart_z!!.setOnClickListener(this)
        driver_idcart_f!!.setOnClickListener(this)
        driver_temp_hotel!!.setOnClickListener(this)
        driver_temp_hotel_f!!.setOnClickListener(this)
        driver_qualification_certificate!!.setOnClickListener(this)
        driver_qualification_certificate_f!!.setOnClickListener(this)
        driver_driver_license!!.setOnClickListener(this)
        driver_c_driver_license!!.setOnClickListener(this)
        driver_c_driver_license_f!!.setOnClickListener(this)
        md_driver_safety_undertaking!!.setOnClickListener(this)
    }

    override fun getData(bean: MainDirverBean.DataBean) {
        uid = bean.uid
        dinfoBean = bean.dinfo
        driver_name!!.text = dinfoBean!!.name
        driver_sex!!.text = dinfoBean!!.sex
        driver_id_card!!.text = dinfoBean!!.didnum
        driver_phone_one!!.text = dinfoBean!!.dphonenum
        driver_phone_two!!.text = dinfoBean!!.dphone2num
        driver_license!!.text = dinfoBean!!.dlicnum
        Quasi_driving_type!!.text = dinfoBean!!.dcartype
        driver_license_begin!!.text = dinfoBean!!.dlicsdate
        driver_license_end!!.text = dinfoBean!!.dlicedate
        Certificate_num!!.text = dinfoBean!!.dctnum
        type_certificate!!.text = dinfoBean!!.dcttype
        certificate_end!!.text = dinfoBean!!.dctetime
        certificate_ia!!.text = dinfoBean!!.dctstr
        id_car_address!!.text = dinfoBean!!.didaddr
        now_address!!.text = dinfoBean!!.didnowaddr

        if (!dinfoBean!!.p1_thumb.isEmpty()) {
            App.imageLoader.displayImage(dinfoBean!!.p1_thumb, driver_idcart_z_img!!, App.RoundeOptions)
        }
        if (!dinfoBean!!.p2_thumb.isEmpty()) {
            App.imageLoader.displayImage(dinfoBean!!.p2_thumb, driver_idcart_f_img!!, App.RoundeOptions)
        }
        if (!dinfoBean!!.p3_thumb.isEmpty()) {
            App.imageLoader.displayImage(dinfoBean!!.p3_thumb, driver_temp_hotel_img!!, App.RoundeOptions)
        }
        if (!dinfoBean!!.p4_thumb.isEmpty()) {
            App.imageLoader.displayImage(dinfoBean!!.p4_thumb, driver_Qualification_certificate_img!!, App.RoundeOptions)
        }
        if (!dinfoBean!!.p5_thumb.isEmpty()) {
            App.imageLoader.displayImage(dinfoBean!!.p5_thumb, driver_Driver_license_img!!, App.RoundeOptions)
        }
        if (!dinfoBean!!.p6_thumb.isEmpty()) {
            App.imageLoader.displayImage(dinfoBean!!.p6_thumb, driver_c_Driver_license_img!!, App.RoundeOptions)
        }
        if (!dinfoBean!!.p7_thumb.isEmpty()) {
            App.imageLoader.displayImage(dinfoBean!!.p7_thumb, driver_temp_hotel_F_img!!, App.RoundeOptions)
        }
        if (!dinfoBean!!.p8_thumb.isEmpty()) {
            App.imageLoader.displayImage(dinfoBean!!.p1_thumb, driver_idcart_z_img!!, App.RoundeOptions)
        }
        if (!dinfoBean!!.p9_thumb.isEmpty()) {
            App.imageLoader.displayImage(dinfoBean!!.p9_thumb, driver_c_Driver_license_F_img!!, App.RoundeOptions)
        }
        if (!dinfoBean!!.p10_thumb.isEmpty()) {
            App.imageLoader.displayImage(dinfoBean!!.p10_thumb, md_Driver_safety_undertaking_img!!, App.RoundeOptions)
        }
    }

    override fun toastMsg(msg: String) {
        when (msg) {
            "already logout" -> {
                ToastUtil.showShortToast(activity, msg)
                mPresenter!!.toLogin(activity)
            }
            "未登陆" -> {
                ToastUtil.showShortToast(activity, msg)
                mPresenter!!.toLogin(activity)
            }
            "已登出,或在其它设备上登陆!" -> {
                ToastUtil.showShortToast(activity, msg)
                mPresenter!!.alreadyLogin(activity)
            }
        }
    }

    override fun showError() {
        Snackbar.make(md_Driver_safety_undertaking_img!!, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试") { mPresenter!!.getData(mCid) }.show()
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
        val preImage = layout.findViewById<View>(R.id.preImage) as ImageView
        val img_detail = layout.findViewById<View>(R.id.img_detail) as MatrixImageView
        val takePhotoBtn = layout.findViewById<View>(R.id.takePhotoBtn) as TextView
        val pickPhotoBtn = layout.findViewById<View>(R.id.pickPhotoBtn) as TextView
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
            showOriginalPic(dinfoBean!!.p1, UrlConfig.driverid1_url)
            height = (screenHeight * 0.78).toInt()
            tag = 101

        } else if (i == R.id.driver_idcart_f) {
            showOriginalPic(dinfoBean!!.p2, UrlConfig.driverid2_url)
            height = (screenHeight * 0.78).toInt()
            tag = 102

        } else if (i == R.id.driver_temp_hotel) {
            showOriginalPic(dinfoBean!!.p3, UrlConfig.drivertmpid_url)
            height = (screenHeight * 0.78).toInt()
            tag = 103

        } else if (i == R.id.driver_Qualification_certificate) {
            showOriginalPic(dinfoBean!!.p4, UrlConfig.drivercert_url)
            height = (screenHeight * 0.78).toInt()
            tag = 104

        } else if (i == R.id.driver_Driver_license) {
            showOriginalPic(dinfoBean!!.p5, UrlConfig.driverlic1_url)
            height = (screenHeight * 0.73).toInt()
            tag = 105

        } else if (i == R.id.driver_c_Driver_license) {
            showOriginalPic(dinfoBean!!.p6, UrlConfig.driverlic3_url)
            height = (screenHeight * 0.73).toInt()
            tag = 106

        } else if (i == R.id.driver_temp_hotel_F) {
            showOriginalPic(dinfoBean!!.p7, UrlConfig.drivertmpidb_url)
            height = (screenHeight * 0.78).toInt()
            tag = 107

        } else if (i == R.id.driver_Qualification_certificate_F) {
            showOriginalPic(dinfoBean!!.p8, UrlConfig.drivercertb_url)
            height = (screenHeight * 0.78).toInt()
            tag = 108

        } else if (i == R.id.driver_c_Driver_license_F) {
            showOriginalPic(dinfoBean!!.p9, UrlConfig.driverlic4_url)
            height = (screenHeight * 0.73).toInt()
            tag = 109

        } else if (i == R.id.md_Driver_safety_undertaking) {
            showOriginalPic(dinfoBean!!.p10, UrlConfig.driversafe_url)
            height = (screenHeight * 0.78).toInt()
            tag = 110

        }
    }

    override fun companyId(): String = create("userInfo").get("compid", "") as String

    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    companion object {
        private val GALLERY_REQUEST_CODE = 2//相册
        private val REQUEST_CAMERA_PERMISSION = 0x01
        fun newInstance(str: String): MainDriver {
            val args = Bundle()
            args.putString("str", str)
            val mainDriver = MainDriver()
            mainDriver.arguments = args
            return mainDriver
        }
    }
}
