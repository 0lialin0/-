package cn.wtkj.charge_inspect.data.rest;

import cn.wtkj.charge_inspect.data.SeApiManager;
import cn.wtkj.charge_inspect.data.net.DangerousApi;
import retrofit.Callback;

/**
 * Created by lcl on 2016/9/30.
 */
public class BusinessInfoDataImpl implements BusinessInfoData {
    private final DangerousApi dangerousApi;

    public BusinessInfoDataImpl() {
        dangerousApi = SeApiManager.apiMangerAdapter();
    }

    @Override
    public void getBusinessData(Callback<BusinessInfoData> callback) {
        dangerousApi.getBusinessData(callback);
    }
}
