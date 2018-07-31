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
//import com.xuliucar.xuli.xuliucar.bean.ContactsBean;
//import com.xuliucar.xuli.xuliucar.bean.DriverDetailBean;
//import com.xuliucar.xuli.xuliucar.config.UrlConfig;
//import com.xuliucar.xuli.xuliucar.dataHandlers.ShowImageDialog;
//import com.xuliucar.xuli.xuliucar.mvp.presenter.DriverDetailPresenter;
//import com.xuliucar.xuli.xuliucar.mvp.view.DriverDetailView;
//import com.xuliucar.xuli.xuliucar.ui.cutPic;
//import com.xuliucar.xuli.xuliucar.utils.ToastUtil;
//
//
//public class DriverDetail extends BaseActivity implements View.OnClickListener, DriverDetailView {
//
//    private TextView driver_name, driver_sex, driver_idcart, driever_driverlicense, driver_drivercert, driver_drivercertedate, driver_cartype,
//            driver_driverlicense_sdate, driver_driverlicense_edate, driver_drivercerttype, driver_drivercertauthor, driver_idaddr, driver_idnowaddr,
//            driver_phone, driver_mark, driver_phone2;
//    private ImageView driver_idCart_z_img, driver_idCart_f_img, driver_Temp_img, driver_Qualification_certificate_img, driver_Driver_license_img, driver_c_Driver_license_img;
//    private ImageView driver_Qualification_certificate_b_img;
//    private ImageView d_Driver_safety_undertaking_img;
//    private ImageView d_driver_temp_hotel_F_img;
//    private ImageView d_driver_c_Driver_license_F_img;
//    private String did;
//    private static final int GALLERY_REQUEST_CODE = 2;//相册
//    private String imgUrl;
//    private String fid;
//    private int width;
//    private int height;
//    private int screenHeight;
//    private int tag;
//    private DriverDetailBean.DriverDetailDataBean driverDetailDataBean;
//    private DriverDetailPresenter mPresenter;
//    private Toolbar mDriverDetail_toolbar;
//    private RelativeLayout mDriver_idCart_z;
//    private RelativeLayout mDriver_idCart_f;
//    private RelativeLayout mDriver_temp;
//    private RelativeLayout mDriver_qualification_certificate;
//    private RelativeLayout mDriver_driver_license;
//    private RelativeLayout mDriver_c_driver_license;
//    private RelativeLayout mDriver_qualification_certificate_b;
//    private RelativeLayout mD_driver_safety_undertaking;
//    private RelativeLayout mD_driver_temp_hotel_f;
//    private RelativeLayout mD_driver_c_driver_license_f;
//
//    @Override
//    protected void initContentView(Bundle savedInstanceState) {
//        setContentView(R.layout.driver_detail);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mPresenter.unSubcrible();
//    }
//
//    protected void initView() {
//        mDriverDetail_toolbar = getViewByID(R.id.driverDetail_toobar);
//        driver_name = getViewByID(R.id.driver_name);
//        driver_sex = getViewByID(R.id.driver_sex);
//        driver_idcart = getViewByID(R.id.driver_idcart);
//        driever_driverlicense = getViewByID(R.id.driever_driverlicense);
//        driver_drivercert = getViewByID(R.id.driver_drivercert);
//        driver_drivercertedate = getViewByID(R.id.driver_drivercertedate);
//        driver_cartype = getViewByID(R.id.driver_cartype);
//        driver_driverlicense_sdate = getViewByID(R.id.driver_driverlicense_sdate);
//        driver_driverlicense_edate = getViewByID(R.id.driver_driverlicense_edate);
//        driver_drivercerttype = getViewByID(R.id.driver_drivercerttype);
//        driver_drivercertauthor = getViewByID(R.id.driver_drivercertauthor);
//        driver_idaddr = getViewByID(R.id.driver_idaddr);
//        driver_idnowaddr = getViewByID(R.id.driver_idnowaddr);
//        driver_phone = getViewByID(R.id.driver_phone);
//        driver_mark = getViewByID(R.id.driver_mark);
//        driver_phone2 = getViewByID(R.id.driver_phone2);
//        mDriver_idCart_z = (RelativeLayout) findViewById(R.id.driver_idCart_z);
//        mDriver_idCart_f = (RelativeLayout) findViewById(R.id.driver_idCart_f);
//        mDriver_temp = (RelativeLayout) findViewById(R.id.driver_Temp);
//        mDriver_qualification_certificate = (RelativeLayout) findViewById(R.id.driver_Qualification_certificate);
//        mDriver_driver_license = (RelativeLayout) findViewById(R.id.driver_Driver_license);
//        mDriver_c_driver_license = (RelativeLayout) findViewById(R.id.driver_c_Driver_license);
//        mDriver_qualification_certificate_b = (RelativeLayout) findViewById(R.id.driver_Qualification_certificate_b);
//        driver_Qualification_certificate_b_img = (ImageView) findViewById(R.id.driver_Qualification_certificate_b_img);
//        mD_driver_safety_undertaking = (RelativeLayout) findViewById(R.id.d_Driver_safety_undertaking);
//        driver_idCart_z_img = (ImageView) findViewById(R.id.driver_idCart_z_img);
//        driver_idCart_f_img = (ImageView) findViewById(R.id.driver_idCart_f_img);
//        driver_Temp_img = (ImageView) findViewById(R.id.driver_Temp_img);
//        driver_Qualification_certificate_img = (ImageView) findViewById(R.id.driver_Qualification_certificate_img);
//        driver_Driver_license_img = (ImageView) findViewById(R.id.driver_Driver_license_img);
//        driver_c_Driver_license_img = (ImageView) findViewById(R.id.driver_c_Driver_license_img);
//        d_Driver_safety_undertaking_img = (ImageView) findViewById(R.id.d_Driver_safety_undertaking_img);
//        mD_driver_temp_hotel_f = (RelativeLayout) findViewById(R.id.d_driver_temp_hotel_F);
//        d_driver_temp_hotel_F_img = (ImageView) findViewById(R.id.d_driver_temp_hotel_F_img);
//        mD_driver_c_driver_license_f = (RelativeLayout) findViewById(R.id.d_driver_c_Driver_license_F);
//        d_driver_c_Driver_license_F_img = (ImageView) findViewById(R.id.d_driver_c_Driver_license_F_img);
//        mDriverDetail_toolbar.setNavigationIcon(R.drawable.back);
//        //定义DisplayMetrics 对象;
//        DisplayMetrics dm = new DisplayMetrics();
//        //取得窗口属性
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        //窗口的宽度
//        int screenWidth = dm.widthPixels;
//        //窗口高度
//        screenHeight = dm.heightPixels;
//        width = (int) (screenWidth * 0.8);
//        height = (int) (screenHeight * 0.78);
//    }
//
//    @Override
//    protected void setListener() {
//        super.setListener();
//        mDriverDetail_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//        mDriver_idCart_z.setOnClickListener(this);
//        mDriver_idCart_f.setOnClickListener(this);
//        mDriver_temp.setOnClickListener(this);
//        mDriver_qualification_certificate.setOnClickListener(this);
//        mDriver_driver_license.setOnClickListener(this);
//        mDriver_c_driver_license.setOnClickListener(this);
//        mDriver_qualification_certificate_b.setOnClickListener(this);
//        mD_driver_safety_undertaking.setOnClickListener(this);
//        mD_driver_temp_hotel_f.setOnClickListener(this);
//        mD_driver_c_driver_license_f.setOnClickListener(this);
//    }
//
//    @Override
//    protected void initLogic() {
//        super.initLogic();
//        initData();
//        mPresenter = new DriverDetailPresenter(this);
//        mPresenter.getData(App.loginid, did, App.compid);
//    }
//
//    private void initData() {
//        ContactsBean contactsBean = (ContactsBean) getIntent().getSerializableExtra("contactsBean");
//        did = contactsBean.getId();
//    }
//
//    @Override
//    public void getData(DriverDetailBean bean) {
//        driverDetailDataBean = bean.getData();
//        fid = driverDetailDataBean.getFid();
//        driver_name.setText(driverDetailDataBean.getDrivername());
//        driver_sex.setText(driverDetailDataBean.getSex());
//        driver_idcart.setText(driverDetailDataBean.getDriverid());
//        driever_driverlicense.setText(driverDetailDataBean.getDriverlicense());
//        driver_drivercert.setText(driverDetailDataBean.getDrivercert());
//        driver_drivercertedate.setText(driverDetailDataBean.getDrivercertedate());
//        driver_cartype.setText(driverDetailDataBean.getCartype());
//        driver_driverlicense_sdate.setText(driverDetailDataBean.getDriverlicense_sdate());
//        driver_driverlicense_edate.setText(driverDetailDataBean.getDriverlicense_edate());
//        driver_drivercerttype.setText(driverDetailDataBean.getDrivercerttype());
//        driver_drivercertauthor.setText(driverDetailDataBean.getDrivercertauthor());
//        driver_idaddr.setText(driverDetailDataBean.getIdaddr());
//        driver_idnowaddr.setText(driverDetailDataBean.getIdnowaddr());
//        driver_phone.setText(driverDetailDataBean.getPhone());
//        driver_phone2.setText(driverDetailDataBean.getPhone2());
//        driver_mark.setText(driverDetailDataBean.getMark());
//
//        if (!driverDetailDataBean.getP1_thumb().isEmpty()) {
//            App.imageLoader.displayImage(driverDetailDataBean.getP1_thumb(), driver_idCart_z_img, App.RoundeOptions);
//        }
//        if (!driverDetailDataBean.getP2_thumb().isEmpty()) {
//            App.imageLoader.displayImage(driverDetailDataBean.getP2_thumb(), driver_idCart_f_img, App.RoundeOptions);
//        }
//        if (!driverDetailDataBean.getP3_thumb().isEmpty()) {
//            App.imageLoader.displayImage(driverDetailDataBean.getP3_thumb(), driver_Temp_img, App.RoundeOptions);
//        }
//        if (!driverDetailDataBean.getP4_thumb().isEmpty()) {
//            App.imageLoader.displayImage(driverDetailDataBean.getP4_thumb(), driver_Qualification_certificate_img, App.RoundeOptions);
//        }
//        if (!driverDetailDataBean.getP5_thumb().isEmpty()) {
//            App.imageLoader.displayImage(driverDetailDataBean.getP5_thumb(), driver_Driver_license_img, App.RoundeOptions);
//        }
//        if (!driverDetailDataBean.getP6_thumb().isEmpty()) {
//            App.imageLoader.displayImage(driverDetailDataBean.getP6_thumb(), driver_c_Driver_license_img, App.RoundeOptions);
//        }
//        if (!driverDetailDataBean.getP7_thumb().isEmpty()) {
//            App.imageLoader.displayImage(driverDetailDataBean.getP7_thumb(), d_driver_temp_hotel_F_img, App.RoundeOptions);
//        }
//        if (!driverDetailDataBean.getP8_thumb().isEmpty()) {
//            App.imageLoader.displayImage(driverDetailDataBean.getP8_thumb(), driver_Qualification_certificate_b_img, App.RoundeOptions);
//        }
//        if (!driverDetailDataBean.getP9_thumb().isEmpty()) {
//            App.imageLoader.displayImage(driverDetailDataBean.getP9_thumb(), d_driver_c_Driver_license_F_img, App.RoundeOptions);
//        }
//        if (!driverDetailDataBean.getP10_thumb().isEmpty()) {
//            App.imageLoader.displayImage(driverDetailDataBean.getP10_thumb(), d_Driver_safety_undertaking_img, App.RoundeOptions);
//        }
//    }
//
//    @Override
//    public void toastMsg(String msg) {
//        switch (msg) {
//            case "already logout":
//                ToastUtil.showShortToast(DriverDetail.this, msg);
//                mPresenter.toLogin(DriverDetail.this);
//                break;
//            case "未登陆":
//                ToastUtil.showShortToast(DriverDetail.this, msg);
//                mPresenter.toLogin(DriverDetail.this);
//                break;
//            case "已登出,或在其它设备上登陆!":
//                ToastUtil.showShortToast(DriverDetail.this, msg);
//                mPresenter.alreadyLogin(DriverDetail.this);
//                break;
//        }
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == GALLERY_REQUEST_CODE) {
//            if (data != null) {
//                Intent intent = new Intent(getApplicationContext(), cutPic.class);
//                intent.setData(data.getData());
//                intent.putExtra("angle", -90);
//                intent.putExtra("uid", fid);
//                intent.putExtra("url", imgUrl);
//                intent.putExtra("type", "1");
//                startActivityForResult(intent, tag);
//            }
//        }
//        if (data != null) {
//            if (requestCode == 101 && resultCode == 1001) {
//                ShowImageDialog.getInstance().ImageWithRound(driver_idCart_z_img, data);
//            } else if (requestCode == 102 && resultCode == 1001) {
//                ShowImageDialog.getInstance().ImageWithRound(driver_idCart_f_img, data);
//            } else if (requestCode == 103 && resultCode == 1001) {
//                ShowImageDialog.getInstance().ImageWithRound(driver_Temp_img, data);
//            } else if (requestCode == 104 && resultCode == 1001) {
//                ShowImageDialog.getInstance().ImageWithRound(driver_Qualification_certificate_img, data);
//            } else if (requestCode == 105 && resultCode == 1001) {
//                ShowImageDialog.getInstance().ImageWithRound(driver_Driver_license_img, data);
//            } else if (requestCode == 106 && resultCode == 1001) {
//                ShowImageDialog.getInstance().ImageWithRound(driver_c_Driver_license_img, data);
//            } else if (requestCode == 107 && resultCode == 1001) {
//                ShowImageDialog.getInstance().ImageWithRound(d_driver_temp_hotel_F_img, data);
//            } else if (requestCode == 108 && resultCode == 1001) {
//                ShowImageDialog.getInstance().ImageWithRound(driver_Qualification_certificate_b_img, data);
//            } else if (requestCode == 109 && resultCode == 1001) {
//                ShowImageDialog.getInstance().ImageWithRound(d_driver_c_Driver_license_F_img, data);
//            } else if (requestCode == 110 && resultCode == 1001) {
//                ShowImageDialog.getInstance().ImageWithRound(d_Driver_safety_undertaking_img, data);
//            } else if (requestCode == 101 && resultCode == 1002) {
//                ShowImageDialog.getInstance().ImageWithRound(driver_idCart_z_img, data);
//            } else if (requestCode == 102 && resultCode == 1002) {
//                ShowImageDialog.getInstance().ImageWithRound(driver_idCart_f_img, data);
//            } else if (requestCode == 103 && resultCode == 1002) {
//                ShowImageDialog.getInstance().ImageWithRound(driver_Temp_img, data);
//            } else if (requestCode == 104 && resultCode == 1002) {
//                ShowImageDialog.getInstance().ImageWithRound(driver_Qualification_certificate_img, data);
//            } else if (requestCode == 105 && resultCode == 1002) {
//                ShowImageDialog.getInstance().ImageWithRound(driver_Driver_license_img, data);
//            } else if (requestCode == 106 && resultCode == 1002) {
//                ShowImageDialog.getInstance().ImageWithRound(driver_c_Driver_license_img, data);
//            } else if (requestCode == 107 && resultCode == 1002) {
//                ShowImageDialog.getInstance().ImageWithRound(d_driver_temp_hotel_F_img, data);
//            } else if (requestCode == 108 && resultCode == 1002) {
//                ShowImageDialog.getInstance().ImageWithRound(driver_Qualification_certificate_b_img, data);
//            } else if (requestCode == 109 && resultCode == 1002) {
//                ShowImageDialog.getInstance().ImageWithRound(d_driver_c_Driver_license_F_img, data);
//            } else if (requestCode == 110 && resultCode == 1002) {
//                ShowImageDialog.getInstance().ImageWithRound(d_Driver_safety_undertaking_img, data);
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
//        if (i == R.id.driver_idCart_z) {
//            tag = 101;
//            imgUrl = ShowImageDialog.getInstance().showDialog(DriverDetail.this, driverDetailDataBean.getP1(), UrlConfig.driverid1_url, type, fid, angle, width, height, tag);
//
//        } else if (i == R.id.driver_idCart_f) {
//            tag = 102;
//            imgUrl = ShowImageDialog.getInstance().showDialog(DriverDetail.this, driverDetailDataBean.getP2(), UrlConfig.driverid2_url, type, fid, angle, width, height, tag);
//
//        } else if (i == R.id.driver_Temp) {
//            tag = 103;
//            imgUrl = ShowImageDialog.getInstance().showDialog(DriverDetail.this, driverDetailDataBean.getP3(), UrlConfig.drivertmpid_url, type, fid, angle, width, height, tag);
//
//        } else if (i == R.id.driver_Qualification_certificate) {
//            tag = 104;
//            imgUrl = ShowImageDialog.getInstance().showDialog(DriverDetail.this, driverDetailDataBean.getP4(), UrlConfig.drivercert_url, type, fid, angle, width, height, tag);
//
//        } else if (i == R.id.driver_Driver_license) {
//            tag = 105;
//            imgUrl = ShowImageDialog.getInstance().showDialog(DriverDetail.this, driverDetailDataBean.getP5(), UrlConfig.driverlic1_url, type, fid, angle, width, height, tag);
//
//        } else if (i == R.id.driver_c_Driver_license) {
//            tag = 106;
//            imgUrl = ShowImageDialog.getInstance().showDialog(DriverDetail.this, driverDetailDataBean.getP6(), UrlConfig.driverlic3_url, type, fid, angle, width, height, tag);
//
//        } else if (i == R.id.d_driver_temp_hotel_F) {
//            tag = 107;
//            height = (int) (screenHeight * 0.78);
//            imgUrl = ShowImageDialog.getInstance().showDialog(DriverDetail.this, driverDetailDataBean.getP7(), UrlConfig.drivertmpidb_url, type, fid, angle, width, height, tag);
//
//        } else if (i == R.id.driver_Qualification_certificate_b) {
//            tag = 108;
//            height = (int) (screenHeight * 0.78);
//            imgUrl = ShowImageDialog.getInstance().showDialog(DriverDetail.this, driverDetailDataBean.getP8(), UrlConfig.drivercertb_url, type, fid, angle, width, height, tag);
//
//        } else if (i == R.id.d_driver_c_Driver_license_F) {
//            tag = 109;
//            height = (int) (screenHeight * 0.73);
//            imgUrl = ShowImageDialog.getInstance().showDialog(DriverDetail.this, driverDetailDataBean.getP9(), UrlConfig.driverlic4_url, type, fid, angle, width, height, tag);
//
//        } else if (i == R.id.d_Driver_safety_undertaking) {
//            tag = 110;
//            height = (int) (screenHeight * 0.78);
//            imgUrl = ShowImageDialog.getInstance().showDialog(DriverDetail.this, driverDetailDataBean.getP10(), UrlConfig.driversafe_url, type, fid, angle, width, height, tag);
//
//        }
//    }
//
//}
