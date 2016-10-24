package cn.wtkj.charge_inspect.mvp.presenter;


import java.util.List;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.data.bean.ViewOrganizationData;
import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.OutListSelView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface OutListSelShowPresenter extends MvpPresenter<OutListSelView> {
    void startPresenter();
    List<KeyValueData> setDropDown();
    List<ViewOrganizationData.MData.info> getOrg(int orgId, String OrgLevel);
    List<ConstAllData.MData.info> getConstByType(int type);
}
