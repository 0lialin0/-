package cn.wtkj.charge_inspect.data.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ghj on 2016/9/20.
 */
public class NameRollXiafaData {

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
            @SerializedName("LISTNUMBER")
            private int LISTNUMBER;
            @SerializedName("VEPCOLORNAME")
            private String VEPCOLORNAME;
            @SerializedName("CREATEDT")
            private String CREATEDT;
            @SerializedName("ISCHECKEDNAME")
            private String ISCHECKEDNAME;
            @SerializedName("VEPPLATENO")
            private String VEPPLATENO;
            @SerializedName("PECCANCYNAME")
            private String PECCANCYNAME;
            @SerializedName("ID")
            private String ID;
            @SerializedName("VEPPLATENOCOLORNAME")
            private String VEPPLATENOCOLORNAME;
            @SerializedName("REPORTORGNAME")
            private String REPORTORGNAME;
            @SerializedName("TYPE")
            private int TYPE;


            public int getLISTNUMBER() {
                return LISTNUMBER;
            }

            public void setLISTNUMBER(int LISTNUMBER) {
                this.LISTNUMBER = LISTNUMBER;
            }

            public String getVEPCOLORNAME() {
                return VEPCOLORNAME;
            }

            public void setVEPCOLORNAME(String VEPCOLORNAME) {
                this.VEPCOLORNAME = VEPCOLORNAME;
            }

            public String getCREATEDT() {
                return CREATEDT;
            }

            public void setCREATEDT(String CREATEDT) {
                this.CREATEDT = CREATEDT;
            }

            public String getISCHECKEDNAME() {
                return ISCHECKEDNAME;
            }

            public void setISCHECKEDNAME(String ISCHECKEDNAME) {
                this.ISCHECKEDNAME = ISCHECKEDNAME;
            }

            public String getVEPPLATENO() {
                return VEPPLATENO;
            }

            public void setVEPPLATENO(String VEPPLATENO) {
                this.VEPPLATENO = VEPPLATENO;
            }

            public String getPECCANCYNAME() {
                return PECCANCYNAME;
            }

            public void setPECCANCYNAME(String PECCANCYNAME) {
                this.PECCANCYNAME = PECCANCYNAME;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getVEPPLATENOCOLORNAME() {
                return VEPPLATENOCOLORNAME;
            }

            public void setVEPPLATENOCOLORNAME(String VEPPLATENOCOLORNAME) {
                this.VEPPLATENOCOLORNAME = VEPPLATENOCOLORNAME;
            }

            public String getREPORTORGNAME() {
                return REPORTORGNAME;
            }

            public void setREPORTORGNAME(String REPORTORGNAME) {
                this.REPORTORGNAME = REPORTORGNAME;
            }

            public int getTYPE() {
                return TYPE;
            }

            public void setTYPE(int TYPE) {
                this.TYPE = TYPE;
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
