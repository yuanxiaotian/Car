package com.xuliucar.xuli.xuliucar.ui.homePage;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cangmaomao.lib.contacturi.GlideApp;
import com.google.gson.Gson;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.base.BaseActivity;
import com.xuliucar.xuli.xuliucar.bean.HomePageBean;
import com.xuliucar.xuli.xuliucar.config.UrlConfig;
import com.xuliucar.xuli.xuliucar.dataHandlers.ShowImageDialog;
import com.xuliucar.xuli.xuliucar.ui.cutPic;
import com.xuliucar.xuli.xuliucar.utils.SavePic;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;
import com.xuliucar.xuli.xuliucar.widget.MatrixImageView;


public class CompanyDetail extends BaseActivity implements View.OnClickListener {

    private TextView companyName_text, user_text, userp_text, compoptype_text, cnum_text, complicnum_text, comptype_text,
            compestdate_text, regcapital_text, tel_text, raddress_text, tpstr_text, tpedate_text, mb_text, address_text, owner_text;
    private ImageView detail_business_license_img, detail_Road_transport_permit_img, detail_Local_tax_img, detail_State_tax_img, detail_Organizational_institution_img;
    private Dialog dialog;
    private static final int GALLERY_REQUEST_CODE = 2;//相册
    private String imgUrl;
    private int width;
    private int height;
    private String uid;
    private int tag;
    private SavePic savePic;
    private HomePageBean mHomePageBean;
    private HomePageBean.DataBean mDataBean;
    private RelativeLayout mDetail_business_license;
    private RelativeLayout mDetail_organizational_institution;
    private RelativeLayout mDetail_local_tax;
    private RelativeLayout mDetail_state_tax;
    private RelativeLayout mDetail_road_transport_permit;
    private Toolbar mCompanyDetail_toolbar;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.company_detail);
    }

    private void setData() {
        mDataBean = mHomePageBean.getData();
        companyName_text.setText(mDataBean.getCompname());
        user_text.setText(mDataBean.getUser());
        if (!TextUtils.isEmpty(mDataBean.getUserp())) {
            switch (mDataBean.getUserp()) {
                case "10":
                    userp_text.setText("超级管理员");
                    break;
                case "3":
                    userp_text.setText("销售人员");
                    break;
                case "2":
                    userp_text.setText("非管理员");
                    break;
                case "1":
                    userp_text.setText("管理员");
                    break;
            }
        } else {
            userp_text.setText("");
        }

        compoptype_text.setText(mDataBean.getCompoptype());
        cnum_text.setText(mDataBean.getCnum());
        complicnum_text.setText(mDataBean.getComplicnum());
        comptype_text.setText(mDataBean.getComptype());
        compestdate_text.setText(mDataBean.getCompestdate());
        address_text.setText(mDataBean.getAddress());
        owner_text.setText(mDataBean.getOwner());
        regcapital_text.setText(mDataBean.getRegcapital());
        tel_text.setText(mDataBean.getTel());
        raddress_text.setText(mDataBean.getRaddress());
        tpstr_text.setText(mDataBean.getTpstr());
        tpedate_text.setText(mDataBean.getTpedate());
        mb_text.setText(mDataBean.getMb());
        if (!mDataBean.getP1_thumb().isEmpty()) {
            GlideApp.with(this).load(mDataBean.getP1_thumb()).into(detail_business_license_img);
        }
        if (!mDataBean.getP2_thumb().isEmpty()) {
            GlideApp.with(this).load(mDataBean.getP2_thumb()).into(detail_Organizational_institution_img);
        }
        if (!mDataBean.getP3_thumb().isEmpty()) {

            GlideApp.with(this).load(mDataBean.getP3_thumb()).into(detail_Local_tax_img);

        }
        if (!mDataBean.getP4_thumb().isEmpty()) {

            GlideApp.with(this).load(mDataBean.getP4_thumb()).into(detail_State_tax_img);

        }
        if (!mDataBean.getP5_thumb().isEmpty()) {
            GlideApp.with(this).load(mDataBean.getP5_thumb()).into(detail_Road_transport_permit_img);

        }

    }

    private void getIntentData() {
        Intent intent = getIntent();
        String homePageData = intent.getStringExtra("homePageData");
        Gson gson = new Gson();
        mHomePageBean = gson.fromJson(homePageData, HomePageBean.class);
    }

    protected void initView() {
        companyName_text = getViewByID(R.id.companyName_text);
        user_text = getViewByID(R.id.user_text);
        userp_text = getViewByID(R.id.userp_text);
        compoptype_text = getViewByID(R.id.compoptype_text);
        cnum_text = getViewByID(R.id.cnum_text);
        complicnum_text = getViewByID(R.id.complicnum_text);
        comptype_text = getViewByID(R.id.comptype_text);
        compestdate_text = getViewByID(R.id.compestdate_text);
        address_text = getViewByID(R.id.address_text);
        owner_text = getViewByID(R.id.owner_text);
        regcapital_text = getViewByID(R.id.regcapital_text);
        tel_text = getViewByID(R.id.tel_text);
        raddress_text = getViewByID(R.id.raddress_text);
        tpstr_text = getViewByID(R.id.tpstr_text);
        tpedate_text = getViewByID(R.id.tpedate_text);
        mb_text = getViewByID(R.id.mb_text);

        mDetail_business_license = getViewByID(R.id.detail_business_license);
        mDetail_organizational_institution = getViewByID(R.id.detail_Organizational_institution);
        mDetail_local_tax = getViewByID(R.id.detail_Local_tax);
        mDetail_state_tax = getViewByID(R.id.detail_State_tax);
        mDetail_road_transport_permit = getViewByID(R.id.detail_Road_transport_permit);
        LinearLayout layout1 = getViewByID(R.id.layout1);
        LinearLayout layout2 = getViewByID(R.id.layout2);
        detail_business_license_img = getViewByID(R.id.detail_business_license_img);
        detail_Organizational_institution_img = getViewByID(R.id.detail_Organizational_institution_img);
        detail_Local_tax_img = getViewByID(R.id.detail_Local_tax_img);
        detail_State_tax_img = getViewByID(R.id.detail_State_tax_img);
        detail_Road_transport_permit_img = getViewByID(R.id.detail_Road_transport_permit_img);
        mCompanyDetail_toolbar = getViewByID(R.id.companyDetail_toolbar);

        mCompanyDetail_toolbar.setNavigationIcon(R.drawable.back);


        if (App.ctype.equals("1")) {
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.GONE);
        }

        int num = 1000 + App.compid;
        String strNum = String.valueOf(num);
        uid = "CO-" + strNum + "-" + "1";
    }

    @Override
    protected void setListener() {
        super.setListener();
        mCompanyDetail_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mDetail_business_license.setOnClickListener(this);
        mDetail_organizational_institution.setOnClickListener(this);
        mDetail_local_tax.setOnClickListener(this);
        mDetail_state_tax.setOnClickListener(this);
        mDetail_road_transport_permit.setOnClickListener(this);
    }

    @Override
    protected void initLogic() {
        super.initLogic();
        getIntentData();
        setData();
        //定义DisplayMetrics 对象;
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        int screenWidth = dm.widthPixels;
        //窗口高度
        int screenHeight = dm.heightPixels;
        width = (int) (screenWidth * 0.8);
        height = (int) (screenHeight * 0.75);
        savePic = new SavePic();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                Intent intent = new Intent(getApplicationContext(), cutPic.class);
                intent.setData(data.getData());
                intent.putExtra("angle", -90);
                intent.putExtra("uid", uid);
                intent.putExtra("url", imgUrl);
                intent.putExtra("type", "1");
                startActivityForResult(intent, tag);
            }
        }
        if (data != null) {
            if (requestCode == 101 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(detail_Organizational_institution_img, data);
            } else if (requestCode == 102 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(detail_Local_tax_img, data);
            } else if (requestCode == 103 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(detail_State_tax_img, data);
            } else if (requestCode == 104 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(detail_Road_transport_permit_img, data);
            } else if (requestCode == 101 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(detail_Organizational_institution_img, data);
            } else if (requestCode == 102 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(detail_Local_tax_img, data);
            } else if (requestCode == 103 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(detail_State_tax_img, data);
            } else if (requestCode == 104 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(detail_Road_transport_permit_img, data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showOriginalPic(final String url) {

        dialog = new Dialog(CompanyDetail.this, R.style.dialog);
        View view = View.inflate(CompanyDetail.this, R.layout.alert_img, null);
        dialog.setContentView(view);
        dialog.show();
        ImageView preImage = (ImageView) view.findViewById(R.id.preImage);
        MatrixImageView img_detail = (MatrixImageView) view.findViewById(R.id.img_detail);
        LinearLayout pop_layout = (LinearLayout) view.findViewById(R.id.pop_layout);
        pop_layout.setVisibility(View.GONE);
        if (url.isEmpty()) {
            preImage.setImageResource(R.drawable.hang);
            preImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        } else {
            preImage.setVisibility(View.GONE);
            img_detail.setVisibility(View.VISIBLE);
            App.imageLoader.displayImage(url, img_detail, App.options);
        }

        img_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        img_detail.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                savePic.getDownload(url);
                ToastUtil.showShortToast(getApplicationContext(), "保存成功!");
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        final String type = "1";
        final int angle = -90;
        int i = view.getId();
        if (i == R.id.detail_business_license) {
            showOriginalPic(mDataBean.getP1());

        } else if (i == R.id.detail_Organizational_institution) {
            tag = 101;
            imgUrl = ShowImageDialog.getInstance().showDialog(CompanyDetail.this, mDataBean.getP2(), UrlConfig.compstr_url, type, uid, angle, width, height, tag);

        } else if (i == R.id.detail_Local_tax) {
            tag = 102;
            imgUrl = ShowImageDialog.getInstance().showDialog(CompanyDetail.this, mDataBean.getP3(), UrlConfig.ltax_url, type, uid, angle, width, height, tag);

        } else if (i == R.id.detail_State_tax) {
            tag = 103;
            imgUrl = ShowImageDialog.getInstance().showDialog(CompanyDetail.this, mDataBean.getP4(), UrlConfig.ctax_url, type, uid, angle, width, height, tag);

        } else if (i == R.id.detail_Road_transport_permit) {
            tag = 104;
            imgUrl = ShowImageDialog.getInstance().showDialog(CompanyDetail.this, mDataBean.getP5(), UrlConfig.tpermit_url, type, uid, angle, width, height, tag);

        }
    }

}
