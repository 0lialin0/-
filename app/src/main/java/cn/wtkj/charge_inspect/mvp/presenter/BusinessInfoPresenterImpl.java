package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.BusinessInfoView;


/**
 * Created by lxg on 2015/11/5.
 */
public class BusinessInfoPresenterImpl extends MvpBasePresenter<BusinessInfoView> implements
        BusinessInfoPresenter {



    private Context context;
    private Intent intent;

    @Override
    public void attachContextIntent(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void startPresenter() {

    }



}
