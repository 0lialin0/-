package cn.wtkj.charge_inspect.mvp.views;


import java.util.List;

import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.data.bean.NameRollXiafaData;
import cn.wtkj.charge_inspect.mvp.MvpView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface NameRollXiafaView extends MvpView {

    void showList();

    void setList(List<NameRollXiafaData.MData.info> list);

    //提示用户等待
    void showLoding();

    //隐藏等待
    void hideLoging();

    void hideDialog();

    //跳转到下一个页面
    void nextView();

    //提示错误信息
    void showMes(String msg);


}
