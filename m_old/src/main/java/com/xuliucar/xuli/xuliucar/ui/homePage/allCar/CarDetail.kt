package com.xuliucar.xuli.xuliucar.ui.homePage.allCar

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.text.TextUtils
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.*
import kotlinx.android.synthetic.main.car_detail_top.*

class CarDetail : BaseNewFragment<BasePresenter>(), View.OnClickListener {
    private var carInfoFra: CarInfo? = null
    private var insuranceInfoFra: InsuranceInfo? = null
    private var businessInfoFragment: BusinessInfo? = null
    private var carOwnerInfoFra: CarOwnerInfo? = null
    private var mainDriverFra: MainDriver? = null
    private var assistantDriver: AssistantDriver? = null
    private var cid: String? = null
    private var page: String? = null
    private var isown: String? = null
    private lateinit var infoBean: CarsListBean.DataBean.InfoBean

    override fun layViewId(): Int = R.layout.car_detail
    override fun addViewId(): Int = 0


    override fun initView(savedInstanceState: Bundle?, view: View) {
        infoBean = arguments!!.getSerializable("carInfoBean") as CarsListBean.DataBean.InfoBean
        backToolbar()
        setToolbarTitle(infoBean.plates)
        car_plate_info_layout!!.setOnClickListener(this)
        Insurance_info_layout!!.setOnClickListener(this)
        Operation_info_layout!!.setOnClickListener(this)
        car_owner_info_layout!!.setOnClickListener(this)
        main_driver_layout!!.setOnClickListener(this)
        assistant_driver_layout!!.setOnClickListener(this)
        getCarList()
        getJumpData()
        if (page == "1") {
            selectMenu(1)
        } else {
            selectMenu(0)
        }
        if (!TextUtils.isEmpty(isown)) {
            if (isown == "0") {
                car_owner_info_layout!!.visibility = View.VISIBLE
            } else if (isown == "1") {
                car_owner_info_layout!!.visibility = View.GONE
            }
        }
    }


    private fun getJumpData() {
        val bundle = arguments
        page = bundle!!.getString("page")
        val pageName = bundle.getString("pageName")
        if (pageName != null) {
            when (pageName) {
                "doperpetuae" -> {
                    val bean = bundle.getSerializable("doperpetuaeData") as DoPerpetuaeBean.DataBean.InfoBean
                    val plates = bean.plates
//                    car_plate!!.text = plates
                    cid = bean.id
                    val carOwner = bean.owner
                    create("userInfo").add("carOwner", carOwner).add("carNum", plates)
                }
                "limited" -> {
                    val bean1 = bundle.getSerializable("limitedData") as LimitedBean.DataBean.InfoBean
                    val plates1 = bean1.plates
//                    car_plate!!.text = plates1
                    cid = bean1.id
                    val carOwner1 = bean1.rphone
                    create("userInfo").add("carOwner", carOwner1).add("carNum", plates1)
                }
                "yearticket" -> {
                    val bean2 = bundle.getSerializable("yearticketData") as YearTicketBean.DataBean.InfoBean
                    val plates2 = bean2.plates
//                    car_plate!!.text = plates2
                    cid = bean2.id
                    val carOwner2 = bean2.rphone
                    create("userInfo").add("carOwner", carOwner2).add("carNum", plates2)
                }
                "insurance" -> {
                    val bean3 = bundle.getSerializable("insuranceData") as InSuranceBean.DataBean.InfoBean
                    val plates3 = bean3.plates
//                    car_plate!!.text = plates3
                    cid = bean3.id
                    val carOwner3 = bean3.rphone
                    create("userInfo").add("carOwner", carOwner3).add("carNum", plates3)
                }
                "doroadtc" -> {
                    val bean4 = bundle.getSerializable("doroadtcData") as DoRoadTCBean.DataBean.InfoBean
                    val plates4 = bean4.plates
//                    car_plate!!.text = plates4
                    cid = bean4.id
                    val carOwner4 = bean4.rphone
                    create("userInfo").add("carOwner", carOwner4).add("carNum", plates4)
                }
                "environment" -> {
                    val bean5 = bundle.getSerializable("enviromentData") as EnvironmentBean.DataBean.InfoBean
                    val plates5 = bean5.plates
//                    car_plate!!.text = plates5
                    cid = bean5.id
                    val carOwner5 = bean5.rphone
                    create("userInfo").add("carOwner", carOwner5).add("carNum", plates5)
                }
                "constructionwast" -> {
                    val bean6 = bundle.getSerializable("constructionwastData") as ConstructionWasteBean.DataBean.InfoBean
                    val plates6 = bean6.plates
//                    car_plate!!.text = plates6
                    cid = bean6.id
                    val carOwner6 = bean6.rphone
                    create("userInfo").add("carOwner", carOwner6).add("carNum", plates6)
                }
                "passperpetuae" -> {
                    val bean7 = bundle.getSerializable("passperpetuaeData") as PassPerPetaueBean.DataBean.InfoBean
                    val plates7 = bean7.plates
//                    car_plate!!.text = plates7
                    cid = bean7.id
                    val carOwner7 = bean7.rphone
                    create("userInfo").add("carOwner", carOwner7).add("carNum", plates7)
                }
            }
        }
    }

