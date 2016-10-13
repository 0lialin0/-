package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.data.dataBase.ConstAllDb;
import cn.wtkj.charge_inspect.data.dataBase.EscapeBookDb;
import cn.wtkj.charge_inspect.data.dataBase.OrganizationDb;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.IncrementAddView;
import cn.wtkj.charge_inspect.mvp.views.IncrementListView;
import cn.wtkj.charge_inspect.util.Setting;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;


/**
 * Created by lxg on 2015/11/5.
 */
public class IncrementAddPresenterImpl extends MvpBasePresenter<IncrementAddView> implements
        IncrementAddPresenter {


    private Context context;
    private Intent intent;
    private OrganizationDb organizationDb;
    private ConstAllDb constAllDb;
    private EscapeBookDb escapeBookDb;
    private boolean isNumber = true;//控制上传频率

    @Override
    public void attachContextIntent(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        organizationDb = new OrganizationDb(context);
        constAllDb = new ConstAllDb(context);
        escapeBookDb=new EscapeBookDb(context);
    }

    @Override
    public List<KeyValueData> getOrgShortName(int orgId,String orgLevel) {
        List<KeyValueData> list = organizationDb.getCheckUnit(orgId,orgLevel);
        return list;
    }

    @Override
    public List<ConstAllData.MData.info> getConstByType(int type) {
        List<ConstAllData.MData.info> list_type=constAllDb.getConstList(type);
        return list_type;
    }

    @Override
    public void submitData(JCEscapeBookData data) {
        if (isNumber) {
            getView().showLoding();
            escapeBookDb.updateEscapBook(data);
            getView().hideLoging();
            getView().nextView();
        }
        isNumber = !isNumber;//控制上传时间间隔
    }


    @Override
    public void startPresenter() {
        List<KeyValueData> list=new ArrayList<>();
        list.add(new KeyValueData("1","早班"));
        list.add(new KeyValueData("2","中班"));
        list.add(new KeyValueData("3","晚班"));

        List<KeyValueData> zhoushuo=new ArrayList<>();
        zhoushuo.add(new KeyValueData("2","2轴"));
        zhoushuo.add(new KeyValueData("3","3轴"));
        zhoushuo.add(new KeyValueData("4","4轴"));
        zhoushuo.add(new KeyValueData("5","5轴"));
        zhoushuo.add(new KeyValueData("6","6轴"));
        zhoushuo.add(new KeyValueData("7","7轴"));
        getView().setDropDown(list,zhoushuo);
    }






}
