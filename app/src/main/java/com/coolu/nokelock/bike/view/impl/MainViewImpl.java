package com.coolu.nokelock.bike.view.impl;


import com.coolu.nokelock.bike.bean.BikeClockBean;
import com.coolu.nokelock.bike.bean.MacBeann;
import com.coolu.nokelock.bike.bean.UseBean;
import com.coolu.nokelock.bike.bean.UserEntityBean;
import com.coolu.nokelock.bike.view.MainView;

import java.util.List;

/**
 * Created by admin on 2017/8/14.
 */

public interface MainViewImpl extends MainView {

    void getInfo(UseBean useBean);
    void getInfo2(UserEntityBean user);

    void getMac(MacBeann macBean);
    void upBleOpenLock(String json);

    void upBleCloseLock(String time, String money,List<BikeClockBean.ResultBean.YouhuiBean> youhui,String forceMoney);

    void upBleCancelLock(String json);
    void CancleDiolag(); //取消对话框
    void getNewMac(String json); //新锁
   // void getStopPoint(StopPointBean stopPointBean);

//    void getLine(LineBean lineBean);

    void ForgetClose(String json);


}
