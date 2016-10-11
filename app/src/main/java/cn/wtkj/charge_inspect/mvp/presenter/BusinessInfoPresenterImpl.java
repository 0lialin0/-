package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;

import java.util.List;

import cn.wtkj.charge_inspect.data.rest.BusinessInfoDataImpl;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.BusinessInfoView;


/**
 * Created by lxg on 2015/11/5.
 */
public class BusinessInfoPresenterImpl extends MvpBasePresenter<BusinessInfoView> implements
        BusinessInfoPresenter {

    private Context context;
    List<String> businessInfoDataList;
    private BusinessInfoDataImpl businessInfoData;
    public BusinessInfoPresenterImpl(Context context) {
        this.context = context;
        businessInfoData=new BusinessInfoDataImpl();
    }

    @Override
    public void startPresenter() {

    }
}
