package cn.wtkj.charge_inspect.mvp.views;


import cn.wtkj.charge_inspect.data.bean.ArticleDetail;
import cn.wtkj.charge_inspect.data.bean.ArticleListData;
import cn.wtkj.charge_inspect.data.bean.ContactListData;
import cn.wtkj.charge_inspect.mvp.MvpView;

/**
 * Created by lxg on 2015/11/5.
 */
public interface ContactListView extends MvpView {
    // 业务单列表
    void showList(ContactListData contactListData);

    //提示错误信息
    void showMes(String msg);
}
