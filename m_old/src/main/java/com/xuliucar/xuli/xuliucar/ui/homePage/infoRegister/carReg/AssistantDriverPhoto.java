package com.xuliucar.xuli.xuliucar.ui.homePage.infoRegister.carReg;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.base.BaseFragment;
import com.xuliucar.xuli.xuliucar.config.UrlConfig;
import com.xuliucar.xuli.xuliucar.dataHandlers.ShowImageDialog;
import com.xuliucar.xuli.xuliucar.myCamera.RectCameraActivity;
import com.xuliucar.xuli.xuliucar.myCamera2.CameraActivity;
import com.xuliucar.xuli.xuliucar.permission.AfterPermissionGranted;
import com.xuliucar.xuli.xuliucar.permission.PermissionUtils;
import com.xuliucar.xuli.xuliucar.ui.cutPic;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;


public class AssistantDriverPhoto extends BaseFragment implements View.OnClickListener{

    private ImageView d_idcart_z_img,d_idcart_f_img,Temporary_residence_permit_z_img,Temporary_residence_permit_F_img,
            Qualification_certificate_z_img,Qualification_certificate_F_img,d_Driver_license_z_img,d_Copy_driver_license_img,d_Copy_driver_license_F_img,
            driver_safe_permisson_img;

    private static final int GALLERY_REQUEST_CODE = 2;//相册
    private String imgUrl;
    private int tag;
    private int width;
    private int height;
    private Dialog dialog;
    private RelativeLayout d_idcart_z;
    private RelativeLayout d_idcart_f;
    private RelativeLayout temporary_residence_permit_z;
    private RelativeLayout temporary_residence_permit_f;
    private RelativeLayout qualification_certificate_z;
    private RelativeLayout qualification_certificate_f;
    private RelativeLayout d_driver_license_z;
    private RelativeLayout d_copy_driver_license;
    private RelativeLayout d_copy_driver_license_f;
    private RelativeLayout driver_safe_permisson;
    private static final int REQUEST_CAMERA_PERMISSION = 0x01;

    @Override
    protected void setContentView() {
        setContentView(R.layout.driver_photo);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }
    @Override
    protected void initView() {
        d_idcart_z = getViewById(R.id.d_idcart_z);
        d_idcart_f = getViewById(R.id.d_idcart_f);
        temporary_residence_permit_z = getViewById(R.id.Temporary_residence_permit_z);
        temporary_residence_permit_f = getViewById(R.id.Temporary_residence_permit_F);
        qualification_certificate_z = getViewById(R.id.Qualification_certificate_z);
        qualification_certificate_f = getViewById(R.id.Qualification_certificate_F);
        d_driver_license_z = getViewById(R.id.d_Driver_license_z);
        d_copy_driver_license = getViewById(R.id.d_Copy_driver_license);
        d_copy_driver_license_f = getViewById(R.id.d_Copy_driver_license_F);
        driver_safe_permisson = getViewById(R.id.driver_safe_permisson);

        d_idcart_z_img = getViewById(R.id.d_idcart_z_img);
        d_idcart_f_img = getViewById(R.id.d_idcart_f_img);
        Temporary_residence_permit_z_img = getViewById(R.id.Temporary_residence_permit_z_img);
        Temporary_residence_permit_F_img = getViewById(R.id.Temporary_residence_permit_F_img);
        Qualification_certificate_F_img = getViewById(R.id.Qualification_certificate_F_img);
        Qualification_certificate_z_img = getViewById(R.id.Qualification_certificate_z_img);
        d_Driver_license_z_img = getViewById(R.id.d_Driver_license_z_img);
        d_Copy_driver_license_img = getViewById(R.id.d_Copy_driver_license_img);
        d_Copy_driver_license_F_img = getViewById(R.id.d_Copy_driver_license_F_img);
        driver_safe_permisson_img = getViewById(R.id.driver_safe_permisson_img);


        //定义DisplayMetrics 对象;
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        int screenWidth = dm.widthPixels;
        //窗口高度
        int  screenHeight = dm.heightPixels;
        width = (int) (screenWidth*0.8);
        height = (int) (screenHeight*0.78);
    }

