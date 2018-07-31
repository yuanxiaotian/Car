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
import com.cangmaomao.lib.utils.SPUtils
import com.cangmaomao.lib.utils.SPUtils.create

import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.base.BaseFragment
import com.xuliucar.xuli.xuliucar.bean.CarInfoBean
import com.xuliucar.xuli.xuliucar.config.UrlConfig
import com.xuliucar.xuli.xuliucar.dataHandlers.ShowImageDialog
import com.xuliucar.xuli.xuliucar.mvp.presenter.CarInfoPresenter
import com.xuliucar.xuli.xuliucar.mvp.view.CarDetailView
import com.xuliucar.xuli.xuliucar.myCamera.RectCameraActivity
import com.xuliucar.xuli.xuliucar.myCamera2.CameraActivity
import com.xuliucar.xuli.xuliucar.permission.AfterPermissionGranted
import com.xuliucar.xuli.xuliucar.permission.PermissionUtils
import com.xuliucar.xuli.xuliucar.ui.cutPic
import com.xuliucar.xuli.xuliucar.utils.SavePic
import com.xuliucar.xuli.xuliucar.utils.ToastUtil
import com.xuliucar.xuli.xuliucar.widget.MatrixImageView

/**
 * A simple [Fragment] subclass.
 */
class CarInfo : BaseFragment(), View.OnClickListener, CarDetailView<CarInfoBean.DataBean> {


    private var use_property: TextView? = null
    private var Purchase_price: TextView? = null
    private var Frame_number: TextView? = null
    private var Engine_number: TextView? = null
    private var register_data: TextView? = null
    private var Verification_qualification_to: TextView? = null
    private var Nuclear_load: TextView? = null
    private var environmental_protection_begin: TextView? = null
    private var environmental_protection_end: TextView? = null
    private var car_plate_type: TextView? = null
    private var environmental_protection_color: TextView? = null
    private var self_num: TextView? = null
    private var start_commission_manager_date: TextView? = null
    private var end_commission_manager_date: TextView? = null
    private var Outline_length: TextView? = null
    private var Outline_width: TextView? = null
    private var Outline_height: TextView? = null
    private var ID_num: TextView? = null
    private var Enterprise_class: TextView? = null
    private var start_date: TextView? = null
    private var end_date: TextView? = null
    private var cp_p1_thumb_img: ImageView? = null
    private var cp_p1_thumb_img_F: ImageView? = null
    private var cp_p2_thumb_img: ImageView? = null
    private var cp_p2_thumb_img_F: ImageView? = null
    private var cp_p2_thumb_img_h: ImageView? = null
    private var cp_p3_thumb_img: ImageView? = null
    private var cp_p3_thumb_img_m: ImageView? = null
    private var cp_p4_thumb_img: ImageView? = null
    private var cp_p5_thumb_img: ImageView? = null
    private var md_Construction_waste_positive_img: ImageView? = null
    private var md_Construction_waste_opposite_img: ImageView? = null

