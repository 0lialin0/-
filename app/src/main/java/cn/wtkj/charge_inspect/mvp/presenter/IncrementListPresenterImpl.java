package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.dataBase.EscapeBookDb;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordListView;
import cn.wtkj.charge_inspect.mvp.views.IncrementListView;
import cn.wtkj.charge_inspect.util.Setting;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;


/**
 * Created by lxg on 2015/11/5.
 */
public class IncrementListPresenterImpl extends MvpBasePresenter<IncrementListView> implements
        IncrementListPresenter {


    private Context context;
    private Intent intent;
    private EscapeBookDb escapeBookDb;

    @Override
    public void attachContextIntent(Context context) {
        this.context = context;
        escapeBookDb=new EscapeBookDb(context);
    }

    @Override
    public void startPresenter() {
        List<JCEscapeBookData> list=escapeBookDb.getEscapeBook(Setting.USERID);
        if(list.size()>0){
            getView().setList(list);
            getView().hideLoging();
        }else{
            getView().hideLoging();
            getView().showMes("暂无数据");
        }

    }



}
