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
public class OtherPhoto extends BaseFragment implements View.OnClickListener {

    private ImageView car_photo_p_Construction_waste_img;
    private ImageView car_photo_p_Construction_waste_opposite_img;
    private ImageView car_photo_p_img, car_photo_p_img2, car_photo_p_img3, car_photo_p_img4, car_photo_p_img5, car_photo_p_img6,
            car_photo_p_img7, car_photo_p_img8, car_photo_p_img9, car_photo_p_img10, car_photo_p_img11, car_photo_p_img12;

    private static final int GALLERY_REQUEST_CODE = 2;//相册
    private String imgUrl;
    private int tag;
    private int width;
    private int height;
    private Dialog dialog;
    private RelativeLayout mCar_photo_p2;
    private RelativeLayout mCar_photo_p3;
    private RelativeLayout mCar_photo_p4;
    private RelativeLayout mCar_photo_p5;
    private RelativeLayout mCar_photo_p6;
    private RelativeLayout mCar_photo_p7;
    private RelativeLayout mCar_photo_p8;
    private RelativeLayout mCar_photo_p9;
    private RelativeLayout mCar_photo_p10;
    private RelativeLayout mCar_photo_p11;
    private RelativeLayout mCar_photo_p12;
    private RelativeLayout car_photo_construction_waste;
    private RelativeLayout car_photo_construction_waste_opposite;
    private RelativeLayout car_photo_p;
    private static final int REQUEST_CAMERA_PERMISSION = 0x01;

    @Override
    protected void setContentView() {
        setContentView(R.layout.car_photo);
    }

    @Override
    protected void initView() {
        car_photo_construction_waste = getViewById(R.id.car_photo_Construction_waste);
        car_photo_construction_waste_opposite = getViewById(R.id.car_photo_Construction_waste_opposite);
        car_photo_p_Construction_waste_img = getViewById(R.id.car_photo_p_Construction_waste_img);
        car_photo_p_Construction_waste_opposite_img = getViewById(R.id.car_photo_p_Construction_waste_opposite_img);
        car_photo_p = getViewById(R.id.car_photo_p);
        mCar_photo_p2 = getViewById(R.id.car_photo_p2);
        mCar_photo_p3 = getViewById(R.id.car_photo_p3);
        mCar_photo_p4 = getViewById(R.id.car_photo_p4);
        mCar_photo_p5 = getViewById(R.id.car_photo_p5);
        mCar_photo_p6 = getViewById(R.id.car_photo_p6);
        mCar_photo_p7 = getViewById(R.id.car_photo_p7);
        mCar_photo_p8 = getViewById(R.id.car_photo_p8);
        mCar_photo_p9 = getViewById(R.id.car_photo_p9);
        mCar_photo_p10 = getViewById(R.id.car_photo_p10);
        mCar_photo_p11 = getViewById(R.id.car_photo_p11);
        mCar_photo_p12 = getViewById(R.id.car_photo_p12);
        car_photo_p_img = getViewById(R.id.car_photo_p_img);
        car_photo_p_img2 = getViewById(R.id.car_photo_p_img2);
        car_photo_p_img3 = getViewById(R.id.car_photo_p_img3);
        car_photo_p_img4 = getViewById(R.id.car_photo_p_img4);
        car_photo_p_img5 = getViewById(R.id.car_photo_p_img5);
        car_photo_p_img6 = getViewById(R.id.car_photo_p_img6);
        car_photo_p_img7 = getViewById(R.id.car_photo_p_img7);
        car_photo_p_img8 = getViewById(R.id.car_photo_p_img8);
        car_photo_p_img9 = getViewById(R.id.car_photo_p_img9);
        car_photo_p_img10 = getViewById(R.id.car_photo_p_img10);
        car_photo_p_img11 = getViewById(R.id.car_photo_p_img11);
        car_photo_p_img12 = getViewById(R.id.car_photo_p_img12);

        //定义DisplayMetrics 对象;
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        int screenWidth = dm.widthPixels;
        //窗口高度
        int screenHeight = dm.heightPixels;
        width = (int) (screenWidth * 0.8);
        height = (int) (screenHeight * 0.78);
    }