    private var dialog: Dialog? = null
    private var imgUrl: String? = null
    private var uid: String? = null
    private var width: Int = 0
    private var height: Int = 0
    private var angle: Int = 0
    private var screenHeight: Int = 0
    private var tag: Int = 0
    private var savePic: SavePic? = null
    private var mCinfoBean: CarInfoBean.DataBean.CinfoBean? = null
    private var mPresenter: CarInfoPresenter? = null
    private var mCid: String? = null
    private var cp_p1_thumb: RelativeLayout? = null
    private var cp_p1_thumb_f: RelativeLayout? = null
    private var cp_p2_thumb: RelativeLayout? = null
    private var cp_p2_thumb_f: RelativeLayout? = null
    private var cp_p2_thumb_h: RelativeLayout? = null
    private var cp_p3_thumb: RelativeLayout? = null
    private var cp_p3_thumb_m: RelativeLayout? = null
    private var cp_p4_thumb: RelativeLayout? = null
    private var cp_p5_thumb: RelativeLayout? = null
    private var md_construction_waste_positive: RelativeLayout? = null
    private var md_construction_waste_opposite: RelativeLayout? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter = CarInfoPresenter(this)
        mCid = arguments!!.getString("str")
        mPresenter!!.getData(mCid)
        initView()
        savePic = SavePic()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.unSubcrible()
    }

    override fun setContentView() {
        setContentView(R.layout.car_info)
    }

    override fun initView() {
        use_property = getViewById(R.id.use_property)
        Purchase_price = getViewById(R.id.Purchase_price)
        Frame_number = getViewById(R.id.Frame_number)
        Engine_number = getViewById(R.id.Engine_number)
        register_data = getViewById(R.id.register_data)
        Verification_qualification_to = getViewById(R.id.Verification_qualification_to)
        Nuclear_load = getViewById(R.id.Nuclear_load)
        environmental_protection_begin = getViewById(R.id.environmental_protection_begin)
        environmental_protection_end = getViewById(R.id.environmental_protection_end)
        car_plate_type = getViewById(R.id.car_plate_type)
        environmental_protection_color = getViewById(R.id.environmental_protection_color)
        self_num = getViewById(R.id.self_num)
        start_commission_manager_date = getViewById(R.id.start_commission_manager_date)
        end_commission_manager_date = getViewById(R.id.end_commission_manager_date)
        Outline_length = getViewById(R.id.Outline_length)
        Outline_width = getViewById(R.id.Outline_width)
        Outline_height = getViewById(R.id.Outline_height)
        ID_num = getViewById(R.id.ID_num)
        Enterprise_class = getViewById(R.id.Enterprise_class)
        start_date = getViewById(R.id.start_date)
        end_date = getViewById(R.id.end_date)

        cp_p1_thumb = getViewById(R.id.cp_p1_thumb)
        cp_p1_thumb_f = getViewById(R.id.cp_p1_thumb_F)
        cp_p2_thumb = getViewById(R.id.cp_p2_thumb)
        cp_p2_thumb_f = getViewById(R.id.cp_p2_thumb_F)
        cp_p2_thumb_h = getViewById(R.id.cp_p2_thumb_h)
        cp_p3_thumb = getViewById(R.id.cp_p3_thumb)
        cp_p3_thumb_m = getViewById(R.id.cp_p3_thumb_m)
        cp_p4_thumb = getViewById(R.id.cp_p4_thumb)
        cp_p5_thumb = getViewById(R.id.cp_p5_thumb)
        md_construction_waste_positive = getViewById(R.id.md_Construction_waste_positive)
        md_construction_waste_opposite = getViewById(R.id.md_Construction_waste_opposite)

        cp_p1_thumb_img = getViewById(R.id.cp_p1_thumb_img)
        cp_p1_thumb_img_F = getViewById(R.id.cp_p1_thumb_img_F)
        cp_p2_thumb_img = getViewById(R.id.cp_p2_thumb_img)
        cp_p2_thumb_img_F = getViewById(R.id.cp_p2_thumb_img_F)
        cp_p2_thumb_img_h = getViewById(R.id.cp_p2_thumb_img_h)
        cp_p3_thumb_img = getViewById(R.id.cp_p3_thumb_img)
        cp_p3_thumb_img_m = getViewById(R.id.cp_p3_thumb_img_m)
        cp_p4_thumb_img = getViewById(R.id.cp_p4_thumb_img)
        cp_p5_thumb_img = getViewById(R.id.cp_p5_thumb_img)
        md_Construction_waste_positive_img = getViewById(R.id.md_Construction_waste_positive_img)
        md_Construction_waste_opposite_img = getViewById(R.id.md_Construction_waste_opposite_img)


        //定义DisplayMetrics 对象;
        val dm = DisplayMetrics()
        //取得窗口属性
        activity!!.windowManager.defaultDisplay.getMetrics(dm)
        //窗口的宽度
        val screenWidth = dm.widthPixels
        //窗口高度
        screenHeight = dm.heightPixels
        width = (screenWidth * 0.8).toInt()
    }

    override fun setListener() {
        cp_p1_thumb!!.setOnClickListener(this)
        cp_p1_thumb_f!!.setOnClickListener(this)
        cp_p2_thumb!!.setOnClickListener(this)
        cp_p2_thumb_f!!.setOnClickListener(this)
        cp_p2_thumb_h!!.setOnClickListener(this)
        cp_p3_thumb!!.setOnClickListener(this)
        cp_p3_thumb_m!!.setOnClickListener(this)
        cp_p4_thumb!!.setOnClickListener(this)
        cp_p5_thumb!!.setOnClickListener(this)
        md_construction_waste_positive!!.setOnClickListener(this)
        md_construction_waste_opposite!!.setOnClickListener(this)
    }

    override fun getData(bean: CarInfoBean.DataBean) {
        uid = bean.uid
        mCinfoBean = bean.cinfo
        use_property!!.text = mCinfoBean!!.utype
        Purchase_price!!.text = mCinfoBean!!.carprice
        Frame_number!!.text = mCinfoBean!!.fnum
        Engine_number!!.text = mCinfoBean!!.enumX
        register_data!!.text = mCinfoBean!!.yregtime
        Verification_qualification_to!!.text = mCinfoBean!!.ychketime
        Nuclear_load!!.text = mCinfoBean!!.loadp
        environmental_protection_begin!!.text = mCinfoBean!!.empchkstime
        environmental_protection_end!!.text = mCinfoBean!!.empchketime
        car_plate_type!!.text = mCinfoBean!!.platetype
        environmental_protection_color!!.text = mCinfoBean!!.ccolor
        self_num!!.text = mCinfoBean!!.filenum
        start_commission_manager_date!!.text = mCinfoBean!!.trstime
        end_commission_manager_date!!.text = mCinfoBean!!.tretime
        Outline_length!!.text = mCinfoBean!!.olength
        Outline_width!!.text = mCinfoBean!!.owidth
        Outline_height!!.text = mCinfoBean!!.oheight
        ID_num!!.text = mCinfoBean!!.cwcode
        Enterprise_class!!.text = mCinfoBean!!.cwlevel
        start_date!!.text = mCinfoBean!!.cwstime
        end_date!!.text = mCinfoBean!!.cwetime

        if (!mCinfoBean!!.p1_thumb.isEmpty()) {
            App.imageLoader.displayImage(mCinfoBean!!.p1_thumb, cp_p1_thumb_img!!, App.RoundeOptions)
        }
        if (!mCinfoBean!!.p2_thumb.isEmpty()) {
            App.imageLoader.displayImage(mCinfoBean!!.p2_thumb, cp_p2_thumb_img!!, App.RoundeOptions)
        }
        if (!mCinfoBean!!.p3_thumb.isEmpty()) {
            App.imageLoader.displayImage(mCinfoBean!!.p3_thumb, cp_p3_thumb_img!!, App.RoundeOptions)
        }
        if (!mCinfoBean!!.p4_thumb.isEmpty()) {
            App.imageLoader.displayImage(mCinfoBean!!.p4_thumb, cp_p4_thumb_img!!, App.RoundeOptions)
        }
        if (!mCinfoBean!!.p5_thumb.isEmpty()) {
            App.imageLoader.displayImage(mCinfoBean!!.p5_thumb, cp_p5_thumb_img!!, App.RoundeOptions)
        }
        if (!mCinfoBean!!.p6_thumb.isEmpty()) {
            App.imageLoader.displayImage(mCinfoBean!!.p6_thumb, cp_p1_thumb_img_F!!, App.RoundeOptions)
        }
        if (!mCinfoBean!!.p7_thumb.isEmpty()) {
            App.imageLoader.displayImage(mCinfoBean!!.p7_thumb, cp_p2_thumb_img_F!!, App.RoundeOptions)
        }
        if (!mCinfoBean!!.p8_thumb.isEmpty()) {
            App.imageLoader.displayImage(mCinfoBean!!.p8_thumb, cp_p3_thumb_img_m!!, App.RoundeOptions)
        }
        if (!mCinfoBean!!.p9_thumb.isEmpty()) {
            App.imageLoader.displayImage(mCinfoBean!!.p9_thumb, cp_p2_thumb_img_h!!, App.RoundeOptions)
        }
        if (!mCinfoBean!!.p10_thumb.isEmpty()) {
            App.imageLoader.displayImage(mCinfoBean!!.p10_thumb, md_Construction_waste_positive_img!!, App.RoundeOptions)
        }
        if (!mCinfoBean!!.p11_thumb.isEmpty()) {
            App.imageLoader.displayImage(mCinfoBean!!.p11_thumb, md_Construction_waste_opposite_img!!, App.RoundeOptions)
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
        Snackbar.make(md_Construction_waste_opposite_img!!, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试") { mPresenter!!.getData(mCid) }.show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                val intent = Intent(this.activity, cutPic::class.java)
                intent.data = data.data
                intent.putExtra("angle", angle)
                intent.putExtra("uid", uid)
                intent.putExtra("url", imgUrl)
                intent.putExtra("type", "1")
                startActivityForResult(intent, tag)
            }
        }
        if (data != null) {
            if (requestCode == 101 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p1_thumb_img, data, dialog)
            } else if (requestCode == 102 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p2_thumb_img, data, dialog)
            } else if (requestCode == 103 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p3_thumb_img, data, dialog)
            } else if (requestCode == 104 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p4_thumb_img, data, dialog)
            } else if (requestCode == 105 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p5_thumb_img, data, dialog)
            } else if (requestCode == 106 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p1_thumb_img_F, data, dialog)
            } else if (requestCode == 107 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p2_thumb_img_F, data, dialog)
            } else if (requestCode == 108 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p3_thumb_img_m, data, dialog)
            } else if (requestCode == 109 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p2_thumb_img_h, data, dialog)
            } else if (requestCode == 110 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(md_Construction_waste_positive_img, data, dialog)
            } else if (requestCode == 111 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(md_Construction_waste_opposite_img, data, dialog)
            } else if (requestCode == 101 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p1_thumb_img, data, dialog)
            } else if (requestCode == 102 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p2_thumb_img, data, dialog)
            } else if (requestCode == 103 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p3_thumb_img, data, dialog)
            } else if (requestCode == 104 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p4_thumb_img, data, dialog)
            } else if (requestCode == 105 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p5_thumb_img, data, dialog)
            } else if (requestCode == 106 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p1_thumb_img_F, data, dialog)
            } else if (requestCode == 107 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p2_thumb_img_F, data, dialog)
            } else if (requestCode == 108 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p3_thumb_img_m, data, dialog)
            } else if (requestCode == 109 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(cp_p2_thumb_img_h, data, dialog)
            } else if (requestCode == 110 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(md_Construction_waste_positive_img, data, dialog)
            } else if (requestCode == 111 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(md_Construction_waste_opposite_img, data, dialog)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun showOriginalPic(Picurl: String, uploadUrl: String, angle: Int) {
        dialog = Dialog(activity!!, R.style.dialog)
        val layout = View.inflate(activity, R.layout.alert_img, null)
        dialog!!.setContentView(layout)
        dialog!!.show()
        val preImage = layout.findViewById<View>(R.id.preImage) as ImageView
        val img_detail = layout.findViewById<View>(R.id.img_detail) as MatrixImageView
        val takePhotoBtn = layout.findViewById<View>(R.id.takePhotoBtn) as TextView
        val pickPhotoBtn = layout.findViewById<View>(R.id.pickPhotoBtn) as TextView
        if (Picurl.isEmpty()) {
            if (angle == 0) {//根据角度来判断是显示横的还是纵的拍照示意图
                preImage.setImageResource(R.drawable.zhong)
            } else if (angle == -90) {
                preImage.setImageResource(R.drawable.hang)
            }
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
            intent1.putExtra("angle", angle)
            intent1.putExtra("uid", uid)
            intent1.putExtra("url", uploadUrl)
            intent1.putExtra("type", "1")
            startActivityForResult(intent1, tag)
        } else {
            val intent = Intent(activity, RectCameraActivity::class.java)
            intent.putExtra("width", width)
            intent.putExtra("height", height)
            intent.putExtra("angle", angle)
            intent.putExtra("uid", uid)
            intent.putExtra("url", uploadUrl)
            intent.putExtra("type", "1")
            startActivityForResult(intent, tag)
        }
    }

    override fun onClick(views: View) {
        val i = views.id
        if (i == R.id.cp_p1_thumb) {
            showOriginalPic(mCinfoBean!!.p1, UrlConfig.ownerregistration1_url, -90)
            height = (screenHeight * 0.73).toInt()
            angle = -90
            tag = 101
            // imgUrl = ShowImageDialog.getInstance().showDialog(this.getActivity(),dialog,mCinfoBean.getP1(),UrlConfig.ownerregistration1_url,width,height,angle,uid,tag);

        } else if (i == R.id.cp_p2_thumb) {
            showOriginalPic(mCinfoBean!!.p2, UrlConfig.ownerregistration3_url, -90)
            height = (screenHeight * 0.73).toInt()
            angle = -90
            tag = 102

        } else if (i == R.id.cp_p3_thumb) {
            showOriginalPic(mCinfoBean!!.p3, UrlConfig.carreg_url, 0)
            height = (screenHeight * 0.8).toInt()
            angle = 0
            tag = 103

        } else if (i == R.id.cp_p4_thumb) {
            showOriginalPic(mCinfoBean!!.p4, UrlConfig.invoice_url, -90)
            height = (screenHeight * 0.68).toInt()
            angle = -90
            tag = 104

        } else if (i == R.id.cp_p5_thumb) {
            height = (screenHeight * 0.64).toInt()
            angle = -90
            tag = 105
            showOriginalPic(mCinfoBean!!.p5, UrlConfig.tax_url, -90)

        } else if (i == R.id.cp_p1_thumb_F) {
            showOriginalPic(mCinfoBean!!.p6, UrlConfig.ownerregistration2_url, -90)
            height = (screenHeight * 0.73).toInt()
            angle = -90
            tag = 106

        } else if (i == R.id.cp_p2_thumb_F) {
            showOriginalPic(mCinfoBean!!.p7, UrlConfig.ownerregistration4_url, -90)
            height = (screenHeight * 0.73).toInt()
            angle = -90
            tag = 107

        } else if (i == R.id.cp_p3_thumb_m) {
            showOriginalPic(mCinfoBean!!.p8, UrlConfig.carregtransfer_url, 0)
            height = (screenHeight * 0.8).toInt()
            angle = 0
            tag = 108

        } else if (i == R.id.cp_p2_thumb_h) {
            showOriginalPic(mCinfoBean!!.p9, UrlConfig.carcert_url, 0)
            height = (screenHeight * 0.73).toInt()
            angle = 0
            tag = 109

        } else if (i == R.id.md_Construction_waste_positive) {
            showOriginalPic(mCinfoBean!!.p10, UrlConfig.cwastef_url, -90)
            height = (screenHeight * 0.64).toInt()
            angle = -90
            tag = 110

        } else if (i == R.id.md_Construction_waste_opposite) {
            showOriginalPic(mCinfoBean!!.p11, UrlConfig.cwasteb_url, -90)
            height = (screenHeight * 0.64).toInt()
            angle = -90
            tag = 111

        }
    }

    override fun companyId(): String = create("userInfo").get("compid", "") as String

    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    companion object {
        private val GALLERY_REQUEST_CODE = 2//相册
        private val REQUEST_CAMERA_PERMISSION = 0x01

        fun newInstance(str: String): CarInfo {
            val args = Bundle()
            args.putString("str", str)
            val carInfo = CarInfo()
            carInfo.arguments = args
            return carInfo
        }
    }
}
