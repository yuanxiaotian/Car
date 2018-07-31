package com.xuliucar.xuli.xuliucar.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.cangmaomao.lib.utils.DisplayUtilsKt;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.CropperImage;
import com.xuliucar.xuli.xuliucar.cropper.CropImageView;
import com.xuliucar.xuli.xuliucar.myCamera.Utils;
import com.xuliucar.xuli.xuliucar.utils.ImageTools;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;


public class cutPic extends AppCompatActivity {
    private CropImageView mCropImageView;
    private static final String PATH = Environment.getExternalStorageDirectory()
            .toString() + "/AndroidMedia/";
    private static final Uri IMAGE_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    private String picUrl;
    private String type;//更具不同的类型（有些会有uid）来分类上传不同类别的图片：0为不区分，1为需要uid
    private String uid;
    private Uri uri;
    private String msg;
    private Dialog dialog;
    private static final int SCALE = 4;//照片缩小比例
    private int angle;
    private Bitmap newBitmap;
    private Bitmap bitmap;
    String TAG ="myLog";
    private static final int REQUECT_CODE_SDCARD = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.cut_pic);
        mCropImageView = (CropImageView) findViewById(R.id.cupCropImageView);
        mCropImageView.setGuidelines(2);
        Intent intent = getIntent();
        Uri uri1 = getIntent().getData();

        picUrl = intent.getStringExtra("url");
        type = intent.getExtras().getString("type");
        uid = intent.getExtras().getString("uid");
        angle = intent.getExtras().getInt("angle");
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri1);
            //将图片缩小
            newBitmap = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);
            mCropImageView.setImageBitmap(newBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //android6.0动态权限

    }


    public void startPhotoCropper(View view) {


        CropperImage cropperImage = mCropImageView.getCroppedImage();

        bitmap = Utils.rotate(cropperImage.getBitmap(), angle);
//        Bitmap bitmap = mCropImageView.getCroppedImage();

        long dateTaken = System.currentTimeMillis();

        String filename = DateFormat.format("yyyy-MM-dd kk.mm.ss", dateTaken)
                .toString() + ".jpg";
        uri = insertImage(getContentResolver(), filename, dateTaken, PATH,filename, bitmap, null);
        String filePath = PATH+filename;
        compressBmpToFile(bitmap,new File(filePath));
        Map<String,Object> map = new HashMap<>();
        map.put("loginid", App.loginid);
        map.put("uid",uid);
        if(type.equals("0")){
            upload(filePath,picUrl);
        }else if(type.equals("1")){
            uploadWithArg(filePath,picUrl,map);
        }

        LayoutInflater inflater = LayoutInflater.from(cutPic.this);
        final ViewGroup nullParent = null;
        View views = inflater.inflate(R.layout.upload_tips, nullParent);
        AlertDialog.Builder builder = new AlertDialog.Builder(cutPic.this);
        builder.setView(views);
        builder.setCancelable(false);
        dialog = builder.show();
        cropperImage.getBitmap().recycle();
        cropperImage.setBitmap(null);
        bitmap.recycle();


    }

    public void closeCropper(View view){
        finish();
    }

    //上传时判断压缩图片
    private static void compressBmpToFile(Bitmap bmp, File file){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 80;//个人喜欢从80开始,
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            options -= 10;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void upload(final String path, final String imgUrl){
        File files = new File(path);
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder requestBody  = new MultipartBody.Builder().setType(MultipartBody.FORM);
        RequestBody body = RequestBody.create(MediaType.parse("image/*"), files);
        requestBody.addFormDataPart("file",files.getName(),body);
        Request request = new Request.Builder().url(imgUrl).post(requestBody.build()).tag(cutPic.this).build();
        client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShortToast(getApplicationContext(),"网路访问错误");
                        finish();
                    }
                });

            }

            @Override
            public void onResponse(Call call,final Response response) throws IOException {
                final String t = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject object = new JSONObject(t);

                                String status = object.getString("success");
                                msg = object.getString("message");
                                Log.i("myLog","okhttp营业执照成功返回"+object);
                                if(status.equals("true") && msg.equals("上传成功!")){
                                    ToastUtil.showShortToast(getApplicationContext(),msg);
                                    JSONObject object1 = object.getJSONObject("data");
                                    String Rpname = object1.getString("pname");
                                    PreferencesUtils.putSharePre(getApplicationContext(),"Reg","pname",Rpname);
                                }else{

                                    if(msg.equals("认证信息失效!")){
                                        ToastUtil.showShortToast(getApplicationContext(), msg+"请重新登陆！");
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        ToastUtil.showShortToast(getApplicationContext(), msg);
                                    }
                                }
                                Intent intent = new Intent();
                                intent.setData(uri);
                                intent.putExtra("msg",msg);
                                setResult(1002,intent);
                                dialog.dismiss();
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            ToastUtil.showShortToast(getApplicationContext(),"上传失败");
                            finish();
                        }
                    }
                });
            }
        });

    }


    private void uploadWithArg(final String path, final String imgUrl, Map<String,Object> map){
        File files = new File(path);
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder requestBody  = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(files != null){
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), files);
            requestBody.addFormDataPart("file",files.getName(),body);
        }
        if(map != null){
            for(Map.Entry entry : map.entrySet()){
                requestBody.addFormDataPart(valueOf(entry.getKey()),valueOf(entry.getValue()));
            }
        }
        Request request = new Request.Builder().addHeader("cookie", App.cookie).url(imgUrl).post(requestBody.build()).tag(cutPic.this).build();
        client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       ToastUtil.showShortToast(getApplicationContext(),"网路访问错误");
                       finish();
                   }
               });

            }

            @Override
            public void onResponse(Call call,final Response response) throws IOException {
               final String t = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject object = new JSONObject(t);

                                String status = object.getString("success");
                                msg = object.getString("message");
                                Log.i("myLog","okhttp2 object"+object);
                                if(status.equals("true") && msg.equals("上传成功!")){
                                    ToastUtil.showShortToast(getApplicationContext(), msg);
                                }else{

                                    if(msg.equals("认证信息失效!")){
                                        ToastUtil.showShortToast(getApplicationContext(), msg+"请重新登陆！");
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        ToastUtil.showShortToast(getApplicationContext(), msg);
                                    }
                                }
                                Intent intent = new Intent();
                                intent.setData(uri);
                                intent.putExtra("msg",msg);
                                setResult(1002,intent);
                                dialog.dismiss();
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            ToastUtil.showShortToast(getApplicationContext(),"上传失败");
                            finish();
                        }
                    }
                });

            }
        });
    }


    private Uri insertImage(ContentResolver cr, String name, long dateTaken,
                            String directory, String filename, Bitmap source, byte[] jpegData) {
        OutputStream outputStream = null;
        String filePath = directory + filename;
        try {
            File dir = new File(directory);
            boolean isDirectoryCreated = dir.exists();

            if (!isDirectoryCreated) {
                dir.mkdir();
            }
            if(isDirectoryCreated){
                File file = new File(directory, filename);
                if (file.createNewFile()) {
                    outputStream = new FileOutputStream(file);
                    if (source != null) {
                        source.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    } else {
                        outputStream.write(jpegData);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return null;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
        ContentValues values = new ContentValues(7);
        values.put(MediaStore.Images.Media.TITLE, name);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
        values.put(MediaStore.Images.Media.DATE_TAKEN, dateTaken);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.DATA, filePath);
        return cr.insert(IMAGE_URI, values);
    }
}
