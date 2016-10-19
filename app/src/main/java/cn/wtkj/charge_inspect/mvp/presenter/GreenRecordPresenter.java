package cn.wtkj.charge_inspect.mvp.presenter;


import android.content.Context;
import android.content.Intent;

import java.io.File;
import java.util.List;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.JCGreenChannelRecData;
import cn.wtkj.charge_inspect.data.bean.PhotoVideoData;
import cn.wtkj.charge_inspect.data.bean.ViewOrganizationData;
import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;
import cn.wtkj.charge_inspect.mvp.views.MainView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface GreenRecordPresenter extends MvpPresenter<GreenRecordView> {
    void startPresenter(List<File> fileList, JCGreenChannelRecData data);
    void attachContextIntent(Context context, Intent intent);
    List<ViewOrganizationData.MData.info> getOrg(int orgId, String OrgLevel);
    List<ConstAllData.MData.info> getConstByType(int type);
    List<PhotoVideoData> getListPvById(String uuid);
}
