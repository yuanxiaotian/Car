package com.xuliucar.xuli.xuliucar.ui.homePage.infoRegister.carReg;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class CarOwnerPhoto extends BaseFragment implements View.OnClickListener{

    private ImageView idCartOwnerPhoto_z_img,idCarOwnerPhoto_f_img,TempCarOwnerPhoto_z_img,TempCarOwnerPhoto_F_img;

    private static final int GALLERY_REQUEST_CODE = 2;//相册
    private String imgUrl;
    private int tag;
    private int width;
    private int height;
    private Dialog dialog;
    private RelativeLayout idCartOwnerPhoto_z;
    private RelativeLayout idCarOwnerPhoto_f;
    private RelativeLayout tempCarOwnerPhoto_z;
    private RelativeLayout tempCarOwnerPhoto_f;
    private static final int REQUEST_CAMERA_PERMISSION = 0x01;
    @Override
    protected void setContentView() {
        setContentView(R.layout.car_owner_photo);
    }

    @Override
    protected void initView() {
        idCartOwnerPhoto_z = getViewById(R.id.idCartOwnerPhoto_z);
        idCarOwnerPhoto_f = getViewById(R.id.idCarOwnerPhoto_f);
        tempCarOwnerPhoto_z = getViewById(R.id.TempCarOwnerPhoto_z);
        tempCarOwnerPhoto_f = getViewById(R.id.TempCarOwnerPhoto_F);
        idCartOwnerPhoto_z_img = getViewById(R.id.idCartOwnerPhoto_z_img);
        idCarOwnerPhoto_f_img = getViewById(R.id.idCarOwnerPhoto_f_img);
        TempCarOwnerPhoto_z_img =getViewById(R.id.TempCarOwnerPhoto_z_img);
        TempCarOwnerPhoto_F_img = getViewById(R.id.TempCarOwnerPhoto_F_img);

        //定义DisplayMetrics 对象;
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        int screenWidth = dm.widthPixels;
        //窗口高度
        int screenHeight = dm.heightPixels;
        width = (int) (screenWidth*0.8);
        height = (int) (screenHeight*0.78);
    }

    @Override
    protected void setListener() {
        idCartOwnerPhoto_z.setOnClickListener(this);
        idCarOwnerPhoto_f.setOnClickListener(this);
        tempCarOwnerPhoto_z.setOnClickListener(this);
        tempCarOwnerPhoto_f.setOnClickListener(this);
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
                 ShowImageDialog.getInstance().ImageWithRound(idCartOwnerPhoto_z_img,data,dialog);
             }else if(requestCode == 102 && resultCode == 1001){
                 ShowImageDialog.getInstance().ImageWithRound(idCarOwnerPhoto_f_img,data,dialog);
             }else if(requestCode == 103 && resultCode == 1001){
                 ShowImageDialog.getInstance().ImageWithRound(TempCarOwnerPhoto_z_img,data,dialog);
             }else if(requestCode == 104 && resultCode == 1001){
                 ShowImageDialog.getInstance().ImageWithRound(TempCarOwnerPhoto_F_img,data,dialog);
             }else if(requestCode == 101 && resultCode == 1002){
                 ShowImageDialog.getInstance().ImageWithRound(idCartOwnerPhoto_z_img,data,dialog);
             }else if(requestCode == 102 && resultCode == 1002){
                 ShowImageDialog.getInstance().ImageWithRound(idCarOwnerPhoto_f_img,data,dialog);
             }else if(requestCode == 103 && resultCode == 1002){
                 ShowImageDialog.getInstance().ImageWithRound(TempCarOwnerPhoto_z_img,data,dialog);
             }else if(requestCode == 104 && resultCode == 1002){
                 ShowImageDialog.getInstance().ImageWithRound(TempCarOwnerPhoto_F_img,data,dialog);
             }

         }

        super.onActivityResult(requestCode, resultCode, data);
    }
    private void showOriginalPic(final String uploadUrl,int angle){
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
        if (i == R.id.idCartOwnerPhoto_z) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.ownerid1_url, -90);
                tag = 101;
            }

        } else if (i == R.id.idCarOwnerPhoto_f) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.ownerid2_url, -90);
                tag = 102;
            }

        } else if (i == R.id.TempCarOwnerPhoto_z) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.ownertmpid_url, -90);
                tag = 103;
            }

        } else if (i == R.id.TempCarOwnerPhoto_F) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(getActivity(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.ownertmpidbb_url, -90);
                tag = 104;
            }

        }
    }

}
