package com.xuliucar.xuli.xuliucar.interfaces;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

/**
 * Created by skyward on 2016/10/8.
 *
 */

public interface ImageDialog {


    /**
     *
     * @param context 上下文
     * @param uploadUrl 图片上传地址
     * @param type 根据类型是否要携带uid上传图片0为不用uid，1为要uid
     * @param angle 上传图片的角度，是横着还是竖着上传
     * @param width 传入打开相机的宽度
     * @param height 传入打开相机的高度
     * @param tag 设定上传后的返回值
     */
    void showDialog(Context context,String uploadUrl,String type,int angle,int width,int height,int tag);

    /**
     * @param context 上下文
     * @param uploadUrl 图片上传地址
     * @param type 根据类型是否要携带uid上传图片0为不用uid，1为要uid
     * @param uid 上传图片要用的的uid
     * @param angle 上传图片的角度，是横着还是竖着上传
     * @param width 传入打开相机的宽度
     * @param height 传入打开相机的高度
     * @param tag 设定上传后的返回值
     */
    String showDialog(Context context,String uploadUrl,String type,String uid,int angle,int width,int height,int tag);

    /**
     *
     * @param context 上下文
     * @param imageUrl 图片下载地址
     * @param uploadUrl 图片上传地址
     * @param type 根据类型是否要携带uid上传图片0为不用uid，1为要uid
     * @param uid 上传图片要用的的uid
     * @param angle 上传图片的角度，是横着还是竖着上传
     * @param width 传入打开相机的宽度
     * @param height 传入打开相机的高度
     * @param tag 设定上传后的返回值
     * @return 返回上传图片的url是为了从相册打开选择图片回调上传图片的时候要用到
     */
    String showDialog(Context context, String imageUrl, String uploadUrl, String type, String uid, int angle, int width, int height, int tag);

    String showDialog(Context context, Dialog dialog, String imageUrl, String uploadUrl, int width, int height, int angle, String uid, int tag);
}
