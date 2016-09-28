package cn.wtkj.charge_inspect.mvp.presenter;


import android.content.Context;
import android.content.Intent;

import java.io.File;
import java.util.List;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;
import cn.wtkj.charge_inspect.mvp.views.NameRollAddView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface NameRollAddPresenter extends MvpPresenter<NameRollAddView> {
    void startPresenter(List<File> files);
    List<ConstAllData.MData.info> getConstByType(int type);
}
