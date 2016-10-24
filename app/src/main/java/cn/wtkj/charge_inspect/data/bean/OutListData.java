package cn.wtkj.charge_inspect.data.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ghj on 2016/10/24.
 */
public class OutListData implements Serializable {
    public static final int SUCCESS = 0;
    public static final String STATE_SUCCESS = "0";
    public static final String NET_ERROR = "网络异常！";

    private int code;
    private String msg;
    private MData data;

    public class MData{
        private int state;
        private List<info> info;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public List<info> getInfo() {
            return info;
        }

        public void setInfo(List<info> info) {
            this.info = info;
        }

        public class info {
            @SerializedName("LISTID")
            private String listId;
            @SerializedName("INVEHPLATENO")
            private String invehplateNo; //入口车牌号
            @SerializedName("VEHPLATENO")
            private String vehplateNo;//车牌号

            @SerializedName("INOPRID")
            private String inoprId;//入口收费员工号
            @SerializedName("OPRID")
            private String oprId;//收费员工号
            @SerializedName("SHIFTID")
            private int shiftId; //班次ID
            @SerializedName("INVEHTYPE")
            private int invehType; //入口车型ID
            @SerializedName("INVEHTYPENAME")
            private String invehTypeName; //入口车型
            @SerializedName("VEHTYPE")
            private int vehType; //车型ID
            @SerializedName("VEHTYPENAME")
            private String vehTypeName; //车型
            @SerializedName("AXLENUMBER")
            private int axleNumber; //轴数
            @SerializedName("OPERATEON")
            private String operateOn; //操作时间（出口的）

            @SerializedName("INOPERATEON")
            private String inoperateOn; //操作时间（入口的）
            @SerializedName("INSTATIONID")
            private int instationId; //入口站址ID
            @SerializedName("INSTATIONNAME")
            private String instationName; //入口站址
            @SerializedName("INLANEID")
            private int inlaneId; //入口车道ID
            @SerializedName("INLANENAME")
            private String inlaneName; //入口车道
            @SerializedName("OUTSTATIONID")
            private int outstationId; //出口站址ID
            @SerializedName("OUTSTATIONNAME")
            private String outstationName; //出口站址

            @SerializedName("OUTLANEID")
            private int outlaneId; //出口车道ID
            @SerializedName("OUTLANENAME")
            private String outlaneName; //出口车道
            @SerializedName("WEIGHT")
            private int weight; //重量

            @SerializedName("VEHCLASSID")
            private int vehclassId; //车种id（出口）
            @SerializedName("VEHCLASS")
            private String vehclass; //车种（出口）
            @SerializedName("INVEHCLASSID")
            private int invehclassId; //车种id（入口）
            @SerializedName("INVEHCLASS")
            private String invehclass; //车种（入口）

            @SerializedName("UCRMONEY")
            private String ucrmoney; //折扣前金额
            @SerializedName("CARDVEHPLATE")
            private String cardvehpLate; //预付卡车牌
            @SerializedName("CRMONEY")
            private String crmoney; //应收金额
            @SerializedName("PAYMONEY")
            private String paymoney; //实收金额

            @SerializedName("INVOICENO")
            private int invoiceNo; //发票号
            @SerializedName("MILES")
            private int miles; //里程
            @SerializedName("CARDNO")
            private int cardNo; //通行卡号
            @SerializedName("SPEVENTID")
            private int speventId; //特殊事件id
            @SerializedName("SPEVENT")
            private String spevent; //特殊事件


            public String getListId() {
                return listId;
            }

            public void setListId(String listId) {
                this.listId = listId;
            }

            public String getInvehplateNo() {
                return invehplateNo;
            }

            public void setInvehplateNo(String invehplateNo) {
                this.invehplateNo = invehplateNo;
            }

            public String getVehplateNo() {
                return vehplateNo;
            }

            public void setVehplateNo(String vehplateNo) {
                this.vehplateNo = vehplateNo;
            }

            public String getInoprId() {
                return inoprId;
            }

            public void setInoprId(String inoprId) {
                this.inoprId = inoprId;
            }

            public String getOprId() {
                return oprId;
            }

            public void setOprId(String oprId) {
                this.oprId = oprId;
            }

            public int getShiftId() {
                return shiftId;
            }

