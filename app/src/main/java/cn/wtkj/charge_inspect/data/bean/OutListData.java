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

    public class MData implements Serializable{
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

        public class info implements Serializable{
            @SerializedName("LISTID")
            private String listId;
            @SerializedName("INVEHPLATENO")
            private String invehplateNo; //入口车牌号
            @SerializedName("VEHPLATENO")
            private String vehplateNo;//车牌号
            @SerializedName("OPERATEON")
            private String operateOn; //操作时间（出口的）
            @SerializedName("INSTATIONNAME")
            private String instationName; //入口站址
            @SerializedName("INLANENAME")
            private String inlaneName; //入口车道
            @SerializedName("OUTSTATIONNAME")
            private String outstationName; //出口站址
            @SerializedName("OUTLANENAME")
            private String outlaneName; //出口车道
            @SerializedName("OPRID")
            private String oprId;//收费员工号
            @SerializedName("VEHTYPENAME")
            private String vehTypeName; //车型

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

            public String getOperateOn() {
                return operateOn;
            }

            public void setOperateOn(String operateOn) {
                this.operateOn = operateOn;
            }

            public String getInstationName() {
                return instationName;
            }

            public void setInstationName(String instationName) {
                this.instationName = instationName;
            }

            public String getInlaneName() {
                return inlaneName;
            }

            public void setInlaneName(String inlaneName) {
                this.inlaneName = inlaneName;
            }

            public String getOutstationName() {
                return outstationName;
            }

            public void setOutstationName(String outstationName) {
                this.outstationName = outstationName;
            }

            public String getOutlaneName() {
                return outlaneName;
            }

            public void setOutlaneName(String outlaneName) {
                this.outlaneName = outlaneName;
            }

            public String getOprId() {
                return oprId;
            }

            public void setOprId(String oprId) {
                this.oprId = oprId;
            }

            public String getVehTypeName() {
                return vehTypeName;
            }

            public void setVehTypeName(String vehTypeName) {
                this.vehTypeName = vehTypeName;
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

