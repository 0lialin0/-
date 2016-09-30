package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.dataBase.ConstAllDb;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;
import cn.wtkj.charge_inspect.mvp.views.MainView;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;


/**
 * Created by lxg on 2015/11/5.
 */
public class GreenRecordPresenterImpl extends MvpBasePresenter<GreenRecordView> implements
        GreenRecordPresenter {



    private Context context;
    private Intent intent;
    private ConstAllDb constAllDb;

    @Override
    public void attachContextIntent(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        constAllDb=new ConstAllDb(context);
    }

    @Override
    public void startPresenter() {

    }


    @Override
    public List<ConstAllData.MData.info> getConstByType(int type) {
        List<ConstAllData.MData.info> list_type=constAllDb.getConstList(type);
        return list_type;
    }


}
