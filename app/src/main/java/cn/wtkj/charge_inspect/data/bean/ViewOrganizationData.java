package cn.wtkj.charge_inspect.data.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ghj on 2016/9/20.
 * 组织机构
 */
public class ViewOrganizationData implements Serializable {

    public static final int SUCCESS = 0;
    public static final String STATE_SUCCESS = "0";
    public static final String NET_ERROR = "网络异常！";

    private int code;
    private String msg;
    private MData data;

    public class MData {
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
            @SerializedName("ORGCODE")
            private int OrgCode;
            @SerializedName("ORGLEVEL")
            private String OrgLevel;
            @SerializedName("NAME")
            private String Name;
            @SerializedName("SHORTNAME")
            private String ShortName;
            @SerializedName("VIEWCODE")
            private String Viewcode;
            @SerializedName("EFFECTTIME")
            private String EffectTime;
            @SerializedName("LVS")
            private int Lvs;
            @SerializedName("LEAF")
            private int Leaf;
            @SerializedName("BUSINESSLINEID")
            private String Businesslineid;
            @SerializedName("STATUS")
            private int Status;
            @SerializedName("ORGVERSION")
            private int Orgversion;


            public int getOrgCode() {
                return OrgCode;
            }

            public void setOrgCode(int orgCode) {
                OrgCode = orgCode;
            }

            public String getOrgLevel() {
                return OrgLevel;
            }

            public void setOrgLevel(String orgLevel) {
                OrgLevel = orgLevel;
            }

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getShortName() {
                return ShortName;
            }

            public void setShortName(String shortName) {
                ShortName = shortName;
            }

            public String getViewcode() {
                return Viewcode;
            }

            public void setViewcode(String viewcode) {
                Viewcode = viewcode;
            }

            public String getEffectTime() {
                return EffectTime;
            }

            public void setEffectTime(String effectTime) {
                EffectTime = effectTime;
            }

            public int getLvs() {
                return Lvs;
            }

            public void setLvs(int lvs) {
                Lvs = lvs;
            }

            public int getLeaf() {
                return Leaf;
            }

            public void setLeaf(int leaf) {
                Leaf = leaf;
            }

            public String getBusinesslineid() {
                return Businesslineid;
            }

            public void setBusinesslineid(String businesslineid) {
                Businesslineid = businesslineid;
            }

            public int getStatus() {
                return Status;
            }

            public void setStatus(int status) {
                Status = status;
            }

            public int getOrgversion() {
                return Orgversion;
            }

            public void setOrgversion(int orgversion) {
                Orgversion = orgversion;
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
