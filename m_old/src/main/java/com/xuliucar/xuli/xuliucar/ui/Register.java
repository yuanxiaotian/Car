package com.xuliucar.xuli.xuliucar.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.base.BaseActivity;
import com.xuliucar.xuli.xuliucar.config.UrlConfig;
import com.xuliucar.xuli.xuliucar.myCamera.RectCameraActivity;
import com.xuliucar.xuli.xuliucar.myCamera2.CameraActivity;
import com.xuliucar.xuli.xuliucar.permission.AfterPermissionGranted;
import com.xuliucar.xuli.xuliucar.permission.PermissionUtils;
import com.xuliucar.xuli.xuliucar.qrCode.CustomScanAct;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;
import com.xuliucar.xuli.xuliucar.utils.Watcher;
import com.xuliucar.xuli.xuliucar.widget.SelectPicPopupWindow;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends BaseActivity implements View.OnClickListener {
    private EditText reg_username, reg_phoneNumber, reg_email;
    private Button Business_license_photo, Business_license__QR_code, submit_reg;
    private ImageView reg_username_icon, reg_phoneNumber_icon, reg_email_icon;
    private TextView optional;

    private static final int REQUEST_CAMERA_PERMISSION = 0x01;
    private static final int REQUEST_QRCAMERA_PERMISSION = 0X02;
    private static final int REQUEST_PHOTO = 0x03;
    private SelectPicPopupWindow menuWindow; // 自定义的头像编辑弹出框
    private static final int GALLERY_REQUEST_CODE = 2;//相册
    private static final String[] PERMISSION = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,// 读写入权限
            Manifest.permission.CAMERA,  //拍照权限
    };
    //二维码扫描
    /**
     * Request Code
     */

    private String url = "";

    private String username;
    private String phone;
    private Dialog dialog;
    private int width;
    private int height;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.register);
    }

    protected void initView() {
        Toolbar register_toolbar = (Toolbar) findViewById(R.id.register_toolbar);
        reg_username = (EditText) findViewById(R.id.reg_username);
        reg_phoneNumber = (EditText) findViewById(R.id.reg_phoneNumber);
        reg_email = (EditText) findViewById(R.id.reg_email);
        Business_license_photo = (Button) findViewById(R.id.Business_license_photo);
        Business_license__QR_code = (Button) findViewById(R.id.Business_license__QR_code);
        submit_reg = (Button) findViewById(R.id.submit_reg);
        reg_username_icon = (ImageView) findViewById(R.id.reg_username_icon);
        reg_phoneNumber_icon = (ImageView) findViewById(R.id.reg_phoneNumber_icon);
        reg_email_icon = (ImageView) findViewById(R.id.reg_email_icon);
        optional = (TextView) findViewById(R.id.optional);
        register_toolbar.setNavigationIcon(R.drawable.back);
        register_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //定义DisplayMetrics 对象;
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        int screenWidth = dm.widthPixels;
        //窗口高度
        int screenHeight = dm.heightPixels;

        width = (int) (screenWidth * 0.8);
        height = (int) (screenHeight * 0.7);
    }

    @Override
    protected void setListener() {
        super.setListener();
        Business_license_photo.setOnClickListener(this);
        Business_license__QR_code.setOnClickListener(this);
        submit_reg.setOnClickListener(this);
        reg_username_icon.setOnClickListener(this);
        reg_phoneNumber_icon.setOnClickListener(this);
        reg_email_icon.setOnClickListener(this);
        Watcher.watcher(reg_username, reg_username_icon);
        Watcher.watcher(reg_phoneNumber, reg_phoneNumber_icon);
        Watcher.watcher(reg_email, reg_email_icon);
        Watcher.watcherText(reg_email, optional);
        Watcher.IllegalCharacter(reg_username);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.Business_license_photo) {
            menuWindow = new SelectPicPopupWindow(Register.this, itemsOnClick);
            menuWindow.showAtLocation(findViewById(R.id.register_pager),
                    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


        } else if (i == R.id.Business_license__QR_code) {
            if (PermissionUtils.hasPermissions(Register.this, Manifest.permission.CAMERA)) {
                uploadRQCode();
            } else {
                PermissionUtils.requestPermissions(Register.this, getString(R.string.rationale_cameras), REQUEST_QRCAMERA_PERMISSION, PERMISSION);
            }


        } else if (i == R.id.submit_reg) {
            subRegister();

        } else if (i == R.id.reg_username_icon) {
            reg_username.setText("");

        } else if (i == R.id.reg_phoneNumber_icon) {
            reg_phoneNumber.setText("");

        } else if (i == R.id.reg_email_icon) {
            reg_email.setText("");

        }
    }

    @AfterPermissionGranted(REQUEST_QRCAMERA_PERMISSION)
    private void uploadRQCode() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(Register.this);
        intentIntegrator
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                .setPrompt("将二维码对正放入框内，即可自动扫描")//写那句提示的话
                .setOrientationLocked(false)//扫描方向固定
                .setCaptureActivity(CustomScanAct.class) // 设置自定义的activity是CustomActivity
                .initiateScan(); // 初始化扫描
    }

    private void subRegister() {
        String phoneExp = "13\\d{9}|14[57]\\d{8}|15[012356789]\\d{8}|18[01256789]\\d{8}|17[0678]\\d{8}";//手机号验证
        String emailExp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";//电子邮箱验证
        username = reg_username.getText().toString();
        phone = reg_phoneNumber.getText().toString();
        String email = reg_email.getText().toString();
        String rpName = PreferencesUtils.getSharePreStr(getApplicationContext(), "Reg", "pname");
        String RqCode = PreferencesUtils.getSharePreStr(getApplicationContext(), "Reg", "RQurl");

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(phone)) {
            ToastUtil.showShortToast(Register.this, "申请人或手机号不能为空");
        } else {
            if (rpName.isEmpty() || rpName.equals("")) {
                ToastUtil.showShortToast(Register.this, "营业执照照片没上传");
            } else {
                if (RqCode.isEmpty() || RqCode.equals("")) {
                    ToastUtil.showShortToast(Register.this, "营业执二维码没有扫描上传");
                } else {
                    if (email.isEmpty()) {
                        if (phone.matches(phoneExp)) {
                            //不填邮箱提交操作
                            RegDo("");

                        } else {
                            ToastUtil.showShortToast(Register.this, "手机号格式不正确");
                        }
                    } else {
                        if (phone.matches(phoneExp)) {
                            if (email.matches(emailExp)) {
                                //填写邮箱操作
                                RegDo(email);
                            } else {
                                ToastUtil.showShortToast(Register.this, "邮箱格式不正确");
                            }
                        } else {
                            ToastUtil.showShortToast(Register.this, "手机号格式不正确");
                        }
                    }
                }
            }

        }

    }

    //注册操作
    private void RegDo(final String email) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("name", username)
                .add("telephone", phone)
                .add("email", email)
                .build();
        Request request = new Request.Builder()
                .url(UrlConfig.checkReg_url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShortToast(getApplicationContext(), "网络请求失败，请稍后重试！");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String t = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject object = new JSONObject(t);
                            String status = object.getString("success");
                            String message = object.getString("message");
                            if (status.equals("true")) {
                                LayoutInflater inflater = LayoutInflater.from(Register.this);
                                final ViewGroup nullParent = null;
                                View view = inflater.inflate(R.layout.register_tips, nullParent);
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setView(view);
                                builder.setCancelable(false);
                                dialog = builder.show();
                                permissionReg(email);
                            } else {
                                ToastUtil.showShortToast(Register.this, message);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void permissionReg(String email) {
        username = reg_username.getText().toString();
        phone = reg_phoneNumber.getText().toString();
        String pname = PreferencesUtils.getSharePreStr(Register.this, "Reg", "pname");
        String rQurl = PreferencesUtils.getSharePreStr(Register.this, "Reg", "RQurl");
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("name", username)
                .add("telephone", phone)
                .add("url", rQurl)
                .add("complic", pname)
                .add("email", email)
                .build();
        Request request = new Request.Builder()
                .url(UrlConfig.register_url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                ToastUtil.showShortToast(getApplicationContext(), "网络请求失败，请稍后重试！");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String t = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject object = new JSONObject(t);
                            Log.i("myLog", "object " + object);
                            String status = object.getString("success");
                            if (status.equals("true")) {
                                dialog.dismiss();
                                PreferencesUtils.clearSharePre(Register.this, "Reg");
                                ToastUtil.showShortToast(Register.this, "申请提交成功,请等候服务人员联系！");
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(Register.this, Login.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }, 1000);
                            } else {
                                dialog.dismiss();
                                ToastUtil.showShortToast(Register.this, "申请失败,请稍后重试");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }


    /**
     * 下面是照片上传
     */
    //为弹出窗口类实现监听类
    private final View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            menuWindow.dismiss();
            int i = view.getId();
            if (i == R.id.takePhotoBtn) {
                if (PermissionUtils.hasPermissions(Register.this, Manifest.permission.CAMERA)) {
                    openCamera();
                } else {
                    PermissionUtils.requestPermissions(Register.this, getString(R.string.rationale_cameras), REQUEST_CAMERA_PERMISSION, PERMISSION);
                }


                // 相册选择图片
            } else if (i == R.id.pickPhotoBtn) {
                if (PermissionUtils.hasPermissions(Register.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    photo();
                } else {
                    PermissionUtils.requestPermissions(Register.this, getString(R.string.rationale_read_write), REQUEST_PHOTO, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }

            }
        }
    };

    @AfterPermissionGranted(REQUEST_PHOTO)
    private void photo() {
        Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
        intent1.setType("image/*");
        startActivityForResult(intent1, GALLERY_REQUEST_CODE);
    }

    @AfterPermissionGranted(REQUEST_CAMERA_PERMISSION)
    private void openCamera() {
        if (App.sdkVsesion >= 21 && App.brand.equals("HONOR")) {
            Intent intent2 = new Intent(getApplicationContext(), CameraActivity.class);
            intent2.putExtra("width", width);
            intent2.putExtra("height", height);
            intent2.putExtra("url", UrlConfig.uploadRegImg_url);
            intent2.putExtra("angle", 0);
            intent2.putExtra("type", "0");
            startActivity(intent2);
        } else {
            Intent intent = new Intent(getApplicationContext(), RectCameraActivity.class);
            intent.putExtra("width", width);
            intent.putExtra("height", height);
            intent.putExtra("angle", 0);
            intent.putExtra("url", UrlConfig.uploadRegImg_url);
            intent.putExtra("type", "0");
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                Intent intent = new Intent(getApplicationContext(), cutPic.class);
                intent.setData(data.getData());
                intent.putExtra("url", UrlConfig.uploadRegImg_url);
                intent.putExtra("type", "0");
                startActivity(intent);
            }
        }

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (intentResult != null) {
            if (intentResult.getContents() != null) {
                String str = intentResult.getContents();
                String MatchUrl = "(http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w\\-./?%&*=]*))";
                if (!TextUtils.isEmpty(str)) {
                    Pattern pattern = Pattern.compile(MatchUrl);
                    Matcher matcher = pattern.matcher(str);
                    while (matcher.find()) {
                        url = matcher.group();
                    }
                }

                Pattern p = Pattern.compile("[0-9]*");
                Matcher m = p.matcher(url);

                if (m.matches() || url.equals("")) {
                    ToastUtil.showShortToast(Register.this, "扫码获取结果失败,请重新扫描!");
                } else {
                    PreferencesUtils.putSharePre(Register.this, "Reg", "RQurl", url);
                    ToastUtil.showShortToast(Register.this, "扫码成功");
                }
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

}