    private fun getCarList() {
        if (infoBean != null) {
            val carPlate = infoBean.plates
            cid = infoBean.id
            //            car_plate.setText(carPlate);
            val carOwner = infoBean.owner
            val carNum = infoBean.plates
            create("userInfo").add("carOwner", carOwner).add("carNum", carNum)
            isown = infoBean.plates
            val intent = arguments
            page = intent!!.getString("page")
        }
    }

    private fun selectMenu(index: Int) {
        clearSelection()//清除选中状态
        val transaction = fragmentManager!!.beginTransaction()
        hideFragment(transaction)//隐藏所有的fragment，以防叠加在一起
        when (index) {
            0 -> {
                car_plate_info_layout!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (carInfoFra == null) {
                    carInfoFra = CarInfo.newInstance(cid!!)
                    transaction.add(R.id.car_detail_content, carInfoFra)
                } else {
                    transaction.show(carInfoFra)
                }
            }
            1 -> {
                Insurance_info_layout!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (insuranceInfoFra == null) {
                    insuranceInfoFra = InsuranceInfo.newInstance(cid!!)
                    transaction.add(R.id.car_detail_content, insuranceInfoFra)
                } else {
                    transaction.show(insuranceInfoFra)
                }
            }
            2 -> {
                Operation_info_layout!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (businessInfoFragment == null) {
                    businessInfoFragment = BusinessInfo.newInstance(cid!!)
                    transaction.add(R.id.car_detail_content, businessInfoFragment)
                } else {
                    transaction.show(businessInfoFragment)
                }
            }
            3 -> {
                car_owner_info_layout!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (carOwnerInfoFra == null) {
                    carOwnerInfoFra = CarOwnerInfo.newInstance(cid!!)
                    transaction.add(R.id.car_detail_content, carOwnerInfoFra)
                } else {
                    transaction.show(carOwnerInfoFra)
                }
            }
            4 -> {
                main_driver_layout!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (mainDriverFra == null) {
                    mainDriverFra = MainDriver.newInstance(cid!!)
                    transaction.add(R.id.car_detail_content, mainDriverFra)
                } else {
                    transaction.show(mainDriverFra)
                }
            }
            5 -> {
                assistant_driver_layout!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (assistantDriver == null) {
                    assistantDriver = AssistantDriver.newInstance(cid!!)
                    transaction.add(R.id.car_detail_content, assistantDriver)
                } else {
                    transaction.show(assistantDriver)
                }
            }
        }
        transaction.commit()
    }

    private fun hideFragment(transaction: FragmentTransaction) {
        if (carInfoFra != null) {
            transaction.hide(carInfoFra)
        }
        if (insuranceInfoFra != null) {
            transaction.hide(insuranceInfoFra)
        }
        if (businessInfoFragment != null) {
            transaction.hide(businessInfoFragment)
        }
        if (carOwnerInfoFra != null) {
            transaction.hide(carOwnerInfoFra)
        }
        if (mainDriverFra != null) {
            transaction.hide(mainDriverFra)
        }
        if (assistantDriver != null) {
            transaction.hide(assistantDriver)
        }
    }

    private fun clearSelection() {
        car_plate_info_layout!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        Insurance_info_layout!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        Operation_info_layout!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        car_owner_info_layout!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        main_driver_layout!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        assistant_driver_layout!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
    }

    override fun onClick(view: View) {
        val i = view.id
        if (i == R.id.car_plate_info_layout) {
            selectMenu(0)

        } else if (i == R.id.Insurance_info_layout) {
            selectMenu(1)

        } else if (i == R.id.Operation_info_layout) {
            selectMenu(2)

        } else if (i == R.id.car_owner_info_layout) {
            selectMenu(3)

        } else if (i == R.id.main_driver_layout) {
            selectMenu(4)

        } else if (i == R.id.assistant_driver_layout) {
            selectMenu(5)

        }
    }

}
