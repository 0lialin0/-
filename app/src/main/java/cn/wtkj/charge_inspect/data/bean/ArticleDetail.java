package cn.wtkj.charge_inspect.data.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ghj on 2016/9/20.
 */
public class ArticleDetail implements Serializable{

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
            @SerializedName("ARTICLEID")
            private String articleId;
            @SerializedName("HTMLTEXT")
            private String htmlText;
            @SerializedName("TITLE")
            private String title;
            @SerializedName("FILES")
            private List<files> files;

            public String getArticleId() {
                return articleId;
            }

            public void setArticleId(String articleId) {
                this.articleId = articleId;
            }

            public String getHtmlText() {
                return htmlText;
            }

            public void setHtmlText(String htmlText) {
                this.htmlText = htmlText;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<MData.info.files> getFiles() {
                return files;
            }

            public void setFiles(List<MData.info.files> files) {
                this.files = files;
            }

            public class files implements Serializable{
                @SerializedName("FILEEXTENSION")
                private String fileExtension;
                @SerializedName("FILESIZE")
                private int fileSize;
                @SerializedName("FILENAME")
                private String fileName;
                @SerializedName("URL")
                private String url;

                public String getFileExtension() {
                    return fileExtension;
                }

                public void setFileExtension(String fileExtension) {
                    this.fileExtension = fileExtension;
                }

                public int getFileSize() {
                    return fileSize;
                }

                public void setFileSize(int fileSize) {
                    this.fileSize = fileSize;
                }

                public String getFileName() {
                    return fileName;
                }

                public void setFileName(String fileName) {
                    this.fileName = fileName;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
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
