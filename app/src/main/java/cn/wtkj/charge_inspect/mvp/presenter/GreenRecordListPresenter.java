package cn.wtkj.charge_inspect.mvp.presenter;


import android.content.Context;
import android.content.Intent;

import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.JCGreenChannelRecData;
import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordListView;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface GreenRecordListPresenter extends MvpPresenter<GreenRecordListView> {
    void startPresenter(String keywork);
    void attachContextIntent(Context context);
    void deleteById(String id);
    void sendData(JCGreenChannelRecData data);
}
