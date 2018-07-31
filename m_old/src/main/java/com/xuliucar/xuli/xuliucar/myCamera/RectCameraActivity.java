package com.xuliucar.xuli.xuliucar.myCamera;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.CropperImage;
import com.xuliucar.xuli.xuliucar.cropper.CropImageView;
import com.xuliucar.xuli.xuliucar.ui.Login;
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

public class RectCameraActivity extends AppCompatActivity implements OnCaptureCallback,View.OnClickListener{
    private MaskSurfaceView surfaceview;


    //	拍照后得到的保存的文件路径
    private String filepath;
    private LinearLayout cropper_layout;
    private CropImageView cropImageView;

    private static final Uri IMAGE_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    private static final String PATH = Environment.getExternalStorageDirectory()
            .toString() + "/AndroidMedia/";
    String TAG ="myLog";
    private ImageView takephoto_side,takephoto_just,takephoto_side_no,takephoto_just_no;
    private RelativeLayout ic_ok_c,ic_ok_z;
    private int angle;
    private String url;
    private String type;//更具不同的类型（有些会有uid）来分类上传不同类别的图片：0为不区分，1为需要uid
    private String uid;
    private String msg;
    private Uri uri;
    private Dialog dialog;
    private static final int code=10;
    private static final String[] PERMISSION = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,// 读写入权限
            Manifest.permission.CAMERA,  //拍照权限
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rect_camera);
        this.surfaceview = (MaskSurfaceView) findViewById(R.id.surface_view);
        this.cropImageView = (CropImageView) findViewById(R.id.cropImageView);
        this.cropper_layout = (LinearLayout) findViewById(R.id.cropper_layout);
        RelativeLayout takephoto_c = (RelativeLayout) findViewById(R.id.takephoto_c);
        RelativeLayout takephoto_z = (RelativeLayout) findViewById(R.id.takephoto_z);
        ic_ok_c = (RelativeLayout) findViewById(R.id.ic_ok_c);
        ic_ok_z = (RelativeLayout) findViewById(R.id.ic_ok_z);
        takephoto_side = (ImageView) findViewById(R.id.takephoto_side);
        takephoto_just = (ImageView) findViewById(R.id.takephoto_just);
        takephoto_side_no = (ImageView) findViewById(R.id.takephoto_side_no);
        takephoto_just_no = (ImageView) findViewById(R.id.takephoto_just_no);
        Intent intent = getIntent();
        this.cropImageView.setGuidelines(2);
        int width = intent.getExtras().getInt("width");
        int height = intent.getExtras().getInt("height");
        angle = intent.getExtras().getInt("angle");
        url = intent.getExtras().getString("url");
        type = intent.getExtras().getString("type");
        uid = intent.getExtras().getString("uid");
//		设置矩形区域大小
        this.surfaceview.setMaskSize(width, height);
        //设置摄像头按钮的位置
        if(angle == -90){
            takephoto_c.setVisibility(View.VISIBLE);
            takephoto_z.setVisibility(View.GONE);

        }else if(angle == 0){
            takephoto_c.setVisibility(View.GONE);

            takephoto_z.setVisibility(View.VISIBLE);

        }

        takephoto_side.setOnClickListener(this);
        takephoto_just.setOnClickListener(this);
        //Log.i("myLog","type "+ type);