    @Override
    protected void setListener() {
        car_photo_construction_waste.setOnClickListener(this);
        car_photo_construction_waste_opposite.setOnClickListener(this);
        car_photo_p.setOnClickListener(this);
        mCar_photo_p2.setOnClickListener(this);
        mCar_photo_p3.setOnClickListener(this);
        mCar_photo_p4.setOnClickListener(this);
        mCar_photo_p5.setOnClickListener(this);
        mCar_photo_p6.setOnClickListener(this);
        mCar_photo_p7.setOnClickListener(this);
        mCar_photo_p8.setOnClickListener(this);
        mCar_photo_p9.setOnClickListener(this);
        mCar_photo_p10.setOnClickListener(this);
        mCar_photo_p11.setOnClickListener(this);
        mCar_photo_p12.setOnClickListener(this);
    }


    private void showOriginalPic(final String uploadUrl, int angle) {
        dialog = new Dialog(getActivity(), R.style.dialog);
        final View layout = View.inflate(getActivity(), R.layout.alert_img, null);
        dialog.setContentView(layout);
        dialog.show();
        ImageView preImage = (ImageView) layout.findViewById(R.id.preImage);
        TextView takePhotoBtn = (TextView) layout.findViewById(R.id.takePhotoBtn);
        TextView pickPhotoBtn = (TextView) layout.findViewById(R.id.pickPhotoBtn);
        if (angle == 0) {//根据角度来判断是显示横的还是纵的拍照示意图
            preImage.setImageResource(R.drawable.zhong);
        } else if (angle == -90) {
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
        int num = 1000 + App.compid;
        String uid = "NC-" + num + "-" + App.from;
        if (App.sdkVsesion >= 21 && App.brand.equals("HONOR")) {
            Intent intent1 = new Intent(getActivity(), CameraActivity.class);
            intent1.putExtra("width", width);
            intent1.putExtra("height", height);
            intent1.putExtra("angle", -90);
            intent1.putExtra("uid", uid);
            intent1.putExtra("url", uploadUrl);
            intent1.putExtra("type", "1");
            startActivityForResult(intent1, tag);
        } else {

            Intent intent = new Intent(getActivity(), RectCameraActivity.class);
            intent.putExtra("width", width);
            intent.putExtra("height", height);
            intent.putExtra("angle", -90);
            intent.putExtra("uid", uid);
            intent.putExtra("url", uploadUrl);
            intent.putExtra("type", "1");
            startActivityForResult(intent, tag);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                int num = 1000 + App.compid;
                String uid = "NC-" + num + "-" + App.from;
                Intent intent = new Intent(getActivity(), cutPic.class);
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
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img, data, dialog);
                mCar_photo_p2.setVisibility(View.VISIBLE);
            } else if (requestCode == 102 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img2, data, dialog);
                mCar_photo_p3.setVisibility(View.VISIBLE);
            } else if (requestCode == 103 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img3, data, dialog);
                mCar_photo_p4.setVisibility(View.VISIBLE);
            } else if (requestCode == 104 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img4, data, dialog);
                mCar_photo_p5.setVisibility(View.VISIBLE);
            } else if (requestCode == 105 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img5, data, dialog);
                mCar_photo_p6.setVisibility(View.VISIBLE);
            } else if (requestCode == 106 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img6, data, dialog);
                mCar_photo_p7.setVisibility(View.VISIBLE);
            } else if (requestCode == 107 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img7, data, dialog);
                mCar_photo_p8.setVisibility(View.VISIBLE);
            } else if (requestCode == 108 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img8, data, dialog);
                mCar_photo_p9.setVisibility(View.VISIBLE);
            } else if (requestCode == 109 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img9, data, dialog);
                mCar_photo_p10.setVisibility(View.VISIBLE);
            } else if (requestCode == 110 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img10, data, dialog);
                mCar_photo_p11.setVisibility(View.VISIBLE);
            } else if (requestCode == 111 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img11, data, dialog);
                mCar_photo_p12.setVisibility(View.VISIBLE);
            } else if (requestCode == 112 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img12, data, dialog);
            } else if (requestCode == 113 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_Construction_waste_img, data, dialog);
            } else if (requestCode == 114 && resultCode == 1001) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_Construction_waste_opposite_img, data, dialog);
            } else if (requestCode == 101 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img, data, dialog);
                mCar_photo_p2.setVisibility(View.VISIBLE);
            } else if (requestCode == 102 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img2, data, dialog);
                mCar_photo_p3.setVisibility(View.VISIBLE);
            } else if (requestCode == 103 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img3, data, dialog);
                mCar_photo_p4.setVisibility(View.VISIBLE);
            } else if (requestCode == 104 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img4, data, dialog);
                mCar_photo_p5.setVisibility(View.VISIBLE);
            } else if (requestCode == 105 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img5, data, dialog);
                mCar_photo_p6.setVisibility(View.VISIBLE);
            } else if (requestCode == 106 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img6, data, dialog);
                mCar_photo_p7.setVisibility(View.VISIBLE);
            } else if (requestCode == 107 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img7, data, dialog);
                mCar_photo_p8.setVisibility(View.VISIBLE);
            } else if (requestCode == 108 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img8, data, dialog);
                mCar_photo_p9.setVisibility(View.VISIBLE);
            } else if (requestCode == 109 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img9, data, dialog);
                mCar_photo_p10.setVisibility(View.VISIBLE);
            } else if (requestCode == 110 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img10, data, dialog);
                mCar_photo_p11.setVisibility(View.VISIBLE);
            } else if (requestCode == 111 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img11, data, dialog);
                mCar_photo_p12.setVisibility(View.VISIBLE);
            } else if (requestCode == 112 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_img12, data, dialog);
            } else if (requestCode == 113 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_Construction_waste_img, data, dialog);
            } else if (requestCode == 114 && resultCode == 1002) {
                ShowImageDialog.getInstance().ImageWithRound(car_photo_p_Construction_waste_opposite_img, data, dialog);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.car_photo_p) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(view.getContext(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.carpics_url, -90);
                tag = 101;
            }

        } else if (i == R.id.car_photo_p2) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(view.getContext(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.carpics_url, -90);
                tag = 102;
            }

        } else if (i == R.id.car_photo_p3) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(view.getContext(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.carpics_url, -90);
                tag = 103;
            }

        } else if (i == R.id.car_photo_p4) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(view.getContext(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.carpics_url, -90);
                tag = 104;
            }

        } else if (i == R.id.car_photo_p5) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(view.getContext(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.carpics_url, -90);
                tag = 105;
            }

        } else if (i == R.id.car_photo_p6) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(view.getContext(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.carpics_url, -90);
                tag = 106;
            }

        } else if (i == R.id.car_photo_p7) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(view.getContext(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.carpics_url, -90);
                tag = 107;
            }

        } else if (i == R.id.car_photo_p8) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(view.getContext(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.carpics_url, -90);
                tag = 108;
            }

        } else if (i == R.id.car_photo_p9) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(view.getContext(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.carpics_url, -90);
                tag = 109;
            }

        } else if (i == R.id.car_photo_p10) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(view.getContext(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.carpics_url, -90);
                tag = 110;
            }

        } else if (i == R.id.car_photo_p11) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(view.getContext(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.carpics_url, -90);
                tag = 111;
            }

        } else if (i == R.id.car_photo_p12) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(view.getContext(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.carpics_url, -90);
                tag = 112;
            }

        } else if (i == R.id.car_photo_Construction_waste) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(view.getContext(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.cwastef_url, -90);
                tag = 113;
            }

        } else if (i == R.id.car_photo_Construction_waste_opposite) {
            if (App.from.equals("")) {
                ToastUtil.showShortToast(view.getContext(), "没有填写表单号");
            } else {
                showOriginalPic(UrlConfig.cwasteb_url, -90);
                tag = 114;
            }

        }
    }
}
