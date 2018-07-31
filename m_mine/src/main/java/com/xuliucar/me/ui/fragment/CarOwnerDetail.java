//package com.xuliucar.me.ui.fragment;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
//import android.util.DisplayMetrics;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.xuliucar.xuli.xuliucar.R;
//import com.xuliucar.xuli.xuliucar.base.App;
//import com.xuliucar.xuli.xuliucar.base.BaseActivity;
//import com.xuliucar.xuli.xuliucar.bean.CarOwnerDetailBean;
//import com.xuliucar.xuli.xuliucar.bean.ContactsBean;
//import com.xuliucar.xuli.xuliucar.config.UrlConfig;
//import com.xuliucar.xuli.xuliucar.dataHandlers.ShowImageDialog;
//import com.xuliucar.xuli.xuliucar.mvp.presenter.CarOwnerDetailPresenter;
//import com.xuliucar.xuli.xuliucar.mvp.view.CarOwnerDetailView;
//import com.xuliucar.xuli.xuliucar.ui.cutPic;
//import com.xuliucar.xuli.xuliucar.utils.ToastUtil;
//
//
//public class CarOwnerDetail extends BaseActivity implements View.OnClickListener, CarOwnerDetailView {
//
//    private TextView carOwner_ownername, carOwner_sex, carOwner_phone, carOwner_ownerid, carOwner_idaddr, carOwner_nowaddr, carOwner_phone2;
//    private ImageView carOwner_p1_img, carOwner_p2_img, carOwner_p3_img, carOwner_p4_img;
//    private static final int GALLERY_REQUEST_CODE = 2;//相册
//    private String imgUrl;
//    private String oid;
//    private String uid;
//    private int width;
//    private int height;
//    private int tag;
//    private CarOwnerDetailBean.CarOwnerDetailBeanDataBean carOwnerDetailBeanDataBean;
//    private CarOwnerDetailPresenter mPresenter;
//    private RelativeLayout mCarOwner_p1;
//    private RelativeLayout mCarOwner_p2;
//    private RelativeLayout mCarOwner_p3;
//    private RelativeLayout mCarOwner_p4;
//    private Toolbar mCarOwnerDetail_toolbar;
//
//    @Override
//    protected void initContentView(Bundle savedInstanceState) {
//        setContentView(R.layout.car_owner_detail);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mPresenter.unSubcrible();
//    }
//
//    private void initData() {
//        ContactsBean contactsBean = (ContactsBean) getIntent().getSerializableExtra("contactsBean");
//        oid = contactsBean.getId();
//    }
//
//
//    @SuppressWarnings("unchecked")
//    protected void initView() {
//        mCarOwnerDetail_toolbar = getViewByID(R.id.carOwnerDtail_toobar);
//        carOwner_ownername = getViewByID(R.id.carOwner_ownername);
//        carOwner_sex = getViewByID(R.id.carOwner_sex);
//        carOwner_phone = getViewByID(R.id.carOwner_phone);
//        carOwner_phone2 = getViewByID(R.id.carOwner_phone2);
//        carOwner_ownerid = getViewByID(R.id.carOwner_ownerid);
//        carOwner_idaddr = getViewByID(R.id.carOwner_idaddr);
//        carOwner_nowaddr = getViewByID(R.id.carOwner_nowaddr);
//        mCarOwner_p1 = getViewByID(R.id.carOwner_p1);
//        mCarOwner_p2 = getViewByID(R.id.carOwner_p2);
//        mCarOwner_p3 = getViewByID(R.id.carOwner_p3);
//        mCarOwner_p4 = getViewByID(R.id.carOwner_p4);
//        carOwner_p1_img = getViewByID(R.id.carOwner_p1_img);
//        carOwner_p2_img = getViewByID(R.id.carOwner_p2_img);
//        carOwner_p3_img = getViewByID(R.id.carOwner_p3_img);
//        carOwner_p4_img = getViewByID(R.id.carOwner_p4_img);
//        mCarOwnerDetail_toolbar.setNavigationIcon(R.drawable.back);
//        //定义DisplayMetrics 对象;
//        DisplayMetrics dm = new DisplayMetrics();
//        //取得窗口属性
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        //窗口的宽度
//        int screenWidth = dm.widthPixels;
//        //窗口高度
//        int screenHeight = dm.heightPixels;
//        width = (int) (screenWidth * 0.8);
//        height = (int) (screenHeight * 0.78);
//    }
//
//    @Override
//    protected void setListener() {
//        super.setListener();
//        mCarOwnerDetail_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//        mCarOwner_p1.setOnClickListener(this);
//        mCarOwner_p2.setOnClickListener(this);
//        mCarOwner_p3.setOnClickListener(this);
//        mCarOwner_p4.setOnClickListener(this);
//    }
//
//    @Override
//    protected void initLogic() {
//        super.initLogic();
//        initData();
//        mPresenter = new CarOwnerDetailPresenter(this);
//        mPresenter.getData(App.loginid, oid, App.compid);
//    }
//
//    @Override
//    public void getCarOwnerDetailData(CarOwnerDetailBean bean) {
//        carOwnerDetailBeanDataBean = bean.getData();
//        uid = carOwnerDetailBeanDataBean.getFid();
//        carOwner_ownername.setText(carOwnerDetailBeanDataBean.getOwnername());
//        carOwner_sex.setText(carOwnerDetailBeanDataBean.getSex());
//        carOwner_phone.setText(carOwnerDetailBeanDataBean.getPhone());
//        carOwner_phone2.setText(carOwnerDetailBeanDataBean.getPhone2());
//        carOwner_ownerid.setText(carOwnerDetailBeanDataBean.getOwnerid());
//        carOwner_idaddr.setText(carOwnerDetailBeanDataBean.getIdaddr());
//        carOwner_nowaddr.setText(carOwnerDetailBeanDataBean.getNowaddr());
//        if (!carOwnerDetailBeanDataBean.getP1_thumb().isEmpty()) {
//            App.imageLoader.displayImage(carOwnerDetailBeanDataBean.getP1_thumb(), carOwner_p1_img, App.RoundeOptions);
//        }
//        if (!carOwnerDetailBeanDataBean.getP2_thumb().isEmpty()) {
//            App.imageLoader.displayImage(carOwnerDetailBeanDataBean.getP2_thumb(), carOwner_p2_img, App.RoundeOptions);
//        }
//        if (!carOwnerDetailBeanDataBean.getP3_thumb().isEmpty()) {
//            App.imageLoader.displayImage(carOwnerDetailBeanDataBean.getP3_thumb(), carOwner_p3_img, App.RoundeOptions);
//        }
//        if (!carOwnerDetailBeanDataBean.getP4_thumb().isEmpty()) {
//            App.imageLoader.displayImage(carOwnerDetailBeanDataBean.getP4_thumb(), carOwner_p4_img, App.RoundeOptions);
//        }
//    }
//
//    @Override
//    public void toastMsg(String msg) {
//        switch (msg) {
//            case "already logout":
//                ToastUtil.showShortToast(CarOwnerDetail.this, msg);
//                mPresenter.toLogin(CarOwnerDetail.this);
//                break;
//            case "未登陆":
//                ToastUtil.showShortToast(CarOwnerDetail.this, msg);
//                mPresenter.toLogin(CarOwnerDetail.this);
//                break;
//            case "已登出,或在其它设备上登陆!":
//                ToastUtil.showShortToast(CarOwnerDetail.this, msg);
//                mPresenter.alreadyLogin(CarOwnerDetail.this);
//                break;
//        }
//
//    }
//
//    @Override
//    public void showError(String msg) {
//
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == GALLERY_REQUEST_CODE) {
//            if (data != null) {
//                Intent intent = new Intent(getApplicationContext(), cutPic.class);
//                intent.setData(data.getData());
//                intent.putExtra("angle", -90);
//                intent.putExtra("uid", uid);
//                intent.putExtra("url", imgUrl);
//                intent.putExtra("type", "1");
//                startActivityForResult(intent, tag);
//            }
//
//        }
//        if (data != null) {
//            if (requestCode == 101 && resultCode == 1001) {
//                ShowImageDialog.getInstance().ImageWithRound(carOwner_p1_img, data);
//            } else if (requestCode == 102 && resultCode == 1001) {
//                ShowImageDialog.getInstance().ImageWithRound(carOwner_p2_img, data);
//            } else if (requestCode == 103 && resultCode == 1001) {
//                ShowImageDialog.getInstance().ImageWithRound(carOwner_p3_img, data);
//            } else if (requestCode == 104 && resultCode == 1001) {
//                ShowImageDialog.getInstance().ImageWithRound(carOwner_p4_img, data);
//            } else if (requestCode == 101 && resultCode == 1002) {
//                ShowImageDialog.getInstance().ImageWithRound(carOwner_p1_img, data);
//            } else if (requestCode == 102 && resultCode == 1002) {
//                ShowImageDialog.getInstance().ImageWithRound(carOwner_p2_img, data);
//            } else if (requestCode == 103 && resultCode == 1002) {
//                ShowImageDialog.getInstance().ImageWithRound(carOwner_p3_img, data);
//            } else if (requestCode == 104 && resultCode == 1002) {
//                ShowImageDialog.getInstance().ImageWithRound(carOwner_p4_img, data);
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    @Override
//    public void onClick(View view) {
//        final String type = "1";
//        final int angle = -90;
//        int i = view.getId();
//        if (i == R.id.carOwner_p1) {
//            tag = 101;
//            imgUrl = ShowImageDialog.getInstance().showDialog(CarOwnerDetail.this, carOwnerDetailBeanDataBean.getP1(), UrlConfig.ownerid1_url, type, uid, angle, width, height, tag);
//
//        } else if (i == R.id.carOwner_p2) {
//            tag = 102;
//            imgUrl = ShowImageDialog.getInstance().showDialog(CarOwnerDetail.this, carOwnerDetailBeanDataBean.getP2(), UrlConfig.ownerid2_url, type, uid, angle, width, height, tag);
//
//        } else if (i == R.id.carOwner_p3) {
//            tag = 103;
//            imgUrl = ShowImageDialog.getInstance().showDialog(CarOwnerDetail.this, carOwnerDetailBeanDataBean.getP3(), UrlConfig.ownertmpid_url, type, uid, angle, width, height, tag);
//
//        } else if (i == R.id.carOwner_p4) {
//            tag = 104;
//            imgUrl = ShowImageDialog.getInstance().showDialog(CarOwnerDetail.this, carOwnerDetailBeanDataBean.getP4(), UrlConfig.ownertmpidbb_url, type, uid, angle, width, height, tag);
//
//        }
//    }
//
//
//}
