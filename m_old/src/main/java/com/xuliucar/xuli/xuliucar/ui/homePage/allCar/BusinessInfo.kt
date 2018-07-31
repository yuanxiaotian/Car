package com.xuliucar.xuli.xuliucar.ui.homePage.allCar


import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.cangmaomao.lib.utils.SPUtils.create

import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.base.BaseFragment
import com.xuliucar.xuli.xuliucar.bean.BusinessInfoBean
import com.xuliucar.xuli.xuliucar.config.UrlConfig
import com.xuliucar.xuli.xuliucar.permission.AfterPermissionGranted
import com.xuliucar.xuli.xuliucar.permission.PermissionUtils
import com.xuliucar.xuli.xuliucar.widget.MatrixImageView
import com.xuliucar.xuli.xuliucar.dataHandlers.ShowImageDialog
import com.xuliucar.xuli.xuliucar.mvp.presenter.BusinessInfoPresenter
import com.xuliucar.xuli.xuliucar.mvp.view.CarDetailView
import com.xuliucar.xuli.xuliucar.myCamera.RectCameraActivity
import com.xuliucar.xuli.xuliucar.myCamera2.CameraActivity
import com.xuliucar.xuli.xuliucar.ui.cutPic
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils
import com.xuliucar.xuli.xuliucar.utils.SavePic
import com.xuliucar.xuli.xuliucar.utils.ToastUtil

/**
 * A simple [Fragment] subclass.
 */
class BusinessInfo : BaseFragment(), View.OnClickListener, CarDetailView<BusinessInfoBean.DataBean> {

    private var Business_name: TextView? = null
    private var car_plate_num: TextView? = null
    private var car_type: TextView? = null
    private var Brand_model: TextView? = null
    private var tonnage: TextView? = null
    private var tpdates: TextView? = null
    private var yy_Operation_z_img: ImageView? = null
    private var yy_Operation_f_img: ImageView? = null
    private var yy_Fire_Extinguisher_img: ImageView? = null

    private var dialog: Dialog? = null
    private var imgUrl: String? = null
    private var uid: String? = null
    private var width: Int = 0
    private var height: Int = 0
    private var tag: Int = 0
    private var savePic: SavePic? = null
    private var mCtinfoBean: BusinessInfoBean.DataBean.CtinfoBean? = null
    private var mCid: String? = null
    private var mPresenter: BusinessInfoPresenter? = null
    private var yy_operation_z: RelativeLayout? = null
    private var yy_operation_f: RelativeLayout? = null
    private var yy_fire_extinguisher: RelativeLayout? = null

    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter = BusinessInfoPresenter(this)
        mCid = arguments!!.getString("str")
        mPresenter!!.getData(mCid)
        initView()
        val carNum = PreferencesUtils.getSharePreStr(activity!!, "userInfo", "carNum")
        car_plate_num!!.text = carNum
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.unSubcrible()
    }

    override fun setContentView() {
        setContentView(R.layout.business_info)
    }

    override fun initView() {
        Business_name = getViewById(R.id.Business_name)
        car_plate_num = getViewById(R.id.car_plate_num)
        car_type = getViewById(R.id.car_type)
        Brand_model = getViewById(R.id.Brand_model)
        tonnage = getViewById(R.id.tonnage)
        tpdates = getViewById(R.id.tpdate)
        yy_operation_z = getViewById(R.id.yy_Operation_z)
        yy_operation_f = getViewById(R.id.yy_Operation_f)
        yy_fire_extinguisher = getViewById(R.id.yy_Fire_Extinguisher)
        yy_Operation_z_img = getViewById(R.id.yy_Operation_z_img)
        yy_Operation_f_img = getViewById(R.id.yy_Operation_f_img)
        yy_Fire_Extinguisher_img = getViewById(R.id.yy_Fire_Extinguisher_img)


        //定义DisplayMetrics 对象;
        val dm = DisplayMetrics()
        //取得窗口属性
        activity!!.windowManager.defaultDisplay.getMetrics(dm)
        //窗口的宽度
        val screenWidth = dm.widthPixels
        //窗口高度
        val screenHeight = dm.heightPixels
        width = (screenWidth * 0.8).toInt()
        height = (screenHeight * 0.8).toInt()
        savePic = SavePic()
    }

    override fun setListener() {
        yy_operation_z!!.setOnClickListener(this)
        yy_operation_f!!.setOnClickListener(this)
        yy_fire_extinguisher!!.setOnClickListener(this)
    }

    override fun getData(bean: BusinessInfoBean.DataBean) {
        uid = bean.uid
        mCtinfoBean = bean.ctinfo
        Business_name!!.text = mCtinfoBean!!.compname
        car_type!!.text = mCtinfoBean!!.cartype
        Brand_model!!.text = mCtinfoBean!!.modelname
        tonnage!!.text = mCtinfoBean!!.weight
        tpdates!!.text = mCtinfoBean!!.tpdate
        if (!mCtinfoBean!!.p1_thumb.isEmpty()) {
            App.imageLoader.displayImage(mCtinfoBean!!.p1_thumb, yy_Operation_z_img!!, App.RoundeOptions)
        }
        if (!mCtinfoBean!!.p2_thumb.isEmpty()) {
            App.imageLoader.displayImage(mCtinfoBean!!.p2_thumb, yy_Operation_f_img!!, App.RoundeOptions)
        }
        if (!mCtinfoBean!!.p3_thumb.isEmpty()) {
            App.imageLoader.displayImage(mCtinfoBean!!.p3_thumb, yy_Fire_Extinguisher_img!!, App.RoundeOptions)
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
        Snackbar.make(yy_Fire_Extinguisher_img!!, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试") { mPresenter!!.getData(mCid) }.show()
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
                ShowImageDialog.getInstance().ImageWithRound(yy_Operation_z_img, data, dialog)
            } else if (requestCode == 102 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(yy_Operation_f_img, data, dialog)
            } else if (requestCode == 103 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(yy_Fire_Extinguisher_img, data, dialog)
            } else if (requestCode == 101 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(yy_Operation_z_img, data, dialog)
            } else if (requestCode == 102 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(yy_Operation_f_img, data, dialog)
            } else if (requestCode == 103 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(yy_Fire_Extinguisher_img, data, dialog)
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
        if (i == R.id.yy_Operation_z) {
            showOriginalPic(mCtinfoBean!!.p1, UrlConfig.certf_url)
            tag = 101

        } else if (i == R.id.yy_Operation_f) {
            showOriginalPic(mCtinfoBean!!.p2, UrlConfig.certb_url)
            tag = 102

        } else if (i == R.id.yy_Fire_Extinguisher) {
            showOriginalPic(mCtinfoBean!!.p3, UrlConfig.pic45d_url)
            tag = 103

        }
    }



    companion object {
        private val GALLERY_REQUEST_CODE = 2//相册;
        private val REQUEST_CAMERA_PERMISSION = 0x01

        fun newInstance(str: String): BusinessInfo {
            val args = Bundle()
            args.putString("str", str)
            val businessInfo = BusinessInfo()
            businessInfo.arguments = args
            return businessInfo
        }
    }
}
