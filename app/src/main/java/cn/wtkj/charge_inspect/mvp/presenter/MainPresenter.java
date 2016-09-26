package cn.wtkj.charge_inspect.mvp.presenter;


import android.content.Context;
import android.content.Intent;

import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.LoginView;
import cn.wtkj.charge_inspect.mvp.views.MainView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface MainPresenter extends MvpPresenter<MainView> {
    void startPresenter();
    void attachContextIntent(Context context, Intent intent);
    void getUserState();
}
