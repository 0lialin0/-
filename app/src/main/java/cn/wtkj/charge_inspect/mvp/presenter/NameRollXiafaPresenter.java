package cn.wtkj.charge_inspect.mvp.presenter;


import android.content.Context;

import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.NameRollManageView;
import cn.wtkj.charge_inspect.mvp.views.NameRollXiafaView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface NameRollXiafaPresenter extends MvpPresenter<NameRollXiafaView> {
    void startPresenter();
    void attachContextIntent(Context context);
}
