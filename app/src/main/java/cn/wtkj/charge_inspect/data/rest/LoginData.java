package cn.wtkj.charge_inspect.data.rest;

import java.util.Map;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.ViewOrganizationData;
import retrofit.Callback;


/**
 * Created by lxg on 2015/11/9.
 */
public interface LoginData {
    void login(Map<String, String> map, Callback callback);
    void updateConstAll(Callback<ConstAllData> callback);
    void updateOrg(Map<String, String> map,Callback<ViewOrganizationData> callback);
}
