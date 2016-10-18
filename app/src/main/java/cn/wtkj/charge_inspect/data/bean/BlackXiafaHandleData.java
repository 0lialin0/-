package cn.wtkj.charge_inspect.data.bean;

import java.io.Serializable;

/**
 * Created by ghj on 2016/10/17.
 */
public class BlackXiafaHandleData implements Serializable {

    private String USERID; //用户ID
    private String BLACKLISTID;  //黑名单ID
    private String PARTYNAME;    //当事人姓名
    private String PAYMONEY;    //补缴金额
    private String RUNCARDNUM;    //行驶证档案号
    private String DRIVECARDNUM;    //驾驶证档案号
    private String REMARK;    //处理备注
    private String DISPOSENAME;    //处理人


    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getBLACKLISTID() {
        return BLACKLISTID;
    }

    public void setBLACKLISTID(String BLACKLISTID) {
        this.BLACKLISTID = BLACKLISTID;
    }

    public String getPARTYNAME() {
        return PARTYNAME;
    }

    public void setPARTYNAME(String PARTYNAME) {
        this.PARTYNAME = PARTYNAME;
    }

    public String getPAYMONEY() {
        return PAYMONEY;
    }

    public void setPAYMONEY(String PAYMONEY) {
        this.PAYMONEY = PAYMONEY;
    }

    public String getRUNCARDNUM() {
        return RUNCARDNUM;
    }

    public void setRUNCARDNUM(String RUNCARDNUM) {
        this.RUNCARDNUM = RUNCARDNUM;
    }

    public String getDRIVECARDNUM() {
        return DRIVECARDNUM;
    }

    public void setDRIVECARDNUM(String DRIVECARDNUM) {
        this.DRIVECARDNUM = DRIVECARDNUM;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getDISPOSENAME() {
        return DISPOSENAME;
    }

    public void setDISPOSENAME(String DISPOSENAME) {
        this.DISPOSENAME = DISPOSENAME;
    }
}
