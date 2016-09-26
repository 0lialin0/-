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
    private Intent intent;

    @Override
    public void attachContextIntent(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void startPresenter() {
        getView().setList();
    }



}
