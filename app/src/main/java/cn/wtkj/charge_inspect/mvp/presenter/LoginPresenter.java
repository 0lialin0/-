package cn.wtkj.charge_inspect.mvp.presenter;


import cn.wtkj.charge_inspect.mvp.MvpPresenter;
import cn.wtkj.charge_inspect.mvp.views.LoginView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface LoginPresenter extends MvpPresenter<LoginView> {

    void login(String user, String passWord);
    void checkUpdate();
}
