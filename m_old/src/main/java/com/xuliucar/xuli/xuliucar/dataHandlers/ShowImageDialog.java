package com.xuliucar.xuli.xuliucar.dataHandlers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.interfaces.ImageDialog;
import com.xuliucar.xuli.xuliucar.myCamera.RectCameraActivity;
import com.xuliucar.xuli.xuliucar.myCamera2.CameraActivity;
import com.xuliucar.xuli.xuliucar.utils.SavePic;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;
import com.xuliucar.xuli.xuliucar.widget.MatrixImageView;

/**
 * Created by skyward on 2016/10/8.
 *
 */
public class ShowImageDialog implements ImageDialog{
    private Dialog dialog;
    private static final int GALLERY_REQUEST_CODE = 2;//相册
    private SavePic savePic = new SavePic();
    private static class InnerShowImageDialog{
        private static ShowImageDialog instance = new ShowImageDialog();
    }



    public static ShowImageDialog getInstance() {
        return InnerShowImageDialog.instance;
    }

    private ShowImageDialog() {
    }


    @Override
    public void showDialog(Context context, String uploadUrl, String type, int angle, int width, int height, int tag) {

    }

    @Override
    public String showDialog(final Context context, final String uploadUrl, final String type, final String uid, final int angle, final int width, final int height, final int tag) {
        dialog =new Dialog(context,R.style.dialog);
        final View layout = View.inflate(context,R.layout.alert_img,null);
        dialog.setContentView(layout);
        dialog.show();
        ImageView preImage = (ImageView) layout.findViewById(R.id.preImage);
        TextView takePhotoBtn = (TextView) layout.findViewById(R.id.takePhotoBtn);
        TextView pickPhotoBtn = (TextView) layout.findViewById(R.id.pickPhotoBtn);
        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(App.sdkVsesion >= 21 && App.brand.equals("HONOR")){
                    Intent intent1 = new Intent(context, CameraActivity.class);
                    intent1.putExtra("width", width);
                    intent1.putExtra("height", height);
                    intent1.putExtra("angle", angle);
                    intent1.putExtra("uid", uid);
                    intent1.putExtra("url",uploadUrl);
                    intent1.putExtra("type",type);
                    ((Activity)context).startActivityForResult(intent1,tag);
                }else {
                    Intent intent = new Intent(context, RectCameraActivity.class);
                    intent.putExtra("width", width);
                    intent.putExtra("height", height);
                    intent.putExtra("angle", angle);
                    intent.putExtra("uid", uid);
                    intent.putExtra("url",uploadUrl);
                    intent.putExtra("type",type);
                    ((Activity)context).startActivityForResult(intent,tag);
                }
            }
        });
        pickPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setType("image/*");
                ((Activity)context).startActivityForResult(intent1, GALLERY_REQUEST_CODE);
            }
        });

        if(angle == 0){//根据角度来判断是显示横的还是纵的拍照示意图
            preImage.setImageResource(R.drawable.zhong);
        }else if(angle == -90){
            preImage.setImageResource(R.drawable.hang);
        }
        preImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        return uploadUrl;
    }

    @Override
    public String showDialog(final Context context, final String imageUrl, final String uploadUrl, final String type, final String uid, final int angle, final int width, final int height, final int tag) {
        dialog = new Dialog(context, R.style.dialog);
        View layout = View.inflate(context,R.layout.alert_img,null);
        dialog.setContentView(layout);
        dialog.show();
        ImageView preImage = (ImageView) layout.findViewById(R.id.preImage);
        MatrixImageView img_detail = (MatrixImageView) layout.findViewById(R.id.img_detail);
        TextView takePhotoBtn = (TextView) layout.findViewById(R.id.takePhotoBtn);
        TextView pickPhotoBtn = (TextView) layout.findViewById(R.id.pickPhotoBtn);
        if (imageUrl.isEmpty()) {
            preImage.setImageResource(R.drawable.hang);
            final Dialog finalDialog = dialog;
            preImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalDialog.dismiss();
                }
            });
        } else {
            preImage.setVisibility(View.GONE);
            img_detail.setVisibility(View.VISIBLE);
            App.imageLoader.displayImage(imageUrl,img_detail,App.options);
        }

        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (App.sdkVsesion >= 21 && App.brand.equals("HONOR")) {
                    Intent intent = new Intent(context, CameraActivity.class);
                    intent.putExtra("width", width);
                    intent.putExtra("height", height);
                    intent.putExtra("angle", angle);
                    intent.putExtra("uid", uid);
                    intent.putExtra("url", uploadUrl);
                    intent.putExtra("type", type);
                    ((Activity)context).startActivityForResult(intent, tag);

                }else {
                    Intent intent1 = new Intent(context, RectCameraActivity.class);
                    intent1.putExtra("width", width);
                    intent1.putExtra("height", height);
                    intent1.putExtra("angle", angle);
                    intent1.putExtra("uid", uid);
                    intent1.putExtra("url", uploadUrl);
                    intent1.putExtra("type", type);
                    ((Activity)context).startActivityForResult(intent1, tag);
                }
            }
        });

        pickPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);
                intent2.setType("image/*");
                ((Activity)context).startActivityForResult(intent2, GALLERY_REQUEST_CODE);

            }
        });

        final Dialog finalDialog1 = dialog;
        img_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog1.dismiss();
            }
        });
        img_detail.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                savePic.getDownload(imageUrl);
                ToastUtil.showShortToast(context, "保存成功!");
                return true;
            }
        });
        return uploadUrl;
    }

    @Override
    public String showDialog(final Context context, Dialog dialog, final String imageUrl, final String uploadUrl, final int width, final int height, final int angle, final String uid, final int tag) {
        dialog = new Dialog(context,R.style.dialog);
        final View layout = View.inflate(context,R.layout.alert_img,null);
        dialog.setContentView(layout);
        dialog.show();
        ImageView preImage = (ImageView) layout.findViewById(R.id.preImage);
        MatrixImageView img_detail = (MatrixImageView) layout.findViewById(R.id.img_detail);
        TextView takePhotoBtn = (TextView) layout.findViewById(R.id.takePhotoBtn);
        TextView pickPhotoBtn = (TextView) layout.findViewById(R.id.pickPhotoBtn);
        if(imageUrl.isEmpty()){
            if(angle == 0){//根据角度来判断是显示横的还是纵的拍照示意图
                preImage.setImageResource(R.drawable.zhong);
            }else if(angle == -90){
                preImage.setImageResource(R.drawable.hang);
            }
            final Dialog finalDialog = dialog;
            preImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalDialog.dismiss();
                }
            });
        }else{
            preImage.setVisibility(View.GONE);
            img_detail.setVisibility(View.VISIBLE);
            App.imageLoader.displayImage(imageUrl,img_detail,App.options);
        }
        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(App.sdkVsesion >= 21 && App.brand.equals("HONOR")){
                    Intent intent1 = new Intent(context, CameraActivity.class);
                    intent1.putExtra("width", width);
                    intent1.putExtra("height", height);
                    intent1.putExtra("angle", angle);
                    intent1.putExtra("uid", uid);
                    intent1.putExtra("url",uploadUrl);
                    intent1.putExtra("type","1");
                    ((Activity)context).startActivityForResult(intent1,tag);
                }else {
                    Intent intent = new Intent(context, RectCameraActivity.class);
                    intent.putExtra("width", width);
                    intent.putExtra("height", height);
                    intent.putExtra("angle", angle);
                    intent.putExtra("uid", uid);
                    intent.putExtra("url",uploadUrl);
                    intent.putExtra("type","1");
                    ((Activity)context).startActivityForResult(intent,tag);

                }
            }
        });
        pickPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setType("image/*");
                ((Activity)context).startActivityForResult(intent1, GALLERY_REQUEST_CODE);
            }
        });


        final Dialog finalDialog1 = dialog;
        img_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog1.dismiss();
            }
        });
        img_detail.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                savePic.getDownload(imageUrl);
                ToastUtil.showShortToast(context, "保存成功!");
                return true;
            }
        });

        return uploadUrl;
    }


    public void ImageWithRound(ImageView imageView,Intent data){
        Uri uri = data.getData();
        String imageUrl = uri.toString();
        App.imageLoader.displayImage(imageUrl,imageView,App.RoundeOptions);
        Bundle bundle = data.getExtras();
        String msg = bundle.getString("msg");
        if(!TextUtils.isEmpty(msg) && msg.equals("上传成功!")){
            dialog.dismiss();
        }
    }

    public void ImageWithRound(ImageView imageView,Intent data,Dialog dialog1){
        Uri uri = data.getData();
        String imageUrl = uri.toString();
        App.imageLoader.displayImage(imageUrl,imageView,App.RoundeOptions);
        Bundle bundle = data.getExtras();
        String msg = bundle.getString("msg");
        if(!TextUtils.isEmpty(msg) && msg.equals("上传成功!")){
            dialog1.dismiss();
        }
    }

    public void ImageWithoutCacheRound(ImageView imageView,Intent data){
        Uri uri = data.getData();
        String imageUrl = uri.toString();
        App.imageLoader.displayImage(imageUrl,imageView,App.RoundeWithoutCache);
        Bundle bundle = data.getExtras();
        String msg = bundle.getString("msg");
        if(!TextUtils.isEmpty(msg) && msg.equals("上传成功!")){
            dialog.dismiss();
        }
    }


}
