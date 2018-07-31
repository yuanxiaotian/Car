package com.xuliucar.xuli.xuliucar.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.pay.wxpay.Constants;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent,this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        Log.i(TAG, "onPayFinish, errCode = " + resp.errCode);
        if(resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.show();
            Intent intent;
            int code = resp.errCode;
            switch (code){
                case 0:
                    ToastUtil.showShortToast(this,"支付成功");
                    finish();
                    break;
                case -1:
                    ToastUtil.showShortToast(this,"支付失败");
                    finish();
                    break;
                case -2:
                    ToastUtil.showShortToast(this,"支付取消");
                    break;
            }
        }
    }
}
