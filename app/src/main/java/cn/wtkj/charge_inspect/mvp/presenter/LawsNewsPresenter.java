package cn.wtkj.charge_inspect.mvp.presenter;


import android.content.Context;
import android.content.Intent;

import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;
import cn.wtkj.charge_inspect.mvp.views.LawsNewsView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface LawsNewsPresenter extends MvpPresenter<LawsNewsView> {
    void startPresenter();
    void attachContextIntent(Context context, Intent intent);
}
