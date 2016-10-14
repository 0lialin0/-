package cn.wtkj.charge_inspect.mvp.presenter;


import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.ContactListView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface ContactListPresenter extends MvpPresenter<ContactListView> {
    void startPresenter();
    void getContactList();
}
