package cn.wtkj.charge_inspect.data.bean;

import java.io.Serializable;

/**
 * Created by ghj on 2016/10/12.
 */
public class PhotoVideoData implements Serializable {
    private int pvId;
    private String blackListID; //jcbacklist id
    private int nameType;   // 0：黑名单，1：灰名单，2：黄名单，3，绿通
    private String fileName;
    private String fileUrl;
    private int fileType;    // 0：图片，1： 视频
    private String createTime;

    public int getPvId() {
        return pvId;
    }

    public void setPvId(int pvId) {
        this.pvId = pvId;
    }

    public String getBlackListID() {
        return blackListID;
    }

    public void setBlackListID(String blackListID) {
        this.blackListID = blackListID;
    }

    public int getNameType() {
        return nameType;
    }

    public void setNameType(int nameType) {
        this.nameType = nameType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
