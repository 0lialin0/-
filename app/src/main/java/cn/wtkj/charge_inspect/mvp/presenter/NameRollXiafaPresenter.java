package cn.wtkj.charge_inspect.mvp.presenter;


import android.content.Context;

import cn.wtkj.charge_inspect.data.bean.BlackListData;
import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.NameRollManageView;
import cn.wtkj.charge_inspect.mvp.views.NameRollXiafaView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface NameRollXiafaPresenter extends MvpPresenter<NameRollXiafaView> {
    void startPresenter(String keyword,int page, int pagerSize);
    void attachContextIntent(Context context);
    BlackListData.MData.info getBlackData(String id, int type);
}
