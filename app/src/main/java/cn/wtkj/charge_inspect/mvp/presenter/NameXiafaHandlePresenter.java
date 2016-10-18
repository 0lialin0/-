package cn.wtkj.charge_inspect.mvp.presenter;


import android.content.Context;
import android.content.Intent;

import cn.wtkj.charge_inspect.data.bean.BlackXiafaHandleData;
import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.MainView;
import cn.wtkj.charge_inspect.mvp.views.NameXiafaHandleView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface NameXiafaHandlePresenter extends MvpPresenter<NameXiafaHandleView> {
    void startPresenter(BlackXiafaHandleData data);
    void attachContextIntent(Context context);
}