//		重拍
//        btn_recapture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btn_capture.setEnabled(true);
//                btn_ok.setEnabled(false);
//                btn_recapture.setEnabled(false);
//                imageView.setVisibility(View.GONE);
//                surfaceview.setVisibility(View.VISIBLE);
//                deleteFile();
//                CameraHelper.getInstance().startPreview();
//            }
//        });


        //定义DisplayMetrics 对象;
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //配置需要取的权限




    }

    @Override
    protected void onPause() {
        super.onPause();
        deleteFile();
    }
    /**
     * 删除图片文件呢
     */
    private void deleteFile(){
        if(this.filepath==null || this.filepath.equals("")){
            return;
        }
        File f = new File(this.filepath);
        if(f.exists()){
            f.delete();
        }
    }

    @Override
    public void onCapture(boolean success, String filepath) {
        this.filepath = filepath;
       // String message = "拍照成功";
        if(!success){
          //  message = "拍照失败";
            CameraHelper.getInstance().startPreview();

            this.surfaceview.setVisibility(View.VISIBLE);
        }else{
            cropper_layout.setVisibility(View.VISIBLE);
            this.surfaceview.setVisibility(View.GONE);
            if(angle == -90){
                ic_ok_c.setVisibility(View.VISIBLE);
                ic_ok_z.setVisibility(View.GONE);
            }else if(angle == 0){
                ic_ok_c.setVisibility(View.GONE);
                ic_ok_z.setVisibility(View.VISIBLE);
            }
            this.cropImageView.setImageBitmap(BitmapFactory.decodeFile(filepath));


        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.takephoto_side) {
            CameraHelper.getInstance().tackPicture(RectCameraActivity.this, getApplicationContext());
            handler.postDelayed(runnableSide, 1500);
            takephoto_side.setVisibility(View.INVISIBLE);
            takephoto_side_no.setVisibility(View.VISIBLE);
            // Log.i("myLog","go on");

        } else if (i == R.id.takephoto_just) {
            CameraHelper.getInstance().tackPicture(RectCameraActivity.this, getApplicationContext());
            handler.postDelayed(runnableJust, 1500);
            takephoto_just.setVisibility(View.INVISIBLE);
            takephoto_just_no.setVisibility(View.VISIBLE);
            //Log.i("myLog","go on");

        }
    }


    private final Handler handler = new Handler();
    private final Runnable runnableSide = new Runnable() {
        @Override
        public void run() {
           // Log.i("myLog","stop");
            handler.removeCallbacks(runnableSide);
            takephoto_side.setVisibility(View.VISIBLE);
            takephoto_side_no.setVisibility(View.INVISIBLE);
        }
    };

    private final Runnable runnableJust = new Runnable() {
        @Override
        public void run() {
            //Log.i("myLog","stop");
            handler.removeCallbacks(runnableJust);
            takephoto_just.setVisibility(View.VISIBLE);
            takephoto_just_no.setVisibility(View.INVISIBLE);
        }
    };

    public void close(View view){
        deleteFile();
        RectCameraActivity.this.finish();
    }

    public void closeCropper(View view){
        deleteFile();
        RectCameraActivity.this.finish();
    }



    public void startCropper(View view){
        CropperImage cropperImage = cropImageView.getCroppedImage();
       // Log.e(TAG, cropperImage.getX() + "," + cropperImage.getY());
       // Log.e(TAG, cropperImage.getWidth() + "," + cropperImage.getHeight());


        //获取截图并旋转角度
       Bitmap bitmap = Utils.rotate(cropperImage.getBitmap(), angle);

        // 系统时间
        long dateTaken = System.currentTimeMillis();
        // 图像名称
        String filename = DateFormat.format("yyyy-MM-dd kk.mm.ss", dateTaken).toString() + ".JPEG";
        uri = insertImage(getContentResolver(), filename, dateTaken, PATH,filename, bitmap, null);
        String filepath = PATH+filename;
        compressBmpToFile(bitmap,new File(filepath));//上传前检查图片是否要压缩

        if(type.equals("0")){
            uploadImg0(filepath,url);

        }else if(type.equals("1")){
            Map<String,Object> map = new HashMap<>();
            map.put("loginid", App.loginid);
            map.put("uid",uid);
            uploadImg1(filepath,url,map);
        }
//        Log.i("myLog","uri "+ uri);
        LayoutInflater inflater = LayoutInflater.from(RectCameraActivity.this);
        final ViewGroup nullParent = null;
        View views = inflater.inflate(R.layout.upload_tips, nullParent);
        AlertDialog.Builder builder = new AlertDialog.Builder(RectCameraActivity.this);
        builder.setView(views);
        builder.setCancelable(false);
        dialog = builder.show();
        cropperImage.getBitmap().recycle();
        cropperImage.setBitmap(null);
        bitmap.recycle();
    }

    //上传时判断压缩图片
    private static void compressBmpToFile(Bitmap bmp, File file){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
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

    //上传图片方法
    private void uploadImg0(final String path, String imgUrl) {
        final File files = new File(path);

        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(files!=null){
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), files);
            builder.addFormDataPart("file",files.getName(),body);
        }
        Request request = new Request.Builder().url(imgUrl).post(builder.build()).tag(RectCameraActivity.this).build();
        client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("myLog" ,"onFailure");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShortToast(getApplicationContext(),"网路访问错误");
                        finish();
                    }
                });

            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
              final  String t = response.body().string();
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
                                setResult(1001,intent);
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


    private void uploadImg1(final String path, String imgUrl, Map<String,Object> map) {
        final File files = new File(path);
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
        Request request = new Request.Builder().addHeader("cookie", App.cookie).url(imgUrl).post(requestBody.build()).tag(RectCameraActivity.this).build();
        client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("myLog" ,"onFailure");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShortToast(getApplicationContext(),"网路访问错误");
                        finish();
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
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
                                if(status.equals("true")){
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
                                setResult(1001,intent);
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


    /**
     * 存储图像并将信息添加入媒体数据库
     */
    private Uri insertImage(ContentResolver cr, String name, long dateTaken,
                            String directory, String filename, Bitmap source, byte[] jpegData) {
        OutputStream outputStream = null;
        String filePath = directory + filename;
        try {
            File dir = new File(directory);
            if (!dir.exists()) {
                dir.mkdir();
            }
            if(dir.exists()){
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
