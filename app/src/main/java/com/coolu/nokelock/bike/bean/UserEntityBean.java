package com.coolu.nokelock.bike.bean;

/**
 * Created by admin on 2017/9/22.
 */

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 *   "id": 897667898452082700,
 "userId": 18813151324,
 "nicName": "18813151324",
 "idcheck": 0,
 "pinNo": "2984",
 "pinTime": "2017-08-22 11:01:30",
 "userToken": "06883fc21b68599187df134811df413d",
 "loginTime": "2017-08-21 13:56:03",
 "deposit": 0,
 "defaultDeposit": 99,
 "orderNo": "60e33526d0f1554d0ba0d6343116a2b1bd",
 "userMoney": 0,
 "userBonus": 0,
 "userType": 0,
 "userStatus": 1,
 "userCredit": 100,
 "idealMoney": 0,
 "userLevel": 0,
 "userFrom": "",
 "userFromUrl": "",
 "userLabel": "北戴河"
 */
@Entity
public class UserEntityBean {
    private String userId;
    private String nicName;
    private String idcheck;
    private String pinNo;
    private  String pinTime;
    private String userToken;
    private String loginTime;
    private String deposit;  //保证金
    private String defaultDeposit;
    private String orderNo;
    private String userMoney;  //余额
    private String userBonus; //红包
    private String userType;
    private String userStatus;
    private String userCredit;
    private String idealMoney;

    private String userFrom;
    private String userFromUrl;
    private String userLabel;
    private String shebieId;
    private String ridingprice;
    private String lockid;
    private String userPic;
    private float openmoney;  //余额最低数，才能打开

    private String userLevel;  //会员

    private String userLevelEndTime; //会员到期时间

    private String barCode;
    private String lockmac;  //mac

    private String locktype; //锁的类型

    private String lockdata;
    private String starttime=null;

    private String idno;
    private String userName;
    private String forcemoney; //强制还车的钱
    private String cardprice;  //优惠券的金额

//    Learning add
//    控制会员卡套餐界面的先不购买按钮的显示
    private String isbuyridingcard;
    private float newopenmoney;
    private float warningmoney;

    public float getWarningmoney() {
        return warningmoney;
    }

    public void setWarningmoney(float warningmoney) {
        this.warningmoney = warningmoney;
    }

    public float getNewopenmoney() {
        return newopenmoney;
    }

    public void setNewopenmoney(float newopenmoney) {
        this.newopenmoney = newopenmoney;
    }

    public String getIsbuyridingcard() {
        return isbuyridingcard;
    }

    public void setIsbuyridingcard(String isbuyridingcard) {
        this.isbuyridingcard = isbuyridingcard;
    }

    public String getCardprice() {
        return cardprice;
    }

    public void setCardprice(String cardprice) {
        this.cardprice = cardprice;
    }

    public String getForcemoney() {
        return forcemoney;
    }

