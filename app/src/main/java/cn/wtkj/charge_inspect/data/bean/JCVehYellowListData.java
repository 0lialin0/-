package cn.wtkj.charge_inspect.data.bean;

import java.io.Serializable;

/**
 * Created by ghj on 2016/9/22.
 * 黄名单
 */
public class JCVehYellowListData implements Serializable {
    private String YListID;
    private String VehPlateNo;
    private int VehPlateNoColor;
    private int VehColor;
    private String FactoryType;

    private int VehicleTypeID;
    private int VehType;
    private int Seating;
    private int AxleCount;
    private String Tonnage;
    private String Remark;
    private String PicturePath;
    private int ReportOrgID;
    private String CreateDT;

    private String CreateUserID;
    private String LastOprDT;
    private String LastOprUser;
    private int IsDeleted;
    private int IsUpLoad;
    private int IsChecked;
    private String CheckDT;
    private String Checker;
    private String CheckOrgName;
    private int CreateFlag;
    private int ID;
    private String ReportOrgLevel;


}
