package com.xuliucar.xuli.xuliucar.ui.homePage.infoRegister

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.ui.homePage.infoRegister.carReg.*
import com.xuliucar.xuli.xuliucar.utils.Watcher
import kotlinx.android.synthetic.main.car_reg.*


class CarReg : BaseNewFragment<BasePresenter>(), View.OnClickListener {
    private var carOwnerPhotoFra: CarOwnerPhoto? = null
    private var carCardPhotoFra: CarCardPhoto? = null
    private var mainDriverPhotoFra: MainDriverPhoto? = null
    private var assistantDriverPhotoFra: AssistantDriverPhoto? = null
    private var insuranceInfomaFra: InsuranceInfoma? = null
    private var roadCTPhotoFra: RoadCTPhoto? = null
    private var otherPhotoFra: OtherPhoto? = null
    private lateinit var manager: FragmentManager

    override fun layViewId(): Int {
        return R.layout.car_reg
    }

    override fun initView(savedInstanceState: Bundle?, view: View) {
        backToolbar()
        setToolbarTitle(getString(R.string.car_reg))
        Watcher.watcherText(bd_num, _mActivity)
        carOwner_photo.setOnClickListener(this)
        car_card_photo.setOnClickListener(this)
        main_driver_photo.setOnClickListener(this)
        assistant_driver_photo.setOnClickListener(this)
        Insurance_info.setOnClickListener(this)
        road_certificate.setOnClickListener(this)
        car_photo.setOnClickListener(this)
        manager = childFragmentManager
        selectMenu(0)
    }

    override fun addViewId(): Int {
        return 0
    }


    private fun selectMenu(index: Int) {
        clearSelection()//清除选中状态
        val transaction = fragmentManager!!.beginTransaction()
        hideFragment(transaction)//隐藏所有的fragment，以防叠加在一起
        when (index) {
            0 -> {
                car_card_photo!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (carCardPhotoFra == null) {
                    carCardPhotoFra = CarCardPhoto()
                    transaction.add(R.id.carReg_content, carCardPhotoFra)
                } else {
                    transaction.show(carCardPhotoFra)
                }
            }
            1 -> {
                Insurance_info!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (insuranceInfomaFra == null) {
                    insuranceInfomaFra = InsuranceInfoma()
                    transaction.add(R.id.carReg_content, insuranceInfomaFra)
                } else {
                    transaction.show(insuranceInfomaFra)
                }
            }

            2 -> {
                road_certificate!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (roadCTPhotoFra == null) {
                    roadCTPhotoFra = RoadCTPhoto()
                    transaction.add(R.id.carReg_content, roadCTPhotoFra)
                } else {
                    transaction.show(roadCTPhotoFra)
                }
            }


            3 -> {
                carOwner_photo!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (carOwnerPhotoFra == null) {
                    carOwnerPhotoFra = CarOwnerPhoto()
                    transaction.add(R.id.carReg_content, carOwnerPhotoFra)
                } else {
                    transaction.show(carOwnerPhotoFra)
                }
            }
            4 -> {
                main_driver_photo!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (mainDriverPhotoFra == null) {
                    mainDriverPhotoFra = MainDriverPhoto()
                    transaction.add(R.id.carReg_content, mainDriverPhotoFra)
                } else {
                    transaction.show(mainDriverPhotoFra)
                }
            }
            5 -> {
                assistant_driver_photo!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (assistantDriverPhotoFra == null) {
                    assistantDriverPhotoFra = AssistantDriverPhoto()
                    transaction.add(R.id.carReg_content, assistantDriverPhotoFra)
                } else {
                    transaction.show(assistantDriverPhotoFra)
                }
            }
            6 -> {
                car_photo!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (otherPhotoFra == null) {
                    otherPhotoFra = OtherPhoto()
                    transaction.add(R.id.carReg_content, otherPhotoFra)
                } else {
                    transaction.show(otherPhotoFra)
                }
            }
        }
        transaction.commit()
    }

    private fun hideFragment(transaction: FragmentTransaction) {
        if (carOwnerPhotoFra != null) {
            transaction.hide(carOwnerPhotoFra)
        }
        if (carCardPhotoFra != null) {
            transaction.hide(carCardPhotoFra)
        }
        if (mainDriverPhotoFra != null) {
            transaction.hide(mainDriverPhotoFra)
        }
        if (assistantDriverPhotoFra != null) {
            transaction.hide(assistantDriverPhotoFra)
        }
        if (insuranceInfomaFra != null) {
            transaction.hide(insuranceInfomaFra)
        }
        if (roadCTPhotoFra != null) {
            transaction.hide(roadCTPhotoFra)
        }
        if (otherPhotoFra != null) {
            transaction.hide(otherPhotoFra)
        }
    }

    private fun clearSelection() {
        carOwner_photo!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        car_card_photo!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        main_driver_photo!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        assistant_driver_photo!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        Insurance_info!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        road_certificate!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        car_photo!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.car_card_photo -> selectMenu(0)
            R.id.Insurance_info -> selectMenu(1)
            R.id.road_certificate -> selectMenu(2)
            R.id.carOwner_photo -> selectMenu(3)
            R.id.main_driver_photo -> selectMenu(4)
            R.id.assistant_driver_photo -> selectMenu(5)
            R.id.car_photo -> selectMenu(6)

        }
    }


}
