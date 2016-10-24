package cn.wtkj.charge_inspect.mvp.views;


import java.util.List;

import cn.wtkj.charge_inspect.data.bean.OutListData;
import cn.wtkj.charge_inspect.mvp.MvpView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface OutListSelView extends MvpView {

    void setDropDown();

    void showView();

    void setView();

    //提示用户等待
    void showLoding();

    //隐藏等待
    void hideLoging();

    //跳转到下一个页面
    void nextView(List<OutListData.MData.info> data);

    /**
     * 显示提示信息
     *
     * @param msg
     */
    void showToast(String msg);

}
