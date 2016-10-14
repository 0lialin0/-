package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;

import java.util.List;

import cn.wtkj.charge_inspect.data.bean.ContactListData;
import cn.wtkj.charge_inspect.data.rest.ContactDataImpl;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.ArticleInfoView;
import cn.wtkj.charge_inspect.mvp.views.ContactListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by lxg on 2015/11/5.
 */
public class ContactListPresenterImpl extends MvpBasePresenter<ContactListView> implements
        ContactListPresenter {

    private Context context;
    List<String> contactDataList;


    private ContactDataImpl contactDataImpl;

    public ContactListPresenterImpl(Context context) {
        this.context = context;
        contactDataImpl = new ContactDataImpl();
    }

    @Override
    public void startPresenter() {

    }

    /**
     * 获取业务 联系方式列表
     */
    public void getContactList() {
        contactDataImpl.getContactList(new Callback<ContactListData>() {
            @Override
            public void success(ContactListData contactListData, Response response) {
                getView().showList(contactListData);
            }

            @Override
            public void failure(RetrofitError error) {
                getView().showMes(error.getMessage());
            }
        });
    }
}
