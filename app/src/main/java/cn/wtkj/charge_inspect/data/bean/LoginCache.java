package cn.wtkj.charge_inspect.data.bean;

import java.io.Serializable;

/**
 * 登陆的用户信息详细数据
 * Created by lxg on 2015/9/8.
 */
public class LoginCache implements Serializable {
    private String userId;
    private String userPass;
    private boolean isRemember;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public boolean isRemember() {
        return isRemember;
    }

    public void setIsRemember(boolean isRemember) {
        this.isRemember = isRemember;
    }
}
