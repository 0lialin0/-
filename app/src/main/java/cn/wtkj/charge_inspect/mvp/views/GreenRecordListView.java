package cn.wtkj.charge_inspect.mvp.views;


import cn.wtkj.charge_inspect.mvp.MvpView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface GreenRecordListView extends MvpView {

    void showList(String[] items, int[] imgs);

    //提示用户等待
    void showLoding();

    //隐藏等待
    void hideLoging();

    //跳转到下一个页面
    void nextView(String phone);

    //提示错误信息
    void showMes(String msg);

    void setList();

}
