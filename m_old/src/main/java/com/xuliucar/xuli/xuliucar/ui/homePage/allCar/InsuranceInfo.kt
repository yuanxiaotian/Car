package com.xuliucar.xuli.xuliucar.ui.homePage.allCar


import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.cangmaomao.lib.base.BaseNewActivity
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.contacturi.GlideApp
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.adapter.PolicyAdapter
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.base.BaseFragment
import com.xuliucar.xuli.xuliucar.bean.InsuranceInfoData
import com.xuliucar.xuli.xuliucar.bean.Policy
import com.xuliucar.xuli.xuliucar.config.UrlConfig
import com.xuliucar.xuli.xuliucar.dataHandlers.GetDetailData
import com.xuliucar.xuli.xuliucar.dataHandlers.ShowImageDialog
import com.xuliucar.xuli.xuliucar.mvp.presenter.InsuranceInfoPresenter
import com.xuliucar.xuli.xuliucar.mvp.view.InsuranceInfoView
import com.xuliucar.xuli.xuliucar.myCamera.RectCameraActivity
import com.xuliucar.xuli.xuliucar.myCamera2.CameraActivity
import com.xuliucar.xuli.xuliucar.permission.PermissionUtils
import com.xuliucar.xuli.xuliucar.ui.cutPic
import com.xuliucar.xuli.xuliucar.utils.SavePic
import com.xuliucar.xuli.xuliucar.utils.ToastUtil
import com.xuliucar.xuli.xuliucar.widget.MatrixImageView
import kotlinx.android.synthetic.main.insurance_info.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class InsuranceInfo : BaseNewFragment<BasePresenter>(), View.OnClickListener, InsuranceInfoView<InsuranceInfoData> {

    private var dialog: Dialog? = null
    private var imgUrl: String? = null
    private var uid: String? = null
    private var p1: String? = null
    private var p2: String? = null
    private var backNums = App.size
    private var toNums = 1
    private var adapter: PolicyAdapter? = null
    private lateinit var policyList: MutableList<Policy>
    private var width: Int = 0
    private var height: Int = 0
    private var tag: Int = 0
    private var savePic: SavePic? = null
    private lateinit var infoPresenter: InsuranceInfoPresenter
    override fun layViewId(): Int = R.layout.insurance_info
    override fun addViewId(): Int = 0
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String
    override fun cid(): String = arguments!!.getString("str")


    override fun initView(savedInstanceState: Bundle?, view: View) {
        bx_Strong_insurance.setOnClickListener(this)
        bx_Commercial_insurance.setOnClickListener(this)
        iPager_back.setOnClickListener(this)
        iPager_to.setOnClickListener(this)
        infoPresenter = InsuranceInfoPresenter(this)
        infoPresenter.getData()

        policyList = ArrayList()

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


    private fun initListener() {
        iPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                business_title!!.text = policyList[position].type_name
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                val intent = Intent(activity, cutPic::class.java)
                intent.data = data.data
                intent.putExtra("angle", 0)
                intent.putExtra("uid", uid)
                intent.putExtra("url", imgUrl)
                intent.putExtra("type", "1")
                startActivityForResult(intent, tag)
            }

        }
        if (data != null) {
            if (requestCode == 101 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(bx_Strong_insurance_img, data, dialog)
            } else if (requestCode == 102 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(bx_Commercial_insurance_img, data, dialog)
            } else if (requestCode == 101 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(bx_Strong_insurance_img, data, dialog)
            } else if (requestCode == 102 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(bx_Commercial_insurance_img, data, dialog)
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
            preImage.setImageResource(R.drawable.zhong)
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
            intent1.putExtra("angle", 0)
            intent1.putExtra("uid", uid)
            intent1.putExtra("url", uploadUrl)
            intent1.putExtra("type", "1")
            startActivityForResult(intent1, tag)
        } else {
            val intent = Intent(activity, RectCameraActivity::class.java)
            intent.putExtra("width", width)
            intent.putExtra("height", height)
            intent.putExtra("angle", 0)
            intent.putExtra("uid", uid)
            intent.putExtra("url", uploadUrl)
            intent.putExtra("type", "1")
            startActivityForResult(intent, tag)
        }
    }

    private fun setBusinessTitleB() {
        //点击返回是数组的最大值
        iPager.currentItem = backNums
        backNums--//每点击一次就减一
        if (backNums < 0) {//小于零的时候从新赋值为数组的最大值
            backNums = App.size
        }
    }

    private fun setBusinessTitleT() {
        iPager.currentItem = toNums//点击下一个viewpager从1开始
        toNums++//每点击一次就+1
        if (toNums > App.size) {//点到最大的时候赋值为零
            toNums = 0
        }
    }

    override fun onClick(views: View) {
        val i = views.id
        if (i == R.id.bx_Strong_insurance) {
            showOriginalPic(p1!!, UrlConfig.is1_url)
            tag = 101

        } else if (i == R.id.bx_Commercial_insurance) {
            showOriginalPic(p2!!, UrlConfig.is2_url)
            tag = 102

        } else if (i == R.id.iPager_back) {
            setBusinessTitleB()

        } else if (i == R.id.iPager_to) {
            setBusinessTitleT()

        }
    }

    override fun OnSuccess(data: InsuranceInfoData) {
        policyList = ArrayList()

        val d = data.data.isinfo.is2infolist
        val d1 = d.childtype_1

        policyList.add(Policy(d1.type_name, d1.indemnity_norm, d1.abatement, d1.base, d1.coefficient, d1.total))

        val d2 = d.childtype_2
        policyList.add(Policy(d2.type_name, d2.indemnity_norm, d2.abatement, d2.base, d2.coefficient, d2.total))

        val d3 = d.childtype_3
        policyList.add(Policy(d3.type_name, d3.indemnity_norm, d3.abatement, d3.base, d3.coefficient, d3.total))

        val d4 = d.childtype_4
        policyList.add(Policy(d4.type_name, d4.indemnity_norm, d4.abatement, d4.base, d4.coefficient, d4.total))

        val d5 = d.childtype_5
        policyList.add(Policy(d5.type_name, d5.indemnity_norm, d5.abatement, d5.base, d5.coefficient, d5.total))

        val d6 = d.childtype_6
        policyList.add(Policy(d6.type_name, d6.indemnity_norm, d6.abatement, d6.base, d6.coefficient, d6.total))

        val d7 = d.childtype_7
        policyList.add(Policy(d7.type_name, d7.indemnity_norm, d7.abatement, d7.base, d7.coefficient, d7.total))

        val d8 = d.childtype_8
        policyList.add(Policy(d8.type_name, d8.indemnity_norm, d8.abatement, d8.base, d8.coefficient, d8.total))

        val d9 = d.childtype_9
        policyList.add(Policy(d9.type_name, d9.indemnity_norm, d9.abatement, d9.base, d9.coefficient, d9.total))


        initListener()
        adapter = PolicyAdapter(activity, policyList)
        iPager.adapter = adapter
        iPager.currentItem = 0

        business_title.text = policyList[0].type_name
        adapter!!.notifyDataSetChanged()

        //交强险
        insurance_company_name.text = data.data.isinfo.is1comp
        Policy_No_one_name.text = data.data.isinfo.is1num
        st_one_one.text = data.data.isinfo.insure1sdate
        st_one_two.text = data.data.isinfo.insure1edate
        The_is1tax.text = data.data.isinfo.is1tax
        iMoney!!.text = data.data.isinfo.is1amount

        //商业险
        Commercial_insurance_name.text = data.data.isinfo.is2comp
        Policy_No_two_name.text = data.data.isinfo.is2num
        st_two_one.text = data.data.isinfo.insure2sdate
        st_two_two.text = data.data.isinfo.insure2edate
        The_sum_insured_t.text = data.data.isinfo.is2thirdp
        iMoney_t.text = data.data.isinfo.is2amount
        GlideApp.with(this).load(data.data.isinfo.p1_thumb).into(bx_Strong_insurance_img)
        GlideApp.with(this).load(data.data.isinfo.p2_thumb).into(bx_Commercial_insurance_img)
    }

    override fun OnFailure(e: Throwable) {

    }

    companion object {
        private val GALLERY_REQUEST_CODE = 2//相册
        private val REQUEST_CAMERA_PERMISSION = 0x01

        fun newInstance(str: String): InsuranceInfo {
            val args = Bundle()
            args.putString("str", str)
            val insuranceInfo = InsuranceInfo()
            insuranceInfo.arguments = args
            return insuranceInfo
        }
    }
}
