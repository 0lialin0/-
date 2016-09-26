package cn.wtkj.charge_inspect.data.rest;

import java.util.Map;

import cn.wtkj.charge_inspect.data.SeApiManager;
import cn.wtkj.charge_inspect.data.bean.ViewOrganizationData;
import cn.wtkj.charge_inspect.data.net.DangerousApi;
import retrofit.Callback;

/**
 * Created by lxg on 2015/11/9.
 */
public class LoginDataImpl implements LoginData {
    private final DangerousApi dangerousApi;

    public LoginDataImpl() {
        dangerousApi = SeApiManager.apiMangerAdapter();
    }

    @Override
    public void login(Map<String, String> loginMap, Callback callback) {
        dangerousApi.login(loginMap, callback);
    }

    @Override
    public void updateConstAll(Callback callback) {
        dangerousApi.getConstAll(callback);
    }

    @Override
    public void updateOrg(Map<String, String> map,Callback<ViewOrganizationData> callback) {
        dangerousApi.getOrgan(map,callback);
    }
}
