package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;
import cn.wtkj.charge_inspect.mvp.views.MainView;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;


/**
 * Created by lxg on 2015/11/5.
 */
public class GreenRecordPresenterImpl extends MvpBasePresenter<GreenRecordView> implements
        GreenRecordPresenter, OnItemClickListener {



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


    @Override
    public void onItemClick(String tags) {

    }
}
