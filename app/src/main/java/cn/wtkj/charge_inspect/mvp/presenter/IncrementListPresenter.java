package cn.wtkj.charge_inspect.mvp.presenter;


import android.content.Context;
import android.content.Intent;

import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordListView;
import cn.wtkj.charge_inspect.mvp.views.IncrementListView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface IncrementListPresenter extends MvpPresenter<IncrementListView> {
    void startPresenter();
    void attachContextIntent(Context context);
    void deleteById(String id);
    void sendData(JCEscapeBookData data);
}
