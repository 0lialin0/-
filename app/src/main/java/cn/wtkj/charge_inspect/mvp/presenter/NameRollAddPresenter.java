package cn.wtkj.charge_inspect.mvp.presenter;


import android.content.Context;
import android.content.Intent;

import java.io.File;
import java.util.List;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.data.bean.PhotoVideoData;
import cn.wtkj.charge_inspect.data.bean.ViewOrganizationData;
import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;
import cn.wtkj.charge_inspect.mvp.views.NameRollAddView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface NameRollAddPresenter extends MvpPresenter<NameRollAddView> {
    void startPresenter(List<File> files, JCBlackListData data);
    List<KeyValueData> setDropDown();
    List<ViewOrganizationData.MData.info> getOrg(int orgId, String OrgLevel);
    List<ConstAllData.MData.info> getConstByType(int type);
    List<PhotoVideoData> getPvList(String uuid, int type);
}