    @Override
    protected void setListener() {
        d_idcart_z.setOnClickListener(this);
        d_idcart_f.setOnClickListener(this);
        temporary_residence_permit_z.setOnClickListener(this);
        temporary_residence_permit_f.setOnClickListener(this);
        qualification_certificate_z.setOnClickListener(this);
        qualification_certificate_f.setOnClickListener(this);
        d_driver_license_z.setOnClickListener(this);
        d_copy_driver_license.setOnClickListener(this);
        d_copy_driver_license_f.setOnClickListener(this);
        driver_safe_permisson.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GALLERY_REQUEST_CODE){
            if(data != null){
                int    num = 1000 + App.compid;
                String uid = "NC-"+num+"-"+ App.from;
                Intent intent = new Intent(getActivity(), cutPic.class);
                intent.setData(data.getData());
                intent.putExtra("angle", -90);
                intent.putExtra("uid",uid);
                intent.putExtra("url",imgUrl);
                intent.putExtra("type","1");
                startActivityForResult(intent,tag);
            }

        }
        if(data != null){
            if(requestCode == 101 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(d_idcart_z_img,data,dialog);
            }else if(requestCode == 102 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(d_idcart_f_img,data,dialog);
            }else if(requestCode == 103 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(Temporary_residence_permit_z_img,data,dialog);
            }else if(requestCode == 104 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(Temporary_residence_permit_F_img,data,dialog);
            }else if(requestCode == 105 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(Qualification_certificate_z_img,data,dialog);
            }else if(requestCode == 106 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(Qualification_certificate_F_img,data,dialog);
            }else if(requestCode == 107 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(d_Driver_license_z_img,data,dialog);
            }else if(requestCode == 108 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(d_Copy_driver_license_img,data,dialog);
            }else if(requestCode == 109 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(d_Copy_driver_license_F_img,data,dialog);
            }else if(requestCode == 110 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(driver_safe_permisson_img,data,dialog);
            } else if(requestCode == 101 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(d_idcart_z_img,data,dialog);
            }else if(requestCode == 102 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(d_idcart_f_img,data,dialog);
            }else if(requestCode == 103 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(Temporary_residence_permit_z_img,data,dialog);
            }else if(requestCode == 104 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(Temporary_residence_permit_F_img,data,dialog);
            }else if(requestCode == 105 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(Qualification_certificate_z_img,data,dialog);
            }else if(requestCode == 106 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(Qualification_certificate_F_img,data,dialog);
            }else if(requestCode == 107 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(d_Driver_license_z_img,data,dialog);
            }else if(requestCode == 108 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(d_Copy_driver_license_img,data,dialog);
            }else if(requestCode == 109 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(d_Copy_driver_license_F_img,data,dialog);
            }else if(requestCode == 110 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(driver_safe_permisson_img,data,dialog);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showOriginalPic(final String uploadUrl, final int angle){
        dialog =new Dialog(getActivity(),R.style.dialog);
        final View layout = View.inflate(getActivity(),R.layout.alert_img,null);
        dialog.setContentView(layout);
        dialog.show();
        ImageView preImage = (ImageView) layout.findViewById(R.id.preImage);
        TextView takePhotoBtn = (TextView) layout.findViewById(R.id.takePhotoBtn);
        TextView pickPhotoBtn = (TextView) layout.findViewById(R.id.pickPhotoBtn);
        if(angle == 0){//根据角度来判断是显示横的还是纵的拍照示意图
            preImage.setImageResource(R.drawable.zhong);
        }else if(angle == -90){
            preImage.setImageResource(R.drawable.hang);
        }

        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PermissionUtils.hasPermissions(getActivity(), Manifest.permission.CAMERA)) {
                    openCamera(uploadUrl);
                } else {
                    PermissionUtils.requestPermissions(getActivity(), getString(R.string.rationale_cameras),REQUEST_CAMERA_PERMISSION,Manifest.permission.CAMERA);
                }
            }
        });
        pickPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setType("image/*");
                startActivityForResult(intent1, GALLERY_REQUEST_CODE);
                imgUrl = uploadUrl;
            }
        });
        preImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @AfterPermissionGranted(REQUEST_CAMERA_PERMISSION)
    private void openCamera(String uploadUrl){
        int    num = 1000 + App.compid;
        String uid = "NC-"+num+"-"+ App.from;
        if(App.sdkVsesion >= 21 && App.brand.equals("HONOR")){
            Intent intent1 = new Intent(getActivity(), CameraActivity.class);
            intent1.putExtra("width", width);
            intent1.putExtra("height", height);
            intent1.putExtra("angle", -90);
            intent1.putExtra("uid", uid);
            intent1.putExtra("url",uploadUrl);
            intent1.putExtra("type","1");
            startActivityForResult(intent1,tag);
        }else {

            Intent intent = new Intent(getActivity(), RectCameraActivity.class);
            intent.putExtra("width", width);
            intent.putExtra("height", height);
            intent.putExtra("angle", -90);
            intent.putExtra("uid", uid);
            intent.putExtra("url",uploadUrl);
            intent.putExtra("type","1");
            startActivityForResult(intent,tag);
        }
    }

    @Override
    public void onClick(View views) {
        int i = views.getId();
        if (i == R.id.d_idcart_z) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.driver2id1_url, -90);
                tag = 101;
            }

        } else if (i == R.id.d_idcart_f) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.driver2id2_url, -90);
                tag = 102;
            }

        } else if (i == R.id.Temporary_residence_permit_z) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.driver2tmpid_url, -90);

                tag = 103;
            }

        } else if (i == R.id.Temporary_residence_permit_F) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.driver2tmpidb_url, -90);

                tag = 104;
            }

        } else if (i == R.id.Qualification_certificate_z) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.driver2cert_url, -90);

                tag = 105;
            }

        } else if (i == R.id.Qualification_certificate_F) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.driver2certb_url, -90);

                tag = 106;
            }

        } else if (i == R.id.d_Driver_license_z) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.driver2lic1_url, -90);
                tag = 107;
            }

        } else if (i == R.id.d_Copy_driver_license) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.driver2lic3_url, -90);
                tag = 108;
            }

        } else if (i == R.id.d_Copy_driver_license_F) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.driver2lic4_url, -90);
                tag = 109;
            }

        } else if (i == R.id.driver_safe_permisson) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.driver2safe_url, -90);
                tag = 110;
            }

        }
    }



}
