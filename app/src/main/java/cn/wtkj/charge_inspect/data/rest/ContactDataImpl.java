package cn.wtkj.charge_inspect.data.rest;

import cn.wtkj.charge_inspect.data.SeApiManager;
import cn.wtkj.charge_inspect.data.bean.ArticleDetail;
import cn.wtkj.charge_inspect.data.bean.ArticleListData;
import cn.wtkj.charge_inspect.data.bean.ContactListData;
import cn.wtkj.charge_inspect.data.net.DangerousApi;
import retrofit.Callback;

/**
 * Created by lcl on 2016/9/30.
 */
public class ContactDataImpl {

    private final DangerousApi dangerousApi;

    public ContactDataImpl() {
        dangerousApi = SeApiManager.apiMangerAdapter();
    }

    public void getContactList(Callback<ContactListData> callback) {
        dangerousApi.getContactList(callback);
    }
}
