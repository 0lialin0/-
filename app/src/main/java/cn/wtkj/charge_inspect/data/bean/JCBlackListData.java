package cn.wtkj.charge_inspect.data.bean;

import java.io.Serializable;

/**
 * Created by ghj on 2016/9/20.
 * 黑名单
 */
public class JCBlackListData implements Serializable {

    private String BlackListID; //黑名单ID
    private String VehicleID;  //灰名单ID
    private String YListID;    //黄名单ID


    private String VepPlateNo; //车牌号
    private int VepPlateNoColor;//车牌颜色ID
    private String VepPlateNoColorName;//车牌颜色

    private String FactoryType;//厂牌型号

    private int VepColor; //车身颜色ID
    private String VepColorName; //车身颜色

    private int VehicleTypeID; //车辆类别ID
    private String VehicleTypeName; //车辆类别
    private int VehType; //车型类别ID
    private String VehTypeName; //车型类别

    private int Seating; //座位数

    private String CardNo;  //通行卡号
    private int PeccancyTypeID; //违章类型ID
    private String PeccancyTypeName; //违章类型
    private String GenDT;//违章时间
    private int PeccancyOrgID; //违章地点ID
    private String PeccancyOrgName; //违章地点

    private int InOrgID; //始行驶区间ID
    private String InOrgName; //始行驶区间

    private int OutOrgID;//结行驶区间ID
    private String OutOrgName;//结行驶区间
    private String GenCause;//情况说明

    private int AxleCount;  //轴数
    private String AxleCountName; //轴数(轴)

    private String Tonnage; //吨位数

    private String videoName;
    private String videoList;
    private String photoName;
    private String photoList;

    private int OperType;////1：新增 2：修改
    private String userID;

    private int NameType;// 0：黑名单，1：灰名单，2：黄名单

    private String businessId;

    //灰名单字段
    private String OwnerAddress; //通信地址
    private int OwnerType; //所有者类型ID
    private String OwnerTypeName; //所有者类型
    private String Postalcode; //邮政编码
    private String TeletePhone; //联系电话
    private String MobilePhone; //手机号码
    private String Owner; //车辆所有者
    private String PeccancyDescription;//情况说明
    private String HistoryInfo; //历史违章情况

    public String getPeccancyDescription() {
        return PeccancyDescription;
    }

    public void setPeccancyDescription(String peccancyDescription) {
        PeccancyDescription = peccancyDescription;
    }

    public String getHistoryInfo() {
        return HistoryInfo;
    }

    public void setHistoryInfo(String historyInfo) {
        HistoryInfo = historyInfo;
    }

    public String getVehicleID() {
        return VehicleID;
    }

    public void setVehicleID(String vehicleID) {
        VehicleID = vehicleID;
    }

    public String getYListID() {
        return YListID;
    }

    public void setYListID(String YListID) {
        this.YListID = YListID;
    }

    public int getNameType() {
        return NameType;
    }

    public void setNameType(int nameType) {
        NameType = nameType;
    }

    public String getOwnerAddress() {
        return OwnerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        OwnerAddress = ownerAddress;
    }

    public int getOwnerType() {
        return OwnerType;
    }

    public void setOwnerType(int ownerType) {
        OwnerType = ownerType;
    }

    public String getOwnerTypeName() {
        return OwnerTypeName;
    }

    public void setOwnerTypeName(String ownerTypeName) {
        OwnerTypeName = ownerTypeName;
    }

    public String getPostalcode() {
        return Postalcode;
    }

    public void setPostalcode(String postalcode) {
        Postalcode = postalcode;
    }

    public String getTeletePhone() {
        return TeletePhone;
    }

    public void setTeletePhone(String teletePhone) {
        TeletePhone = teletePhone;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getAxleCountName() {
        return AxleCountName;
    }

    public void setAxleCountName(String axleCountName) {
        AxleCountName = axleCountName;
    }

    public String getBlackListID() {
        return BlackListID;
    }

    public void setBlackListID(String blackListID) {
        BlackListID = blackListID;
    }

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String cardNo) {
        CardNo = cardNo;
    }

