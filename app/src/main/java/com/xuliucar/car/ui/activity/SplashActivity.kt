package com.xuliucar.car.ui.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import com.cangmaomao.lib.action.a_main
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.car.R

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_splash)
        val loginId = create("userInfo").get("loginid", "")
        if (TextUtils.isEmpty(loginId as String)) {
            Handler().postDelayed(
                    {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(a_main)).putExtra("flag", 110))
                        finish()
                    }
                    , 2500)
        } else {
            Handler().postDelayed(
                    {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(a_main)).putExtra("flag", 111))
                        finish()
                    }
                    , 2500)
        }
    }
}