package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordListView;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;


/**
 * Created by lxg on 2015/11/5.
 */
public class GreenRecordListPresenterImpl extends MvpBasePresenter<GreenRecordListView> implements
        GreenRecordListPresenter {


    private Context context;

    @Override
    public void attachContextIntent(Context context) {
        this.context = context;
    }

    @Override
    public void startPresenter() {
        getView().hideLoging();
    }



}
