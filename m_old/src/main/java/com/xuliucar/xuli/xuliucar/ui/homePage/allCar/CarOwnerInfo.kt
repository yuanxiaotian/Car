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
import com.xuliucar.xuli.xuliucar.bean.CarOwnerInfoBean
import com.xuliucar.xuli.xuliucar.config.UrlConfig
import com.xuliucar.xuli.xuliucar.permission.AfterPermissionGranted
import com.xuliucar.xuli.xuliucar.permission.PermissionUtils
import com.xuliucar.xuli.xuliucar.widget.MatrixImageView
import com.xuliucar.xuli.xuliucar.dataHandlers.ShowImageDialog
import com.xuliucar.xuli.xuliucar.mvp.presenter.CarOwnerInfoPresenter
import com.xuliucar.xuli.xuliucar.mvp.view.CarDetailView
import com.xuliucar.xuli.xuliucar.myCamera.RectCameraActivity
import com.xuliucar.xuli.xuliucar.myCamera2.CameraActivity
import com.xuliucar.xuli.xuliucar.ui.cutPic
import com.xuliucar.xuli.xuliucar.utils.SavePic
import com.xuliucar.xuli.xuliucar.utils.ToastUtil

class CarOwnerInfo : BaseFragment(), View.OnClickListener, CarDetailView<CarOwnerInfoBean.DataBean> {
    private var car_owner_name: TextView? = null
    private var carOwner_sex: TextView? = null
    private var carOwner_id_card: TextView? = null
    private var carOwner_phone_one: TextView? = null
    private var carOwner_phone_two: TextView? = null
    private var carOwner_card_address: TextView? = null
    private var carOwner_card_nowAdd: TextView? = null
    private var cz_idcart_z_img: ImageView? = null
    private var cz_idcart_f_img: ImageView? = null
    private var cz_Temporary_residence_permit_img: ImageView? = null
    private var Temp_residence_permit_opposite_img: ImageView? = null

    private var dialog: Dialog? = null
    private var imgUrl: String? = null
    private var uid: String? = null
    private var width: Int = 0
    private var height: Int = 0
    private var tag: Int = 0
    private var savePic: SavePic? = null
    private var oinfoBean: CarOwnerInfoBean.DataBean.OinfoBean? = null
    private var mCid: String? = null
    private var mPresenter: CarOwnerInfoPresenter? = null
    private var cz_idcart_z: RelativeLayout? = null
    private var cz_idcart_f: RelativeLayout? = null
    private var cz_temporary_residence_permit: RelativeLayout? = null
    private var temp_residence_permit_opposite: RelativeLayout? = null

