package cn.wtkj.charge_inspect.mvp.views;


import cn.wtkj.charge_inspect.mvp.MvpView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface LoginView extends MvpView {
    //提示用户等待
    void showLoding();

    //隐藏等待
    void hideLoging();

    //显示网络错误
    void showNetError();

    void hideNetError();

    //跳转到下一个页面
    void nextView();

    //提示错误信息
    void showMes(String msg);

    //如果用户不在系统中，直接退出程序
    void viewDestroy();

    void showPass();

    void setCode(String code);
}
