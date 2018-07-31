package com.xuliucar.xuli.xuliucar.ui.homePage.illegalQuery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.CarTypeAdapter;
import com.xuliucar.xuli.xuliucar.widget.MyDialog;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class AutoQuery extends AppCompatActivity {
    private Spinner spinner;
    private Toolbar illegal_info_List_toobar;
    private TextView count_points,already_use,today_give,car_type;
    private EditText plate_num,frame_last_six_num,engine_last_six_num;
    private RelativeLayout car_type_layout;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private RadioGroup carType_radioGroup;
    private Button auto_query_sub_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_query);
        initView();
    }

    private void initView() {
        SpannableString s = new SpannableString(getString(R.string.input_plate_num_tips));
        SpannableString s1 = new SpannableString(getString(R.string.frame_last_six_num_tips));
        SpannableString s2 = new SpannableString(getString(R.string.engine_last_six_num_tips));
        AbsoluteSizeSpan as = new AbsoluteSizeSpan(12, true);
        s.setSpan(as,0,s.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        s1.setSpan(as, 0, s1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        s2.setSpan(as, 0, s2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        illegal_info_List_toobar = (Toolbar) findViewById(R.id.illegal_info_List_toobar);
        count_points = (TextView) findViewById(R.id.count_points);
        already_use = (TextView) findViewById(R.id.already_use);
        today_give = (TextView) findViewById(R.id.today_give);
        car_type = (TextView) findViewById(R.id.car_type);
        spinner = (Spinner) findViewById(R.id.spinner);
        plate_num = (EditText) findViewById(R.id.plate_num);
        frame_last_six_num = (EditText) findViewById(R.id.frame_last_six_num);
        engine_last_six_num = (EditText) findViewById(R.id.engine_last_six_num);
        car_type_layout = (RelativeLayout) findViewById(R.id.car_type_layout);
        carType_radioGroup = (RadioGroup) findViewById(R.id.carType_radioGroup);
        auto_query_sub_layout = (Button) findViewById(R.id.auto_query_sub_layout);
        plate_num.setHint(new SpannableString(s));
        frame_last_six_num.setHint(new SpannableString(s1));
        engine_last_six_num.setHint(new SpannableString(s2));
        car_type.setText("请选择车辆类型");
        illegal_info_List_toobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //数据
        data_list = new ArrayList<String>();
        data_list.add("粤A");
        data_list.add("粤B");
        data_list.add("粤");
        data_list.add("粤A");
        data_list.add("粤B");
        data_list.add("粤");
        data_list.add("粤A");
        data_list.add("粤B");
        data_list.add("粤");
        data_list.add("粤A");
        data_list.add("粤B");
        data_list.add("粤");
        data_list.add("粤A");
        data_list.add("粤B");
        data_list.add("粤");
        data_list.add("粤A");
        data_list.add("粤B");
        data_list.add("粤");
        data_list.add("粤A");
        data_list.add("粤B");
        data_list.add("粤");
        data_list.add("自定义");
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(arr_adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = data_list.get(position);
                if(value.equals("自定义")){

                }
                Log.i("myLog","选择了 "+value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        car_type_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertCarType();
            }
        });

        carType_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.person) {
                    ToastUtil.showShortToast(getApplicationContext(), "选择个人");

                } else if (checkedId == R.id.company) {
                    ToastUtil.showShortToast(getApplicationContext(), "选择单位");

                }
            }
        });

        auto_query_sub_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void alertCarType() {
        LayoutInflater  inflater = LayoutInflater.from(this);
        final ViewGroup nullParent = null;
        View view = inflater.inflate(R.layout.alert_car_type,nullParent);
        final MyDialog dialog = new MyDialog(this,0,0,view,R.style.DialogTheme);
        dialog.setCancelable(false);
        dialog.show();
        WindowManager m = this.getWindowManager();
        Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        lp.x = -1; // 新位置X坐标 负值表示这个被忽略不起作用
        lp.y = -1; // 新位置Y坐标
        lp.width = (int) (d.getWidth() * 0.7); // 宽度
        lp.alpha = 1f; // 透明度
        dialogWindow.setAttributes(lp);
        dialog.setCanceledOnTouchOutside(true);//触摸到对话框以外自动关闭对话框

        ListView car_Type_list = (ListView) view.findViewById(R.id.car_Type_list);
        final List<String> carTypeList = new ArrayList<>();
        carTypeList.add("小型汽车");
        carTypeList.add("中型汽车");
        carTypeList.add("大型汽车");
        carTypeList.add("卡车");
        CarTypeAdapter adapter = new CarTypeAdapter(this,carTypeList);
        car_Type_list.setAdapter(adapter);

        car_Type_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                car_type.setText(carTypeList.get(position));
                dialog.dismiss();
            }
        });

    }

}
