package com.xuliucar.xuli.xuliucar.pay;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.pay.alipay.domain.PayResult;
import com.xuliucar.xuli.xuliucar.pay.alipay.util.SignUtils;
import com.xuliucar.xuli.xuliucar.pay.wxpay.Constants;
import com.xuliucar.xuli.xuliucar.pay.wxpay.Util;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class PayActivity extends AppCompatActivity {
    private Button ali_pay,wx_pay;
    // 商户PID
    public static final String PARTNER = "2088221430178440";
    // 商户收款账号
    public static final String SELLER = "gelantang@qq.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKx2OlRXp6X7gaAhLmIrjNlpCOIDGgOjo/vrtnjRjoV6oDPa2mJoO6vRsAGWwgncBC66mZYSYPU40zEj3PYsI8CVhJoRXpIvKV0SGJutQWlvo2ofMEJ34kCb9ZPN2AcEK/ExEPtZY5B5GaOlu+563n8tsu22pSjFUt3orTd6t3FVAgMBAAECgYA4oVnl+CKEzmFyQ3MHqWVGvsa78GjKaSGfx3bTlXxHOE0Cugqb5b4yXVh+LRLqSeTVs1Bbhw1veIlgyXX1H+lc+aBjTZWY1KfoV/Vmz/1hdRbl0mAoI7wFfv2rhdZQKrfGpmMLvUup0ABOI1P9oKUSCEPMN2K/NDf5IerBagxwAQJBANrUT6O8cR1Y4rZZVZzy1e6H36fTnyOrl3lMtfBhRx+grtYXsF0qv8jjLdHri/nvODZDdnSi3bK03naSVnxQksECQQDJwaXQHumPbSi02b6r82OCuKCgBbho6RWveDTOZ9UB84vYq2u3/nz+ISo4qn8wZBxdATV1mhoS6ZeYgZ6zPseVAkBRlDTcZbQFrM1YDZXNHPqEIkNO3aXGNoIRxndNVfad49lKFyq4kOdOjOLYD2fvlS7vi9btRIpBxQvqSEPVtHQBAkB/Gj7ioUbUs5ksbezgGOH87WFLzq7BcO3XcGwcCG+53fuhEUU4UdveoXge4NJl4vu3X9aU8vMwLPby/d3D4G4NAkB9eREg5tqXlYfpY2Aog+XOok7IBhkDatSYAURCM57w1/7cedUXbWs6HlKLJoBMHcHKqDL6C6MjBb5F3yvVVd8b";
    private static final int SDK_PAY_FLAG = 1;
    //微信支付的
    private IWXAPI api;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((String)msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if(TextUtils.equals(resultStatus,"9000")){
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    }else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if(TextUtils.equals(resultStatus,"8000")){
                            Toast.makeText(PayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        }else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            }
            return true;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_activity);

        api = WXAPIFactory.createWXAPI(this, "wx0b367790146b27c4");
        // 将该app注册到微信
        api.registerApp(Constants.APP_ID);
        ali_pay = (Button) findViewById(R.id.ali_pay);
        ali_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aliPay();
            }
        });

        wx_pay = (Button) findViewById(R.id.wx_pay);
        wx_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wxPay();
            }
        });
    }

    private void wxPay(){
        String url = "http://wxpay.weixin.qq.com/pub_v2/app/app_pay.php?plat=android";
        try{
            byte[] buf = Util.httpGet(url);
            if(buf != null && buf.length >0){
                String content = new String(buf);
                JSONObject json= new JSONObject(content);
                if(null != json && !json.has("retcode")){
                    PayReq req = new PayReq();
                    req.appId = "wx0b367790146b27c4";
                    //req.appId = json.getString("appid");
                    req.partnerId		= json.getString("partnerid");
                    req.prepayId		= json.getString("prepayid");
                    req.nonceStr		= json.getString("noncestr");
                    req.timeStamp		= json.getString("timestamp");
                    req.packageValue	= json.getString("package");
                    req.sign			= json.getString("sign");
                    req.extData			= "app data"; // optional
                    api.sendReq(req);
                }else {
                    Log.d("PAY_GET", "返回错误"+json.getString("retmsg"));
                    ToastUtil.showShortToast(getApplicationContext(),"返回错误 "+json.getString("retmsg"));
                }
            }else {
                Log.d("PAY_GET", "服务器请求错误");
                ToastUtil.showShortToast(getApplicationContext(),"服务器请求错误");
            }

        }catch (Exception e){
            Log.e("PAY_GET", "异常："+e.getMessage());
            ToastUtil.showShortToast(getApplicationContext(),"异常"+e.getMessage());
        }



    }

    private void aliPay(){
        String orderInfo = getOrderInfo("测试的商品","该商品的详细描述","0.01");
        //特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo+"&sign=\""+sign+"\"&"+getSignType();
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask aliPay = new PayTask(PayActivity.this);
                // 调用支付接口，获取支付结果
                String  result = aliPay.pay(payInfo,true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    //创建订单信息
    private String getOrderInfo(String subject,String body,String price){
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        return orderInfo;
    }

    /**
     * 对订单信息进行签名
     */
    private String sign(String content){
        return SignUtils.sign(content,RSA_PRIVATE);
    }

    /**
     * 获取签名格式
     */
    private String getSignType(){
        return "sign_type=\"RSA\"";
    }
    /**
     * 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    private String getOutTradeNo(){
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }
}
