package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2016/7/22.
 *
 */
public class BannerBean {
    private String bannerUrl;

    public BannerBean(){

    }
    public BannerBean(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
}