            public void setShiftId(int shiftId) {
                this.shiftId = shiftId;
            }

            public int getInvehType() {
                return invehType;
            }

            public void setInvehType(int invehType) {
                this.invehType = invehType;
            }

            public String getInvehTypeName() {
                return invehTypeName;
            }

            public void setInvehTypeName(String invehTypeName) {
                this.invehTypeName = invehTypeName;
            }

            public int getVehType() {
                return vehType;
            }

            public void setVehType(int vehType) {
                this.vehType = vehType;
            }

            public String getVehTypeName() {
                return vehTypeName;
            }

            public void setVehTypeName(String vehTypeName) {
                this.vehTypeName = vehTypeName;
            }

            public int getAxleNumber() {
                return axleNumber;
            }

            public void setAxleNumber(int axleNumber) {
                this.axleNumber = axleNumber;
            }

            public String getOperateOn() {
                return operateOn;
            }

            public void setOperateOn(String operateOn) {
                this.operateOn = operateOn;
            }

            public String getInoperateOn() {
                return inoperateOn;
            }

            public void setInoperateOn(String inoperateOn) {
                this.inoperateOn = inoperateOn;
            }

            public int getInstationId() {
                return instationId;
            }

            public void setInstationId(int instationId) {
                this.instationId = instationId;
            }

            public String getInstationName() {
                return instationName;
            }

            public void setInstationName(String instationName) {
                this.instationName = instationName;
            }

            public int getInlaneId() {
                return inlaneId;
            }

            public void setInlaneId(int inlaneId) {
                this.inlaneId = inlaneId;
            }

            public String getInlaneName() {
                return inlaneName;
            }

            public void setInlaneName(String inlaneName) {
                this.inlaneName = inlaneName;
            }

            public int getOutstationId() {
                return outstationId;
            }

            public void setOutstationId(int outstationId) {
                this.outstationId = outstationId;
            }

            public String getOutstationName() {
                return outstationName;
            }

            public void setOutstationName(String outstationName) {
                this.outstationName = outstationName;
            }

            public int getOutlaneId() {
                return outlaneId;
            }

            public void setOutlaneId(int outlaneId) {
                this.outlaneId = outlaneId;
            }

            public String getOutlaneName() {
                return outlaneName;
            }

            public void setOutlaneName(String outlaneName) {
                this.outlaneName = outlaneName;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public int getVehclassId() {
                return vehclassId;
            }

            public void setVehclassId(int vehclassId) {
                this.vehclassId = vehclassId;
            }

            public String getVehclass() {
                return vehclass;
            }

            public void setVehclass(String vehclass) {
                this.vehclass = vehclass;
            }

            public int getInvehclassId() {
                return invehclassId;
            }

            public void setInvehclassId(int invehclassId) {
                this.invehclassId = invehclassId;
            }

            public String getInvehclass() {
                return invehclass;
            }

            public void setInvehclass(String invehclass) {
                this.invehclass = invehclass;
            }

            public String getUcrmoney() {
                return ucrmoney;
            }

            public void setUcrmoney(String ucrmoney) {
                this.ucrmoney = ucrmoney;
            }

            public String getCardvehpLate() {
                return cardvehpLate;
            }

            public void setCardvehpLate(String cardvehpLate) {
                this.cardvehpLate = cardvehpLate;
            }

            public String getCrmoney() {
                return crmoney;
            }

            public void setCrmoney(String crmoney) {
                this.crmoney = crmoney;
            }

            public String getPaymoney() {
                return paymoney;
            }

            public void setPaymoney(String paymoney) {
                this.paymoney = paymoney;
            }

            public int getInvoiceNo() {
                return invoiceNo;
            }

            public void setInvoiceNo(int invoiceNo) {
                this.invoiceNo = invoiceNo;
            }

            public int getMiles() {
                return miles;
            }

            public void setMiles(int miles) {
                this.miles = miles;
            }

            public int getCardNo() {
                return cardNo;
            }

            public void setCardNo(int cardNo) {
                this.cardNo = cardNo;
            }

            public int getSpeventId() {
                return speventId;
            }

            public void setSpeventId(int speventId) {
                this.speventId = speventId;
            }

            public String getSpevent() {
                return spevent;
            }

            public void setSpevent(String spevent) {
                this.spevent = spevent;
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

