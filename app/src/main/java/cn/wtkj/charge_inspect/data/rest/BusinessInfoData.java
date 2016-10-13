package cn.wtkj.charge_inspect.data.rest;

import java.util.Map;

import retrofit.Callback;

/**
 * Created by lcl on 2016/9/30.
 */
public interface BusinessInfoData {
    void getBusinessData(Callback<cn.wtkj.charge_inspect.data.bean.BusinessInfoData> callback);
}
