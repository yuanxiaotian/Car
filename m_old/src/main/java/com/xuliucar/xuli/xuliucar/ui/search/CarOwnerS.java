package com.xuliucar.xuli.xuliucar.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.ContactsSearch;
import com.xuliucar.xuli.xuliucar.adapter.SearchAdapter;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.base.BaseActivity;
import com.xuliucar.xuli.xuliucar.bean.ContactsBean;
import com.xuliucar.xuli.xuliucar.bean.SearchLightBean;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.SearchLightUtil;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;
import com.xuliucar.xuli.xuliucar.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CarOwnerS extends BaseActivity implements SearchView.SearchViewListener,AdapterView.OnItemClickListener{
    private static final String URL="http://www.gzxlxx.com:8866/index.php/Home/App/user_list?utype=owner";
    private ContactsSearch resultAdapter;
    private ListView lvResults;
    private List<ContactsBean> contactsBeanList;
    private List<ContactsBean> resultData;
    private SearchAdapter autoCompleteAdapter;
    private List<SearchLightBean> autoCompleteData;
    private List<String> allData;
    private List<String> filterAutoCompleteData;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
        initData();
    }

    @Override
    public void initView() {
        lvResults = (ListView) findViewById(R.id.recyclerView);
        SearchView searchView = (SearchView) findViewById(R.id.main_search_layout);
        //设置监听
        searchView.setSearchViewListener(this);
        searchView.setAutoCompleteAdapter(autoCompleteAdapter,autoCompleteData);
        lvResults.setOnItemClickListener(this);
    }


    protected void initData() {
        getData();
        //初始化自动补全数据
        getAutoCompleteData(null);
        //初始化搜索结果数据
        getResultData(null);
    }
    private void getData() {
        contactsBeanList = new ArrayList<>();
        allData = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("loginid", App.loginid)
                .add("compid", String.valueOf(App.compid))
                .build();
        Request request  = new Request.Builder()
                .addHeader("cookie", App.cookie)
                .url(URL)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShortToast(getApplicationContext(),"网络访问错误!");
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
                            //Log.i("myLog","object "+object);
                            String success = object.getString("success");
                            if(success.equals("true")){
                                JSONArray jsonArray = object.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object1 = jsonArray.getJSONObject(i);
                                    String id = object1.getString("id");
                                    String phone = object1.getString("phone");
                                    String phone2 = object1.getString("phone2");
                                    String ownername = object1.getString("ownername");
                                    String search = ownername + "  " + phone;
                                    contactsBeanList.add(new ContactsBean(id,ownername,phone,phone2,search));
                                    allData.add(contactsBeanList.get(i).getSortLetters());
                                }
                                filterAutoCompleteData  = removeDuplicateWithOrder(allData);
                            }else if (success.equals("false")) {
                                String msg = object.getString("message");
                                if (msg.equals("already logout")) {
                                    ToastUtil.showShortToast(getApplicationContext(),msg);
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                }else if (msg.equals("未登陆")) {
                                    Intent intent = new Intent(CarOwnerS.this,Login.class);
                                    startActivity(intent);
                                }
                            }
                            String msg = object.getString("message");
                            if (msg.equals("已登出,或在其它设备上登陆!")) {
                                ToastUtil.showShortToast(getApplicationContext(), msg);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i = new Intent(CarOwnerS.this, Login.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }, 1000);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    //过滤自动填充搜索内容
    @SuppressWarnings("unchecked")
    private static List<String> removeDuplicateWithOrder(List<String> list) {
        Set set = new HashSet();
        List<String> newList = new ArrayList();
        for (String element : list) {
            if (set.add(element))
                newList.add(element);
        }
        return newList;
    }

    /**
     * 获取自动补全data 和adapter
     */
    private void getAutoCompleteData(String text) {

        Log.i("myLog","输入 "+text);
        int hintSize = 20;
        if (autoCompleteData == null) {
            //初始化
            autoCompleteData = new ArrayList<>(hintSize);
        } else {
            // 根据text 获取auto data
            autoCompleteData.clear();
            for (int i = 0, count = 0; i < filterAutoCompleteData.size() && count < hintSize; i++) {
                if (filterAutoCompleteData.get(i).contains(text.trim())) {
                    SpannableString s = SearchLightUtil.setKeyWordColor(filterAutoCompleteData.get(i),text);
                    autoCompleteData.add(new SearchLightBean(s));
                    count++;
                }
            }
        }
        if (autoCompleteAdapter == null) {
            autoCompleteAdapter = new SearchAdapter(CarOwnerS.this,autoCompleteData);
        } else {
            autoCompleteAdapter.notifyDataSetChanged();
        }
    }


    /**
     * 获取搜索结果data和adapter
     */
    private void getResultData(String text) {
        if (resultData == null) {
            // 初始化
            resultData = new ArrayList<>();
        } else {
            resultData.clear();
            for (int i = 0; i < contactsBeanList.size(); i++) {
                if (contactsBeanList.get(i).getSortLetters().contains(text.trim())) {
                    resultData.add(contactsBeanList.get(i));
                }
            }
        }
        if (resultAdapter == null) {
            resultAdapter = new ContactsSearch(this, resultData);
        } else {
            resultAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefreshAutoComplete(String text) {
        getAutoCompleteData(text);
    }

    @Override
    public void onSearch(String text) {
        //更新result数据
        getResultData(text);

        //第一次获取结果 还未配置适配器
        if (lvResults.getAdapter() == null) {
            //获取搜索数据 设置适配器
            lvResults.setAdapter(resultAdapter);

        } else {
            //更新搜索数据
            resultAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        ContactsBean contactsBean = resultData.get(i);
//        Intent intent = new Intent(CarOwnerS.this,CarOwnerDetail.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("contactsBean",contactsBean);
//        intent.putExtras(bundle);
//        startActivity(intent);
    }
}
