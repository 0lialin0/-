package cn.wtkj.charge_inspect.mvp.views;


import java.util.List;

import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.mvp.MvpView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface IncrementAddView extends MvpView {

    void setView();

    void setDropDown(List<KeyValueData> classes,List<KeyValueData> zhoushuo);

    void showView();

    //提示用户等待
    void showLoding();

    //隐藏等待
    void hideLoging();

    //跳转到下一个页面
    void nextView();

    //提示错误信息
    void showMes(String msg);



}
