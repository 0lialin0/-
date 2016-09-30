package cn.wtkj.charge_inspect.mvp.presenter;


import android.content.Context;
import android.content.Intent;

import java.util.List;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;
import cn.wtkj.charge_inspect.mvp.views.MainView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface GreenRecordPresenter extends MvpPresenter<GreenRecordView> {
    void startPresenter();
    void attachContextIntent(Context context, Intent intent);
    List<ConstAllData.MData.info> getConstByType(int type);
}
