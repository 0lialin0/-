package cn.wtkj.charge_inspect.data.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ghj on 2016/9/20.
 * 组织机构
 */
public class BlackListData implements Serializable {

    public static final int SUCCESS = 0;
    public static final String STATE_SUCCESS = "0";
    public static final String NET_ERROR = "网络异常！";

    private int code;
    private String msg;
    private MData data;

    public class MData implements Serializable{
        private int state;
        private info info;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public info getInfo() {
            return info;
        }

        public void setInfo(info info) {
            this.info = info;
        }


        public class info implements Serializable{
            @SerializedName("BLACKLISTID")
            private String BlackListID; //黑名单ID
            @SerializedName("VEHICLEID")
            private String VehicleID;  //灰名单ID
            @SerializedName("YLISTID")
            private String YListID;    //黄名单ID

            /* 车辆信息 */
            @SerializedName("VEPPLATENO")
            private String VepPlateNo; //车牌号
            @SerializedName("VEPPLATENOCOLOR")
            private int VepPlateNoColor;//车牌颜色ID
            @SerializedName("VEPPLATENOCOLORNAME")
            private String VepPlateNoColorName;//车牌颜色
            @SerializedName("FACTORYTYPE")
            private String FactoryType;//厂牌型号
            @SerializedName("VEPCOLOR")
            private int VepColor; //车身颜色ID
            @SerializedName("VEPCOLORNAME")
            private String VepColorName; //车身颜色
            @SerializedName("VEHICLETYPEID")
            private int VehicleTypeID; //车辆类别ID
            @SerializedName("VEHICLETYPENAME")
            private String VehicleTypeName; //车辆类别
            @SerializedName("VEHTYPE")
            private int VehType; //车型类别ID
            @SerializedName("VEHTYPENAME")
            private String VehTypeName; //车型类别
            @SerializedName("SEATING")
            private int Seating; //座位数
            @SerializedName("AXLECOUNT")
            private int AxleCount;  //轴数
            @SerializedName("AxleCountName")
            private String AxleCountName; //轴数(轴)
            @SerializedName("TONNAGE")
            private String Tonnage; //吨位数

            /* 违章信息 */
            @SerializedName("CARDNO")
            private String CardNo;  //通行卡号
            @SerializedName("PECCANCYTYPEID")
            private int PeccancyTypeID; //违章类型ID
            @SerializedName("PECCANCYNAME")
            private String PeccancyTypeName; //违章类型
            @SerializedName("GENDT")
            private String GenDT;//违章时间
            @SerializedName("PECCANCYORGID")
            private int PeccancyOrgID; //违章地点ID
            @SerializedName("PECCANCYORGNAME")
            private String PeccancyOrgName; //违章地点
            @SerializedName("INORGID")
            private int InOrgID; //始行驶区间ID
            @SerializedName("INSTATIONNAME")
            private String InOrgName; //始行驶区间
            @SerializedName("OUTORGID")
            private int OutOrgID;//结行驶区间ID
            @SerializedName("OUTSTATIONNAME")
            private String OutOrgName;//结行驶区间
            @SerializedName("GENCAUSE")
            private String GenCause;//情况说明

            /* 车辆所有者 */
            @SerializedName("OWNERADDRESS")
            private String OwnerAddress; //通信地址
            @SerializedName("OWNERTYPE")
            private int OwnerType; //所有者类型ID
            @SerializedName("OwnerTypeName")
            private String OwnerTypeName; //所有者类型
            @SerializedName("POSTALCODE")
            private String Postalcode; //邮政编码
            @SerializedName("TELETEPHONE")
            private String TeletePhone; //联系电话
            @SerializedName("MOBILEPHONE")
            private String MobilePhone; //手机号码
            @SerializedName("OWNER")
            private String Owner; //车辆所有者
            @SerializedName("PECCANCYDESCRIPTION")
            private String PeccancyDescription;//情况说明
            @SerializedName("HISTORYINFO")
            private String HistoryInfo; //历史违章情况

            public String getBlackListID() {
                return BlackListID;
            }

            public void setBlackListID(String blackListID) {
                BlackListID = blackListID;
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

            public String getVepPlateNo() {
                return VepPlateNo;
            }

            public void setVepPlateNo(String vepPlateNo) {
                VepPlateNo = vepPlateNo;
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

            public String getFactoryType() {
                return FactoryType;
            }

            public void setFactoryType(String factoryType) {
                FactoryType = factoryType;
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

            public int getSeating() {
                return Seating;
            }

            public void setSeating(int seating) {
                Seating = seating;
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

            public String getCardNo() {
                return CardNo;
            }

            public void setCardNo(String cardNo) {
                CardNo = cardNo;
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

            public String getGenDT() {
                return GenDT;
            }

            public void setGenDT(String genDT) {
                GenDT = genDT;
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

            public String getGenCause() {
                return GenCause;
            }

            public void setGenCause(String genCause) {
                GenCause = genCause;
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
        }
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public MData getMData() {
        return data;
    }

    public void setMData(MData data) {
        this.data = data;
    }


}
