package cn.wtkj.charge_inspect.data.bean;

import java.io.Serializable;

/**
 * Created by ghj on 2016/9/22.
 * 稽核绿通档案信息
 */
public class JCGreenChannelRecData implements Serializable {


    private String GCListID; //id
    private String CardNo; //卡号
    private String VehPlateNo; //车牌号

    private int VehicleTypeID; //车辆类型ID
    private String VehicleTypeIDName; //车辆类型

    private String FactoryType; //厂牌型号
    private String Capacity; //有效容积

    private int AxleCount; //轴数
    private String AxleCountName;//轴数

    private String Tonnage;//吨位(吨)
    private int ReportOrgID;//上报单位ID
    private String ReportOrgLevel;//上报单位
    private String CheckDate;//查验时间

    private int InStationID;//入口站址ID
    private String InStationName;//入口站址

    private int LaneID;//出口车道ID
    private String LaneName;//出口车道

    private String OprName;//收费员
    private String Shiftman; //班长
    private int IsMix;//绿通类型
    private String IsMixName;//绿通类型
    private String GoodsName;//货物名称
    private String MixGoodsName;//混装货物名称
    private int IsEnjoy;//是否减免
    private String FreeMoney;//减免金额(元)
    private String EscapeMoney;//增收金额(元)
    private String Remark;//备注

    private int OperType;////1：新增 2：修改
    private String userID;


    public String getIsMixName() {
        return IsMixName;
    }

    public void setIsMixName(String isMixName) {
        IsMixName = isMixName;
    }

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String cardNo) {
        CardNo = cardNo;
    }

    public String getGCListID() {
        return GCListID;
    }

    public void setGCListID(String GCListID) {
        this.GCListID = GCListID;
    }

    public String getVehPlateNo() {
        return VehPlateNo;
    }

    public void setVehPlateNo(String vehPlateNo) {
        VehPlateNo = vehPlateNo;
    }

    public int getVehicleTypeID() {
        return VehicleTypeID;
    }

    public void setVehicleTypeID(int vehicleTypeID) {
        VehicleTypeID = vehicleTypeID;
    }

    public String getVehicleTypeIDName() {
        return VehicleTypeIDName;
    }

    public void setVehicleTypeIDName(String vehicleTypeIDName) {
        VehicleTypeIDName = vehicleTypeIDName;
    }

    public String getFactoryType() {
        return FactoryType;
    }

    public void setFactoryType(String factoryType) {
        FactoryType = factoryType;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }

    public int getAxleCount() {
        return AxleCount;
    }

    public void setAxleCount(int axleCount) {
        AxleCount = axleCount;
    }

    public String getAxleCountName() {
        return AxleCountName;
    }

    public void setAxleCountName(String axleCountName) {
        AxleCountName = axleCountName;
    }

    public String getTonnage() {
        return Tonnage;
    }

    public void setTonnage(String tonnage) {
        Tonnage = tonnage;
    }

    public int getReportOrgID() {
        return ReportOrgID;
    }

    public void setReportOrgID(int reportOrgID) {
        ReportOrgID = reportOrgID;
    }

    public String getReportOrgLevel() {
        return ReportOrgLevel;
    }

    public void setReportOrgLevel(String reportOrgLevel) {
        ReportOrgLevel = reportOrgLevel;
    }

    public String getCheckDate() {
        return CheckDate;
    }

    public void setCheckDate(String checkDate) {
        CheckDate = checkDate;
    }

    public int getInStationID() {
        return InStationID;
    }

    public void setInStationID(int inStationID) {
        InStationID = inStationID;
    }

    public String getInStationName() {
        return InStationName;
    }

    public void setInStationName(String inStationName) {
        InStationName = inStationName;
    }

    public int getLaneID() {
        return LaneID;
    }

    public void setLaneID(int laneID) {
        LaneID = laneID;
    }

    public String getLaneName() {
        return LaneName;
    }

    public void setLaneName(String laneName) {
        LaneName = laneName;
    }

    public String getOprName() {
        return OprName;
    }

    public void setOprName(String oprName) {
        OprName = oprName;
    }

    public String getShiftman() {
        return Shiftman;
    }

    public void setShiftman(String shiftman) {
        Shiftman = shiftman;
    }

    public int getIsMix() {
        return IsMix;
    }

    public void setIsMix(int isMix) {
        IsMix = isMix;
    }

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public String getMixGoodsName() {
        return MixGoodsName;
    }

    public void setMixGoodsName(String mixGoodsName) {
        MixGoodsName = mixGoodsName;
    }

    public int getIsEnjoy() {
        return IsEnjoy;
    }

    public void setIsEnjoy(int isEnjoy) {
        IsEnjoy = isEnjoy;
    }

    public String getFreeMoney() {
        return FreeMoney;
    }

    public void setFreeMoney(String freeMoney) {
        FreeMoney = freeMoney;
    }

    public String getEscapeMoney() {
        return EscapeMoney;
    }

    public void setEscapeMoney(String escapeMoney) {
        EscapeMoney = escapeMoney;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getOperType() {
        return OperType;
    }

    public void setOperType(int operType) {
        OperType = operType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
