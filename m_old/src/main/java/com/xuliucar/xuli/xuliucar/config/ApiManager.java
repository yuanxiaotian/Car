package com.xuliucar.xuli.xuliucar.config;

import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xuliucar.xuli.xuliucar.base.App;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by skyward on 2016/11/21.
 *
 */

public class ApiManager {

    private ApiConfig mApiConfig;
    private final Object mObject = new Object();


    private static class InnerApiManager{
        private static final ApiManager instance = new ApiManager();
    }
    public static ApiManager getInstance(){
        return InnerApiManager.instance;
    }

    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    if(TextUtils.isEmpty(App.cookie)){//获取cookie
                        Response response = chain.proceed(chain.request());
                        if(!response.header("Set-Cookie").isEmpty()) {
                            final StringBuffer buffer = new StringBuffer();
                            Observable.from(response.headers("Set-Cookie"))
                                    .map(new Func1<String, String>() {
                                        @Override
                                        public String call(String s) {
                                            String[] cookieArray = s.split(";");
                                            return cookieArray[0];
                                        }
                                    })
                                    .subscribe(new Action1<String>() {
                                        @Override
                                        public void call(String s) {
                                            buffer.append(s).append(";");
                                        }
                                    });
                            App.cookie = buffer.toString();
                        }
                            return response;
                    }else {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("cookie", App.cookie)
                                .build();
                        return chain.proceed(request);
                    }

                }
            }).build();

    public ApiConfig getCommonApi(){
        if(mApiConfig == null){
            synchronized (mObject){
                if(mApiConfig == null){
                    mApiConfig = new Retrofit.Builder()
                            .baseUrl("http://www.gzxlxx.com:8866/index.php/Home/App/")
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(ApiConfig.class);
                }
            }
        }
        return mApiConfig;
    }


}
