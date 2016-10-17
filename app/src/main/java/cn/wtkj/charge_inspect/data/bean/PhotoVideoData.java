package cn.wtkj.charge_inspect.data.bean;

import java.io.Serializable;

/**
 * Created by ghj on 2016/10/12.
 */
public class PhotoVideoData implements Serializable {
    private int pvid;
    private String BlackListID; //黑名单ID
    private String VehicleID;  //灰名单ID
    private String YListID;    //黄名单ID
    private int NameType;// 0：黑名单，1：灰名单，2：黄名单
    private String videoName;
    private String videoUrl;
    private String photoName;
    private String photoUrl;
    private String creartTime;

    public int getPvid() {
        return pvid;
    }

    public void setPvid(int pvid) {
        this.pvid = pvid;
    }

    public String getBlackListID() {
        return BlackListID;
    }

    public void setBlackListID(String blackListID) {
        BlackListID = blackListID;
    }

    public String getVehicleID() {
        return VehicleID;
    }

    public void setVehicleID(String vehicleID) {
        VehicleID = vehicleID;
    }

    public String getYListID() {
        return YListID;
    }

    public void setYListID(String YListID) {
        this.YListID = YListID;
    }

    public int getNameType() {
        return NameType;
    }

    public void setNameType(int nameType) {
        NameType = nameType;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }


    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }


    public String getCreartTime() {
        return creartTime;
    }

    public void setCreartTime(String creartTime) {
        this.creartTime = creartTime;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
