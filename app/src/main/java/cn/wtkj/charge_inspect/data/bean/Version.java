package cn.wtkj.charge_inspect.data.bean;

/**
 * Created by lxg on 2015/11/12.
 */
public class Version {
    public static final String SUCCESS = "SUCCESS";
    public static final String STATE_SUCCESS = "0";
    public static final String NET_ERROR = "网络异常！";

    private String code;
    private String msg;
    private int data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
