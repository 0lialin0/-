package cn.wtkj.charge_inspect.data.rest;

import java.util.Map;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import retrofit.Callback;

/**
 * Created by lcl on 2016/9/30.
 */
public interface BusinessInfoData {
    void getBusinessData(Callback<BusinessInfoData> callback);
}
