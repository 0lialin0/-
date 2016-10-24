package cn.wtkj.charge_inspect.mvp.views;


import java.util.List;

import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.mvp.MvpView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface OutListSelShowView extends MvpView {




    //提示用户等待
    void showLoding();

    //隐藏等待
    void hideLoging();

    //隐藏等待
    void hideDialog();

    //跳转到下一个页面
    void nextView();


    void setList(List<JCEscapeBookData> list);
    /**
     * 显示提示信息
     *
     * @param msg
     */
    void showToast(String msg);

}
