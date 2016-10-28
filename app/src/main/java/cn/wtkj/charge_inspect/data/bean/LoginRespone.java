package cn.wtkj.charge_inspect.data.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ghj on 2016/9/21.
 */
public class LoginRespone implements Serializable {
    public static final int SUCCESS = 0;
    public static final String STATE_SUCCESS = "0";
    public static final String NET_ERROR = "网络异常！";
    private Data data;
    private int code;
    private String msg;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        private int state;
        private info info;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Data.info getInfo() {
            return info;
        }

        public void setInfo(Data.info info) {
            this.info = info;
        }

        public class info {
            private String ORGLEVEL;
            private String OPRID;
            private String NAME;
            private String OFFICEPHONE;
            private String MOBILEPHONE;
            private String EMAIL;
            private int ORGID;
            private String ORGANIZATION_NAME;
            private String ORGANIZATION_SHORTNAME;

            public String getOFFICEPHONE() {
                return OFFICEPHONE;
            }

            public void setOFFICEPHONE(String OFFICEPHONE) {
                this.OFFICEPHONE = OFFICEPHONE;
            }

            public String getORGLEVEL() {
                return ORGLEVEL;
            }

            public void setORGLEVEL(String ORGLEVEL) {
                this.ORGLEVEL = ORGLEVEL;
            }

            public String getOPRID() {
                return OPRID;
            }

            public void setOPRID(String OPRID) {
                this.OPRID = OPRID;
            }

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }

            public String getMOBILEPHONE() {
                return MOBILEPHONE;
            }

            public void setMOBILEPHONE(String MOBILEPHONE) {
                this.MOBILEPHONE = MOBILEPHONE;
            }

            public String getEMAIL() {
                return EMAIL;
            }

            public void setEMAIL(String EMAIL) {
                this.EMAIL = EMAIL;
            }

            public int getORGID() {
                return ORGID;
            }

            public void setORGID(int ORGID) {
                this.ORGID = ORGID;
            }

            public String getORGANIZATION_NAME() {
                return ORGANIZATION_NAME;
            }

            public void setORGANIZATION_NAME(String ORGANIZATION_NAME) {
                this.ORGANIZATION_NAME = ORGANIZATION_NAME;
            }

            public String getORGANIZATION_SHORTNAME() {
                return ORGANIZATION_SHORTNAME;
            }

            public void setORGANIZATION_SHORTNAME(String ORGANIZATION_SHORTNAME) {
                this.ORGANIZATION_SHORTNAME = ORGANIZATION_SHORTNAME;
            }
        }
    }
}
