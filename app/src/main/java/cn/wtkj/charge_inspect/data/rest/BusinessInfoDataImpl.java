package cn.wtkj.charge_inspect.data.rest;

import cn.wtkj.charge_inspect.data.SeApiManager;
import cn.wtkj.charge_inspect.data.bean.ArticleDetail;
import cn.wtkj.charge_inspect.data.net.DangerousApi;
import cn.wtkj.charge_inspect.data.bean.ArticleListData;
import retrofit.Callback;

/**
 * Created by lcl on 2016/9/30.
 */
public class BusinessInfoDataImpl {

    private final DangerousApi dangerousApi;

    public BusinessInfoDataImpl() {
        dangerousApi = SeApiManager.apiMangerAdapter();
    }

    public void getBusinessData(Callback<ArticleListData> callback) {
        dangerousApi.getBusinessData(callback);
    }

    public void getBusinessDetail(String articleId,Callback<ArticleDetail> callback) {
        dangerousApi.getBusinessDetail(articleId,callback);
    }
}
