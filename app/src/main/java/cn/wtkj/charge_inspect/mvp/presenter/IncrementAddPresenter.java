package cn.wtkj.charge_inspect.mvp.presenter;


import android.content.Context;
import android.content.Intent;

import java.util.List;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.IncrementAddView;
import cn.wtkj.charge_inspect.mvp.views.IncrementListView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface IncrementAddPresenter extends MvpPresenter<IncrementAddView> {
    void startPresenter();
    void attachContextIntent(Context context, Intent intent);
    List<KeyValueData> getOrgShortName(int orgId,String OrgLevel);
    List<ConstAllData.MData.info> getConstByType(int type);
    void submitData(JCEscapeBookData data);
}
