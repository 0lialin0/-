package cn.wtkj.charge_inspect.data.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ghj on 2016/9/20.
 */
public class BusinessContentData {

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
            private int code;
            @SerializedName("SPOTID")
            private String name;
            @SerializedName("ISDELETED")
            private int type1;
            @SerializedName("SWITCHBOARD")
            private int type2;
            @SerializedName("CREATEDT")
            private int type3;
            @SerializedName("OUTSIDEPHONE")
            private int type4;
            @SerializedName("INTERPHONE")
            private int type5;
            @SerializedName("SPOTNAME")
            private int type6;
            @SerializedName("CREATEUSER")
            private int type7;
            @SerializedName("ORGID")
            private int type8;
            @SerializedName("ORGNAME")
            private int type;


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
