package cn.wtkj.charge_inspect.mvp.presenter;


import android.content.Context;
import android.content.Intent;

import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.BusinessInfoView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface BusinessInfoPresenter extends MvpPresenter<BusinessInfoView> {
    void startPresenter();
}
