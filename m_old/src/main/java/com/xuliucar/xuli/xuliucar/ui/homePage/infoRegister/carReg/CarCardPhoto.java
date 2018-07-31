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


public class CarCardPhoto extends BaseFragment implements View.OnClickListener{
    private ImageView Driving_license_z_img,Driving_license_F_img,Copy_driving_license_img,Copy_driving_license_F_img,Motor_vehicle_registration_img,Motor_vehicle_registration_move_img,Motor_vehicle_qualified_img,Purchase_invoice_img,tax_records_img;

    private static final int GALLERY_REQUEST_CODE = 2;//相册
    private String imgUrl;
    private Dialog dialog;
    private int tag;
    private int width;
    private int height;
    private int angle;
    private int screenHeight;
    private RelativeLayout driving_license_z;
    private RelativeLayout driving_license_f;
    private RelativeLayout copy_driving_license;
    private RelativeLayout copy_driving_license_f;
    private RelativeLayout motor_vehicle_registration;
    private RelativeLayout motor_vehicle_registration_move;
    private RelativeLayout motor_vehicle_qualified;
    private RelativeLayout purchase_invoice;
    private RelativeLayout tax_records;
    private static final int REQUEST_CAMERA_PERMISSION = 0x01;

    @Override
    protected void setContentView() {
        setContentView(R.layout.car_card_photo);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    protected void initView() {

        driving_license_z = getViewById(R.id.Driving_license_z);
        driving_license_f = getViewById(R.id.Driving_license_F);
        copy_driving_license = getViewById(R.id.Copy_driving_license);
        copy_driving_license_f = getViewById(R.id.Copy_driving_license_F);
        motor_vehicle_registration = getViewById(R.id.Motor_vehicle_registration);
        motor_vehicle_registration_move = getViewById(R.id.Motor_vehicle_registration_move);
        motor_vehicle_qualified = getViewById(R.id.Motor_vehicle_qualified);
        purchase_invoice = getViewById(R.id.Purchase_invoice);
        tax_records = getViewById(R.id.tax_records);
        Driving_license_z_img = getViewById(R.id.Driving_license_z_img);
        Driving_license_F_img = getViewById(R.id.Driving_license_F_img);
        Copy_driving_license_img = getViewById(R.id.Copy_driving_license_img);
        Copy_driving_license_F_img = getViewById(R.id.Copy_driving_license_F_img);
        Motor_vehicle_registration_img = getViewById(R.id.Motor_vehicle_registration_img);
        Motor_vehicle_registration_move_img = getViewById(R.id.Motor_vehicle_registration_move_img);
        Motor_vehicle_qualified_img = getViewById(R.id.Motor_vehicle_qualified_img);
        Purchase_invoice_img = getViewById(R.id.Purchase_invoice_img);
        tax_records_img = getViewById(R.id.tax_records_img);

        //定义DisplayMetrics 对象;
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        int screenWidth = dm.widthPixels;
        //窗口高度
        screenHeight = dm.heightPixels;
        width = (int) (screenWidth*0.8);
    }

    @Override
    protected void setListener() {
        driving_license_z.setOnClickListener(this);
        driving_license_f.setOnClickListener(this);
        copy_driving_license.setOnClickListener(this);
        copy_driving_license_f.setOnClickListener(this);
        motor_vehicle_registration.setOnClickListener(this);
        motor_vehicle_registration_move.setOnClickListener(this);
        motor_vehicle_qualified.setOnClickListener(this);
        purchase_invoice.setOnClickListener(this);
        tax_records.setOnClickListener(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GALLERY_REQUEST_CODE){
            if(data != null){
                int    num = 1000 + App.compid;
                String uid = "NC-"+num+"-"+ App.from;
                Intent intent = new Intent(getActivity(), cutPic.class);
                intent.setData(data.getData());
                intent.putExtra("angle", angle);
                intent.putExtra("uid",uid);
                intent.putExtra("url",imgUrl);
                intent.putExtra("type","1");
                startActivityForResult(intent,tag);
            }

        }
        if(data != null){
            if(requestCode == 101 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(Driving_license_z_img,data,dialog);
            }else if(requestCode == 102 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(Copy_driving_license_img,data,dialog);
            }else if(requestCode == 103 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(Motor_vehicle_registration_img,data,dialog);
            }else if(requestCode == 104  && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(Purchase_invoice_img,data,dialog);
            }else if(requestCode == 105 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(tax_records_img,data,dialog);
            }else if(requestCode == 106 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(Driving_license_F_img,data,dialog);
            }else if(requestCode == 107 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(Copy_driving_license_F_img,data,dialog);
            }else if(requestCode == 108 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(Motor_vehicle_registration_move_img,data,dialog);
            }else if(requestCode == 109 && resultCode == 1001){
                ShowImageDialog.getInstance().ImageWithRound(Motor_vehicle_qualified_img,data,dialog);
            } else if(requestCode ==101 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(Driving_license_z_img,data,dialog);
            }else if(requestCode == 102 && resultCode ==1002){
                ShowImageDialog.getInstance().ImageWithRound(Copy_driving_license_img,data,dialog);
            }else if(requestCode == 103 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(Motor_vehicle_registration_img,data,dialog);
            }else if(requestCode == 104 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(Purchase_invoice_img,data,dialog);
            }else if(requestCode == 105 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(tax_records_img,data,dialog);
            }else if(requestCode == 106 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(Driving_license_F_img,data,dialog);
            }else if(requestCode == 107 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(Copy_driving_license_F_img,data,dialog);
            }else if(requestCode == 108 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(Motor_vehicle_registration_move_img,data,dialog);
            }else if(requestCode == 109 && resultCode == 1002){
                ShowImageDialog.getInstance().ImageWithRound(Motor_vehicle_qualified_img,data,dialog);
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
            intent1.putExtra("angle", angle);
            intent1.putExtra("uid", uid);
            intent1.putExtra("url",uploadUrl);
            intent1.putExtra("type","1");
            startActivityForResult(intent1,tag);
        }else {

            Intent intent = new Intent(getActivity(), RectCameraActivity.class);
            intent.putExtra("width", width);
            intent.putExtra("height", height);
            intent.putExtra("angle", angle);
            intent.putExtra("uid", uid);
            intent.putExtra("url",uploadUrl);
            intent.putExtra("type","1");
            startActivityForResult(intent,tag);
        }
    }

    @Override
    public void onClick(View views) {
        int i = views.getId();
        if (i == R.id.Driving_license_z) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.ownerregistration1_url, -90);
                height = (int) (screenHeight * 0.73);
                tag = 101;
                angle = -90;
            }

        } else if (i == R.id.Copy_driving_license) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.ownerregistration3_url, -90);
                height = (int) (screenHeight * 0.73);
                tag = 102;
                angle = -90;
            }

        } else if (i == R.id.Motor_vehicle_registration) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.carreg_url, 0);
                height = (int) (screenHeight * 0.8);
                tag = 103;
                angle = 0;
            }

        } else if (i == R.id.Purchase_invoice) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.invoice_url, -90);
                height = (int) (screenHeight * 0.78);
                tag = 104;
                angle = -90;
            }

        } else if (i == R.id.tax_records) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.tax_url, -90);
                height = (int) (screenHeight * 0.78);
                tag = 105;
                angle = -90;
            }

        } else if (i == R.id.Driving_license_F) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.ownerregistration2_url, -90);
                height = (int) (screenHeight * 0.73);
                tag = 106;
                angle = -90;
            }

        } else if (i == R.id.Copy_driving_license_F) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.ownerregistration4_url, -90);
                height = (int) (screenHeight * 0.73);
                tag = 107;
                angle = -90;
            }

        } else if (i == R.id.Motor_vehicle_registration_move) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.carregtransfer_url, 0);
                height = (int) (screenHeight * 0.8);
                tag = 108;
                angle = 0;
            }

        } else if (i == R.id.Motor_vehicle_qualified) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.carcert_url, 0);
                height = (int) (screenHeight * 0.73);
                tag = 109;
                angle = 0;
            }

        }
    }
}