    public void setForcemoney(String forcemoney) {
        this.forcemoney = forcemoney;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getLockdata() {
        return lockdata;
    }

    public void setLockdata(String lockdata) {
        this.lockdata = lockdata;
    }

    public String getLocktype() {
        return locktype;
    }

    public void setLocktype(String locktype) {
        this.locktype = locktype;
    }

    public String getLockmac() {
        return lockmac;
    }

    public void setLockmac(String lockmac) {
        this.lockmac = lockmac;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getUserLevelEndTime() {
        return userLevelEndTime;
    }

    public void setUserLevelEndTime(String userLevelEndTime) {
        this.userLevelEndTime = userLevelEndTime;
    }

    public float getOpenmoney() {
        return openmoney;
    }

    public void setOpenmoney(float openmoney) {
        this.openmoney = openmoney;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getRidingprice() {
        return ridingprice;
    }

    public void setRidingprice(String ridingprice) {
        this.ridingprice = ridingprice;
    }

    public String getLockid() {
        return lockid;
    }

    public void setLockid(String lockid) {
        this.lockid = lockid;
    }

    public String getShebieId() {
        return shebieId;
    }

    public void setShebieId(String shebieId) {
        this.shebieId = shebieId;
    }

    public String getUserLabel() {
        return this.userLabel;
}
public void setUserLabel(String userLabel) {
        this.userLabel = userLabel;
}
public String getUserFromUrl() {
        return this.userFromUrl;
}
public void setUserFromUrl(String userFromUrl) {
        this.userFromUrl = userFromUrl;
}
public String getUserFrom() {
        return this.userFrom;
}
public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
}
public String getUserLevel() {
        return this.userLevel;
}
public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
}
public String getIdealMoney() {
        return this.idealMoney;
}
public void setIdealMoney(String idealMoney) {
        this.idealMoney = idealMoney;
}
public String getUserCredit() {
        return this.userCredit;
}
public void setUserCredit(String userCredit) {
        this.userCredit = userCredit;
}
public String getUserStatus() {
        return this.userStatus;
}
public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
}
public String getUserType() {
        return this.userType;
}
public void setUserType(String userType) {
        this.userType = userType;
}
public String getUserBonus() {
        return this.userBonus;
}
public void setUserBonus(String userBonus) {
        this.userBonus = userBonus;
}
public String getUserMoney() {
        return this.userMoney;
}
public void setUserMoney(String userMoney) {
        this.userMoney = userMoney;
}
public String getOrderNo() {
        return this.orderNo;
}
public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
}
public String getDefaultDeposit() {
        return this.defaultDeposit;
}
public void setDefaultDeposit(String defaultDeposit) {
        this.defaultDeposit = defaultDeposit;
}
public String getDeposit() {
        return this.deposit;
}
public void setDeposit(String deposit) {
        this.deposit = deposit;
}
public String getLoginTime() {
        return this.loginTime;
}
public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
}
public String getUserToken() {
        return this.userToken;
}
public void setUserToken(String userToken) {
        this.userToken = userToken;
}
public String getPinTime() {
        return this.pinTime;
}
public void setPinTime(String pinTime) {
        this.pinTime = pinTime;
}
public String getPinNo() {
        return this.pinNo;
}
public void setPinNo(String pinNo) {
        this.pinNo = pinNo;
}
public String getIdcheck() {
        return this.idcheck;
}
public void setIdcheck(String idcheck) {
        this.idcheck = idcheck;
}
public String getNicName() {
        return this.nicName;
}
public void setNicName(String nicName) {
        this.nicName = nicName;
}
public String getUserId() {
        return this.userId;
}
public void setUserId(String userId) {
        this.userId = userId;
}
@Generated(hash = 2058108086)
public UserEntityBean(String userId, String nicName, String idcheck,
        String pinNo, String pinTime, String userToken, String loginTime,
        String deposit, String defaultDeposit, String orderNo,
        String userMoney, String userBonus, String userType, String userStatus,
        String userCredit, String idealMoney, String userFrom,
        String userFromUrl, String userLabel, String shebieId,
        String ridingprice, String lockid, String userPic, float openmoney,
        String userLevel, String userLevelEndTime, String barCode,
        String lockmac, String locktype, String lockdata, String starttime,
        String idno, String userName, String forcemoney, String cardprice,
        String isbuyridingcard, float newopenmoney, float warningmoney) {
    this.userId = userId;
    this.nicName = nicName;
    this.idcheck = idcheck;
    this.pinNo = pinNo;
    this.pinTime = pinTime;
    this.userToken = userToken;
    this.loginTime = loginTime;
    this.deposit = deposit;
    this.defaultDeposit = defaultDeposit;
    this.orderNo = orderNo;
    this.userMoney = userMoney;
    this.userBonus = userBonus;
    this.userType = userType;
    this.userStatus = userStatus;
    this.userCredit = userCredit;
    this.idealMoney = idealMoney;
    this.userFrom = userFrom;
    this.userFromUrl = userFromUrl;
    this.userLabel = userLabel;
    this.shebieId = shebieId;
    this.ridingprice = ridingprice;
    this.lockid = lockid;
    this.userPic = userPic;
    this.openmoney = openmoney;
    this.userLevel = userLevel;
    this.userLevelEndTime = userLevelEndTime;
    this.barCode = barCode;
    this.lockmac = lockmac;
    this.locktype = locktype;
    this.lockdata = lockdata;
    this.starttime = starttime;
    this.idno = idno;
    this.userName = userName;
    this.forcemoney = forcemoney;
    this.cardprice = cardprice;
    this.isbuyridingcard = isbuyridingcard;
    this.newopenmoney = newopenmoney;
    this.warningmoney = warningmoney;
}

@Generated(hash = 1904906531)
public UserEntityBean() {
}


}
