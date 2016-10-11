package cn.wtkj.charge_inspect.data.net;

public class ResponeData {
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

    public class Data{
        private int state;
        private String info;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