    public String getVepPlateNo() {
        return VepPlateNo;
    }

    public void setVepPlateNo(String vepPlateNo) {
        VepPlateNo = vepPlateNo;
    }

    public int getVehicleTypeID() {
        return VehicleTypeID;
    }

    public void setVehicleTypeID(int vehicleTypeID) {
        VehicleTypeID = vehicleTypeID;
    }

    public String getVehicleTypeName() {
        return VehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        VehicleTypeName = vehicleTypeName;
    }

    public int getVehType() {
        return VehType;
    }

    public void setVehType(int vehType) {
        VehType = vehType;
    }

    public String getVehTypeName() {
        return VehTypeName;
    }

    public void setVehTypeName(String vehTypeName) {
        VehTypeName = vehTypeName;
    }

    public int getVepColor() {
        return VepColor;
    }

    public void setVepColor(int vepColor) {
        VepColor = vepColor;
    }

    public String getVepColorName() {
        return VepColorName;
    }

    public void setVepColorName(String vepColorName) {
        VepColorName = vepColorName;
    }

    public int getVepPlateNoColor() {
        return VepPlateNoColor;
    }

    public void setVepPlateNoColor(int vepPlateNoColor) {
        VepPlateNoColor = vepPlateNoColor;
    }

    public String getVepPlateNoColorName() {
        return VepPlateNoColorName;
    }

    public void setVepPlateNoColorName(String vepPlateNoColorName) {
        VepPlateNoColorName = vepPlateNoColorName;
    }

    public int getPeccancyTypeID() {
        return PeccancyTypeID;
    }

    public void setPeccancyTypeID(int peccancyTypeID) {
        PeccancyTypeID = peccancyTypeID;
    }

    public String getPeccancyTypeName() {
        return PeccancyTypeName;
    }

    public void setPeccancyTypeName(String peccancyTypeName) {
        PeccancyTypeName = peccancyTypeName;
    }

    public String getFactoryType() {
        return FactoryType;
    }

    public void setFactoryType(String factoryType) {
        FactoryType = factoryType;
    }

    public String getGenDT() {
        return GenDT;
    }

    public void setGenDT(String genDT) {
        GenDT = genDT;
    }

    public int getInOrgID() {
        return InOrgID;
    }

    public void setInOrgID(int inOrgID) {
        InOrgID = inOrgID;
    }

    public String getInOrgName() {
        return InOrgName;
    }

    public void setInOrgName(String inOrgName) {
        InOrgName = inOrgName;
    }

    public int getOutOrgID() {
        return OutOrgID;
    }

    public void setOutOrgID(int outOrgID) {
        OutOrgID = outOrgID;
    }

    public String getOutOrgName() {
        return OutOrgName;
    }

    public void setOutOrgName(String outOrgName) {
        OutOrgName = outOrgName;
    }

    public int getPeccancyOrgID() {
        return PeccancyOrgID;
    }

    public void setPeccancyOrgID(int peccancyOrgID) {
        PeccancyOrgID = peccancyOrgID;
    }

    public String getPeccancyOrgName() {
        return PeccancyOrgName;
    }

    public void setPeccancyOrgName(String peccancyOrgName) {
        PeccancyOrgName = peccancyOrgName;
    }

    public String getGenCause() {
        return GenCause;
    }

    public void setGenCause(String genCause) {
        GenCause = genCause;
    }

    public int getAxleCount() {
        return AxleCount;
    }

    public void setAxleCount(int axleCount) {
        AxleCount = axleCount;
    }

    public String getTonnage() {
        return Tonnage;
    }

    public void setTonnage(String tonnage) {
        Tonnage = tonnage;
    }

    public int getSeating() {
        return Seating;
    }

    public void setSeating(int seating) {
        Seating = seating;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoList() {
        return videoList;
    }

    public void setVideoList(String videoList) {
        this.videoList = videoList;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoList() {
        return photoList;
    }

    public void setPhotoList(String photoList) {
        this.photoList = photoList;
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
