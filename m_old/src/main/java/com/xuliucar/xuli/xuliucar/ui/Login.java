package com.xuliucar.xuli.xuliucar.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.jakewharton.rxbinding.view.RxView;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.mvp.presenter.LoginPresenter;
import com.xuliucar.xuli.xuliucar.mvp.view.LoginView;
import com.xuliucar.xuli.xuliucar.permission.AfterPermissionGranted;
import com.xuliucar.xuli.xuliucar.permission.PermissionUtils;
import com.xuliucar.xuli.xuliucar.ui.index.MainActivity;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;
import com.xuliucar.xuli.xuliucar.utils.Watcher;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * Created by skyward on 2016/6/29.
 *
 */

public class Login extends AppCompatActivity implements View.OnClickListener, LoginView {
    private TextInputEditText edit_company, edit_username, edit_password;
    private ImageView company_name_operation, username_operation, password_operation;
    private Button login, register, cannot_login;
    private CheckBox autoLogin;
    private static final int REQUECT_CODE_SDCARD = 2;

    private LoginPresenter mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        initEvent();
        mLoginPresenter.autoLogin();//自动登陆
        mLoginPresenter.getPhoneMsg();//获取手机信息
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.unSubcrible();
    }

    private void initView() {
        edit_company = findViewById(R.id.company_name);
        edit_username = findViewById(R.id.username);
        edit_password = findViewById(R.id.password);
        company_name_operation = (ImageView) findViewById(R.id.company_name_operation);
        username_operation = (ImageView) findViewById(R.id.username_operation);
        password_operation = (ImageView) findViewById(R.id.password_operation);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        cannot_login = (Button) findViewById(R.id.cannot_login);
        autoLogin = (CheckBox) findViewById(R.id.autoLogin);
        edit_company.setText(PreferencesUtils.getSharePreStr(Login.this, "userInfo", "companyName"));
        edit_username.setText(PreferencesUtils.getSharePreStr(Login.this, "userInfo", "username"));
        edit_password.setText(PreferencesUtils.getSharePreStr(Login.this, "userInfo", "password"));
        if (!TextUtils.isEmpty(edit_company.getText().toString().trim())) {
            company_name_operation.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(edit_username.getText().toString().trim())) {
            username_operation.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(edit_password.getText().toString().trim())) {
            password_operation.setVisibility(View.VISIBLE);
        }
        mLoginPresenter = new LoginPresenter(this);
    }

    private void initEvent() {
        filterMultiClick();
        register.setOnClickListener(this);
        cannot_login.setOnClickListener(this);
        company_name_operation.setOnClickListener(this);
        username_operation.setOnClickListener(this);
        password_operation.setOnClickListener(this);
        Watcher.watcher(edit_company, company_name_operation);
        Watcher.watcher(edit_username, username_operation);
        Watcher.watcher(edit_password, password_operation);
        Watcher.IllegalCharacter(edit_company);
        Watcher.IllegalCharacter(edit_username);
        String isAutoLogin = PreferencesUtils.getSharePreStr(getApplicationContext(), "userInfo", "autoLogin");
        switch (isAutoLogin) {
            case "0":
                autoLogin.setChecked(false);
                break;
            case "1":
                autoLogin.setChecked(true);
                break;
            default:
                autoLogin.setChecked(false);
                break;
        }
        autoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autoLogin.isChecked()) {
                    PreferencesUtils.putSharePre(getApplicationContext(), "userInfo", "autoLogin", "1");
                } else {
                    PreferencesUtils.putSharePre(getApplicationContext(), "userInfo", "autoLogin", "0");
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.company_name_operation) {
            edit_company.setText("");

        } else if (i == R.id.username_operation) {
            edit_username.setText("");

        } else if (i == R.id.password_operation) {
            edit_password.setText("");

        } else if (i == R.id.register) {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);

        } else if (i == R.id.cannot_login) {
        } else {
        }
    }

    /**
     * 防止多次点击
     */
    private void filterMultiClick(){
        RxView.clicks(login)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                            if (PermissionUtils.hasPermissions(Login.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                login();
                            } else {
                                PermissionUtils.requestPermissions(Login.this, getString(R.string.rationale_read_write),REQUECT_CODE_SDCARD,Manifest.permission.WRITE_EXTERNAL_STORAGE);
                            }
                    }
                });
    }

    @AfterPermissionGranted(REQUECT_CODE_SDCARD)
    private void login(){
        mLoginPresenter.doLogin();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mLoginPresenter.isSureExit(Login.this);
        }
        return false;
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showShortToast(this, msg);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra("num", "0");
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

    @Override
    public void showError(String msg) {
        ToastUtil.showShortToast(getApplicationContext(), msg);
    }

    @Override
    public void showError() {
        Snackbar.make(login,getString(R.string.snack_infor),Snackbar.LENGTH_SHORT).setAction("重试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.doLogin();
            }
        }).show();
    }

    @Override
    public String getCompany() {
        return edit_company.getText().toString().trim();
    }

    @Override
    public String getUsername() {
        return edit_username.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return edit_password.getText().toString().trim();
    }

}
