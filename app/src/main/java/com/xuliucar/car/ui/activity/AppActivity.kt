package com.xuliucar.car.ui.activity

import android.annotation.SuppressLint
import android.support.v4.app.FragmentTransaction
import com.cangmaomao.lib.action.*
import com.cangmaomao.lib.base.BaseActivity
import com.cangmaomao.lib.config.CODE
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.utils.StatusBarUtil
import com.xuliucar.car.R
import com.xuliucar.car.contract.MainContract
import com.xuliucar.car.ui.fragment.MainFragment
import com.xuliucar.car.ui.fragment.Notice
import com.xuliucar.me.ui.fragment.*
import com.xuliucar.xuli.xuliucar.newmvp.view.messageManage.MessageManageIndex
import com.xuliucar.xuli.xuliucar.newmvp.view.messageManage.child.CustomPeople
import com.xuliucar.xuli.xuliucar.newmvp.view.saleManage.SaleManager
import com.xuliucar.xuli.xuliucar.newmvp.view.saleManage.detail.ContractMDetail
import com.xuliucar.xuli.xuliucar.newmvp.view.saleManage.detail.OrderDetail
import com.xuliucar.xuli.xuliucar.ui.homePage.allCar.AllCar
import com.xuliucar.xuli.xuliucar.ui.homePage.allCar.CarDetail
import com.xuliucar.xuli.xuliucar.newmvp.view.wait.WaitIndex
import com.xuliucar.xuli.xuliucar.ui.homePage.doItems.DoTrusteeship
import com.xuliucar.xuli.xuliucar.ui.homePage.financeManager.MoneyManager
import com.xuliucar.xuli.xuliucar.ui.homePage.illegalQuery.IllegalIndex
import com.xuliucar.xuli.xuliucar.ui.homePage.infoRegister.*
import com.xuliucar.xuli.xuliucar.newmvp.view.obsolete.ObsoleteItem
import com.xuliucar.xuli.xuliucar.newmvp.view.obsolete.child.ChildObsolete
import com.xuliucar.xuli.xuliucar.newmvp.view.wait.child.ChildWait
import com.xuliucar.xuli.xuliucar.ui.me.ChangePwd
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.anim.DefaultNoAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


@SuppressLint("Registered")
class AppActivity : BaseActivity<MainContract.Presenter>() {

    override fun layViewId(): Int {
        return R.layout.activity_view
    }


    override fun addViewId(): Int {
        return R.id.frameLayout
    }

    override fun initView() {
        StatusBarUtil.transparencyBar(this)
        val flag = intent.getIntExtra("flag", 0)
        loadRootFragment(addViewId(), if (flag == 110) LoginFragment() else MainFragment(), false, false)
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return DefaultNoAnimator()
    }


    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: AppEvent) {
        when (event.flag) {
            f_main -> loadRootFragment(addViewId(), MainFragment(), false, false)
            f_login -> start(LoginFragment())
            f_reg -> start(RegFragment())
            f_allCar -> {
                val c = AllCar()
                c.arguments = event.bundle
                start(c)
            }
            f_informationreg -> start(InformationReg())
            f_illegalindex -> start(IllegalIndex())
            f_doitemsindex -> start(WaitIndex())
            f_doTrusteeship -> start(DoTrusteeship())
            f_carReg -> start(CarReg())
            f_carOwnerReg -> start(CarOwnerReg())
            f_driverReg -> start(DriverReg())
            f_peopleReg -> start(PeopleReg())
            f_companyDoc -> start(CompanyDoc())
            f_moneyManager -> start(MoneyManager())
            f_doPerpetuae -> start(ChildWait())
            f_carDetail -> {
                val c = CarDetail()
                c.arguments = event.bundle
                start(c)
            }
            f_passItemsIndex -> start(ObsoleteItem())
            f_saleManager -> start(SaleManager())
            f_orderDetail -> {
                val c = OrderDetail()
                c.arguments = event.bundle
                start(c)
            }

            f_contractMDetail -> {
                val c = ContractMDetail()
                c.arguments = event.bundle
                start(c)
            }

            f_messageManageIndex -> start(MessageManageIndex())
            f_customPeople -> {
                val c = CustomPeople()
                c.arguments = event.bundle
                startForResult(c, CODE)
            }
            f_obsoleteTrusteeship -> {
                val c = ChildObsolete()
                c.arguments = event.bundle
                start(c)
            }
            f_childWait -> {
                val c = ChildWait()
                c.arguments = event.bundle
                start(c)
            }
            f_feedBack -> start(FeedBack())
            f_changePwd -> start(ChangePwd())
            f_informationCenter -> start(InformationCenter())
            f_notice -> start(Notice())
            f_peopleList -> {
                val c = PeopleList()
                c.arguments = event.bundle
                start(c)
            }
            f_peopleDetail -> {
                val c = PeopleDetail()
                c.arguments = event.bundle
                start(c)
            }
            f_showBigImage -> {
                val c = ShowBigImage()
                c.arguments = event.bundle
                start(c)
            }
        }
    }

    override fun onBackPressedSupport() {
        when (topFragment) {
            is MainFragment -> finish()
            is LoginFragment -> finish()
            else -> {
                pop()
            }
        }
    }
}