    override fun companyId(): String = create("userInfo").get("compid", "") as String

    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter = CarOwnerInfoPresenter(this)
        savePic = SavePic()
        mCid = arguments!!.getString("str")
        mPresenter!!.getData(mCid)
        initView()

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.unSubcrible()
    }


    override fun setContentView() {
        setContentView(R.layout.car_owner_info)
    }

    override fun initView() {
        car_owner_name = getViewById(R.id.car_owner_name)
        carOwner_sex = getViewById(R.id.carOwner_sex)
        carOwner_id_card = getViewById(R.id.carOwner_id_card)
        carOwner_phone_one = getViewById(R.id.carOwner_phone_one)
        carOwner_phone_two = getViewById(R.id.carOwner_phone_two)
        carOwner_card_address = getViewById(R.id.carOwner_card_address)
        carOwner_card_nowAdd = getViewById(R.id.carOwner_card_nowAdd)
        cz_idcart_z = getViewById(R.id.cz_idcart_z)
        cz_idcart_f = getViewById(R.id.cz_idcart_f)
        cz_temporary_residence_permit = getViewById(R.id.cz_Temporary_residence_permit)
        temp_residence_permit_opposite = getViewById(R.id.Temp_residence_permit_opposite)
        cz_idcart_z_img = getViewById(R.id.cz_idcart_z_img)
        cz_idcart_f_img = getViewById(R.id.cz_idcart_f_img)
        cz_Temporary_residence_permit_img = getViewById(R.id.cz_Temporary_residence_permit_img)
        Temp_residence_permit_opposite_img = getViewById(R.id.Temp_residence_permit_opposite_img)


        //定义DisplayMetrics 对象;
        val dm = DisplayMetrics()
        //取得窗口属性
        activity!!.windowManager.defaultDisplay.getMetrics(dm)
        //窗口的宽度
        val screenWidth = dm.widthPixels
        //窗口高度
        val screenHeight = dm.heightPixels
        width = (screenWidth * 0.8).toInt()
        height = (screenHeight * 0.78).toInt()
    }

    override fun setListener() {
        cz_idcart_z!!.setOnClickListener(this)
        cz_idcart_f!!.setOnClickListener(this)
        cz_temporary_residence_permit!!.setOnClickListener(this)
        temp_residence_permit_opposite!!.setOnClickListener(this)
    }

    override fun getData(bean: CarOwnerInfoBean.DataBean) {
        uid = bean.uid
        oinfoBean = bean.oinfo
        car_owner_name!!.text = oinfoBean!!.name
        carOwner_sex!!.text = oinfoBean!!.sex
        carOwner_id_card!!.text = oinfoBean!!.oidnum
        carOwner_phone_one!!.text = oinfoBean!!.ophonenum
        carOwner_phone_two!!.text = oinfoBean!!.ophone2num
        carOwner_card_address!!.text = oinfoBean!!.oidaddr
        carOwner_card_nowAdd!!.text = oinfoBean!!.oidnowaddr
        if (!oinfoBean!!.p1_thumb.isEmpty()) {
            App.imageLoader.displayImage(oinfoBean!!.p1_thumb, cz_idcart_z_img!!, App.RoundeOptions)
        }
        if (!oinfoBean!!.p2_thumb.isEmpty()) {
            App.imageLoader.displayImage(oinfoBean!!.p2_thumb, cz_idcart_f_img!!, App.RoundeOptions)
        }
        if (!oinfoBean!!.p3_thumb.isEmpty()) {
            App.imageLoader.displayImage(oinfoBean!!.p3_thumb, cz_Temporary_residence_permit_img!!, App.RoundeOptions)
        }
        if (!oinfoBean!!.p4_thumb.isEmpty()) {
            App.imageLoader.displayImage(oinfoBean!!.p4_thumb, Temp_residence_permit_opposite_img!!, App.RoundeOptions)
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
        Snackbar.make(Temp_residence_permit_opposite_img!!, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试") { mPresenter!!.getData(mCid) }.show()
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
                ShowImageDialog.getInstance().ImageWithRound(cz_idcart_z_img, data, dialog)
            } else if (requestCode == 102 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(cz_idcart_f_img, data, dialog)
            } else if (requestCode == 103 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(cz_Temporary_residence_permit_img, data, dialog)
            } else if (requestCode == 104 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(Temp_residence_permit_opposite_img, data, dialog)
            } else if (requestCode == 101 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(cz_idcart_z_img, data, dialog)
            } else if (requestCode == 102 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(cz_idcart_f_img, data, dialog)
            } else if (requestCode == 103 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(cz_Temporary_residence_permit_img, data, dialog)
            } else if (requestCode == 104 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(Temp_residence_permit_opposite_img, data, dialog)
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
        if (i == R.id.cz_idcart_z) {
            showOriginalPic(oinfoBean!!.p1, UrlConfig.ownerid1_url)
            tag = 101

        } else if (i == R.id.cz_idcart_f) {
            showOriginalPic(oinfoBean!!.p2, UrlConfig.ownerid2_url)
            tag = 102

        } else if (i == R.id.cz_Temporary_residence_permit) {
            showOriginalPic(oinfoBean!!.p3, UrlConfig.ownertmpid_url)
            tag = 103

        } else if (i == R.id.Temp_residence_permit_opposite) {
            showOriginalPic(oinfoBean!!.p4, UrlConfig.ownertmpidbb_url)
            tag = 104

        }
    }


    companion object {

        private val GALLERY_REQUEST_CODE = 2//相册
        private val REQUEST_CAMERA_PERMISSION = 0x01

        fun newInstance(str: String): CarOwnerInfo {
            val args = Bundle()
            args.putString("str", str)
            val carOwnerInfo = CarOwnerInfo()
            carOwnerInfo.arguments = args
            return carOwnerInfo
        }
    }
}
