package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.PushBean;
import com.xuliucar.xuli.xuliucar.widget.DragView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by skyward on 2016/9/20.
 */
public class InfoCenterAdapter extends BaseAdapter {
    private Context context;
    private List<PushBean> pushBeanList;
    private List<DragView> views = new ArrayList<>();
    private final String URL = "http://www.gzxlxx.com:8866/index.php/Home/App/notedel";
    private final String ISREADURL = "http://www.gzxlxx.com:8866/index.php/Home/App/noteread";

    public InfoCenterAdapter(Context context, List<PushBean> pushBeanList) {
        this.context = context;
        this.pushBeanList = pushBeanList;
    }

    @Override
    public int getCount() {
        return pushBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return pushBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
//        ViewHolder holder = ViewHolder.get(context,view,viewGroup, R.layout.informain_center_item,i);
//        final PushBean pushBean = pushBeanList.get(i);
//        final TextView info_center_content = holder.getView(R.id.info_center_content);
//        final TextView info_center_time = holder.getView(R.id.info_center_time);
//        final ImageView dot_blue = holder.getView(R.id.dot_bule);
//        info_center_content.setText(pushBean.getContent());
//        info_center_time.setText(pushBean.getTimestamp());
//        String isRead = pushBean.getIsread();
//
//        if(isRead.equals("0")){//未读
//            info_center_content.setTextColor(Color.parseColor("#000000"));
//            info_center_time.setTextColor(Color.parseColor("#E9CA1B"));
//            dot_blue.setVisibility(View.VISIBLE);
//        }else{
//            info_center_content.setTextColor(Color.parseColor("#B8B8B8"));
//            info_center_time.setTextColor(Color.parseColor("#B8B8B8"));
//            dot_blue.setVisibility(View.INVISIBLE);
//        }


//         DragView dragView = holder.getView(R.id.drag_view);
//         views.add(dragView);
//
//        dragView.setOnDragStateListener(new DragView.DragStateListener() {
//            @Override
//            public void onOpened(DragView dragView) {
//
//            }
//
//            @Override
//            public void onClosed(DragView dragView) {
//
//            }
//
//            @Override
//            public void onForegroundViewClick(DragView dragView, View v) {
//                info_center_content.setTextColor(Color.parseColor("#B8B8B8"));
//                info_center_time.setTextColor(Color.parseColor("#B8B8B8"));
//                dot_blue.setVisibility(View.INVISIBLE);
//                String migId = pushBean.getMessageid();
//                Intent intent = new Intent(context, PayInfoDetail.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("pushBean",pushBean);
//                intent.putExtras(bundle);
//                postData(ISREADURL,migId);
//                context.startActivity(intent);
//            }
//
//            @Override
//            public void onBackgroundViewClick(DragView dragView, View v) {
//                pushBeanList.remove(i);
//                String msgId = pushBean.getMessageid();
//                postData(URL,msgId);
//                notifyDataSetChanged();
//            }
//
//            @Override
//            public void LongViewClick(DragView dragView, View v) {
//               showMsgAll(pushBean.getContent());
//
//            }
//        });
//
//             dragView.close();

//            return holder.getmConcertView();
        return null;
    }


    public void close() {
        for (int i = 0; i < views.size(); i++) {
            if (views.get(i).isOpen())
                views.get(i).closeAnim();
        }
    }

    private void showMsgAll(String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(content);
        builder.create().show();
    }

    private void postData(String url, String messageid) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("loginid", App.loginid)
                .add("compid", String.valueOf(App.compid))
                .add("messageid", messageid)
                .build();
        Request request = new Request.Builder()
                .addHeader("cookie", App.cookie)
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });

    }

}
