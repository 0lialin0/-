package cn.wtkj.charge_inspect.data.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ghj on 2016/9/20.
 */
public class ArticleListData {

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
            @SerializedName("ARTICLEID")
            private String articleId;
            @SerializedName("CREATEDT")
            private String createDt;
            @SerializedName("TXTTITLE")
            private String textTitle;

            public String getArticleId() {
                return articleId;
            }

            public void setArticleId(String articleId) {
                this.articleId = articleId;
            }

            public String getCreateDt() {
                return createDt;
            }

            public void setCreateDt(String createDt) {
                this.createDt = createDt;
            }

            public String getTextTitle() {
                return textTitle;
            }

            public void setTextTitle(String textTitle) {
                this.textTitle = textTitle;
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
