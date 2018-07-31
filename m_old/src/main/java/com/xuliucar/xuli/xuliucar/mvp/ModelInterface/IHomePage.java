package com.xuliucar.xuli.xuliucar.mvp.ModelInterface;

import com.xuliucar.xuli.xuliucar.bean.Counts;
import com.xuliucar.xuli.xuliucar.bean.HomePageBean;

/**
 * Created by skyward on 2016/11/21.
 *
 */

public interface IHomePage {
    void getData(HomePageBean bean);
    void getCounts(Counts counts);
    void getDataResult(Boolean b);
}
