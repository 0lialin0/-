package cn.wtkj.charge_inspect.data.bean;

import java.io.Serializable;

/**
 * Created by ghj on 2016/10/25.
 */
public class OutListParamData implements Serializable {
    public String VEHPLATENO; //车牌号
    public String BEGINTIME; //起始时间
    public String ENDTIME; //结束时间
    public int OUTSTATIONID; //出口站址ID
    public int VEHCLASSID; //车种ID
    public int VEHTYPEID; //车型ID
    public int OUTLANEID; //出口车道ID
    public String OPRNAME;  //收费员
    public int CARDNO;   //卡号
    public int LANETYPE;  //车道类型
    public int PAGESIZE;
    public int PAGENUM;

    public String getVEHPLATENO() {
        return VEHPLATENO;
    }

    public void setVEHPLATENO(String VEHPLATENO) {
        this.VEHPLATENO = VEHPLATENO;
    }

    public String getBEGINTIME() {
        return BEGINTIME;
    }

    public void setBEGINTIME(String BEGINTIME) {
        this.BEGINTIME = BEGINTIME;
    }

    public String getENDTIME() {
        return ENDTIME;
    }

    public void setENDTIME(String ENDTIME) {
        this.ENDTIME = ENDTIME;
    }

    public int getOUTSTATIONID() {
        return OUTSTATIONID;
    }

    public void setOUTSTATIONID(int OUTSTATIONID) {
        this.OUTSTATIONID = OUTSTATIONID;
    }

    public int getVEHCLASSID() {
        return VEHCLASSID;
    }

    public void setVEHCLASSID(int VEHCLASSID) {
        this.VEHCLASSID = VEHCLASSID;
    }

    public int getVEHTYPEID() {
        return VEHTYPEID;
    }

    public void setVEHTYPEID(int VEHTYPEID) {
        this.VEHTYPEID = VEHTYPEID;
    }

    public int getOUTLANEID() {
        return OUTLANEID;
    }

    public void setOUTLANEID(int OUTLANEID) {
        this.OUTLANEID = OUTLANEID;
    }

    public String getOPRNAME() {
        return OPRNAME;
    }

    public void setOPRNAME(String OPRNAME) {
        this.OPRNAME = OPRNAME;
    }

    public int getCARDNO() {
        return CARDNO;
    }

    public void setCARDNO(int CARDNO) {
        this.CARDNO = CARDNO;
    }

    public int getLANETYPE() {
        return LANETYPE;
    }

    public void setLANETYPE(int LANETYPE) {
        this.LANETYPE = LANETYPE;
    }

    public int getPAGESIZE() {
        return PAGESIZE;
    }

    public void setPAGESIZE(int PAGESIZE) {
        this.PAGESIZE = PAGESIZE;
    }

    public int getPAGENUM() {
        return PAGENUM;
    }

    public void setPAGENUM(int PAGENUM) {
        this.PAGENUM = PAGENUM;
    }
}
