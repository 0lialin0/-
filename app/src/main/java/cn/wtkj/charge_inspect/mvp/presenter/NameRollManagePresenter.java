package cn.wtkj.charge_inspect.mvp.presenter;


import android.content.Context;

import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.IncrementListView;
import cn.wtkj.charge_inspect.mvp.views.NameRollManageView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface NameRollManagePresenter extends MvpPresenter<NameRollManageView> {
    void startPresenter(String keyword);
    void attachContextIntent(Context context);
    void deleteById(String id,int type);
    void sendData(JCBlackListData data);
}
