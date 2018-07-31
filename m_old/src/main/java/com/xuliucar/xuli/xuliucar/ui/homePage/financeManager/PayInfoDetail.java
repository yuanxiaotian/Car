package com.xuliucar.xuli.xuliucar.ui.homePage.financeManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.PayInfoDetailAdapter;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.base.BaseActivity;
import com.xuliucar.xuli.xuliucar.bean.Dates;
import com.xuliucar.xuli.xuliucar.bean.PayInfoBean;
import com.xuliucar.xuli.xuliucar.bean.PayInfoDY;
import com.xuliucar.xuli.xuliucar.bean.PushBean;
import com.xuliucar.xuli.xuliucar.bean.payInfoDetailBean;
import com.xuliucar.xuli.xuliucar.config.UrlConfig;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.ui.index.MainActivity;
import com.xuliucar.xuli.xuliucar.utils.DateUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PayInfoDetail extends BaseActivity {
    private TextView pdplates;
    private TextView pdownername;
    private TextView pdct;
    private TextView pdmd;
    private TextView pdtw;
    private TextView pdew;
    private TextView pdlw;
    private TextView pdem;
    private TextView pdtotalcharge;
    private TextView pdtotalapd;
    private TextView pdtotaldeposits;
    private String cid;
    private PayInfoDetailAdapter adapter;
    private List<Dates> mList = null;
    private String year;
    private List<String> group_list;//年份的标题
    private List<payInfoDetailBean> payInfoBeanList;
    private List<PayInfoDY> item_lt;//年份详情
    private List<List<PayInfoDY>> item_list;
    private ExpandableListView expandableListView;
    private String getItemid;
    private String goItemid;
    private String getYear;
    private int goYear;
    private String push;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.pay_info_detail);

    }

    @Override
    protected void getDates() {
        getPreListViewData();
        getInformationData();
        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void initView() {
        Toolbar payinfod_toolbar = (Toolbar) findViewById(R.id.payinfod_toolbar);
        expandableListView = (ExpandableListView) findViewById(R.id.expendlist);
        group_list = new ArrayList<>();
        item_list = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(PayInfoDetail.this);
        final ViewGroup nullParent = null;
        View view = inflater.inflate(R.layout.pay_info_detail_header,nullParent);
        expandableListView.setGroupIndicator(null);
        pdplates = (TextView) view.findViewById(R.id.pdplates);
        pdownername = (TextView) view.findViewById(R.id.pdownername);
        pdct = (TextView) view.findViewById(R.id.pdct);
        pdmd = (TextView) view.findViewById(R.id.pdmd);
        pdtw = (TextView) view.findViewById(R.id.pdtw);
        pdew = (TextView) view.findViewById(R.id.pdew);
        pdlw = (TextView) view.findViewById(R.id.pdlw);
        pdem = (TextView) view.findViewById(R.id.pdem);
        pdtotalcharge = (TextView) view.findViewById(R.id.pdtotalcharge);
        pdtotalapd = (TextView) view.findViewById(R.id.pdtotalapd);
        pdtotaldeposits = (TextView) view.findViewById(R.id.pdtotaldeposits);
        expandableListView.setSelected(true);
        expandableListView.addHeaderView(view);
        adapter = new PayInfoDetailAdapter(PayInfoDetail.this,group_list,item_lt,item_list);
        expandableListView.setAdapter(adapter);

        payinfod_toolbar.setNavigationIcon(R.drawable.back);
        payinfod_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(push)){
                    finish();
                }else {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("num","0");
                    startActivity(intent);
                    finish();
                }

            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                PayInfoDY payInfoDY = item_list.get(groupPosition).get(childPosition);
                String mark = payInfoDY.getMark();
                if(!TextUtils.isEmpty(mark)){
                    showMsgAll(mark);
                }
                return false;
            }
        });

    }

    private void showMsgAll(String content){
        AlertDialog.Builder builder = new AlertDialog.Builder(PayInfoDetail.this);
        builder.setMessage(content);
        builder.create().show();
    }

    private void getPreListViewData() {
        PayInfoBean.DataBean payInfoBean = (PayInfoBean.DataBean) getIntent().getSerializableExtra("payinfo");
        if (payInfoBean != null) {
            cid = payInfoBean.getCid();
        }

        Intent intent = getIntent();
        String iscid = intent.getStringExtra("cid");

        if(!TextUtils.isEmpty(iscid)){
            cid = iscid;
            getItemid = intent.getStringExtra("itemid");
            getYear = intent.getStringExtra("year");
            push = intent.getStringExtra("push");
        }
    }

    private void getInformationData() {
        PushBean pushBean = (PushBean) getIntent().getSerializableExtra("pushBean");
        if(pushBean != null){
            getItemid = pushBean.getItemid();
            getYear = pushBean.getYear();
            cid = pushBean.getCid();
        }

    }


    private void getData() {
        payInfoBeanList = new ArrayList<>();
        mList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("cid", cid)
                .add("loginid", App.loginid)
                .build();
        Request request = new Request.Builder()
                .addHeader("cookie", App.cookie)
                .url(UrlConfig.payInfoDetail_url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShortToast(getApplicationContext(),"网络连接异常!");
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
                            String success = object.getString("success");
                            if (success.equals("true")) {
                                JSONObject object1 = object.getJSONObject("data");

//                        Log.i("myLog", "object1" + object1);
                                String plates = object1.getString("plates");
                                String ownername = object1.getString("ownername");
                                String ct = object1.getString("ct");
                                String md = object1.getString("md");
                                String tw = object1.getString("tw");
                                String ew = object1.getString("ew");
                                String lw = object1.getString("lw");
                                String em = object1.getString("em");
                                String totalcharge = object1.getString("totalcharge");
                                String totalapd = object1.getString("totalapd");
                                String totaldeposits = object1.getString("totaldeposits");
                                pdplates.setText(plates);
                                pdownername.setText(ownername);
                                pdct.setText(ct);
                                pdmd.setText(md);
                                pdtw.setText(tw);
                                pdew.setText(ew);
                                pdlw.setText(lw);
                                pdem.setText(em);
                                pdtotalcharge.setText(totalcharge);
                                pdtotalapd.setText(totalapd);
                                pdtotaldeposits.setText(totaldeposits);

                                JSONObject object2 = object1.getJSONObject("detail");

                                Iterator iterator = object2.keys();
                                while (iterator.hasNext()) {
                                    String key = iterator.next() + "";
                                    mList.add(new Dates(key));
                                }

                                Collections.sort(mList, new Comparator<Dates>() {
                                    @Override
                                    public int compare(Dates lhs, Dates rhs) {
                                        java.util.Date date1 = DateUtil.stringToDate(lhs.getTime());
                                        java.util.Date date2 = DateUtil.stringToDate(rhs.getTime());

                                        // 对日期字段进行升序，如果欲降序可采用after方法
                                        if (date1.before(date2)) {
                                            return 1;
                                        }
                                        return -1;
                                    }
                                });
                                for (int i = 0; i < object2.length(); i++) {
                                    JSONArray jsonArray = object2.getJSONArray(mList.get(i).getTime());
                                    String json = "{\"" + mList.get(i).getTime() + "\":" + jsonArray + "}";//把某一年分的数据获取下来并把年加进来拼接成数组
//                            Log.i("myLog","年份 " + mList.get(i).getTime());
                                    group_list.add(mList.get(i).getTime());
                                    payInfoBeanList.add(new payInfoDetailBean(json));


                                }

                                //Log.i("myLog","payInfoBeanList.size " +payInfoBeanList.size());
                                for (int i = 0; i <payInfoBeanList.size(); i++) {
                                    item_lt = new ArrayList<>();
                                    payInfoDetailBean payInfoBean = payInfoBeanList.get(i);
                                    String json = payInfoBean.getStr();
                                    JSONObject jsonObject = new JSONObject(json);

                                    Iterator iterator2 = jsonObject.keys();
                                    while (iterator2.hasNext()){
                                        year = iterator2.next()+"";
                                    }

                                    if(TextUtils.equals(getYear,year)){
                                        goYear = i;
                                    }

                                    JSONArray jsonArray =jsonObject.getJSONArray(year);
                                    //Log.i("myLog","jsonArray" + jsonArray);

                                    for (int j = 0; j < jsonArray.length(); j++) {
                                        JSONObject ob=jsonArray.getJSONObject(j);
                                        String mark = ob.getString("mark");
                                        String item  = ob.getString("item");
                                        String charge=ob.getString("charge");
                                        String apd = ob.getString("apd");
                                        String ct2=ob.getString("ct");
                                        String udp =ob.getString("upd");
                                        String itemid = ob.getString("itemid");
                                        //Log.i("myLog","itemid "+itemid);
                                        if(itemid.equals(getItemid)){
                                            String jj = String.valueOf(j);
                                            goItemid =jj ;
                                            // Log.i("myLog","里面的goitemid "+goItemid);
                                        }
                                        item_lt.add(new PayInfoDY(mark,item,charge,apd,ct2,udp,goItemid));


                                    }

                                    item_list.add(item_lt);
                                    //   Log.i("myLog","i "+ i);
                                }
//                        Log.i("myLog","item_lt.size " +item_lt.size());
//                        Log.i("myLog","item_list.size " +item_list.size());
                                for (int i = 0; i < adapter.getGroupCount(); i++) {
                                    expandableListView.expandGroup(i);
                                }
                                adapter.notifyDataSetChanged();
                                //Log.i("myLog","goitemid" + goItemid);
                                // Log.i("myLog","goYear" +goYear);
                                if(!TextUtils.isEmpty(push) || !TextUtils.isEmpty(getItemid)){
                                    int intgoItemid = Integer.parseInt(goItemid);
                                    expandableListView.setSelectedChild(goYear,intgoItemid,true);
                                }

                            } else if (success.equals("false")) {
                                String msg = object.getString("message");
                                if (msg.equals("already logout")) {
                                    ToastUtil.showShortToast(getApplicationContext(),msg);
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                } else if (msg.equals("未登陆")) {
                                    Intent intent = new Intent(PayInfoDetail.this, Login.class);
                                    startActivity(intent);
                                }
                            }
                            String msg = object.getString("message");
                            if (msg.equals("已登出,或在其它设备上登陆!")) {
                                ToastUtil.showShortToast(getApplicationContext(), msg);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i = new Intent(PayInfoDetail.this, Login.class);
                                        PreferencesUtils.clearSharePre(getApplicationContext(), "userInfo", "password");
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            if(TextUtils.isEmpty(push)){
                finish();
            }else {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("num","0");
                startActivity(intent);
                finish();
            }
        }

        return false;

    }


    }

